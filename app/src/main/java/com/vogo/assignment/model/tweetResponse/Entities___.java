
package com.vogo.assignment.model.tweetResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entities___ {

    @SerializedName("url")
    @Expose
    private Url_ url;
    @SerializedName("description")
    @Expose
    private Description_ description;

    public Url_ getUrl() {
        return url;
    }

    public void setUrl(Url_ url) {
        this.url = url;
    }

    public Description_ getDescription() {
        return description;
    }

    public void setDescription(Description_ description) {
        this.description = description;
    }

}
