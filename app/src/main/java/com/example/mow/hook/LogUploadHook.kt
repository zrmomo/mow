import com.example.mow.utils.LogHook
import com.example.mow.utils.LogInfo


/**
 * 上传日志拦截器
 */
class LogUploadHook : LogHook {
    override fun hook(info: LogInfo) {
        info.msg?.let {
            // ... 上传或者保存到本地
        }
    }
}

