package com.vogo.assignment.ui.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vogo.assignment.R;
import com.vogo.assignment.model.tweetResponse.Status;

import java.util.Objects;

/**
 * Created by Nitish Singh on 16/04/19.
 */
public class TweetListAdapter extends PagedListAdapter<Status, RecyclerView.ViewHolder> {


    public TweetListAdapter() {
        super(DIFF_CALLBACK);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View dataView = inflater.inflate(R.layout.list_item_tweet, parent, false);
        viewHolder = new TweetViewHolder(dataView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Status tweet =getItem(position);

        final  TweetViewHolder tweetViewHolder = (TweetViewHolder) holder;
        if (tweet != null) {

            tweetViewHolder.textName.setText(tweet.getUser().getName());
            tweetViewHolder.textTweet.setText(tweet.getText());
            if(tweet.getRetweetedStatus() != null){
                tweetViewHolder.textRetweetCount.setText(""+tweet.getRetweetedStatus().getRetweetCount());
                tweetViewHolder.textFavCount.setText(""+tweet.getRetweetedStatus().getFavoriteCount());
            }
            if(tweet.getUser() != null){
                if (tweet.getUser().isVerified()){
                    tweetViewHolder.imgVerified.setVisibility(View.VISIBLE);
                } else {
                    tweetViewHolder.imgVerified.setVisibility(View.GONE);
                }
            }
            Glide.with(holder.itemView.getContext())
                    .load(tweet.getUser().getProfileImageUrl())
                    .into(tweetViewHolder.imageView);
        }
    }












    private static DiffUtil.ItemCallback<Status> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Status>() {
                @Override
                public boolean areItemsTheSame(Status oldItem, Status newItem) {
                    return oldItem.getId()== newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(Status oldItem, Status newItem) {
                    return oldItem.equals(newItem);
                }
            };


    class NoDataViewHolder extends RecyclerView.ViewHolder {

        public NoDataViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    class TweetViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textTweet;
        TextView textReplyCount;
        TextView textRetweetCount;
        TextView textFavCount;
        ImageView imageView;
        ImageView imgVerified;

        public TweetViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textTweet = itemView.findViewById(R.id.textTweet);
            textReplyCount = itemView.findViewById(R.id.txtReplyCount);
            textRetweetCount = itemView.findViewById(R.id.txtRetweetCount);
            textFavCount = itemView.findViewById(R.id.txtFavCount);
            imageView = itemView.findViewById(R.id.imageView);
            imgVerified = itemView.findViewById(R.id.imgVerified);
        }
    }
}
