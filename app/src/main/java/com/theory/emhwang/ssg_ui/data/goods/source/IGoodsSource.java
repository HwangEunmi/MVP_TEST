package com.theory.emhwang.ssg_ui.data.goods.source;

import com.theory.emhwang.ssg_ui.base.IBaseLoadDataCallback;

public interface IGoodsSource {

    // Goods Data 가져오는 Callback Listener
    interface LoadGoodsDataCallback<T> extends IBaseLoadDataCallback {
        void onDataLoaded(final T data);
    }

    void getGoodsData(final LoadGoodsDataCallback listener);
}
