package com.project.mymarvel.fakes

import com.project.mymarvel.data.server.ApiService
import com.project.mymarvel.fakes.helpers.buildRemoteCharacterDataWrapper
import com.project.mymarvel.fakes.helpers.buildRemoteComicDataWrapper
import com.project.mymarvel.fakes.helpers.buildRemoteEventDataWrapper

class FakeApiService : ApiService {
    override suspend fun getCharacters() = buildRemoteCharacterDataWrapper()
    override suspend fun getEventsByCharacterId(heroId: Int) = buildRemoteEventDataWrapper()
    override suspend fun getComics() = buildRemoteComicDataWrapper()
    override suspend fun getEventsByComicId(comicId: Int) = buildRemoteEventDataWrapper()
}