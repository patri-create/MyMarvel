package com.project.mymarvel.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.mymarvel.domain.Hero
import com.project.mymarvel.usecases.FindHeroesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.project.mymarvel.domain.Error
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val findHeroesUseCase: FindHeroesUseCase):
    ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            findHeroesUseCase().fold(::onError,::onSuccess)
        }
    }

    private fun onError(error: Error) {
        _state.value = _state.value.copy(error = error)
    }

    private fun onSuccess(heroes: List<Hero>) {
        _state.value = _state.value.copy(heroes = heroes)
    }

    data class UiState(
        val heroes: List<Hero>? = null,
        val error: Error? = null
    )
}