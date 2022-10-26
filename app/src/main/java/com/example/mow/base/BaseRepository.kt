package com.example.mow.base

import com.example.mow.data.http.Api
import com.example.mow.data.http.RetrofitClient

/**
 * @description
 * @author: zhourui
 * @date: 2022/10/25
 */
open class BaseRepository {
    protected fun apiService(): Api {
        return RetrofitClient.getApiService()
    }
}