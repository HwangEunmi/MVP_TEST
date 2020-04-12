package com.theory.emhwang.ssg_ui.common.util;

import android.app.Activity;
import android.content.Context;

import com.theory.emhwang.ssg_ui.R;
import com.theory.emhwang.ssg_ui.dialog.CommonDialog;
import com.theory.emhwang.ssg_ui.listener.IDialogButtonListener;

/**
 * @author HwangEunmi
 * @version v1.0.0
 * @file
 * @brief Dialog 공통 유틸
 * @details
 * @date 2020.04.12
 */
public class DialogUtil {

    /**
     * 확인 원버튼 팝업 호출하기
     */
    public static void showConfirmDialog(final Context context, final String message, final IDialogButtonListener listener) {
        if (context == null) {
            return;
        }

        final CommonDialog dialog = new CommonDialog(context, null, message, context.getString(R.string.common_btn_confirm), null, listener);
        if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed()) {
            dialog.show();
        }
    }

    /**
     * 투버튼 팝업 호출하기
     */
    public static void showTwoButtonDialog(final Context context, final String message, final String btnLeft, final String btnRight, final IDialogButtonListener listener) {
        if (context == null) {
            return;
        }

        final CommonDialog dialog = new CommonDialog(context, null, message, btnLeft, btnRight, listener);
        if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed()) {
            dialog.show();
        }
    }

}
