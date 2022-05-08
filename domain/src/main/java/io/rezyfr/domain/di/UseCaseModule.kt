package io.rezyfr.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.rezyfr.data.repositories.MuviRepository
import io.rezyfr.domain.usecase.GetComingSoonMovies
import io.rezyfr.domain.usecase.GetPopularMovies

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @ViewModelScoped
    @Provides
    fun providePopularMovies(repository: MuviRepository): GetPopularMovies {
        return GetPopularMovies(repository)
    }
    @ViewModelScoped
    @Provides
    fun provideComingSoonMovies(repository: MuviRepository): GetComingSoonMovies {
        return GetComingSoonMovies(repository)
    }
}