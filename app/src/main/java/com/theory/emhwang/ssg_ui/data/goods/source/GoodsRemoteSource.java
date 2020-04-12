package com.theory.emhwang.ssg_ui.data.goods.source;

import com.theory.emhwang.ssg_ui.base.BaseResponseModel;
import com.theory.emhwang.ssg_ui.common.ApiManager;
import com.theory.emhwang.ssg_ui.data.goods.GoodsModel;
import com.theory.emhwang.ssg_ui.listener.IHTTPListener;

public class GoodsRemoteSource implements IGoodsSource {

    private ApiManager mApiManager;

    public GoodsRemoteSource() {
        mApiManager = ApiManager.getInstance();
    }

    @Override
    public void getGoodsData(final LoadGoodsDataCallback listener) {
        mApiManager.getApiResponse(GoodsModel.class, mApiManager.getApiService().getRepositoryInfo(), new IHTTPListener() {
            @Override
            public void onSuccess(final BaseResponseModel model) {
                if (listener != null) {
                    final GoodsModel response = (GoodsModel) model;
                    listener.onDataLoaded(response);
                }
            }

            @Override
            public void onFail(final int code, final String message) {
                if (listener != null) {
                    listener.onFailLoaded(code, message);
                }
            }
        });
    }

}
