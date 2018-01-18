package com.lngmnt.longmontobserver.model.wordpress.author;

import com.google.gson.annotations.SerializedName;

/**
 * Created by @sangeles on 12/23/17.
 */

public class AuthorAvatar {

    @SerializedName("24")
    private String avatarImageURLSize24;

    @SerializedName("48")
    private String avatarImageURLSize48;

    @SerializedName("96")
    private String avatarImageURLSize96;

    public String getAvatarImageURLSize24() {
        return avatarImageURLSize24;
    }

    public String getAvatarImageURLSize48() {
        return avatarImageURLSize48;
    }

    public String getAvatarImageURLSize96() {
        return avatarImageURLSize96;
    }
}
