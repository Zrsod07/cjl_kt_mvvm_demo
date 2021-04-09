package com.cjl_base_kt_mvvm.base

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cjl_base_kt_mvvm.utils.JsonUtil

abstract class BaseVM(application: Application) : AndroidViewModel(application) {
    val loadingStr = "请稍后……"
    //显示dialog
    val dialogLD = MutableLiveData<String>()
    val toastLD = MutableLiveData<String>()
    val handle = Handler(Looper.getMainLooper())
    val mJsonUtil: JsonUtil =JsonUtil.getInstance()

    //取消请求
    open fun cancelRequest() {}

}