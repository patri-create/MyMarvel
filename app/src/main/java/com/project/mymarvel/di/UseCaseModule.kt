package com.project.mymarvel.di

import com.project.mymarvel.data.MarvelRepository
import com.project.mymarvel.usecases.FindComicsUseCase
import com.project.mymarvel.usecases.FindEventsByComicIdUseCase
import com.project.mymarvel.usecases.FindEventsByHeroIdUseCase
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
    fun providesFindEventsByHeroIdUseCase(marvelRepository: MarvelRepository): FindEventsByHeroIdUseCase {
        return FindEventsByHeroIdUseCase(marvelRepository)
    }


    @Provides
    @ViewModelScoped
    fun providesFindComicsUseCase(marvelRepository: MarvelRepository): FindComicsUseCase {
        return FindComicsUseCase(marvelRepository)
    }

    @Provides
    @ViewModelScoped
    fun providesFindEventsByComicIdUseCase(marvelRepository: MarvelRepository): FindEventsByComicIdUseCase {
        return FindEventsByComicIdUseCase(marvelRepository)
    }
}