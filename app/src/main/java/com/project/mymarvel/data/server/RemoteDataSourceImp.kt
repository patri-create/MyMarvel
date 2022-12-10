package com.project.mymarvel.data.server

import arrow.core.Either
import com.project.mymarvel.common.utils.tryCall
import com.project.mymarvel.data.datasource.RemoteDataSource
import com.project.mymarvel.domain.Error
import com.project.mymarvel.domain.Hero
import com.project.mymarvel.domain.Comic
import com.project.mymarvel.domain.Event
import javax.inject.Inject


class RemoteDataSourceImp @Inject constructor(private val api: ApiService) : RemoteDataSource {

    override suspend fun findHeroes(): Either<Error, List<Hero>> = tryCall {
        api.getCharacters().data.results.map { it.toDomain() }
    }

    override suspend fun findEventsByHeroId(heroId: Int): Either<Error, List<Event>> =
        tryCall {
            api.getEventsByCharacterId(heroId).data.results.map { it.toDomain() }
        }

    override suspend fun findComics(): Either<Error, List<Comic>> = tryCall {
        api.getComics().data.results.map { it.toDomain() }
    }

    override suspend fun findEventsByComicId(comicId: Int): Either<Error, List<Event>> =
        tryCall {
            api.getEventsByComicId(comicId).data.results.map { it.toDomain() }
        }
}

