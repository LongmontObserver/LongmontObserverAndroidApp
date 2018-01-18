package com.lngmnt.longmontobserver.model.wordpress;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by @sangeles on 12/30/17.
 */

public class TermList {

    @SerializedName("")
    private List<Term> termList;

    public List<Term> getTermList() {
        return termList;
    }
}
