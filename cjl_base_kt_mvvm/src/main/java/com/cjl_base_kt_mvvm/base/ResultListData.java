package com.cjl_base_kt_mvvm.base;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T>
 */
public class ResultListData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public ResultDatas<T> data;

    private boolean succeed;//连接数据库成功

    private String error;


    public ResultDatas<T> getData() {
        return data;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public String getError() {
        return error;
    }

    /**
     * ResultData
     */
    public static class ResultDatas<T> implements Serializable {

        private List<T> items; //数据

        private int totalCount;

        public List<T> getItems() {
            return items;
        }

        public void setItems(List<T> items) {
            this.items = items;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

    }


}
