package com.lngmnt.longmontobserver.model.wordpress.media;

import com.google.gson.annotations.SerializedName;

/**
 * Created by @sangeles on 12/23/17.
 */

public class Caption {

    @SerializedName("rendered")
    private String caption;

    public String getCaption() {
        return caption;
    }
}
