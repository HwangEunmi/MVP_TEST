package com.theory.emhwang.ssg_ui;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.os.Bundle;

import com.google.gson.Gson;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;

public class SsgApplication extends Application {

    private static SsgApplication mSsgApplication;

    private static Gson mGson;

    /**
     * 호출 준비중인 Dialog List
     */
    private List<Dialog> mPreparingDialogList;

    /**
     * Activity가 Foreground 상태인지에 대한 플래그
     */
    private boolean mIsActivityForeground;


    @Override
    public void onCreate() {
        super.onCreate();
        mSsgApplication = this;
        mPreparingDialogList = new ArrayList<>();

        // TODO : https://happydev.kr/17
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    public static SsgApplication getInstance() {
        return mSsgApplication;
    }

    /**
     * Gson 리턴하기
     */
    public Gson getGson() {
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson;
    }

    /**
     * 앱의 Foreground/Background 상태 여부 리턴하기
     */
    public boolean isActivityForeground() {
        return mIsActivityForeground;
    }

    /**
     * 앱이 Foreground 상태로 전환된 경우,
     * 호출되지 못했던 Dialog 들을 show() 해주기
     */
    private void showPreparingDialog() {
        for (final Dialog dialog : mPreparingDialogList) {
            final Activity activity = dialog.getOwnerActivity();
            if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
                dialog.show();
            }
        }
        mPreparingDialogList.clear();
    }

    /**
     * 앱이 Background 상태로 전환된 경우,
     * Dialog 호출 시도 시 Dialog List에 저장하기
     */
    public void savePreparingDialog(final Dialog dialog) {
        mPreparingDialogList.add(dialog);
    }


    /**
     * Activity Life Cycle Callback Listener
     */
    final ActivityLifecycleCallbacks lifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(final Activity activity, final Bundle bundle) {

        }

        @Override
        public void onActivityStarted(final Activity activity) {

        }

        @Override
        public void onActivityResumed(final Activity activity) {
            mIsActivityForeground = true;

            // 호출할 팝업이 있는경우
            if (mPreparingDialogList.size() > 0) {
                showPreparingDialog();
            }
        }

        @Override
        public void onActivityPaused(final Activity activity) {
            mIsActivityForeground = false;
        }

        @Override
        public void onActivityStopped(final Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(final Activity activity) {

        }
    };

}
