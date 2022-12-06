package com.project.mymarvel.ui.helpers

import com.project.mymarvel.data.MarvelRepository
import com.project.mymarvel.data.server.RemoteDataSourceImp
import com.project.mymarvel.ui.FakeApiService

fun buildMarvelRepository(): MarvelRepository {
    val remoteDataSource = RemoteDataSourceImp(FakeApiService())
    return MarvelRepository(remoteDataSource)
}