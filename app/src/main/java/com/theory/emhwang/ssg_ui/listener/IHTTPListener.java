package com.theory.emhwang.ssg_ui.listener;

import com.theory.emhwang.ssg_ui.base.BaseResponseModel;

public interface IHTTPListener {

    void onSuccess(final BaseResponseModel model);

    void onFail(final int code, final String message);
}
