package com.theory.emhwang.ssg_ui.base;

import java.util.List;

// Data 가져오는 Callback Listener
public interface IBaseLoadDataCallback<T> {

    void onDataListLoaded(final List<T> list);

    void onFailLoaded(final int code, final String message);

}
