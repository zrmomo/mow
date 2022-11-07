package com.example.mow.base

import android.app.Application
import android.content.Context
import com.example.mow.BuildConfig
import com.example.mow.utils.ActivityUtil
import com.example.mow.utils.LogUtil
import kotlin.properties.Delegates

class App : Application() {
    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext

        // debug模式下打印log
        LogUtil.isOpenLog(BuildConfig.DEBUG)
        // activity管理
        registerActivityLifecycleCallbacks(
            ActivityUtil.activityLifecycleCallbacks
        )
    }
}