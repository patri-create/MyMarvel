package com.project.mymarvel.di

import com.project.mymarvel.data.MarvelRepository
import com.project.mymarvel.usecases.FindEventsUseCase
import com.project.mymarvel.usecases.FindHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun providesFindHeroesUseCase(marvelRepository: MarvelRepository): FindHeroesUseCase {
        return FindHeroesUseCase(marvelRepository)
    }

    @Provides
    @ViewModelScoped
    fun providesFindEventsUseCase(marvelRepository: MarvelRepository): FindEventsUseCase {
        return FindEventsUseCase(marvelRepository)
    }
}