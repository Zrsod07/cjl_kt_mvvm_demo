package com.cjl2021.cjl_kt_mvvm_demo.loadmore

import android.app.Application
import com.cjl2021.cjl_kt_mvvm_demo.FilePageInfoData
import com.cjl2021.cjl_kt_mvvm_demo.listmore.ListMoreAdapter
import com.cjl_base_kt_mvvm.base.BaseLoadMoreVM

class LoadMoreVM(application: Application) :BaseLoadMoreVM<FilePageInfoData>(application ) {
    override fun getFirstData() {

    }

    override fun getMoreData() {

    }

    override fun initAdapter()=ListMoreAdapter()
}