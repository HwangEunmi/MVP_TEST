package com.theory.emhwang.ssg_ui.adapter.card.holder;

import com.theory.emhwang.ssg_ui.R;
import com.theory.emhwang.ssg_ui.data.card.CardModel;
import com.theory.emhwang.ssg_ui.listener.IItemClickListener;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;

    // 전체 레이아웃
    private LinearLayout mLlLayout;

    // 번호
    private TextView mTvNum;

    // 클릭 리스너
    private IItemClickListener mClickListener;

    public CardViewHolder(@NonNull final Context mContext, final View itemView, final IItemClickListener listener) {
        super(itemView);
        this.mContext = mContext;
        this.mClickListener = listener;
        initView(itemView);
    }

    /**
     * 뷰 초기화하기
     */
    private void initView(final View view) {
        mLlLayout = view.findViewById(R.id.ll_layout);
        mTvNum = view.findViewById(R.id.tv_num);
    }

    /**
     * 뷰 셋팅하기
     */
    public void setViewData(final CardModel model) {
        mTvNum.setText(model.getName());
    }

    /**
     * 전체 레이아웃에 Click Listener 셋팅하기
     */
    public void setClickListener(final int index) {
        mLlLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                if (mClickListener != null) {
                    mClickListener.onItemClick(index);
                }
            }
        });
    }

}
