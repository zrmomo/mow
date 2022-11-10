package com.example.mow.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mow.base.BaseViewModel
import com.example.mow.data.bean.ArticleBean
import com.example.mow.data.http.ApiException
import com.example.mow.utils.ToastUtil

/**
 * @description 主页ViewModel
 * @author: zhourui
 * @date: 2022/10/27
 */
class HomeViewModel : BaseViewModel() {

    private val homeRepository by lazy {
        HomeRepository()
    }

    private val _articleList = MutableLiveData<MutableList<ArticleBean>>()
    val articleList: LiveData<MutableList<ArticleBean>> = _articleList

    fun homeArticleList(page: Int?){
        launch(
            block = {
               val articleBeanData = homeRepository.homeArticleList(page)
                _articleList.value = articleBeanData.data().datas
            },error = {
                if (it is ApiException) {
                    if (-1001 == it.code) {
                        ToastUtil.show("未登录")
                    }
                }
            }
        ,cancel = null,true
        )
    }
}