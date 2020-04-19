package com.theory.emhwang.ssg_ui.data.goods;

import com.theory.emhwang.ssg_ui.base.BaseResponseModel;

public class GoodsModel extends BaseResponseModel {

    private Owner owner;

    public class Owner {
        private String avatar_url;

        public String getAvatar_url() {
            return avatar_url;
        }
    }

    public Owner getOwner() {
        return owner;
    }

}
