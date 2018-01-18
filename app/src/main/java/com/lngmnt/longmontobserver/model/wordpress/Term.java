package com.lngmnt.longmontobserver.model.wordpress;

import com.google.gson.annotations.SerializedName;

/**
 * Created by @sangeles on 12/30/17.
 */

public class Term {

    public static final String TAXONOMY_CATEGORY = "category";

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("taxonomy")
    private String taxonomy;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTaxonomy() {
        return taxonomy;
    }
}
