package com.theory.emhwang.ssg_ui.view.webview.presenter;

import android.content.Context;

import com.theory.emhwang.ssg_ui.view.webview.CustomBridge;
import com.theory.emhwang.ssg_ui.view.webview.CustomWebChromeClient;

import java.util.Map;

/**
 * @author HwangEunmi
 * @version v1.0.0
 * @file
 * @brief WebView Presenter 인터페이스
 * @details
 * @date 2020.04.12
 */
public interface WebViewContract {

    interface View {
        // TODO : JavascriptInterface 작업 끝나고, View 작업할 거 있으면 추가하기
        void requestPayment(final String code, final String message, final String orderNo);

    }

    interface Presenter {

        void attachView(final View view);

        void detatchView();

        Map<String, String> makeHeader();

        CustomBridge getCustomBridge();

        CustomWebChromeClient getCustomWebChromeClienet(final Context context);

        String getSslErrorMessage(final Context context, final int errorType);

    }

}
