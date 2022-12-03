package com.project.mymarvel.di

import com.project.mymarvel.data.MarvelRepository
import com.project.mymarvel.data.datasource.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesMarvelRepository(remoteDataSource: RemoteDataSource): MarvelRepository {
        return MarvelRepository(remoteDataSource)
    }
}