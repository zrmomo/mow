package com.example.mow.utils

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import kotlin.math.min

/**
 * 日志工具类
 */
object LogCat {
    private var TAG = "LogUtil"

    enum class Type {
        VERBOSE, DEBUG, INFO, WARN, ERROR, WTF
    }

    /** 日志默认标签 */
    var tag = "日志"

    /** 是否启用日志 */
    var enabled = true

    /** 日志是否显示代码位置 */
    var traceEnabled = true

    /** 日志的Hook钩子 */
    val logHooks by lazy { ArrayList<LogHook>() }

    /**
     * @param enabled 是否启用日志
     * @param tag 日志默认标签
     */
    fun setDebug(enabled: Boolean, tag: String = this.tag) {
        this.enabled = enabled
        this.tag = tag
    }

    /**
     * 添加日志拦截器
     */
    fun addHook(hook: LogHook) {
        logHooks.add(hook)
    }

    /**
     * 删除日志拦截器
     */
    fun removeHook(hook: LogHook) {
        logHooks.remove(hook)
    }

    @JvmOverloads
    @JvmStatic
    fun v(
        msg: Any?,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {

        print(Type.VERBOSE, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun i(
        msg: Any?,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(Type.INFO, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun d(
        msg: Any?,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(Type.DEBUG, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun w(
        msg: Any?,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(Type.WARN, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun e(
        msg: Any?,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(Type.ERROR, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun e(
        tr: Throwable?,
        tag: String = this.tag,
        occurred: Throwable? = Exception(),
        msg: Any? = "",
    ) {
        print(Type.ERROR, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun wtf(
        msg: Any?,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(Type.WTF, msg, tag, tr, occurred)
    }

    /**
     * 输出日志
     * 如果[msg]和[occurred]为空或者[tag]为空将不会输出日志, 拦截器
     *
     * @param type 日志等级
     * @param msg 日志信息
     * @param tag 日志标签
     * @param occurred 日志异常
     */
    private fun print(
        type: Type = Type.INFO,
        msg: Any? = null,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        if (!enabled || msg == null) return

        var message = msg.toString()

        val info = LogInfo(type, message, tag, tr, occurred)
        for (logHook in logHooks) {
            logHook.hook(info)
            if (info.msg == null) return
        }

        if (traceEnabled && occurred != null) {
            occurred.stackTrace.getOrNull(1)?.run {
                message += " ...($fileName:$lineNumber)"
            }
        }
        val max = 3800
        val length = message.length
        if (length > max) {
            synchronized(this) {
                var startIndex = 0
                var endIndex = max
                while (startIndex < length) {
                    endIndex = min(length, endIndex)
                    val substring = message.substring(startIndex, endIndex)
                    log(type, substring, tag, tr)
                    startIndex += max
                    endIndex += max
                }
            }
        } else {
            log(type, message, tag, tr)
        }
    }

    /**
     * JSON格式化输出日志
     * @param tag 日志标签
     * @param msg 日志信息
     * @param type 日志类型
     * @param occurred 日志发生位置
     */
    @JvmOverloads
    @JvmStatic
    fun json(
        json: Any?,
        tag: String = this.tag,
        msg: String = "",
        type: Type = Type.INFO,
        occurred: Throwable? = Exception()
    ) {
        if (!enabled || json == null) return

        var message = json.toString()

        val occurredMsg = if (traceEnabled && occurred != null) {
            occurred.stackTrace.getOrNull(1)?.run { " ($fileName:$lineNumber)" }
        } else ""

        if (message.isBlank()) {
            print(type, "$msg$occurredMsg\n$message", tag, occurred = null)
            return
        }

        val tokener = JSONTokener(message)
        val obj = try {
            tokener.nextValue()
        } catch (e: Exception) {
            "Parse json error"
        }

        message = when (obj) {
            is JSONObject -> obj.toString(2)
            is JSONArray -> obj.toString(2)
            else -> obj.toString()
        }

        print(type, "$msg$occurredMsg\n$message", tag, occurred = null)
    }

    private fun log(type: Type, msg: String, tag: String, tr: Throwable?) {
        when (type) {
            Type.VERBOSE -> Log.v(tag, msg, tr)
            Type.DEBUG -> Log.d(tag, msg, tr)
            Type.INFO -> Log.i(tag, msg, tr)
            Type.WARN -> Log.w(tag, msg, tr)
            Type.ERROR -> Log.e(tag, msg, tr)
            Type.WTF -> Log.wtf(tag, msg, tr)
        }
    }


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
            for (i in 0..7) {
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
            for (i in 0..7) {
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