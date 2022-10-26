package com.example.mow.data.http

import com.example.mow.base.BaseBean
import com.example.mow.data.bean.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @description 网络请求接口
 * @author: zhourui
 * @date: 2022/10/25
 */
interface Api {
    companion object {
        const val BASE_URL = "https://www.wanandroid.com/"
    }

    //登录
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): BaseBean<User>

    //注册
    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("repassword") repassword: String?
    ): BaseBean<User>
}