package com.theory.emhwang.ssg_ui.view.goods.presenter;

import com.theory.emhwang.ssg_ui.data.goods.GoodsModel;
import com.theory.emhwang.ssg_ui.data.goods.source.GoodsRepository;
import com.theory.emhwang.ssg_ui.data.goods.source.IGoodsSource;

import java.util.List;

public class GoodsPresenter implements GoodsContract.Presenter {

    // View 객체
    private GoodsContract.View mView;

    // Goods Data Repository
    private GoodsRepository mGoodsDataRepository;

    @Override
    public void attachView(final GoodsContract.View view) {
        this.mView = view;
    }

    @Override
    public void detatchView() {
        this.mView = null;
    }

    @Override
    public void setGoodsDataRepository(final GoodsRepository repository) {
        this.mGoodsDataRepository = repository;
    }

    /**
     * Goods 데이터 이미지 로드하기
     */
    @Override
    public void loadGoodsData() {
        mGoodsDataRepository.getGoodsData(new IGoodsSource.LoadGoodsDataCallback<GoodsModel>() {
            @Override
            public void onDataLoaded(final GoodsModel data) {
                mView.drawImage(data.getAvatar_url());
            }

            @Override
            public void onDataListLoaded(final List list) {
            }

            @Override
            public void onFailLoaded(final int code, final String message) {
                mView.showToast(message);
            }
        });
    }

}
