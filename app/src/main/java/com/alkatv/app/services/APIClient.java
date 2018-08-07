package com.alkatv.app.services;

import com.alkatv.app.utils.ResourceURIs;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static Retrofit retrofit = null;

    public static final String TAG = APIClient.class.getName();

    public static Retrofit getClient(){

        if(retrofit == null){
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addNetworkInterceptor(httpLoggingInterceptor);
            httpClient.connectTimeout(10, TimeUnit.SECONDS);

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(ResourceURIs.HOST_URL)
                    .addConverterFactory(GsonConverterFactory.create());

            retrofit = builder
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

}
