package com.cjl2021.cjl_kt_mvvm_demo.list

import android.os.Bundle
import com.cjl2021.cjl_kt_mvvm_demo.R
import com.cjl2021.cjl_kt_mvvm_demo.databinding.ActivityListNoMoreBinding
import com.cjl_base_kt_mvvm.base.BaseKtActivity
import com.cjl_base_kt_mvvm.mode.SimpleParams

class ListNoMoreActivity : BaseKtActivity<ListNoMoreVM, ActivityListNoMoreBinding>() {


    override fun getLayoutId() = R.layout.activity_list_no_more


    override fun initData(savedInstanceState: Bundle?) {
        bind.vm = vm
        vm.getData("videocenterapi/filespaged",SimpleParams.create().putP("StuId","1545"))

    }

    override fun initObserve() {

    }
}