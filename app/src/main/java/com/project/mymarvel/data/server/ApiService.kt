package com.project.mymarvel.data.server


import com.project.mymarvel.data.server.responses.characters.CharacterDataWrapper
import com.project.mymarvel.data.server.responses.events.EventDataWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(): CharacterDataWrapper

    @GET("/v1/public/characters/{characterId}/events")
    suspend fun getEventsByCharacterId(@Path("characterId") heroId: Int): EventDataWrapper
}