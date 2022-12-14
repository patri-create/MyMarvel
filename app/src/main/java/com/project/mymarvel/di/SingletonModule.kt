package com.project.mymarvel.di

import android.app.Application
import android.content.Context
import com.project.mymarvel.common.LocationHelper
import com.project.mymarvel.common.NetworkStatus
import com.project.mymarvel.data.MarvelRepository
import com.project.mymarvel.data.PreferencesRepository
import com.project.mymarvel.data.datasource.PreferencesDataSource
import com.project.mymarvel.data.datasource.RemoteDataSource
import com.project.mymarvel.data.preferences.PreferencesStorage
import com.project.mymarvel.usecases.FindPreferencesUseCase
import com.project.mymarvel.usecases.SavePreferencesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    fun providesMarvelRepository(remoteDataSource: RemoteDataSource): MarvelRepository {
        return MarvelRepository(remoteDataSource)
    }

    @Provides
    fun providesPreferencesStorage(@ApplicationContext context: Context): PreferencesStorage {
        return PreferencesStorage(context)
    }

    @Provides
    fun providesPreferencesRepository(preferencesDataSource: PreferencesDataSource): PreferencesRepository {
        return PreferencesRepository(preferencesDataSource)
    }

    @Provides
    fun providesFindPreferencesUseCase(preferencesRepository: PreferencesRepository): FindPreferencesUseCase {
        return FindPreferencesUseCase(preferencesRepository)
    }

    @Provides
    fun providesSavePreferencesUseCase(preferencesRepository: PreferencesRepository): SavePreferencesUseCase {
        return SavePreferencesUseCase(preferencesRepository)
    }

    @Provides
    fun providesLocationHelper(app: Application): LocationHelper {
        return LocationHelper(app)
    }

    @Provides
    fun providesNetworkStatus(@ApplicationContext context: Context): NetworkStatus {
        return NetworkStatus(context)
    }
}