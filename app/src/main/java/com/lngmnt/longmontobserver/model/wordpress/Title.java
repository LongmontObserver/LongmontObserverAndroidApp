package com.lngmnt.longmontobserver.model.wordpress;

import com.google.gson.annotations.SerializedName;

/**
 * Created by @sangeles on 12/23/17.
 */

public class Title {

    @SerializedName("rendered")
    private String renderedTitle;

    public String getRenderedTitle() {
        return renderedTitle;
    }
}
