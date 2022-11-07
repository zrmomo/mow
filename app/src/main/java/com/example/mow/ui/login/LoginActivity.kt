package com.example.mow.ui.login

import android.content.Intent
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.example.mow.MainActivity
import com.example.mow.R
import com.example.mow.base.BaseVmActivity
import com.example.mow.common.AppConfig
import com.example.mow.databinding.ActivityLoginBinding
import com.example.mow.utils.SpUtil
import com.example.mow.utils.ToastUtil

class LoginActivity :
    BaseVmActivity<ActivityLoginBinding, LoginViewModel>(ActivityLoginBinding::inflate) {

    //初始化ViewModel
    override fun viewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun initView() {
        setText()
    }

    //设置隐私协议
    private fun setText() {
        val spanBuilder = SpannableStringBuilder("同意")
        val color = ContextCompat.getColor(applicationContext, R.color.purple_200)
        var span = SpannableString("服务协议")
        span.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                ToastUtil.show("服务协议")
            }

        }, 0, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        span.setSpan(ForegroundColorSpan(color), 0, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.append(span)

        spanBuilder.append("yu")

        span = SpannableString("隐私政策")
        span.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                ToastUtil.show("隐私政策")
            }

        }, 0, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(ForegroundColorSpan(color), 0, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.append(span)

        mBinding.tvServiceAgreement.movementMethod = LinkMovementMethod.getInstance()
        mBinding.tvServiceAgreement.text = spanBuilder
        //设置高亮颜色透明
        mBinding.tvServiceAgreement.highlightColor =
            ContextCompat.getColor(applicationContext, R.color.transparent)
    }

    override fun setListener() {
        mBinding.btnLogin.setOnClickListener {
            if (!mBinding.cbServiceAgreement.isChecked) {
                ToastUtil.show("点那个checkbox 选中它")
                return@setOnClickListener
            }
            attemptLogin()
        }
    }

    private fun attemptLogin() {
        //将editText右侧的警告图标置空
        mBinding.etUsername.error = null
        mBinding.etPassword.error = null

        val username = mBinding.etUsername.text.toString().trim()
        val password = mBinding.etPassword.text.toString().trim()

        var cancel = false
        var focusView: View? = null

        if (password.isEmpty()) {
            mBinding.etPassword.error = "密码不能为空"
            focusView = mBinding.etPassword
            cancel = true
        }

        if (username.isEmpty()) {
            mBinding.etUsername.error = "账号不能为空"
            focusView = mBinding.etPassword
            cancel = true
        }

        //那个选项为空，那个选项获取焦点。
        if (cancel) focusView?.requestFocus()
        else doLogin(username, password)
    }

    private fun doLogin(username: String, password: String) {
        mViewModel.login(username, password)
    }

    override fun observe() {
        super.observe()
        mViewModel.loginState.observe(this) {
            SpUtil.setBoolean(AppConfig.IS_LOGIN, it)
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                ToastUtil.show("登录失败")
            }

        }
    }

}