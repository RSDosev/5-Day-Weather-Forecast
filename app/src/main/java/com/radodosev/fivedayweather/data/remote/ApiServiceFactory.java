package com.radodosev.fivedayweather.data.remote;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.radodosev.fivedayweather.BuildConfig;
import com.radodosev.fivedayweather.util.NetworkUtil;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rado on 10/6/2016.
 */

public final class ApiServiceFactory {
    private final Retrofit retrofit;

    public ApiServiceFactory() {
        retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildHttpClient())
                .build();
    }


    public APIService createApiService() {
        return retrofit.create(APIService.class);
    }

    @NonNull
    public OkHttpClient buildHttpClient() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(BuildConfig.API_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.readTimeout(BuildConfig.API_READ_TIMEOUT, TimeUnit.SECONDS);
        httpClient.addInterceptor(createLoggingInterceptor());
        httpClient.addInterceptor(createNoNetworkInterceptor());

        return httpClient.build();
    }

    private Interceptor createLoggingInterceptor() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }

    private static Interceptor createNoNetworkInterceptor() {
        return chain -> {
            if (!NetworkUtil.isNetworkConnected()) {
                throw new NoNetworkException();
            }
            return chain.proceed(chain.request());
        };
    }

    public static class NoNetworkException extends IOException {
    }
}
