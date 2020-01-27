package com.theory.emhwang.ssg_ui.adapter.card.contract;

import com.theory.emhwang.ssg_ui.base.BaseAdapterContract;
import com.theory.emhwang.ssg_ui.data.card.CardModel;
import com.theory.emhwang.ssg_ui.listener.IItemClickListener;

import java.util.List;

public interface CardAdapterContract {

    interface View extends BaseAdapterContract.View {

        void setOnClickListener(final IItemClickListener listener);
    }

    interface Model extends BaseAdapterContract.Model<CardModel> {
        @Override
        void addItems(final List<CardModel> list);

        @Override
        void clearAll();

        @Override
        CardModel getItems(final int index);
    }

}
