package com.cjl_base_kt_mvvm.base

import android.app.Application
import android.content.Context
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.MultiDex
import com.cjl_base_kt_mvvm.R
import com.hjq.toast.ToastUtils
import com.tencent.mmkv.MMKV
import me.jessyan.autosize.AutoSizeConfig
import okhttp3.OkHttpClient
import rxhttp.wrapper.param.RxHttp

import java.util.concurrent.TimeUnit



class MyApplication : Application() {

    var activities:ArrayList<AppCompatActivity> = ArrayList()
    lateinit var instance:MyApplication
    lateinit var kv: MMKV

    override fun onCreate() {
        super.onCreate()
        instance=this
        MMKV.initialize(this)
        kv= MMKV.defaultMMKV()!!

        //设置读、写、连接超时时间为15s
        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()


//        crashHandler = CrashHandler().getInstance()
//        crashHandler.setCustomCrashHanler(this)

        RxHttp.init(client)
        RxHttp.setDebug(true)
        RxHttp.setOnParamAssembly { param ->
            val method = param.getMethod()
            //可根据请求类型添加不同的参数
            if (method.isGet) {
                //get方式
            } else if (method.isPost) {
                //post方式
            }
            param.addHeader("Authorization", "Bearer " + kv.decodeBool("tokenId")) //添加公共请求头
            //.add("versionName", "1.0.0")//添加公共参数
        }

        ToastUtils.init(this)
        ToastUtils.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 120)
        ToastUtils.setView(R.layout.layout_toast)

        //今日头条适配方案
        AutoSizeConfig.getInstance().setLog(false)

    }

    fun getMyInstance(): MyApplication {
        return instance
    }

    fun addActivity(activity: AppCompatActivity) {
        activities.add(activity)
    }

    fun removeActivity(activity: AppCompatActivity){
        if (activities.contains(activity)){
            activities.remove(activity)
        }
    }

    /**
     * 解决65535
     */
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    /**
     * 关闭所有的Activity
     */
    fun finishAll() {
        var i = 0
        val len = activities.size
        while (i < len) {
            val act = activities[i]
            act.finish()
            i++
        }
    }
}