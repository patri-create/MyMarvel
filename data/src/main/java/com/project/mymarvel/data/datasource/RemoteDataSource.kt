package com.project.mymarvel.data.datasource

import arrow.core.Either
import com.project.mymarvel.domain.Error
import com.project.mymarvel.domain.Event
import com.project.mymarvel.domain.Hero

interface RemoteDataSource {
    suspend fun findHeroes(): Either<Error, List<Hero>>
    suspend fun findEventsByHeroId(heroId: Int): Either<Error, List<Event>>
}