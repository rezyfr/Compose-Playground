package io.rezyfr.data.remote.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.rezyfr.data.remote.NetworkConfig
import io.rezyfr.data.remote.service.MuviService
import io.rezyfr.data.remote.utils.HttpInterceptor
import io.rezyfr.data.remote.utils.createRetrofit
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL = "base_url"
private const val CONTENT_LENGTH = 250_000L
private const val CLIENT_CACHE_SIZE = 10 * 1024 * 1024L
private const val CLIENT_CACHE_DIRECTORY = "http"
private const val CLIENT_TIME_OUT = 60L

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    @Named(value = BASE_URL)
    fun provideBaseUrl(networkConfig: NetworkConfig): String {
        return networkConfig.baseUrl()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(networkConfig: NetworkConfig): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (networkConfig.isDev()) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideHttpRequestInterceptor(): HttpInterceptor {
        return HttpInterceptor()
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(
            context = context,
            // Toggles visibility of the push notification
            showNotification = true,
            // Allows to customize the retention period of collected data
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        return ChuckerInterceptor.Builder(context)
            // The previously created Collector
            .collector(chuckerCollector)
            // The max body content length in bytes, after this responses will be truncated.
            .maxContentLength(CONTENT_LENGTH)
            // List of headers to replace with ** in the Chucker UI
            .redactHeaders("Auth-Token", "Bearer")
            // Read the whole response body even when the client does not consume the response completely.
            // This is useful in case of parsing errors or when the response body
            // is closed before being read like in Retrofit with Void and Unit types.
            .alwaysReadResponseBody(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        httpRequestInterceptor: HttpInterceptor
    ): OkHttpClient {
        val cache = Cache(
            directory = File(context.cacheDir, CLIENT_CACHE_DIRECTORY),
            maxSize = CLIENT_CACHE_SIZE
        )
        return OkHttpClient.Builder().apply {
            cache(createCache(context))
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(chuckerInterceptor)
            addInterceptor(httpRequestInterceptor)
            connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            followSslRedirects(true)
            followRedirects(true)
            retryOnConnectionFailure(true)
        }.build()
    }

    private fun createCache(context: Context): Cache = Cache(
        directory = File(context.cacheDir, CLIENT_CACHE_DIRECTORY),
        maxSize = CLIENT_CACHE_SIZE
    )

    @Provides
    @Singleton
    fun provideMuviService(
        @Named(value = BASE_URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): MuviService {
        return createRetrofit(
            okHttpClient = okHttpClient,
            moshi = moshi,
            baseUrl = baseUrl
        )
    }
}