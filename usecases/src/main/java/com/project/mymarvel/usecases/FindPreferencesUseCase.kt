package com.project.mymarvel.usecases

import com.project.mymarvel.data.PreferencesRepository
import javax.inject.Inject

class FindPreferencesUseCase @Inject constructor(private val preferencesRepository: PreferencesRepository){

    operator fun invoke(key: String): String {
        return preferencesRepository.find(key)
    }
}