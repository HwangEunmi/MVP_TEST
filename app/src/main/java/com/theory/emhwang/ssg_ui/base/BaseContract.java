package com.theory.emhwang.ssg_ui.base;

public interface BaseContract {
    interface View {

    }

    interface Presenter {

        void attachView(final View view);

        void detatchView();

        void setBaseAdapterViewAndModel(final BaseAdapterContract.View view, final BaseAdapterContract.Model model); // RecyclerView 뷰/모델 객체 셋팅하기
        // void setCardAdapterViewAndModel(final CardAdapterContract.View view, final CardAdapterContract.Model model); // 카드 RecyclerView 뷰/모델 객체 셋팅하기

        void setBaseDataRepository(final BaseRepository repository); // Data Repository 셋팅하기

    }
}
