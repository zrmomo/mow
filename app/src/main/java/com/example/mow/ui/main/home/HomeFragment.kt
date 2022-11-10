package com.example.mow.ui.main.home

import com.example.mow.base.BaseVmFragment
import com.example.mow.databinding.FragmentHomeBinding
import com.example.mow.ui.adapter.ArticleAdapter

class HomeFragment :
    BaseVmFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    private lateinit var mArticleAdapter: ArticleAdapter
    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initView() {
        mArticleAdapter = ArticleAdapter().apply {
            //开启加载动画
            animationEnable = true
            loadMoreModule.setOnLoadMoreListener {
                mBinding.rvHomeList.postDelayed({
                    //当前总共加载的数量 < mTotalCount 时可以加载更多 否则就不再加载更多
                    // 分页加载策略
                    if (mCurrentSize > mTotalCount) {
                        mArticleAdapter.loadMoreModule.loadMoreEnd(true)
                    } else {
                        // 请求页码加1 开始下一页请求
                        mCurrentPage++
                        mViewModel.homeArticleList(mCurrentPage)
                    }
                }, 500)
            }
        }
        mBinding.rvHomeList.adapter = mArticleAdapter
    }

    override fun initData() {
        super.initData()
        mViewModel.homeArticleList(mCurrentPage)
    }

    override fun observe() {
        super.observe()
        mViewModel.articleList.observe(this) {
            mCurrentSize += it.size
            //如果当前加载的是第一页
            if (0 == mCurrentPage) {
                mArticleAdapter.setList(it)
            } else {
                mArticleAdapter.addData(it)
                mArticleAdapter.loadMoreModule.loadMoreComplete()
            }
        }
    }

}