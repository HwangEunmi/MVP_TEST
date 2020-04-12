package com.theory.emhwang.ssg_ui.listener;

/**
 * @author HwangEunmi
 * @version v1.0.0
 * @file
 * @brief JavascriptInterface 메소드 Listener
 * @details
 * @date 2020.04.12
 */
public interface IJavaScriptListener {
    // TODO : @JavascriptInterface 메소드 모두 만들기

    void requestPayment(final String code, final String message, final String orderNo);

}
