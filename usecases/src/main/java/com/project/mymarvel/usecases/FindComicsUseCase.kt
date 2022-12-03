package com.project.mymarvel.usecases

import arrow.core.Either
import com.project.mymarvel.data.MarvelRepository
import com.project.mymarvel.domain.Comic
import com.project.mymarvel.domain.Error
import javax.inject.Inject

class FindComicsUseCase @Inject constructor(private val marvelRepository: MarvelRepository) {

    suspend operator fun invoke(): Either<Error, List<Comic>> {
        return marvelRepository.getComics()
    }
}