package com.cjl2021.cjl_kt_mvvm_demo.list

import com.cjl2021.cjl_kt_mvvm_demo.FilePageInfoData
import com.cjl2021.cjl_kt_mvvm_demo.R
import com.cjl2021.cjl_kt_mvvm_demo.databinding.ItemListNoMoreBinding
import com.cjl_base_kt_mvvm.base.BaseRecyclerVMAdapter
import com.cjl_base_kt_mvvm.base.ViewHolderRecyclerView

/**
 * Created by cjl on 2021/4/7
 * 单一类型布局
 */
class ListAdapter(list: MutableList<FilePageInfoData>) : BaseRecyclerVMAdapter<FilePageInfoData, ItemListNoMoreBinding>(list) {
    override fun getLayoutId(viewType: Int) = R.layout.item_list_no_more

    override fun onBindView(t: FilePageInfoData, bind: ItemListNoMoreBinding, holder: ViewHolderRecyclerView, position: Int) {
        bind.itemData = t
        bind.executePendingBindings()
    }
}

///**
// * Created by cjl on 2021/4/7
// * 多种类型布局
// */
//class ListMultipleAdapter(list: MutableList<FilePageInfoData>) : BaseRecyclerVMAdapter<FilePageInfoData, ViewDataBinding>(list) {
//    override fun getItemViewType(position: Int): Int {
//        return when (position % 2) {
//            0 -> 0
//            else -> 1
//        }
//    }
//
//    override fun getLayoutId(viewType: Int) =
//        when (viewType) {
//            0 -> R.layout.item_list_no_more
//            else -> R.layout.item_list_no_more
//        }
//
//
//    override fun onBindView(t: FilePageInfoData, bind: ViewDataBinding, holder: ViewHolderRecyclerView, position: Int) {
//        if (bind is ItemListNoMoreBinding) {
//            bind.itemData = t
//            bind.executePendingBindings()
//        } else if (bind is ItemListNoMoreBinding) {
//            bind.itemData = t
//            bind.executePendingBindings()
//        }
//    }
//}