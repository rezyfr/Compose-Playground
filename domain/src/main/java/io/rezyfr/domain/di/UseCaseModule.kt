package io.rezyfr.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.rezyfr.data.repositories.MuviRepository
import io.rezyfr.domain.usecase.GetDiscoverMovie

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @ViewModelScoped
    @Provides
    fun provideDiscoverMovies(repository: MuviRepository): GetDiscoverMovie {
        return GetDiscoverMovie(repository)
    }
}