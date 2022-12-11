package com.project.mymarvel.ui.fragments.comics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.mymarvel.domain.Error
import com.project.mymarvel.domain.EventItem
import com.project.mymarvel.domain.MarvelItem
import com.project.mymarvel.usecases.FindComicsUseCase
import com.project.mymarvel.usecases.FindEventsByComicIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val findComicsUseCase: FindComicsUseCase,
    private val findEventsUseCase: FindEventsByComicIdUseCase
) : ViewModel() {

    var items: List<MarvelItem>? = null

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        loadMarvelItems()
    }

    fun loadMarvelItems() {
        viewModelScope.launch {
            loading(true)
            findComicsUseCase().fold(::onError, ::onSuccess)
            loading(false)
        }
    }

    private fun onError(error: Error) {
        _state.value = _state.value.copy(error = error)
    }

    private fun onSuccess(items: List<MarvelItem>) {
        this.items = items
        _state.value = _state.value.copy(items = items, error = null)
    }

    fun updateEvents(pos: Int) {
        items?.let { items ->
            val item = items[pos]
            viewModelScope.launch {
                loading(true, isMarvel = false)
                findEventsUseCase(item.id).fold(::onError, ::onEventSuccess)
                loading(false, isMarvel = false)
            }
        }
    }

    private fun onEventSuccess(items: List<EventItem>) {
        _state.value = _state.value.copy(items = null, events = items, error = null)
    }

    fun reload() {
        items?.let {
            _state.value = _state.value.copy(items = it)
        }
    }

    private fun loading(isLoading: Boolean, isMarvel: Boolean = true) {
        when (isMarvel) {
            true -> _state.value = _state.value.copy(loadingMarvel = isLoading)
            false -> _state.value = _state.value.copy(loadingEvent = isLoading)
        }
    }

    data class UiState(
        val loadingMarvel: Boolean = true,
        val loadingEvent: Boolean = true,
        val items: List<MarvelItem>? = null,
        val events: List<EventItem>? = null,
        val error: Error? = null,
    )
}