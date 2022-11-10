package com.example.mow.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mow.R
import com.example.mow.data.bean.ArticleBean

/**
 * @description 正文adapter
 * @author: zhourui
 * @date: 2022/11/8
 */
class ArticleAdapter2(val data: List<ArticleBean>?) : RecyclerView.Adapter<ArticleAdapter2.BaseViewHolder>() {

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text1: TextView = itemView.findViewById(R.id.tv_home_article_part)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.home_list_item, null)
        )
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.text1.text = "e21321312"
    }
}