package com.theory.emhwang.ssg_ui.view.webview;

import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.theory.emhwang.ssg_ui.R;
import com.theory.emhwang.ssg_ui.common.SsgConstant;
import com.theory.emhwang.ssg_ui.common.util.DialogUtil;
import com.theory.emhwang.ssg_ui.listener.IDialogButtonListener;
import com.theory.emhwang.ssg_ui.view.webview.presenter.WebViewContract;
import com.theory.emhwang.ssg_ui.view.webview.presenter.WebViewPresenter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author HwangEunmi
 * @version v1.0.0
 * @file
 * @brief WebView 화면
 * @details
 * @date 2020.04.12
 */
public class WebViewActivity extends AppCompatActivity implements WebViewContract.View {

    private WebView mWvView;

    private WebViewPresenter mPresenter;

    // Progress 팝업
    // private ProgressDialog mProgressDialog;

    /**
     * 요청할 URL
     */
    private String mUrl = "";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mPresenter = new WebViewPresenter();
        mPresenter.attachView(this);

        mUrl = "https://www.google.com";
        try {
            mUrl = URLDecoder.decode(mUrl, SsgConstant.ENCODING_UTF_8);
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        initView();
        settingWebView();

        mWvView.loadUrl(mUrl);
        // mWvView.loadUrl(mUrl, mPresenter.makeHeader());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWvView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWvView.onPause();
    }

    @Override
    public void onBackPressed() {
        // WebView Stack
        if (mWvView.canGoBack()) {
            mWvView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        // if (isEnabledProgressDialog() && mProgressDialog.isShowing()) {
        //     mProgressDialog.cancel();
        //     mProgressDialog = null;
        // }

        mWvView.clearCache(true);
        deleteDatabase("webview.db");
        deleteDatabase("webviewCache.db");
        mWvView.destroy();
        mPresenter.detatchView();

        super.onDestroy();
    }

    /**
     * 뷰 초기화하기
     */
    private void initView() {
        // mProgressDialog = new ProgressDialog(this);

        mWvView = findViewById(R.id.wv_view);
    }

    /**
     * WebView Setting 설정하기
     */
    private void settingWebView() {
        mWvView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                return true;
            }
        });
        mWvView.setLongClickable(false);
        mWvView.setHapticFeedbackEnabled(false);
        mWvView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        mWvView.getSettings().setJavaScriptEnabled(true);
        mWvView.getSettings().setLoadWithOverviewMode(true);
        mWvView.getSettings().setUseWideViewPort(true);
        mWvView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWvView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWvView.getSettings().setAppCacheEnabled(false);
        mWvView.getSettings().setSupportZoom(true);
        mWvView.getSettings().setBuiltInZoomControls(true);
        mWvView.getSettings().setDisplayZoomControls(false);
        mWvView.getSettings().setDomStorageEnabled(true);
        mWvView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWvView.setScrollbarFadingEnabled(true);
        mWvView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        mWvView.getSettings().setAllowFileAccess(false);

        // Debug용
        mWvView.setWebContentsDebuggingEnabled(true);

        mWvView.addJavascriptInterface(mPresenter.getCustomBridge(), "브릿지이름");
        mWvView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(final WebView view, final SslErrorHandler handler, final SslError error) {
                showSslErrorDialog(error.getPrimaryError(), handler);
            }
        });
        mWvView.setWebChromeClient(mPresenter.getCustomWebChromeClienet(this));
    }

    @Override
    public void requestPayment(final String code, final String message, final String orderNo) {
        // TODO : JavascriptInterface 작업후 View 작업하기
    }

    /**
     * SSL 인증서 관련 팝업 호출하기
     */
    private void showSslErrorDialog(final int errorType, final SslErrorHandler handler) {
        DialogUtil.showTwoButtonDialog(this,
                mPresenter.getSslErrorMessage(this, errorType),
                this.getString(R.string.common_btn_cancel),
                this.getString(R.string.common_btn_confirm),
                new IDialogButtonListener() {
                    @Override
                    public void onLeftClick() { // 취소
                        handler.cancel();
                        finish();
                    }

                    @Override
                    public void onRightClick() { // 확인
                        handler.proceed();
                    }
                });
    }

    /**
     * ProgressDialog 사용가능한 상태인지 여부 리턴하기
     */
    // private boolean isEnabledProgressDialog() {
    // return !isFinishing() && !isDestroyed() && mProgressDialog != null;
    // }

}
