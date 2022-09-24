package com.gunawan.eratani.repository.remote

import android.content.Context
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
            .newBuilder()
            .header("Authorization", "Bearer e2ef084a643d62225c8f72a443cf6a264a91fe4a43a65457bd08d6a1a7fc06dc")
            .build()
        return chain.proceed(originalRequest)
    }

}