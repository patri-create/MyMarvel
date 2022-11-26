package com.project.mymarvel.data.server

import com.project.mymarvel.data.responses.CharacterDataWrapper
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(): Response<CharacterDataWrapper>
}