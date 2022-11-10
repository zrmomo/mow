package com.example.mow.utils

/**
 * @description kotlin中的一些扩展方法
 * @author: zhourui
 * @date: 2022/11/10
 */
object KotlinXUtil {

    fun CharSequence?.isEmpty(): Boolean{
        return this == null || this.length == 0
    }
}