package com.project.mymarvel.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.mymarvel.common.NetworkStatus
import com.project.mymarvel.common.utils.networkMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val networkStatus: NetworkStatus): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun checkNetworkConnection() {
        viewModelScope.launch {
            networkStatus.status
                .networkMap(
                    onAvailable = { true },
                    onUnavailable = { false },
                ).collect { fetched -> _state.update { UiState(isConnected = fetched) } }
        }
    }

    data class UiState(
        val isConnected: Boolean = true
    )
}