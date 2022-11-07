package com.example.mow.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mow.R
import com.example.mow.base.BaseVmFragment
import com.example.mow.databinding.FragmentHomeBinding

class HomeFragment :
    BaseVmFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initView() {

    }


}