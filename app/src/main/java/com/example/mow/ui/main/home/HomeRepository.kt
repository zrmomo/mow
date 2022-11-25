package com.example.mow.ui.main.home

import com.example.mow.base.BaseRepository

/**
 * @description 主页 http repository
 * @author: zhourui
 * @date: 2022/10/27
 */
class HomeRepository : BaseRepository() {

    suspend fun homeArticleList(page: Int?) = apiService().homeArticleList(page)
    suspend fun homeTopArticleList() = apiService().homeTopArticleList()
    suspend fun homeBanner(page: Int?) = apiService().homeBanner(page)

}