package com.cjl_base_kt_mvvm.mviews

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.cjl_base_kt_mvvm.R
import com.cjl_base_kt_mvvm.databinding.ItemLoadingDialogBinding

class LoadingProgressDialog(context: Context, theme: Int) : ProgressDialog(context, theme) {

    lateinit var dialogBinding: ItemLoadingDialogBinding

    private lateinit var msg: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDialog()
    }

    fun initDialog() {
        setCancelable(true)
        setCanceledOnTouchOutside(false)
        dialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_loading_dialog,
            null,
            false
        )

        if (msg==null) {
            msg = "加载中..."
        }
        dialogBinding.tvLoadDialog.setText(msg)
        setContentView(dialogBinding?.root)//loading的xml文件

//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        getWindow().setAttributes(params);


        //3.设置指定的宽高,如果不设置的话，弹出的对话框可能不会显示全整个布局，当然在布局中写死宽高也可以
        val lp = WindowManager.LayoutParams()
        val window = this.window
        lp.copyFrom(window!!.attributes)

        lp.width = (context.resources.displayMetrics.widthPixels * 0.3).toInt()
        lp.height = (context.resources.displayMetrics.heightPixels * 0.15).toInt()
        window.attributes = lp
    }


    fun setMsg(msg: String) {
        this.msg = msg
    }
}
