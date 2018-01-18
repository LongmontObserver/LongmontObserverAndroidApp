package com.lngmnt.longmontobserver.model.wordpress;

import com.google.gson.annotations.SerializedName;
import com.lngmnt.longmontobserver.model.wordpress.author.Author;
import com.lngmnt.longmontobserver.model.wordpress.media.Media;

import java.util.List;

/**
 * Created by @sangeles on 12/24/17.
 */

public class Embedded {

    @SerializedName("author")
    private List<Author> authorList;

    @SerializedName("wp:featuredmedia")
    private List<Media> featuredMediaList;

    @SerializedName("wp:term")
    private List<List<Term>> embeddedTermList;

    public List<Author> getAuthorList() {
        return authorList;
    }

    public List<Media> getFeaturedMediaList() {
        return featuredMediaList;
    }

    public List<List<Term>> getEmbeddedTermList() {
        return embeddedTermList;
    }
}


