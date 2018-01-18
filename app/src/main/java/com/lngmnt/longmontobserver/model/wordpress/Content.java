package com.lngmnt.longmontobserver.model.wordpress;

import com.google.gson.annotations.SerializedName;

/**
 * Created by @sangeles on 12/23/17.
 */

public class Content {

    @SerializedName("rendered")
    private String renderedContent;

    public String getRenderedContent() {
        return renderedContent;
    }
}
