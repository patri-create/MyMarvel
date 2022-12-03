package com.project.mymarvel.data.server

import arrow.core.Either
import com.project.mymarvel.common.utils.tryCall
import com.project.mymarvel.data.datasource.RemoteDataSource
import com.project.mymarvel.data.server.responses.Image
import com.project.mymarvel.data.server.responses.characters.Character
import com.project.mymarvel.data.server.responses.comics.Comic
import com.project.mymarvel.data.server.responses.events.Event
import com.project.mymarvel.domain.Comic as DomainComic
import com.project.mymarvel.domain.Error
import com.project.mymarvel.domain.Event as DomainEvent
import com.project.mymarvel.domain.Hero
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val api: ApiService): RemoteDataSource {

    override suspend fun findHeroes(): Either<Error, List<Hero>> = tryCall {
        api.getCharacters().data.results.map { it.toDomain() }
    }

    override suspend fun findEventsByHeroId(heroId: Int): Either<Error, List<DomainEvent>> = tryCall {
        api.getEventsByCharacterId(heroId).data.results.map { it.toDomain() }
    }

    override suspend fun findComics(): Either<Error, List<DomainComic>> = tryCall {
        api.getComics().data.results.map { it.toDomain() }
    }

    override suspend fun findEventsByComicId(comicId: Int): Either<Error, List<DomainEvent>> = tryCall {
        api.getEventsByComicId(comicId).data.results.map { it.toDomain() }
    }
}

private fun Character.toDomain(): Hero =
    Hero(
        id,
        name,
        thumbnail.toDomain()
    )

private fun Image.toDomain(): String = "$path.$extension"

private fun Event.toDomain(): DomainEvent =
    DomainEvent(
        id,
        thumbnail.toDomain(),
        title,
        description
    )

private fun Comic.toDomain(): DomainComic =
    DomainComic(
        id,
        title,
        thumbnail.toDomain()
    )