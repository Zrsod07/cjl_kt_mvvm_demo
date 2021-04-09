package com.cjl_base_kt_mvvm.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding

/**
 * Created by YB on 2019/9/20
 */
abstract class BaseLoadMoreActivity<V : BaseLoadMoreVM<T>, B : ViewDataBinding, T> : BaseKtActivity<V, B>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        findViewById<SwipeRefreshLayout>(R.id.srlList).setColorSchemeResources(R.color.colorPrimary)
        vm.baseInitData()
    }

    open fun onRefreshListener() {
        vm.baseInitData()
    }
}