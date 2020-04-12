package com.theory.emhwang.ssg_ui.data.card.source;

import java.util.ArrayList;
import java.util.List;

import com.theory.emhwang.ssg_ui.common.ApiManager;
import com.theory.emhwang.ssg_ui.base.BaseResponseModel;
import com.theory.emhwang.ssg_ui.data.card.CardModel;
import com.theory.emhwang.ssg_ui.listener.IHTTPListener;

public class CardRemoteSource implements ICardSource {

    private ApiManager mApiManager;

    public CardRemoteSource() {
        mApiManager = ApiManager.getInstance();
    }

    /**
     * Card Data를 가져온다. (임시로 Github 조회 API를 사용한다)
     */
    @Override
    public void getCardData(final LoadCardDataCallback listener) {
        mApiManager.getApiResponse(CardModel.class,
                mApiManager.getApiService().getRepositoryInfo(),
                new IHTTPListener() {

                    @Override
                    public void onSuccess(final BaseResponseModel model) {
                        if (listener != null) {
                            final CardModel response = (CardModel)model;
                            final List<CardModel> list = new ArrayList<>();
                            for (int i = 0; i < 5; i++) {
                                list.add(new CardModel(response.getName() + i));
                            }
                            listener.onDataListLoaded(list);
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
