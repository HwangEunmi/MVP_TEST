package com.theory.emhwang.ssg_ui.data.card.source;

import java.util.List;

import com.google.firebase.database.DatabaseException;
import com.theory.emhwang.ssg_ui.data.card.CardModel;

public interface ICardSource {

    // Card Data 가져오는 Callback Listener
    interface LoadCardDataCallback {

        void onDataLoaded(final List<CardModel> list);

        void onFailLoaded(final DatabaseException error);
    }

    // Card Data를 가져온다.
    void getCardData(final LoadCardDataCallback listener);
}
