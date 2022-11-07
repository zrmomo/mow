package com.example.mow.utils

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import java.util.Stack

/**
 * @description Activity 管理工具类
 * @author: zhourui
 * @date: 2022/10/25
 */
object ActivityUtil {
    //Activity 栈
    private val activityStack = Stack<Activity>()

    //Activity的生命周期回调，要求API14+（Android 4.0+）
    private val instance = AppActivityLifecycleCallbacks()

    val activityLifecycleCallbacks: ActivityLifecycleCallbacks
        get() = instance
    /**
     * 获得当前栈顶Activity
     */
    val currentActivity: Activity?
        get() {
            var activity: Activity? = null
            if (!activityStack.isEmpty()) activity = activityStack.peek()
            return activity
        }

    /**
     * 每个activity 的生命周期回调都会走ActivityLifecycleCallbacks这个接口
     */
    private class AppActivityLifecycleCallbacks : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            activityStack.remove(activity)
            activityStack.push(activity)
        }

        override fun onActivityStarted(activity: Activity) {}
        override fun onActivityResumed(activity: Activity) {}
        override fun onActivityPaused(activity: Activity) {}
        override fun onActivityStopped(activity: Activity) {}
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
        override fun onActivityDestroyed(activity: Activity) {
            activityStack.remove(activity)
        }
    }
}