package com.theory.emhwang.ssg_ui.view.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theory.emhwang.ssg_ui.R;
import com.theory.emhwang.ssg_ui.adapter.card.CardAdapter;
import com.theory.emhwang.ssg_ui.data.card.source.CardRepository;
import com.theory.emhwang.ssg_ui.view.goods.GoodsFragment;
import com.theory.emhwang.ssg_ui.view.main.presenter.MainContract;
import com.theory.emhwang.ssg_ui.view.main.presenter.MainPresenter;

// TODO : DataBinding
public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainContract.View {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference("cardData");

    // Presenter 객체
    private MainPresenter mPresenter;

    // Spinner(통합검색)
    private Spinner mSpView;

    // 검색창
    private EditText mEtSearch;

    // TabLayout
    private TabLayout mTbLayout;

    // 메인 이미지 ViewPager
    // private ViewPager mVpView;
    private FrameLayout mFlContents;

    // 카드 RecyclerView
    private RecyclerView mRvCardView;

    // 브랜드 RecyclerView
    private RecyclerView mRvBrandView;

    // 하단 큰 ImageView
    private ImageView mIvView;

    // Grid RecyclerView
    private RecyclerView mRvGridView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter();
        mPresenter.attachView(this);

        initView();
        initRvCardSetting();
        initGoodsSetting();

        mRootRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                Log.d("THEEND", "Text: " + text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("THEEND", "Exception: " + databaseError.toException());
            }
        });
    }

    @Override
    protected void onDestroy() {
        mPresenter.detatchView();

        super.onDestroy();
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.iv_search:
                break;
            default:
                break;
        }
    }

    /**
     * 뷰 초기화하기
     */
    private void initView() {
        final Toolbar tbBar = findViewById(R.id.tb_bar);
        setSupportActionBar(tbBar);

        mSpView = findViewById(R.id.sp_view);
        mEtSearch = findViewById(R.id.et_search);
        mTbLayout = findViewById(R.id.tb_layout);
        // mVpView = findViewById(R.id.vp_view);
        mFlContents = findViewById(R.id.contents);
        mRvCardView = findViewById(R.id.rv_card_view);
        mRvBrandView = findViewById(R.id.rv_brand_view);
        mIvView = findViewById(R.id.iv_view);
        mRvGridView = findViewById(R.id.rv_grid_view);

        findViewById(R.id.iv_search).setOnClickListener(this);
    }

    /**
     * 카드 RecyclerView 초기 셋팅하기
     */
    private void initRvCardSetting() {
        final CardAdapter adapter = new CardAdapter();
        mRvCardView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRvCardView.setAdapter(adapter);

        mPresenter.setCardAdapterViewAndModel(adapter, adapter);
        mPresenter.setCardDataRepository(CardRepository.getInstance());
        mPresenter.makeTempCardData();
        mPresenter.loadCardDataList(true);
    }

    /**
     * 상품 Fragment 초기 셋팅하기
     */
    private void initGoodsSetting() {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contents, GoodsFragment.newInstance());
        transaction.commit();
    }

    //////////////////////////////
    @Override
    public void showToast(final String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
