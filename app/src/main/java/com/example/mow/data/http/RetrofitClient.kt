package com.example.mow.data.http

import android.util.Log
import com.example.mow.data.http.interceptor.AddCookiesInterceptor
import com.example.mow.data.http.interceptor.ReceivedCookiesInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @description Retrofit 客户端创建
 * @author: zhourui
 * @date: 2022/10/25
 */
object RetrofitClient {
    private const val CALL_TIMEOUT = 10L
    private const val CONNECT_TIMEOUT = 20L
    private const val IO_TIMEOUT = 20L

    private var apiService: Api

    fun getApiService(): Api {
        return apiService
    }

    init {
        val loggingInterceptor = HttpLoggingInterceptor { Log.d("httpLog", it) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        /**OkHttpClient*/
        val okHttpClient = OkHttpClient.Builder().callTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS).writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(AddCookiesInterceptor()).addInterceptor(ReceivedCookiesInterceptor())
            .addInterceptor(loggingInterceptor).retryOnConnectionFailure(true).build()

        /**Retrofit*/
        val retrofit = Retrofit.Builder().client(okHttpClient).baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        /**ApiService*/
        apiService = retrofit.create(Api::class.java)
    }
}