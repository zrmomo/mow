package com.example.mow.utils

import android.annotation.SuppressLint
import android.view.Gravity
import android.widget.Toast
import com.example.mow.base.App

/**
 * @description Toast 工具类
 * @author: zhourui
 * @date: 2022/10/25
 */
object ToastUtil {
    private var toast: Toast? = null

    /**
     * 向外暴露的普通Toast的调用方法
     */
    fun show(msg: String) {
        if ("main" == Thread.currentThread().name) {
            createToast(msg)
        } else {
            ActivityUtil.currentActivity?.runOnUiThread { createToast(msg) }
        }
    }


    /**
     * createToast
     * @param msg 接收参数
     */
    @SuppressLint("ShowToast")
    private fun createToast(msg: String) {
        if (toast == null) {
            toast = Toast.makeText(App.CONTEXT.applicationContext, msg, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(msg)
        }
        toast!!.show()
    }

    /**
     * toast 任意线程，不重复显示
     */
    fun showCenter(msg: String) {
        if ("main" == Thread.currentThread().name) {
            createCenterToast(msg)
        } else {
            //回归到主线程弹toast
            ActivityUtil.currentActivity!!.runOnUiThread { createCenterToast(msg) }
        }
    }

    /**
     * createCenterToast
     *
     * @param msg 接收参数
     */
    @SuppressLint("ShowToast")
    private fun createCenterToast(msg: String) {
        if (toast == null) {
            toast = Toast.makeText(App.CONTEXT, msg, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(msg)
        }
        toast!!.setGravity(Gravity.CENTER, 0, 0)
        toast!!.show()
    }




}