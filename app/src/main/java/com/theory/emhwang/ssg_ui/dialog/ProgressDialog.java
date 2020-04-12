package com.theory.emhwang.ssg_ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

import com.theory.emhwang.ssg_ui.R;
import com.theory.emhwang.ssg_ui.SsgApplication;

/**
 * @author HwangEunmi
 * @version v1.0.0
 * @file
 * @brief Progress 팝업
 * @details
 * @date 2020.04.12
 */
public class ProgressDialog extends Dialog {

    public ProgressDialog(final Context context) {
        super(context);
        if (context instanceof Activity) {
            setOwnerActivity((Activity) context);
        }
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

        setContentView(R.layout.layout_dialog_progress);
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
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        setCancelable(false); // BackKey로 닫기
        setCanceledOnTouchOutside(false); // 바깥영역 터치시, 닫기
    }

}
