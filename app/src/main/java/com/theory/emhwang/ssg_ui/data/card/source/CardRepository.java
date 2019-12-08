package com.theory.emhwang.ssg_ui.data.card.source;

// Memory Cache / Local/Remote DataSource
public class CardRepository implements ICardSource {

    private CardLocalDataSource mLocalDataSource;

    private CardRemoteSource mRemoteSource;

    private static class CardRepositoryHolder {

        public static final CardRepository INSTANCE = new CardRepository();
    }

    public static CardRepository getInstance() {
        return CardRepositoryHolder.INSTANCE;
    }

    private CardRepository() {
        mLocalDataSource = new CardLocalDataSource();
        mRemoteSource = new CardRemoteSource();
    }

    /**
     * (Remote) Card Data를 가져온다.
     */
    @Override
    public void getCardData(final LoadCardDataCallback listener) {
        if(listener != null) {
            mRemoteSource.getCardData(listener);
        }
    }

}
