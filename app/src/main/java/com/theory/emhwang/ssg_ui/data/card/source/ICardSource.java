package com.theory.emhwang.ssg_ui.data.card.source;

import com.theory.emhwang.ssg_ui.base.IBaseLoadDataCallback;
import com.theory.emhwang.ssg_ui.data.card.CardModel;

public interface ICardSource {

    // Card Data 가져오는 Callback Listener
    interface LoadCardDataCallback extends IBaseLoadDataCallback<CardModel> {

    }

    // Card Data를 가져온다.
    void getCardData(final LoadCardDataCallback listener);
}
