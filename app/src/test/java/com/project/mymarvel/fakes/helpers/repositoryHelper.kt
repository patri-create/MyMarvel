package com.project.mymarvel.fakes.helpers

import com.project.mymarvel.data.MarvelRepository
import com.project.mymarvel.data.server.RemoteDataSourceImp
import com.project.mymarvel.fakes.FakeApiService

fun buildMarvelRepository(): MarvelRepository {
    val remoteDataSource = RemoteDataSourceImp(FakeApiService())
    return MarvelRepository(remoteDataSource)
}