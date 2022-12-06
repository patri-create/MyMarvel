package com.project.mymarvel.data.interceptor

import android.content.Context
import com.project.mymarvel.BuildConfig
import com.project.mymarvel.common.utils.md5
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        builder.url(httpURL(request))

        return chain.proceed(builder.build())
    }

    private fun httpURL(request: Request): HttpUrl {
        val ts = System.currentTimeMillis().toString()
        val apikey = BuildConfig.PBKEY
        val hash = "$ts${BuildConfig.PVKEY}$apikey"

        return request.url.newBuilder()
            .addQueryParameter(TS, ts)
            .addQueryParameter(API_KEY, apikey)
            .addQueryParameter(HASH, hash.md5()).build()
    }

    companion object {
        private const val TS = "ts"
        private const val API_KEY = "apikey"
        private const val HASH = "hash"
    }

}