package com.theory.emhwang.ssg_ui.view.main.presenter;

import com.theory.emhwang.ssg_ui.adapter.card.contract.CardAdapterContract;
import com.theory.emhwang.ssg_ui.data.card.source.CardRepository;

public interface MainContract {

    interface View {
        void showToast(final String text);
    }

    interface Presenter {

        void attachView(final View view);

        void detatchView();

        void setCardAdapterViewAndModel(final CardAdapterContract.View view, final CardAdapterContract.Model model); // 카드 RecyclerView 뷰/모델 객체 셋팅하기

        void setCardDataRepository(final CardRepository repository); // 카드 Data Repository 셋팅하기

        void loadCardDataList(final boolean isClear); // 카드 Data List 불러오기
    }
}
