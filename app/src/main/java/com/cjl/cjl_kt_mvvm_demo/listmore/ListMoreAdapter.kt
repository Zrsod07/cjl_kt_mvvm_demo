package com.cjl.cjl_kt_mvvm_demo.listmore

import com.cjl.cjl_kt_mvvm_demo.FilePageInfoData
import com.cjl.cjl_kt_mvvm_demo.R
import com.cjl.cjl_kt_mvvm_demo.databinding.ItemListNoMoreBinding
import com.cjl_base_kt_mvvm.base.BaseLoadMoreAdapter
import com.cjl_base_kt_mvvm.base.ViewHolderRecyclerView

class ListMoreAdapter : BaseLoadMoreAdapter<FilePageInfoData, ItemListNoMoreBinding>() {

    override fun getLayoutIdByList(viewType: Int)=R.layout.item_list_no_more

    override fun onBindView(t: FilePageInfoData, bind: ItemListNoMoreBinding, holder: ViewHolderRecyclerView, position: Int) {
        bind.itemData=t
        bind.executePendingBindings()
    }
}