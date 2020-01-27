package com.theory.emhwang.ssg_ui.base;

import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>>
        implements BaseAdapterContract.View, BaseAdapterContract.Model<T> {

    private List<T> mList;

    public BaseAdapter() {
        mList = new ArrayList<>();
    }

    @NonNull
    @Override
    abstract public BaseViewHolder<T> onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType);

    @Override
    @CallSuper
    public void onBindViewHolder(@NonNull final BaseViewHolder<T> holder, final int position) {
        holder.setData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 데이터 리스트 추가하기
     *
     * @param list : 데이터 리스트
     */
    public void addItems(final List<T> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
    }

    /**
     * 데이터 리스트 지우기
     */
    public void clearAll() {
        mList.clear();
    }

    /**
     * 데이터 가져오기
     *
     * @param index : Index 값
     * @return
     */
    public T getItems(final int index) {
        return mList.get(index);
    }

    /**
     * 데이터 갱신하기
     */
    @Override
    public void notifyDataAdapter() {
        notifyDataSetChanged();
    }

}