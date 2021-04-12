package com.cjl.cjl_kt_mvvm_demo.listmore

import android.os.Bundle
import com.cjl.cjl_kt_mvvm_demo.FilePageInfoData
import com.cjl.cjl_kt_mvvm_demo.R
import com.cjl.cjl_kt_mvvm_demo.databinding.ActivityListMoreBinding
import com.cjl_base_kt_mvvm.base.BaseLoadMoreActivity
import com.cjl_base_kt_mvvm.mode.SimpleParams

class ListMoreActivity : BaseLoadMoreActivity<ListMoreVM, ActivityListMoreBinding,FilePageInfoData>() {

    override fun getLayoutId() = R.layout.activity_list_more

    override fun initData(savedInstanceState: Bundle?) {
        bind.vm = vm
        bind.view=this
        vm.studId.value="ds"
        vm.params.value= SimpleParams.create().putP("StuId","1545")
    }

    override fun initObserve() {

    }

}