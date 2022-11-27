package com.project.mymarvel.di

import com.project.mymarvel.data.MarvelRepository
import com.project.mymarvel.usecases.FindHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providesFindHeroesUseCase(marvelRepository: MarvelRepository): FindHeroesUseCase {
        return FindHeroesUseCase(marvelRepository)
    }
}