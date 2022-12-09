package com.project.mymarvel.usecases

import com.project.mymarvel.data.PreferencesRepository
import javax.inject.Inject

class SavePreferencesUseCase @Inject constructor(private val preferencesRepository: PreferencesRepository){

    operator fun invoke(key: String, value: String) {
        preferencesRepository.save(key, value)
    }
}