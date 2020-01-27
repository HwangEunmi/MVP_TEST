package com.theory.emhwang.ssg_ui;

import android.app.Application;

import com.google.gson.Gson;
import com.squareup.leakcanary.LeakCanary;

public class SsgApplication extends Application {

    /**
     * Gson 객체
     */
    private static Gson mGson;

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO : https://happydev.kr/17
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    /**
     * Gson 리턴하기
     */
    public static Gson getGson() {
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson;
    }

}
