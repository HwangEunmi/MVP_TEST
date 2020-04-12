package com.theory.emhwang.ssg_ui.view.goods.presenter;

import com.theory.emhwang.ssg_ui.data.goods.source.GoodsRepository;

public interface GoodsContract {
    interface View {
        void showToast(final String text);

        void drawImage(final String url); // 이미지를 그린다.
    }

    interface Presenter {

        void attachView(final GoodsContract.View view);

        void detatchView();

        void setGoodsDataRepository(final GoodsRepository repository); // Goods Data Repository 셋팅하기

        void loadGoodsData(); // Goods Data 불러오기
    }
}
