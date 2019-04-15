package com.vogo.assignment.network;

import com.vogo.assignment.utility.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nitish Singh on 16/04/19.
 */
public class RetrofitClient {


    private static RetrofitClient mInstance;
    private Retrofit retrofit, simpleRetrofit;


    private RetrofitClient(boolean firstLogIn) {
       if(firstLogIn){
           retrofit = new Retrofit.Builder()
                   .baseUrl(AppConstants.BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
       } else {
           retrofit = new Retrofit.Builder()
                   .baseUrl(AppConstants.BASE_URL)
                   .client(provideOkHttpClient())
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
       }
    }

    private OkHttpClient provideOkHttpClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.addInterceptor(new AuthorizationInterceptor(getInstance(true)));
        okhttpClientBuilder.addInterceptor(chain -> {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader(AppConstants.AUTHORIZATION, AppConstants.Bearer)
                    .build();
            return chain.proceed(newRequest);
        });
        okhttpClientBuilder.addInterceptor(logging);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        return okhttpClientBuilder.build();
    }

    public static synchronized RetrofitClient getInstance(boolean isFirstLogIn) {
        if (mInstance == null) {
            mInstance = new RetrofitClient(isFirstLogIn);
        }
        return mInstance;
    }


    public Api getApi() {
        return retrofit.create(Api.class);
    }
}
