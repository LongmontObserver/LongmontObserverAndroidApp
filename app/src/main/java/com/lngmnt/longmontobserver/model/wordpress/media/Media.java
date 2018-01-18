package com.lngmnt.longmontobserver.model.wordpress.media;

import com.google.gson.annotations.SerializedName;
import com.lngmnt.longmontobserver.model.wordpress.Title;

/**
 * Created by @sangeles on 12/23/17.
 */

public class Media {

    @SerializedName("id")
    private Integer id;

    @SerializedName("date")
    private String date;

    @SerializedName("media_type")
    private String mediaType;

    @SerializedName("mime_type")
    private String mimeType;

    @SerializedName("source_url")
    private String sourceUrl;

    @SerializedName("title")
    private Title title;

    @SerializedName("caption")
    private Caption caption;

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public Title getTitle() {
        return title;
    }

    public Caption getCaption() {
        return caption;
    }
}
