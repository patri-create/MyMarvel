package com.project.mymarvel.data.server

import arrow.core.Either
import com.project.mymarvel.common.utils.tryCall
import com.project.mymarvel.data.datasource.RemoteDataSource
import com.project.mymarvel.data.server.responses.Image
import com.project.mymarvel.data.server.responses.Character
import com.project.mymarvel.domain.Error
import com.project.mymarvel.domain.Hero
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val api: ApiService): RemoteDataSource {

    override suspend fun findHeroes(): Either<Error, List<Hero>> = tryCall {
        api.getCharacters().data.results.toDomain()
    }
}

private fun List<Character>.toDomain(): List<Hero> = map { it.toDomain() }
private fun Character.toDomain(): Hero =
    Hero(
        name,
        thumbnail.toDomain()
    )

private fun Image.toDomain(): String = "$path.$extension"