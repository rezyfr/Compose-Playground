package io.rezyfr.muviplayground.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.rezyfr.data.remote.NetworkConfig
import io.rezyfr.muviplayground.provider.AppNetworkProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNetworkConfig(): NetworkConfig {
        return AppNetworkProvider()
    }
}