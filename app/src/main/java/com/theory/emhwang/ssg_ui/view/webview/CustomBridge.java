package com.theory.emhwang.ssg_ui.view.webview;

import android.webkit.JavascriptInterface;

import com.theory.emhwang.ssg_ui.listener.IJavaScriptListener;

/**
 * @author HwangEunmi
 * @version v1.0.0
 * @file
 * @brief JavascriptInterface 메소드 클래스
 * @details
 * @date 2020.04.12
 */
public class CustomBridge {

    private IJavaScriptListener mListener;

    public CustomBridge() {

    }

    /**
     * JavascriptInterface 메소드 Listener 셋팅하기
     */
    public void setIJavaScriptListener(final IJavaScriptListener listener) {
        this.mListener = listener;
    }

    // TODO : 여기에 @JavascriptInterface 메소드 만들기
    // Test용 ...
    @JavascriptInterface
    public void requestPayment(final String code, final String message, final String orderNo) {
        if (mListener != null) {
            mListener.requestPayment(code, message, orderNo);
        }
    }

}
