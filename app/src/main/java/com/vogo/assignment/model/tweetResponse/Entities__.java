
package com.vogo.assignment.model.tweetResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entities__ {

    @SerializedName("hashtags")
    @Expose
    private List<Hashtag_> hashtags = null;
    @SerializedName("symbols")
    @Expose
    private List<Object> symbols = null;
    @SerializedName("user_mentions")
    @Expose
    private List<Object> userMentions = null;
    @SerializedName("urls")
    @Expose
    private List<Url> urls = null;

    public List<Hashtag_> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag_> hashtags) {
        this.hashtags = hashtags;
    }

    public List<Object> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Object> symbols) {
        this.symbols = symbols;
    }

    public List<Object> getUserMentions() {
        return userMentions;
    }

    public void setUserMentions(List<Object> userMentions) {
        this.userMentions = userMentions;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

}
