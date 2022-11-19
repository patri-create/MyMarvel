package com.project.mymarvel.data.datasource

interface RemoteDataSource {
    suspend fun findHeros()
}