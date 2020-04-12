package com.theory.emhwang.ssg_ui.view.webview.presenter;

import android.content.Context;
import android.net.http.SslError;

import com.theory.emhwang.ssg_ui.R;
import com.theory.emhwang.ssg_ui.listener.IJavaScriptListener;
import com.theory.emhwang.ssg_ui.view.webview.CustomBridge;
import com.theory.emhwang.ssg_ui.view.webview.CustomWebChromeClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HwangEunmi
 * @version v1.0.0
 * @file
 * @brief WebView Presenter
 * @details
 * @date 2020.04.12
 */
public class WebViewPresenter implements WebViewContract.Presenter, IJavaScriptListener {

    // View 객체
    private WebViewContract.View mView;

    private CustomBridge mBridge;

    private CustomWebChromeClient mWebChromeClient;


    @Override
    public void attachView(final WebViewContract.View view) {
        this.mView = view;
        mBridge = new CustomBridge();
        mBridge.setIJavaScriptListener(this);
    }

    @Override
    public void detatchView() {
        this.mView = null;
        mBridge = null;
        mBridge.setIJavaScriptListener(null);
    }

    /**
     * WebView Header 만들기
     */
    @Override
    public Map<String, String> makeHeader() {
        final Map<String, String> header = new HashMap<>();
        // header.put()
        return header;
    }

    @Override
    public CustomBridge getCustomBridge() {
        return mBridge;
    }

    @Override
    public CustomWebChromeClient getCustomWebChromeClienet(final Context context) {
        if (mWebChromeClient == null) {
            mWebChromeClient = new CustomWebChromeClient(context);
        }
        return mWebChromeClient;
    }

    @Override
    public void requestPayment(final String code, final String message, final String orderNo) {
        mView.requestPayment(code, message, orderNo);
    }


    /**
     * SSL 인증서 관련 팝업 문구 리턴하기
     */
    @Override
    public String getSslErrorMessage(final Context context, final int errorType) {
        final StringBuilder builder = new StringBuilder();
        switch (errorType) {
            case SslError.SSL_EXPIRED:
            case SslError.SSL_IDMISMATCH:
            case SslError.SSL_NOTYETVALID:
            case SslError.SSL_UNTRUSTED:
                builder.append(context.getString(R.string.webview_ssl_message_01));
                break;
            default:
                builder.append(context.getString(R.string.webview_ssl_message_02));
                break;
        }
        builder.append(context.getString(R.string.webview_ssl_message_03));

        return builder.toString();
    }

}
