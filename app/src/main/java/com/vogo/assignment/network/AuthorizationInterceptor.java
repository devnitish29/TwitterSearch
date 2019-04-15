package com.vogo.assignment.network;

import android.util.Base64;

import com.vogo.assignment.utility.AppConstants;
import com.vogo.assignment.utility.Utils;
import com.vogo.assignment.model.Authorization;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Nitish Singh on 16/04/19.
 */
public class AuthorizationInterceptor implements Interceptor {

    private RetrofitClient apiService;


    public AuthorizationInterceptor(RetrofitClient apiService) {
        this.apiService = apiService;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response mainResponse = chain.proceed(chain.request());
        Request mainRequest = chain.request();
        String authKey = getAuthorizationHeader();
        retrofit2.Response<Authorization> oAuthResponse = apiService.getApi().getAuthToken(authKey,AppConstants.GRANT_TYPE).execute();
        if(oAuthResponse.isSuccessful()){
            Authorization authorization = oAuthResponse.body();
            assert authorization != null;
            Utils.saveAuthToken(authorization.getAccessToken());
            Request.Builder builder = mainRequest.newBuilder().header(AppConstants.AUTHORIZATION, AppConstants.Bearer2+authorization.getAccessToken()).
                    method(mainRequest.method(), mainRequest.body());
            mainResponse = chain.proceed(builder.build());
        }
        return mainResponse;
    }




    public static String getAuthorizationHeader() {
        String credential = AppConstants.CONSUMER_API_KEY + ":" + AppConstants.CONSUMER_SECRET_API_KEY;
        return "Basic " + Base64.encodeToString(credential.getBytes(), Base64.NO_WRAP);
    }
}
