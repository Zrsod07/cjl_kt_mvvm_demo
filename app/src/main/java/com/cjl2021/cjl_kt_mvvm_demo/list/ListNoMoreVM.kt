package com.cjl2021.cjl_kt_mvvm_demo.list

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.rxLifeScope
import com.cjl2021.cjl_kt_mvvm_demo.FilePageInfoData
import com.cjl_base_kt_mvvm.base.BaseVM
import com.cjl_base_kt_mvvm.mode.SimpleParams
import com.google.gson.reflect.TypeToken
import rxhttp.toStr
import rxhttp.wrapper.param.RxHttp

class ListNoMoreVM(application: Application) :BaseVM(application) {

    val adapterLD = MutableLiveData<ListAdapter>()

    fun getData(url:String,params: SimpleParams){
        rxLifeScope.launch ({
            val str=RxHttp.get(url).addAll(params).toStr().await()

            if (mJsonUtil.getBoole(str)) {
                val type = object : TypeToken<MutableList<FilePageInfoData?>?>() {}.type
                val resultListData = mJsonUtil.getObjectByStr<MutableList<FilePageInfoData>>(mJsonUtil.getString(mJsonUtil.getString(str, "data"), "items"), type)
                adapterLD.value= ListAdapter(resultListData)
            }

        },{
            dialogLD.value=null
        },{
            dialogLD.value=loadingStr
        },{
            dialogLD.value=null
        })

    }
}