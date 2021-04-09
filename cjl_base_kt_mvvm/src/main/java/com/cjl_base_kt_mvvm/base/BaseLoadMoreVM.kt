package com.cjl_base_kt_mvvm.base

import android.app.Application

/**
 * Created by YB on 2019/8/29
 */
abstract class BaseLoadMoreVM<T>(app: Application) : BaseVM(app) {
    var adapter: BaseLoadMoreAdapter<T, *> = initAdapter()

    init {
        adapter.retryCallback = ::baseInitData
        adapter.loadMore = ::baseLoadMoreData
    }

    /**
     * 设置首页数据
     */
    fun setData(list: MutableList<T>) {
        adapter.pageCount = adapter.pageCount
        handle.post {
            adapter.pageIndex = adapter.pageIndex + 1
            adapter.refreshing.value = false
            adapter.result.value = true
            if (list.size != adapter.pageCount) {
                //加载完成
                adapter.complete = true
            }
            adapter.setData(list)

        }
    }

    /**
     * 加载更多数据
     */
    fun appendData(list: MutableList<T>) {
        handle.post {
            adapter.pageIndex = adapter.pageIndex + 1
            adapter.loading.value = false
            adapter.result.value = true
            if (list.size != adapter.pageCount) {//当前页数得到的条数不足10页，不再加载下一页
                //加载完成
                adapter.complete = true
            }
            adapter.appendData(list)
        }
    }

    /**
     * 数据获取失败
     */
    fun failedData() {
        adapter.refreshing.postValue(false)
        adapter.loading.postValue(false)
        adapter.result.postValue(false)
        handle.post {
            adapter.notifyDataSetChanged()
        }
    }

    fun baseInitData() {
        adapter.refresh()
        getFirstData()
    }

    fun baseLoadMoreData() {
        adapter.loading.value = true
        getMoreData()
    }

    /**
     * 首页加载数据
     */
    abstract fun getFirstData()

    /**
     * 加载更多数据
     */
    abstract fun getMoreData()

    /**
     * 初始化adapter
     */
    abstract fun initAdapter(): BaseLoadMoreAdapter<T, *>
}