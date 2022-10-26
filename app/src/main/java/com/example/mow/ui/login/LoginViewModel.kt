package com.example.mow.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mow.base.BaseViewModel
import com.example.mow.data.http.ApiException

/**
 * @description login viewmodel
 * @author: zhourui
 * @date: 2022/10/26
 */
class LoginViewModel : BaseViewModel() {
    private val loginRepository by lazy {
        LoginRepository()
    }

    //内部
    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean>
        get() = _loginState

    fun login(username: String?, password: String?) {
        val job = launch(block = {
            val loginData = loginRepository.login(username, password)
            _loginState.value = 0 == loginData.code()
        }, error = {
            if (it is ApiException) {
                if (-1001 == it.code) {
                    _loginState.value = false
                }
            }
        },
            cancel = null, false
        )
    }
}