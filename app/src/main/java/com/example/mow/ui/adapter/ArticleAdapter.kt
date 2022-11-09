package com.example.mow.ui.adapter

import android.annotation.SuppressLint
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
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
    @SuppressLint("SetTextI18n")
    override fun convert(holder: BaseViewHolder, item: ArticleBean) {
        Glide.with(context).load(item.envelopePic).into(holder.getView<ImageView>(R.id.iv_home_article_image))
        holder.getView<TextView>(R.id.tv_home_author_name).text = item.author
        holder.getView<TextView>(R.id.tv_home_article_type)
        holder.getView<TextView>(R.id.tv_home_article_time).text = item.niceDate
        holder.getView<TextView>(R.id.tv_home_article_title).text = Html.fromHtml(item.title)
        holder.getView<TextView>(R.id.tv_home_article_part)
        holder.getView<TextView>(R.id.tv_home_article_source).text = item.superChapterName+"."+item.chapterName

    }
}