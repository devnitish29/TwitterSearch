package com.vogo.assignment.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.vogo.assignment.model.tweetResponse.Status;

/**
 * Created by Nitish Singh on 16/04/19.
 */
public class TweetDataSourceFactory extends DataSource.Factory<String, Status> {

    //creating the mutable live data
    private MutableLiveData<TweetDataSource> tweetLiveDataSource = new MutableLiveData<TweetDataSource>();
    MutableLiveData<String> searchQuery = new MutableLiveData<String>();
    public TweetDataSourceFactory(MutableLiveData<String> query) {
        searchQuery = query;
    }


    @Override
    public DataSource<String, Status> create() {
        //getting our data source object
        TweetDataSource itemDataSource = new TweetDataSource(searchQuery);

        //posting the datasource to get the values

        tweetLiveDataSource.postValue(itemDataSource);

        //returning the datasource
        return itemDataSource;
    }


    //getter for itemlivedatasource
    public MutableLiveData<TweetDataSource> getItemLiveDataSource() {
        return tweetLiveDataSource;
    }
}
