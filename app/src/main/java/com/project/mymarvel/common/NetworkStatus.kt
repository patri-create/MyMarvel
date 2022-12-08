package com.project.mymarvel.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

sealed class NetworkStatusState {
    object Available : NetworkStatusState()
    object Unavailable : NetworkStatusState()
}

@Singleton
class NetworkStatus @Inject constructor(context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val status = callbackFlow<NetworkStatusState> {
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() {
                trySend(NetworkStatusState.Unavailable)
            }

            override fun onAvailable(network: Network) {
                trySend(NetworkStatusState.Available)
            }

            override fun onLost(network: Network) {
                trySend(NetworkStatusState.Unavailable)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, networkStatusCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStatusCallback)
        }
    } .distinctUntilChanged()
}

@FlowPreview
inline fun <T> Flow<NetworkStatusState>.networkMap(
    crossinline onUnavailable: suspend () -> T,
    crossinline onAvailable: suspend () -> T,
): Flow<T> = map { status ->
    when (status) {
        NetworkStatusState.Unavailable -> onUnavailable()
        NetworkStatusState.Available -> onAvailable()
        else -> onUnavailable()
    }
}