package com.project.mymarvel.usecases

import arrow.core.Either
import com.project.mymarvel.data.MarvelRepository
import com.project.mymarvel.domain.Error
import com.project.mymarvel.domain.Event
import javax.inject.Inject

class FindEventsUseCase @Inject constructor(private val marvelRepository: MarvelRepository) {

    suspend operator fun invoke(heroId: Int): Either<Error, List<Event>> {
        return marvelRepository.getEvents(heroId)
    }
}