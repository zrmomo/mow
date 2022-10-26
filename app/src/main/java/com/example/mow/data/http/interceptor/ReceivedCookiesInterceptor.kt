package com.example.mow.data.http.interceptor

import com.example.mow.common.AppConfig
import com.example.mow.utils.SpUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Describe : 从响应头里拿到cookie并存起来，后面的每次请求再添加到请求头里
 */
class ReceivedCookiesInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            val cookies: HashSet<String> = HashSet()
            for (header in originalResponse.headers("Set-Cookie")) {
                cookies.add(header)
            }
            SpUtil.setStringSet(AppConfig.COOKIE, cookies)
        }
        return originalResponse
    }
}