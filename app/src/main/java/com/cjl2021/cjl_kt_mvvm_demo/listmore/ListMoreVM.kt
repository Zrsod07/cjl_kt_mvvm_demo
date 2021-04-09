package com.cjl2021.cjl_kt_mvvm_demo.listmore

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.rxLifeScope
import com.cjl2021.cjl_kt_mvvm_demo.FilePageInfoData
import com.cjl_base_kt_mvvm.base.BaseLoadMoreVM
import com.cjl_base_kt_mvvm.mode.SimpleParams
import com.google.gson.reflect.TypeToken
import rxhttp.toStr
import rxhttp.wrapper.param.RxHttp

class ListMoreVM(application: Application) : BaseLoadMoreVM<FilePageInfoData>(application) {

    var studId=MutableLiveData<String>()
    var params=MutableLiveData<SimpleParams>()


    override fun initAdapter()=ListMoreAdapter()

    override fun getFirstData() {
        adapter.pageIndex = 1
        rxLifeScope.launch({
            var str=RxHttp.get("videocenterapi/filespaged")
                .addAll(params.value)
                .add("PageIndex",adapter.pageIndex)
                .add("PageSize",adapter.pageCount)
                .toStr().await()
            dialogLD.value=null

            if (mJsonUtil.getBoole(str)){
                val type = object : TypeToken<MutableList<FilePageInfoData?>?>() {}.type
                val resultListData = mJsonUtil.getObjectByStr<MutableList<FilePageInfoData>>(mJsonUtil.getString(mJsonUtil.getString(str, "data"), "items"), type)
                setData(resultListData)
            }else{
                toastLD.value="查询失败"
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

    override fun getMoreData() {
        rxLifeScope.launch({
            var str=RxHttp.get("videocenterapi/filespaged")
                .addAll(params.value)
                .add("PageIndex",adapter.pageIndex)
                .add("PageSize",adapter.pageCount)
                .toStr().await()
            dialogLD.value=null

            if (mJsonUtil.getBoole(str)){
                val type = object : TypeToken<MutableList<FilePageInfoData?>?>() {}.type
                val resultListData = mJsonUtil.getObjectByStr<MutableList<FilePageInfoData>>(mJsonUtil.getString(mJsonUtil.getString(str, "data"), "items"), type)
                appendData(resultListData)
            }else{
                toastLD.value="查询失败"
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