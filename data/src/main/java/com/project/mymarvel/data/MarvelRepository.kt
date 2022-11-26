package com.project.mymarvel.data

import arrow.core.Either
import com.project.mymarvel.data.datasource.RemoteDataSource
import com.project.mymarvel.domain.Error
import com.project.mymarvel.domain.Hero
import javax.inject.Inject

class MarvelRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun getHeroes(): Either<Error, List<Hero>> {
        return remoteDataSource.findHeroes()
    }
}