package com.lngmnt.longmontobserver.model.wordpress.author;

import com.google.gson.annotations.SerializedName;

/**
 * Created by @sangeles on 12/23/17.
 */

public class Author {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("avatar_urls")
    private AuthorAvatar authorAvatarUrls;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AuthorAvatar getAuthorAvatarUrls() {
        return authorAvatarUrls;
    }
}
