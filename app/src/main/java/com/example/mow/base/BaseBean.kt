package com.example.mow.base

import com.example.mow.data.http.ApiException

/**
 * @description 基础bean类
 * @author: zhourui
 * @date: 2022/10/26
 */
class BaseBean<T>(private val errorCode: Int, private val data: T, private val errorMsg: String?) {
    fun code(): Int {
        if (errorCode == 0) {
            return 0
        } else {
            throw ApiException(errorCode, errorMsg ?: "")
        }
    }

    fun data(): T {
        if (errorCode == 0) {
            return data
        } else {
            throw ApiException(errorCode, errorMsg ?: "")
        }
    }
}