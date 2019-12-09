package com.theory.emhwang.ssg_ui.common;

import retrofit2.Call;
import retrofit2.http.GET;

/*HTTP 통신을 하기 위한 Retrofit 서비스 인터페이스 생성*/
public interface ApiService {

    // 해당 Repository의 정보 가져오기
    @GET(SsgConstant.Url.GET_REPO_INFO)
    Call<String> getRepositoryInfo();
}
