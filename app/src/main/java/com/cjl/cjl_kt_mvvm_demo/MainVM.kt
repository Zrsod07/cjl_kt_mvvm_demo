package com.cjl.cjl_kt_mvvm_demo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.rxLifeScope
import com.cjl.cjl_kt_mvvm_demo.LoginInfo
import com.cjl_base_kt_mvvm.base.BaseVM
import com.cjl_base_kt_mvvm.mode.SimpleParams
import rxhttp.toStr
import rxhttp.wrapper.param.RxHttp

class MainVM(application: Application) : BaseVM(application) {

    var param=MutableLiveData<SimpleParams>()
    var loginData=MutableLiveData<Any>()
    val userName=MutableLiveData<String>()
    val userPwd=MutableLiveData<String>()

    fun getData(){
        rxLifeScope.launch({
            var str=RxHttp.postJson("token").addAllQuery(param.value).toStr().await()
            dialogLD.value=loadingStr
            if (mJsonUtil.getBoole(str)) {
                loginData.value = mJsonUtil.getObjectByStr(mJsonUtil.getString(str, "data"), LoginInfo::class.java)
//                var ld=loginData.value as LoginInfo
            } else {
                toastLD.value=mJsonUtil.getError(str)
//                loginData.value = mJsonUtil.getError(str)
            }
        },{
            dialogLD.value=null
            toastLD.value=it.message
        },{
            dialogLD.value=loadingStr
        },{
            dialogLD.value=null
        })
    }
}