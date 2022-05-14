package io.rezyfr.data.repositories.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.rezyfr.data.remote.service.MuviService
import io.rezyfr.data.repositories.MuviRepository
import io.rezyfr.data.repositories.MuviRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideRepositoryModule(
        muviService: MuviService
    ): MuviRepository {
        return MuviRepositoryImpl(muviService)
    }
}