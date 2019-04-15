package com.vogo.assignment.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.vogo.assignment.model.tweetResponse.Status;
import com.vogo.assignment.repository.TweetDataSource;
import com.vogo.assignment.repository.TweetDataSourceFactory;

/**
 * Created by Nitish Singh on 16/04/19.
 */
public class TweetViewModel extends ViewModel {


    public LiveData<PagedList<Status>> itemPagedList;
    private MutableLiveData<TweetDataSource> liveDataSource;
    private TweetDataSourceFactory itemDataSourceFactory;
    private final MutableLiveData<String> searchQuery = new MutableLiveData<String>();
    private PagedList.Config pagedListConfig;

    //constructor
    public TweetViewModel() {

    }


    public void setSearchQuery(String query){
        searchQuery.setValue(query);
        //getting our data source factory
        itemDataSourceFactory = new TweetDataSourceFactory(searchQuery);

        //Getting PagedList config
        pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(20)
                        .build();

        //Building the paged list
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }

}
