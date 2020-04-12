package com.theory.emhwang.ssg_ui.data.goods.source;

public class GoodsRepository implements IGoodsSource {

    private GoodsRemoteSource mRemoteSource;

    private static class GoodsRepositoryHolder {
        public static final GoodsRepository INSTANCE = new GoodsRepository();
    }

    public static GoodsRepository getInstance() {
        return GoodsRepositoryHolder.INSTANCE;
    }

    private GoodsRepository() {
        mRemoteSource = new GoodsRemoteSource();
    }

    /**
     * (Remote) Goods Data를 가져온다.
     */
    @Override
    public void getGoodsData(final LoadGoodsDataCallback listener) {
        mRemoteSource.getGoodsData(listener);
    }

}
