package com.theory.emhwang.ssg_ui.view.main.presenter;

import java.util.List;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.theory.emhwang.ssg_ui.adapter.card.contract.CardAdapterContract;
import com.theory.emhwang.ssg_ui.data.card.CardModel;
import com.theory.emhwang.ssg_ui.data.card.source.CardRepository;
import com.theory.emhwang.ssg_ui.data.card.source.ICardSource;
import com.theory.emhwang.ssg_ui.listener.IItemClickListener;

public class MainPresenter implements MainContract.Presenter {

    // View 객체
    private MainContract.View mView;

    // 카드 RecyclerView Contract
    private CardAdapterContract.View mRvCardView;

    private CardAdapterContract.Model mRvCardModel;

    // 카드 Data Repository
    private CardRepository mCardDataRepository;

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

    @Override
    public void setCardDataRepository(final CardRepository repository) {
        this.mCardDataRepository = repository;
    }

    /**
     * 카드 RecyclerView 데이터 셋팅하기
     */
    @Override
    public void loadCardDataList(final boolean isClear) {
        mCardDataRepository.getCardData(new ICardSource.LoadCardDataCallback() {

            @Override
            public void onDataListLoaded(final List<CardModel> list) {
                if (list != null) {
                    if (isClear) {
                        mRvCardModel.clearAll();
                    }
                    mRvCardModel.addItems(list);
                    mRvCardView.notifyDataAdapter();
                }
            }

            @Override
            public void onFailLoaded(final int code, final String message) {
                mView.showToast(message);
            }
        });
    }

    /**
     * (임시로) 카드 데이터 생성하기
     */
    public void makeTempCardData() {
        final DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference("cardData");
        mRootRef.setValue("Card Data");
    }

    // 카드 RecyclerView 클릭 리스너
    private IItemClickListener mCardClickListener = new IItemClickListener() {

        @Override
        public void onItemClick(final int index) {
            final CardModel model = mRvCardModel.getItems(index);
            mView.showToast(model.getName());
        }
    };

}
