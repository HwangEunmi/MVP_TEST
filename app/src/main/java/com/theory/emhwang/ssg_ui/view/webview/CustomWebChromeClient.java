package com.theory.emhwang.ssg_ui.view.webview;

import android.content.Context;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.theory.emhwang.ssg_ui.R;
import com.theory.emhwang.ssg_ui.common.util.DialogUtil;
import com.theory.emhwang.ssg_ui.listener.IDialogButtonListener;

/**
 * @author HwangEunmi
 * @version v1.0.0
 * @file
 * @brief Custom WebChromeClient
 * @details
 * @date 2020.04.12
 */
public class CustomWebChromeClient extends WebChromeClient {

    private Context mContext;

    public CustomWebChromeClient(final Context context) {
        super();
        this.mContext = context;
    }

    // Debug 용
    @Override
    public boolean onConsoleMessage(final ConsoleMessage cm) {
        Log.d("THEEND", "WebView Console Message: " + cm.message());
        return super.onConsoleMessage(cm);
    }

    // TODO : ActionBar의 타이틀 우리쪽에서 셋팅할 경우 만들기
    @Override
    public void onReceivedTitle(final WebView view, final String title) {
        super.onReceivedTitle(view, title);
    }

    @Override
    public boolean onJsAlert(final WebView view, final String url, final String message, final JsResult result) {
        DialogUtil.showConfirmDialog(mContext, message, new IDialogButtonListener() {
            @Override
            public void onLeftClick() {
                result.cancel();
            }
        });
        return true;
    }

    @Override
    public boolean onJsConfirm(final WebView view, final String url, final String message, final JsResult result) {
        DialogUtil.showTwoButtonDialog(mContext, message, mContext.getString(R.string.common_btn_cancel), mContext.getString(R.string.common_btn_confirm), new IDialogButtonListener() {
            @Override
            public void onLeftClick() {
                result.cancel();
            }

            @Override
            public void onRightClick() {
                result.confirm();
            }
        });
        return true;
    }

}
