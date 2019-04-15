package com.vogo.assignment.network;

import com.vogo.assignment.model.Authorization;
import com.vogo.assignment.model.tweetResponse.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Nitish Singh on 16/04/19.
 */

public interface Api {

    @POST("oauth2/token")
    Call<Authorization> getAuthToken(@Header("Authorization") String authKey, @Query("grant_type") String grant_type );


    @GET("1.1/search/tweets.json")
    Call<SearchResponse> searchTweetsByHashTag(@Query("q") String hashtag, @Query("result_type") String recent,@Query("count") String pageCount,@Query("cursor") String cursor);
}
