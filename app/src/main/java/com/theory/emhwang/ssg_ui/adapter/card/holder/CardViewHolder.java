package com.theory.emhwang.ssg_ui.adapter.card.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.theory.emhwang.ssg_ui.R;
import com.theory.emhwang.ssg_ui.base.BaseViewHolder;
import com.theory.emhwang.ssg_ui.data.card.CardModel;
import com.theory.emhwang.ssg_ui.listener.IItemClickListener;

public class CardViewHolder extends BaseViewHolder<CardModel> implements View.OnClickListener {

    // 전체 레이아웃
    private LinearLayout mLlLayout;

    // 번호
    private TextView mTvNum;

    // 클릭 리스너
    private IItemClickListener mClickListener;

    public CardViewHolder(final View itemView) {
        super(itemView);
    }

    @Override
    public void onClick(final View v) {
        if (mClickListener != null) {
            mClickListener.onItemClick(getAdapterPosition());
        }
    }

    @Override
    protected void initView(final View view) {
        mLlLayout = view.findViewById(R.id.ll_layout);
        mTvNum = view.findViewById(R.id.tv_num);

        mLlLayout.setOnClickListener(this);
    }

    @Override
    public void setData(final CardModel model) {
        mTvNum.setText(model.getName());
    }

    @Override
    public void setClickListener(final IItemClickListener listener) {
        this.mClickListener = listener;
    }

}
