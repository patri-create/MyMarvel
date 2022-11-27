package com.project.mymarvel.usecases

import arrow.core.Either
import com.project.mymarvel.data.MarvelRepository
import com.project.mymarvel.domain.Error
import com.project.mymarvel.domain.Hero
import javax.inject.Inject

class FindHeroesUseCase @Inject constructor(private val marvelRepository: MarvelRepository) {

    suspend operator fun invoke(): Either<Error, List<Hero>> {
        return marvelRepository.getHeroes()
    }
}