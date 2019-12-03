package com.theory.emhwang.ssg_ui.view.main.presenter;

import com.theory.emhwang.ssg_ui.adapter.card.contract.CardAdapterContract;
import com.theory.emhwang.ssg_ui.data.card.CardModel;
import com.theory.emhwang.ssg_ui.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    // View 객체
    private MainContract.View mView;

    // 카드 RecyclerView Contract
    private CardAdapterContract.View mRvCardView;

    private CardAdapterContract.Model mRvCardModel;

    @Override
    public void attachView(final MainContract.View view) {
        this.mView = view;
    }

    @Override
    public void detatchView() {
        this.mView = null;
    }

    @Override
    public void setCardAdapterViewAndModel(final CardAdapterContract.View view, final CardAdapterContract.Model model) {
        this.mRvCardView = view;
        this.mRvCardModel = model;

        mRvCardView.setOnClickListener(mCardClickListener);
    }

    // 카드 RecyclerView 클릭 리스너
    private OnItemClickListener mCardClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(final int index) {
            final CardModel model = mRvCardModel.getItems(index);
            mView.showToast(String.valueOf(model.getNum()));
        }
    };

    /**
     * 카드 RecyclerView 임시 데이터 셋팅하기 (List 잘 뿌려지는지 테스트용)
     */
    public void loadTempCardData() {
        final List<CardModel> list = new ArrayList<>();
        list.add(new CardModel(1));
        list.add(new CardModel(2));
        list.add(new CardModel(3));

        mRvCardModel.clearAll();
        mRvCardModel.addItems(list);
        mRvCardView.notifyDataAdapter();
    }
}
