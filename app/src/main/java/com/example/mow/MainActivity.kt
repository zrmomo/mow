package com.example.mow

import androidx.viewpager2.widget.ViewPager2
import com.example.mow.base.BaseActivity
import com.example.mow.databinding.ActivityMainBinding
import com.example.mow.ui.VpCommonAdapter
import com.example.mow.ui.main.home.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun initialize() {
        super.initialize()
        initView()
    }

    private fun initView() {
        // viewpager加载数据
        mBinding.vpViewpager.offscreenPageLimit = 1
        mBinding.vpViewpager.adapter = VpCommonAdapter(supportFragmentManager, lifecycle).apply {
            add { HomeFragment() }
        }

        // 当ViewPager切换页面时，改变底部导航栏的状态
        mBinding.vpViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mBinding.baNavigation.menu.getItem(position).isChecked = true
            }
        })

        // BottomNavigation 改变 viewpager的状态
        mBinding.baNavigation.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.navigation_home -> mBinding.vpViewpager.setCurrentItem(0, true)
            }
        }

    }
}