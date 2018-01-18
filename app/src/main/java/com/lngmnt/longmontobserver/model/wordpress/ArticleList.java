package com.lngmnt.longmontobserver.model.wordpress;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.lngmnt.longmontobserver.model.wordpress.author.Author;
import com.lngmnt.longmontobserver.model.wordpress.media.Media;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by @sangeles on 12/22/17.
 */

public class ArticleList {

    private static final String TAG = "ArticleList";

    public final static String ARTICLE_FORMAT_STANDARD = "standard";
    public final static String ARTICLE_FORMAT_VIDEO = "video";

    @SerializedName("id")
    private Integer id;

    @SerializedName("date")
    private String date;

    @SerializedName("author")
    private Integer authorId;

    @SerializedName("featured_media")
    private Integer featuredImageId;

    @SerializedName("title")
    private Title title;

    @SerializedName("_embedded")
    private Embedded embedded;

    @SerializedName("content")
    private Content content;

    @SerializedName("format")
    private String format;

    @SerializedName("link")
    private String link;

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public Integer getFeaturedImageId() {
        return featuredImageId;
    }

    public Title getTitle() {
        return title;
    }

    public Embedded getEmbedded() {
        return embedded;
    }

    public Content getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }

    /**
     * Util method to get the article title from the title object
     * @return
     */
    @Nullable
    public String getArticleTitle() {
        if (title != null) {
             return title.getRenderedTitle();
        }
        return null;
    }

    /**
     * Util method to get the featured image url from an article
     * @return
     */
    @Nullable
    public String getArticleFeaturedImageURL() {
        if (embedded != null) {
            List<Media> featuredMediaList = embedded.getFeaturedMediaList();
            if (featuredMediaList != null && !featuredMediaList.isEmpty()) {
                Media media = featuredMediaList.get(0);
                if (media != null) {
                    return media.getSourceUrl();
                }
            }
        }
        return null;
    }

    /**
     * Util method to get the author name for a specific article
     *
     * @return
     */
    @Nullable
    public String getArticleAuthor() {
        if (embedded != null) {
            List<Author> authorList = embedded.getAuthorList();
            if (authorList != null && !authorList.isEmpty()) {
                Author author = authorList.get(0);
                if (author != null) {
                    return author.getName();
                }
            }
        }
        return null;
    }

    /**
     * Util method to get the rendered content for an article
     * @return
     */
    @Nullable
    public String getArticleContent() {
        if (content != null) {
            return content.getRenderedContent();
        }
        return null;
    }

    public String getArticleCategory() {
        if (embedded != null) {
            List<List<Term>> termListList = embedded.getEmbeddedTermList();
            if (termListList != null && !termListList.isEmpty()) {

                for (List<Term> termLists : termListList) {
                    if (termLists != null && !termLists.isEmpty()) {
                        for (Term term : termLists) {

                            if (term != null) {
                                String termTaxonomy = term.getTaxonomy();

                                if (!TextUtils.isEmpty(termTaxonomy) && TextUtils.equals(termTaxonomy, Term.TAXONOMY_CATEGORY)) {
                                    Log.d(TAG, "getArticleCategory: term name: " + term.getName());
                                    return term.getName();
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public String getFeaturedImageUrl() {
        if (embedded != null) {
            List<Media> mediaList = embedded.getFeaturedMediaList();
            if (mediaList != null && !mediaList.isEmpty()) {
                //Only get the first one?
                Media media = mediaList.get(0);
                return media.getSourceUrl();
            }
        }
        return null;
    }

    public String getFormattedDate() {
        if (date != null && !TextUtils.isEmpty(date)) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy h:mm aaa", Locale.US);
            SimpleDateFormat wordpressDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);

            try {
                Date dateTest = wordpressDateFormat.parse(date);
                return dateFormat.format(dateTest);
            } catch (ParseException e) {
                Log.e("ArticleList", e.getMessage());
                return null;
            }
        }
        return null;
    }
}
