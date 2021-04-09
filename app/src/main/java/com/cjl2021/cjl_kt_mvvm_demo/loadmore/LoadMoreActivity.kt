package com.cjl2021.cjl_kt_mvvm_demo.loadmore

import android.os.Bundle
import com.cjl2021.cjl_kt_mvvm_demo.FilePageInfoData
import com.cjl2021.cjl_kt_mvvm_demo.R
import com.cjl2021.cjl_kt_mvvm_demo.databinding.ActivityLoadMoreBinding
import com.cjl_base_kt_mvvm.base.BaseLoadMoreActivity

class LoadMoreActivity :
    BaseLoadMoreActivity<LoadMoreVM, ActivityLoadMoreBinding, FilePageInfoData>() {

    override fun getLayoutId() = R.layout.activity_load_more

    override fun initData(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun initObserve() {
        TODO("Not yet implemented")
    }
}