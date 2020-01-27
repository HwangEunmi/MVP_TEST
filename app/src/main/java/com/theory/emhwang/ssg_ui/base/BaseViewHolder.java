package com.theory.emhwang.ssg_ui.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theory.emhwang.ssg_ui.listener.IItemClickListener;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull final View itemView) {
        super(itemView);
        initView(itemView);
    }

    /**
     * 뷰 초기화하기
     *
     * @param view : View 객체
     */
    abstract protected void initView(final View view);

    /**
     * 뷰에 데이터 셋팅하기
     *
     * @param t : 데이터 모델 객체
     */
    abstract public void setData(final T t);

    /**
     * Click Listener 셋팅하기
     *
     * @param listener : 클릭 리스너
     */
    abstract public void setClickListener(final IItemClickListener listener);

}
