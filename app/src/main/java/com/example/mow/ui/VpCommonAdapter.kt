package com.example.mow.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

typealias HandleFragment = () -> Fragment

/**
 * @description ViewPager的公共adapter
 * @author: zhourui
 * @date: 2022/10/27
 */
class VpCommonAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val mFragmentList = mutableListOf<HandleFragment>()
    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position].invoke()
    }

    fun add(fragment: HandleFragment): VpCommonAdapter {
        mFragmentList.add(fragment)
        return this
    }

    fun add(fragmentList: List<HandleFragment>): VpCommonAdapter {
        mFragmentList.addAll(fragmentList)
        return this
    }
}