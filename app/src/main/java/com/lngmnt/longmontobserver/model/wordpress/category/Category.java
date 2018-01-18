package com.lngmnt.longmontobserver.model.wordpress.category;

import com.google.gson.annotations.SerializedName;

/**
 * Created by @sangeles on 12/24/17.
 */

public class Category {

    @SerializedName("id")
    private Integer id;

    @SerializedName("count")
    private Integer count;

    @SerializedName("name")
    private String name;

    public Integer getId() {
        return id;
    }

    public Integer getCount() {
        return count;
    }

    public String getName() {
        return name;
    }
}
