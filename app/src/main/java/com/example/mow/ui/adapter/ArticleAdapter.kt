package com.example.mow.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mow.R
import com.example.mow.data.bean.ArticleBean

/**
 * @description 正文adapter
 * @author: zhourui
 * @date: 2022/11/8
 */
class ArticleAdapter : BaseQuickAdapter<ArticleBean, BaseViewHolder>(R.layout.home_list_item) {
    override fun convert(holder: BaseViewHolder, item: ArticleBean) {

    }
}