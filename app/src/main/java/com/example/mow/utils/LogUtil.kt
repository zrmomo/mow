package com.example.mow.utils

import android.util.Log


object LogUtil {
    private var TAG = "LogUtil"
    private var openLog = false
    private const val MAX_LENGTH = 4000
    fun isOpenLog(isOpen: Boolean) {
        openLog = isOpen
    }

    fun isOpenLog(isOpen: Boolean, tag: String) {
        TAG = tag
        openLog = isOpen
    }

    fun e(msg: String) {
        if (openLog) {
            val info = autoJumpLogInfos
            val msgLength = msg.length
            var start = 0
            var end = MAX_LENGTH
            for (i in 0..99) {
                if (msgLength > end) {
                    Log.e(TAG, info[1] + info[2] + "--->>" + msg.substring(start, end))
                    start = end
                    end += MAX_LENGTH
                } else {
                    Log.e(TAG, info[1] + info[2] + "--->>" + msg.substring(start, msgLength))
                }
            }
        }
    }

    fun e(tag: String, msg: String) {
        if (openLog) {
            val info = autoJumpLogInfos
            val msgLength = msg.length
            var start = 0
            var end = MAX_LENGTH
            for (i in 0..99) {
                if (msgLength > end) {
                    Log.e(tag, info[1] + info[2] + "--->>" + msg.substring(start, end))
                    start = end
                    end += MAX_LENGTH
                } else {
                    Log.e(tag, info[1] + info[2] + "--->>" + msg.substring(start, msgLength))
                }
            }
        }
    }

    /**
     * 获取打印信息所在方法名，行号等信息
     */
    private val autoJumpLogInfos: Array<String>
        get() {
            val infos = arrayOf("", "", "")
            val elements = Thread.currentThread().stackTrace
            infos[0] = elements[4].className.substring(elements[4].className.lastIndexOf(".") + 1)
            infos[1] = elements[4].methodName
            infos[2] = "(" + elements[4].fileName + ":" + elements[4].lineNumber + ")"
            return infos
        }
}