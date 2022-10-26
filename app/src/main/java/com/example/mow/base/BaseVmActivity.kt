package com.example.mow.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseVmActivity<VB : ViewBinding, VM : BaseViewModel>(inflate: (LayoutInflater) -> VB) :
    BaseActivity<VB>(inflate) {
    protected open lateinit var mViewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        observe()
        initView()
        initData()
        setListener()
    }

    /**
     * 初始化ViewModel
     */
    private fun initViewModel() {
        mViewModel = ViewModelProvider(this)[viewModelClass()]
        // java：new ViewModelProvider(this).get(HomeViewModel.class);
    }

    /**
     * 订阅，有逻辑的话，复写的时候super不要去掉
     */
    open fun observe() {}

    /**
     * 获取ViewModel的class
     */
    protected abstract fun viewModelClass(): Class<VM>

    /**
     * 初始化view相关
     */
    open fun initView() {}

    /**
     * 初始化data相关
     */
    open fun initData() {}

    /**
     * 设置监听
     */
    open fun setListener() {}

}