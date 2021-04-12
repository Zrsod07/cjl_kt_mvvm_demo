package com.cjl_base_kt_mvvm.base

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cjl_base_kt_mvvm.R
import com.cjl_base_kt_mvvm.mviews.LoadingProgressDialog
import com.gyf.immersionbar.ImmersionBar
import com.hjq.toast.ToastUtils
import com.tencent.mmkv.MMKV
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KVariance

abstract class BaseKtActivity<VM : BaseVM, D : ViewDataBinding> : AppCompatActivity() {

    protected val TAG = this.javaClass.name
    lateinit var vm: VM
    lateinit var bind: D
    val mApp by lazy { application as MyApplication }
    val mProDialog: LoadingProgressDialog by lazy { LoadingProgressDialog(this, R.style.CustomDialog) }
    private val needBar = true //是否需要baseactivity设置沉浸式状态栏
    var tvTitleLeft: TextView? = null
    val kv:MMKV= MMKV.defaultMMKV()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mApp.addActivity(this)
        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN or
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
        )
        bind = DataBindingUtil.setContentView(this, getLayoutId())
        bind.lifecycleOwner = this
        val type = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        getVM(type[0] as Class<VM>)
        vm.dialogLD.observe(this, Observer { str ->
            if (!TextUtils.isEmpty(str)) {
                showProgressDialog(str)
            } else {
                dismissProgressDialog()
            }
        })

        vm.toastLD.observe(this, Observer { str ->
            if (!TextUtils.isEmpty(str)) {
                showToast(str)
            }
        })
        initObserve()
        initData(savedInstanceState)

        if (needBar) {
            // 所有子类都将继承这些相同的属性,请在设置界面之后设置
            ImmersionBar.with(this).reset()
                .statusBarColor(R.color.white) //状态栏
                .fitsSystemWindows(true) //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
                .statusBarDarkFont(true, 0.2f) //字体为深色
                .navigationBarColor(R.color.white)
                .init()
        }
        backFinish() //返回按钮

    }

    private fun backFinish() {
        tvTitleLeft?.setOnClickListener { finish() }
    }

    private fun getVM(clazz: Class<VM>) {
        this.vm = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(clazz)
    }

    open fun showProgressDialog(msg: String? = null, cancelable: Boolean = false) {
        if (msg == null) {
            mProDialog.setMsg(vm.loadingStr)
        } else {
            mProDialog.setMsg(vm.loadingStr)
        }
        mProDialog.setCancelable(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (!(isDestroyed || isFinishing) && !mProDialog.isShowing) {
                mProDialog.show()
            }
        } else {
            if (!isFinishing && !mProDialog.isShowing) {
                mProDialog.show()
            }
        }
    }

    open fun dismissProgressDialog() {
        if (mProDialog.isShowing) {
            mProDialog.dismiss()
        }
    }

    open fun showToast(msg: String) {
        ToastUtils.show(msg)
    }

    open fun showToast(msg: Int) {
        ToastUtils.show(msg)
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initData(savedInstanceState: Bundle?)

    protected abstract fun initObserve()

    override fun onDestroy() {
        mApp.removeActivity(this)
        super.onDestroy()
    }

}

