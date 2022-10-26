package com.example.mow.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.mow.R
import com.gyf.immersionbar.ImmersionBar

abstract class BaseActivity<VB : ViewBinding>(private val inflate: (LayoutInflater) -> VB) :
    AppCompatActivity() {

    protected open lateinit var mBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = inflate(layoutInflater)
        setContentView(mBinding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED//竖屏

        ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.purple_200)
            .navigationBarColor(R.color.transparent)
            .init()

        initialize()
    }

    open fun initialize() {}

}