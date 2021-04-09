package com.cjl2021.cjl_kt_mvvm_demo


import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.cjl2021.cjl_kt_mvvm_demo.databinding.ActivityMainBinding
import com.cjl2021.cjl_kt_mvvm_demo.list.ListNoMoreActivity
import com.cjl2021.cjl_kt_mvvm_demo.listmore.ListMoreActivity
import com.cjl_base_kt_mvvm.base.BaseKtActivity
import com.cjl_base_kt_mvvm.mode.SimpleParams

class MainActivity : BaseKtActivity<MainVM,ActivityMainBinding>() {

    override fun getLayoutId()=R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        bind.vm=vm
        bind.mainlis=MainLis()

    }

    override fun initObserve() {
        vm.loginData.observe(this, Observer {
            if (it is LoginInfo){
                showToast("登录成功")
            }else if (it is String){
                showToast("请求失败")
            }
        })

    }

    inner class MainLis{

        fun getData(){
            vm.param.value= SimpleParams().putP("StuId","1545")
            vm.getData()
        }

        fun toList(){
            startActivity(Intent(this@MainActivity, ListNoMoreActivity::class.java))
        }
        fun toListMore(){
            startActivity(Intent(this@MainActivity, ListMoreActivity::class.java))
        }

    }
}