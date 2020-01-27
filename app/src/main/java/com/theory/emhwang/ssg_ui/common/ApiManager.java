package com.theory.emhwang.ssg_ui.common;

import java.io.IOException;
import java.lang.reflect.Type;

import com.theory.emhwang.ssg_ui.SsgApplication;
import com.theory.emhwang.ssg_ui.base.BaseResponseModel;
import com.theory.emhwang.ssg_ui.listener.IHTTPListener;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/*Retrofit을 이용하여 REST API 통신을 하는 클래스*/
public class ApiManager {

    // Retrofit Service
    private ApiService mApiService;

    private static class ApiManagerHolder {

        private static final ApiManager INSTANCE = new ApiManager();
    }

    public static ApiManager getInstance() {
        return ApiManagerHolder.INSTANCE;
    }

    private ApiManager() {
    }

    /**
     * ApiService 리턴하기
     * 
     * @return
     */
    public ApiService getApiService() {
        initSetting();
        return mApiService;
    }

    /**
     * Retrofit 초기 셋팅하기
     */
    private void initSetting() {
        final Retrofit.Builder retrofitBuilder =
                                               new Retrofit.Builder().baseUrl(SsgConstant.Url.BASE_URL)
                                                                     .addConverterFactory(ScalarsConverterFactory.create());

        // Request/Response Interceptor 추가
        final Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(final Chain chain) throws IOException {
                final Request request = chain.request();
                final Headers headers = new Headers.Builder().add(SsgConstant.Param.HEADER_TOKEN,
                                                                  SsgConstant.Param.SSG_TOKEN)
                                                             .build();

                final Request newRequest = request.newBuilder().headers(headers).build();
                // 새로운 Request로 Response 구하기
                Response response = chain.proceed(newRequest);
                return response;
            }
        };

        // 통신 로그 Interceptor 추가
        final HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {

            @Override
            public void log(final String message) {
                // Log.d("THEEND", "Retrofit's message: " + message);
            }
        });

        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        builder.interceptors().add(logInterceptor);

        final OkHttpClient client = builder.build();
        retrofitBuilder.client(client);

        final Retrofit retrofit = retrofitBuilder.build();
        mApiService = retrofit.create(ApiService.class);
    }

    /**
     * 서버 API 통신하기
     * 
     * @param type : 클래스 타입 (Ex. CardModel.class)
     * @param request : 요청 Request
     * @param listener : 통신 Listener
     */
    public void getApiResponse(final Type type, final Call<String> request, final IHTTPListener listener) {
        if (listener == null) {
            return;
        }
        // 비동기식으로 호출하기 (동기식은 execute)
        request.enqueue(new Callback<String>() {

            @Override
            public void onResponse(final Call<String> call, final retrofit2.Response<String> response) {
                if ((response.code() == 200 || response.code() == 400 || response.code() == 403)
                    && response.body() != null) {
                    final Object result = SsgApplication.getGson().fromJson(response.body().toString(),
                                                                                          type);
                    final BaseResponseModel model = (BaseResponseModel)result;
                    listener.onSuccess(model);
                } else {
                    listener.onFail(response.code(), response.message());
                }
            }

            @Override
            public void onFailure(final Call<String> call, final Throwable t) {
                listener.onFail(-1, t.getMessage());
            }
        });
    }

}