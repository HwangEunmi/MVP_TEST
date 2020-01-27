package com.theory.emhwang.ssg_ui.data.card;

import com.theory.emhwang.ssg_ui.base.BaseResponseModel;

// Github 조회 API에 Key값 맞추기
public class CardModel extends BaseResponseModel {

    private String name;

    private String full_name;

    public CardModel(final String name) {
        this.name = name;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public String getFull_name() {
        return full_name == null ? "" : full_name;
    }

    public void setFull_name(final String full_name) {
        this.full_name = full_name;
    }

}
