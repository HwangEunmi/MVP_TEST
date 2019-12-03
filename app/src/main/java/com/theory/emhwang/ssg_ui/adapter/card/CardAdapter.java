package com.theory.emhwang.ssg_ui.adapter.card;

import java.util.List;

import com.theory.emhwang.ssg_ui.R;
import com.theory.emhwang.ssg_ui.adapter.card.contract.CardAdapterContract;
import com.theory.emhwang.ssg_ui.adapter.card.holder.CardViewHolder;
import com.theory.emhwang.ssg_ui.data.card.CardModel;
import com.theory.emhwang.ssg_ui.listener.OnItemClickListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder>
                         implements CardAdapterContract.View, CardAdapterContract.Model {

    private Context mContext;

    // 클릭 리스너
    private OnItemClickListener mClickListener;

    private List<CardModel> mList;

    public CardAdapter(final Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.view_adapter_card, parent, false);
        return new CardViewHolder(mContext, view, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, final int position) {
        if (holder == null)
            return;
        holder.setClickListener(position);
        holder.setViewData(position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    ///////

    @Override
    public CardModel getItems(final int index) {
        return mList == null ? null : mList.get(index);
    }

    @Override
    public void setOnClickListener(final OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    @Override
    public void notifyDataAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void addItems(final List<CardModel> list) {
        mList = list;
    }

    @Override
    public void clearAll() {
        if (mList != null) {
            mList.clear();
        }
    }

}
