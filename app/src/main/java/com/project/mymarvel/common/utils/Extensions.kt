package com.project.mymarvel.common.utils

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.project.mymarvel.App
import com.project.mymarvel.R
import com.project.mymarvel.common.LocaleManager
import com.project.mymarvel.common.NetworkStatus
import com.project.mymarvel.domain.Error
import com.project.mymarvel.domain.Language
import com.project.mymarvel.ui.adapters.OnSnapPositionChangeListener
import com.project.mymarvel.ui.adapters.SnapOnScrollListener
import com.project.mymarvel.ui.base.MainActivity
import com.project.mymarvel.ui.base.MainState
import com.project.mymarvel.ui.fragments.comics.ComicsState
import com.project.mymarvel.ui.fragments.home.HomeState
import com.project.mymarvel.ui.fragments.settings.SettingsState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.math.BigInteger
import java.security.MessageDigest

//region Network
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

suspend fun <T> tryCall(action: suspend () -> T): Either<Error, T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}

@FlowPreview
inline fun <T> Flow<NetworkStatus.NetworkStatusState>.networkMap(
    crossinline onUnavailable: suspend () -> T,
    crossinline onAvailable: suspend () -> T,
): Flow<T> = map { status ->
    when (status) {
        NetworkStatus.NetworkStatusState.Unavailable -> onUnavailable()
        NetworkStatus.NetworkStatusState.Available -> onAvailable()
    }
}
//endregion


//region StateFlow
fun <T> LifecycleOwner.launchAndCollect(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    body: (T) -> Unit
) {
    lifecycleScope.launch {
        this@launchAndCollect.repeatOnLifecycle(state) {
            flow.collect(body)
        }
    }
}
//endregion


//region StateHolder
fun MainActivity.buildMainState(
    activity: MainActivity = this,
    navController: NavController =
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
            .navController
) = MainState(activity, navController)

fun Fragment.buildHomeState(
    context: Context = requireContext(),
    navController: NavController = findNavController()
) = HomeState(context, navController)

fun Fragment.buildComicsState(
    context: Context = requireContext(),
    navController: NavController = findNavController()
) = ComicsState(context, navController)

fun Fragment.buildSettingsState(
    context: Context = requireContext(),
    activity: Activity = requireActivity()
) = SettingsState(context, activity)
//endregion


//region RecyclerView
fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
    val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
    val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
    return layoutManager.getPosition(snapView)
}

fun RecyclerView.attachSnapHelperWithListener(
    snapHelper: SnapHelper,
    behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,
    onSnapPositionChangeListener: OnSnapPositionChangeListener
) {
    snapHelper.attachToRecyclerView(this)
    val snapOnScrollListener =
        SnapOnScrollListener(snapHelper, behavior, onSnapPositionChangeListener)
    addOnScrollListener(snapOnScrollListener)
}
//endregion


//region Locale
fun String.toLanguage(): Language {
    val context = App.instance.applicationContext
    return when (this) {
        LocaleManager.ENGLISH -> Language(
            context.getString(R.string.language_english),
            "ic_english_flag",
            LocaleManager.ENGLISH
        )
        LocaleManager.SPANISH -> Language(
            context.getString(R.string.language_spanish),
            "ic_spanish_flag",
            LocaleManager.SPANISH
        )
        else -> {
            Language(
                context.getString(R.string.language_english),
                "ic_english_flag",
                LocaleManager.ENGLISH
            )
        }
    }
}
//endregion