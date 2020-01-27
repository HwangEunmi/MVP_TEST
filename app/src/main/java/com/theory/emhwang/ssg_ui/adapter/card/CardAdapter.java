package com.theory.emhwang.ssg_ui.adapter.card;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.theory.emhwang.ssg_ui.R;
import com.theory.emhwang.ssg_ui.adapter.card.contract.CardAdapterContract;
import com.theory.emhwang.ssg_ui.adapter.card.holder.CardViewHolder;
import com.theory.emhwang.ssg_ui.base.BaseAdapter;
import com.theory.emhwang.ssg_ui.base.BaseViewHolder;
import com.theory.emhwang.ssg_ui.data.card.CardModel;
import com.theory.emhwang.ssg_ui.listener.IItemClickListener;

public class CardAdapter extends BaseAdapter<CardModel>
        implements CardAdapterContract.View, CardAdapterContract.Model {

    // 클릭 리스너
    private IItemClickListener mClickListener;

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_adapter_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<CardModel> holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setClickListener(mClickListener);
    }

    /////// Interface 메소드 ///////

    @Override
    public void setOnClickListener(final IItemClickListener listener) {
        this.mClickListener = listener;
    }

}
