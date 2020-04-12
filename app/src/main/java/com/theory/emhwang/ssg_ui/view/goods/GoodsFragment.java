package com.theory.emhwang.ssg_ui.view.goods;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.theory.emhwang.ssg_ui.R;
import com.theory.emhwang.ssg_ui.data.goods.source.GoodsRepository;
import com.theory.emhwang.ssg_ui.view.goods.presenter.GoodsContract;
import com.theory.emhwang.ssg_ui.view.goods.presenter.GoodsPresenter;

public class GoodsFragment extends Fragment implements GoodsContract.View {

    private Context mContext;

    private ImageView mIvView;

    private GoodsPresenter mPresenter;

    public static GoodsFragment newInstance() {
        final GoodsFragment fragment = new GoodsFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull final Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onAttach(@NonNull final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onDetach() {
        mContext = null;
        mPresenter.detatchView();
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new GoodsPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.view_fragment_goods, container, false);
        initView(view);
        mPresenter.attachView(this);
        mPresenter.setGoodsDataRepository(GoodsRepository.getInstance());
        mPresenter.loadGoodsData();
        return view;
    }

    /**
     * 뷰 초기화하기
     */
    private void initView(final View view) {
        mIvView = view.findViewById(R.id.iv_view);
    }

    ////////////////////////////

    @Override
    public void drawImage(final String url) {
        Glide.with(mContext)
                .load(url).into(mIvView);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

}
