package com.project.mymarvel.ui

import com.project.mymarvel.data.server.ApiService
import com.project.mymarvel.ui.helpers.buildRemoteCharacterDataWrapper
import com.project.mymarvel.ui.helpers.buildRemoteComicDataWrapper
import com.project.mymarvel.ui.helpers.buildRemoteEventDataWrapper

class FakeApiService : ApiService {
    override suspend fun getCharacters() = buildRemoteCharacterDataWrapper()
    override suspend fun getEventsByCharacterId(heroId: Int) = buildRemoteEventDataWrapper()
    override suspend fun getComics() = buildRemoteComicDataWrapper()
    override suspend fun getEventsByComicId(comicId: Int) = buildRemoteEventDataWrapper()
}