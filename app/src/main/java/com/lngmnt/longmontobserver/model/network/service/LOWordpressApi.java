package com.lngmnt.longmontobserver.model.network.service;

import android.content.Intent;

import com.lngmnt.longmontobserver.model.wordpress.ArticleList;
import com.lngmnt.longmontobserver.model.wordpress.author.Author;
import com.lngmnt.longmontobserver.model.wordpress.category.Category;
import com.lngmnt.longmontobserver.model.wordpress.media.Media;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

/**
 * Created by @sangeles on 12/22/17.
 */

public interface LOWordpressApi {

    /**
     *Standard API call without the embed query. Requires subsequent API calls to be made for certain
     * data (e.g. author name, featured image url, etc)
     *
     * @return
     */
    @GET("/wp-json/wp/v2/posts")
    public Observable<List<ArticleList>> getArticleList(
            @Query("page") Integer pageNumber,
            @Query("per_page") Integer articlesPerPage,
            @Query("offset") Integer offsetNumber);

    /**
     * This API calls appends "_embed" which consolidates the article list API call to not create
     * subsequent getAuthor and get Featured Image requests. It bundles the API call into one and
     * reduces the amount of requests we need to make.
     *
     * See: https://developer.wordpress.org/rest-api/using-the-rest-api/linking-and-embedding/
     *
     * @return
     */
    @GET("/wp-json/wp/v2/posts?_embed")
    public Single<List<ArticleList>> getArticleListEmbed(
            @Query("page") Integer pageNumber,
            @Query("per_page") Integer articlesPerPage,
            @Query("offset") Integer offsetNumber);

    @GET("/wp-json/wp/v2/users/{id}")
    public Observable<Author> getAuthorById(@Path("id") String authorId);

    @GET("/wp-json/wp/v2/media/{id}")
    public Observable<Media> getMediaById(@Path("id") String mediaId);

    @GET("/wp-json/wp/v2/categories/{id}")
    public Observable<Category> getCategoryById(@Path("id") String categoryId);

}
