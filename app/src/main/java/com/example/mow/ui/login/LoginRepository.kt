package com.example.mow.ui.login

import com.example.mow.base.BaseRepository

/**
 * @description login repository
 * @author: zhourui
 * @date: 2022/10/26
 */
class LoginRepository : BaseRepository() {
    suspend fun login(username: String?, password: String?) = apiService().login(username, password)
}