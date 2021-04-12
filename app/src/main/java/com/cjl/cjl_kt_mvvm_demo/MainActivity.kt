package com.cjl.cjl_kt_mvvm_demo


import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.cjl.cjl_kt_mvvm_demo.databinding.ActivityMainBinding
import com.cjl.cjl_kt_mvvm_demo.list.ListNoMoreActivity
import com.cjl.cjl_kt_mvvm_demo.listmore.ListMoreActivity
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
                showToast("登录成功:"+it.account)
            }else if (it is String){
                showToast("请求失败")
            }
        })

    }

    inner class MainLis{

        fun getData(){
            vm.param.value= SimpleParams.create().putP("account",vm.userName.value).putP("password",vm.userPwd.value)
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