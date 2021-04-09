package com.cjl_base_kt_mvvm.net

import com.cjl_base_kt_mvvm.BuildConfig
import rxhttp.wrapper.annotation.DefaultDomain

object Url {
    @JvmField
    @DefaultDomain //设置为默认域名
    var  baseUrl = "http://gaozhi.zheke.com/api/"
}