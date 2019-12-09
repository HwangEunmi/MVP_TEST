package com.theory.emhwang.ssg_ui;

import android.app.Application;

import com.google.gson.Gson;

public class SsgApplication extends Application {

    /**
     * Gson 객체
     */
    private Gson mGson;

    private SsgApplication() {
    }

    private static class SsgApplicationHolder {

        private static final SsgApplication instance = new SsgApplication();
    }

    public static SsgApplication getInstance() {
        return SsgApplicationHolder.instance;
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

}
