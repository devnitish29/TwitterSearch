package com.vogo.assignment.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vogo.assignment.AppApplication;
import com.vogo.assignment.utility.AppConstants;
import com.vogo.assignment.model.tweetResponse.SearchResponse;
import com.vogo.assignment.model.tweetResponse.Status;
import com.vogo.assignment.network.RetrofitClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * Created by Nitish Singh on 16/04/19.
 */
public class TweetDataSource extends PageKeyedDataSource<String,Status> {


    public static final int PAGE_SIZE = 10;
    private String TAG = AppApplication.class.getName();
    String simpleQuery="";
    String queryWithHashTag="";
    String encodeQuery="";
    public TweetDataSource(MutableLiveData<String> searchQuery) {

        if (searchQuery.getValue() != null){

            simpleQuery = searchQuery.getValue()!= null?searchQuery.getValue():"";
            queryWithHashTag = "#"+ simpleQuery;

            try {
                encodeQuery = URLEncoder.encode(queryWithHashTag,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

    }





    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, Status> callback) {

        RetrofitClient.getInstance(false).getApi().searchTweetsByHashTag(encodeQuery, AppConstants.RECENT,AppConstants.PAGE_COUNT,"").enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.body() != null) {


                    callback.onResult(response.body().getStatuses(), null,response.body().getSearchMetadata().getNextResults());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });


    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Status> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Status> callback) {
        RetrofitClient.getInstance(false).getApi().searchTweetsByHashTag(encodeQuery,AppConstants.RECENT,AppConstants.PAGE_COUNT,params.key).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.body() != null) {

                    callback.onResult(response.body().getStatuses(), response.body().getSearchMetadata().getNextResults());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }






}
