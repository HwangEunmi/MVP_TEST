package com.theory.emhwang.ssg_ui.adapter.card.contract;

import com.theory.emhwang.ssg_ui.data.card.CardModel;
import com.theory.emhwang.ssg_ui.listener.OnItemClickListener;

import java.util.List;

public interface CardAdapterContract {

    interface View {

        void setOnClickListener(final OnItemClickListener listener);

        void notifyDataAdapter();
    }

    interface Model {

        void addItems(final List<CardModel> list);

        void clearAll();

        CardModel getItems(final int index);
    }
}
