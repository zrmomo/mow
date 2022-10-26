package com.example.mow.data.http.interceptor

import com.example.mow.common.AppConfig
import com.example.mow.utils.SpUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * @description 请求拦截器 ：从响应头里拿到cookie并存起来，后面的每次请求再添加到请求头里
 * @author: zhourui
 * @date: 2022/10/25
 */
class AddCookiesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val stringSet = SpUtil.getStringSet(AppConfig.COOKIE)
        for (cookie in stringSet) {
            builder.addHeader("Cookie", cookie)
        }
        return chain.proceed(builder.build())
    }
}