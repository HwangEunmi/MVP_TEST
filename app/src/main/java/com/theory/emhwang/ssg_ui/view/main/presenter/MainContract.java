package com.theory.emhwang.ssg_ui.view.main.presenter;

import com.theory.emhwang.ssg_ui.adapter.card.contract.CardAdapterContract;

public interface MainContract {

    interface View {
        void showToast(final String text);
    }

    interface Presenter {

        void attachView(final View view);

        void detatchView();

        void setCardAdapterViewAndModel(final CardAdapterContract.View view, final CardAdapterContract.Model model); // 카드 RecyclerView 뷰/모델 객체 셋팅하기
    }
}
