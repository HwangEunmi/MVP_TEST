package com.theory.emhwang.ssg_ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.theory.emhwang.ssg_ui.R;
import com.theory.emhwang.ssg_ui.SsgApplication;
import com.theory.emhwang.ssg_ui.listener.IDialogButtonListener;

/**
 * @author HwangEunmi
 * @version v1.0.0
 * @file
 * @brief 공통 팝업
 * @details
 * @date 2020.04.12
 */
public class CommonDialog extends Dialog implements View.OnClickListener {

    private TextView mTvTitle;
    private String mTitle;

    private TextView mTvContent;
    private String mContent;

    private Button mBtnLeft;
    private String mLeft;

    private Button mBtnRight;
    private String mRight;

    private IDialogButtonListener mClickListener;


    public CommonDialog(final Context context) {
        super(context);
        if (context instanceof Activity) {
            setOwnerActivity((Activity) context);
        }
    }

    public CommonDialog(final Context context, final String title, final String content, final String btnLeft, final String btnRight, final IDialogButtonListener listener) {
        this(context);
        this.mTitle = title;
        this.mContent = content;
        this.mLeft = btnLeft;
        this.mRight = btnRight;
        this.mClickListener = listener;
    }

    @Override
    public void show() {
        if (!SsgApplication.getInstance().isActivityForeground()) {
            SsgApplication.getInstance().savePreparingDialog(this);
        } else {
            super.show();
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingDialog();

        setContentView(R.layout.layout_dialog_custom);
        initView();
        setView();
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            // 왼쪽 버튼
            case R.id.btn_left:
                if (mClickListener != null) {
                    mClickListener.onLeftClick();
                }
                dismiss();
                break;

            // 오른쪽 버튼
            case R.id.btn_right:
                if (mClickListener != null) {
                    mClickListener.onRightClick();
                }
                dismiss();
                break;

            default:
                break;
        }
    }

    /**
     * Dialog Setting 설정하기
     */
    private void settingDialog() {
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.gravity = Gravity.CENTER;
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        params.dimAmount = 0.4f; // 투명도 0~1
        if (getWindow() != null) {
            getWindow().setAttributes(params);
        }

        setCancelable(false); // BackKey로 닫기
        setCanceledOnTouchOutside(false); // 바깥영역 터치시, 닫기
    }

    /**
     * 뷰 초기화하기
     */
    private void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mTvContent = findViewById(R.id.tv_content);
        mBtnLeft = findViewById(R.id.btn_left);
        mBtnRight = findViewById(R.id.btn_right);

        mBtnLeft.setOnClickListener(this);
        mBtnRight.setOnClickListener(this);
    }

    /**
     * 뷰 셋팅하기
     */
    private void setView() {
        // Title 없는 경우 뷰 숨기기
        if (TextUtils.isEmpty(mTitle)) {
            mTvTitle.setVisibility(View.GONE);
        }

        // 우측버튼 문구 없는 경우 뷰 숨기기 (원버튼)
        if (TextUtils.isEmpty(mRight)) {
            mBtnRight.setVisibility(View.GONE);
        }

        mTvContent.setText(mContent == null ? "" : mContent);
        mBtnLeft.setText(mLeft == null ? "" : mLeft);
        mBtnRight.setText(mRight == null ? "" : mRight);
    }

}
