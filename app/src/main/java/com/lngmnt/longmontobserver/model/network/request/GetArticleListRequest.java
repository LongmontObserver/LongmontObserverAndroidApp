package com.lngmnt.longmontobserver.model.network.request;

import com.lngmnt.longmontobserver.model.network.service.LOWordpressApi;
import com.lngmnt.longmontobserver.model.network.service.LOWordpressService;
import com.lngmnt.longmontobserver.model.wordpress.ArticleList;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.net.Inet4Address;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by @sangeles on 12/29/17.
 */

public class GetArticleListRequest extends NetworkRequest {

    private Observer<List<ArticleList>> mArticleListObserver;

    private Integer mPageNumber;
    private Integer mArticlesPerPage;
    private Integer mOffsetNumber;


    public GetArticleListRequest(Observer<List<ArticleList>> articleListObserver, Integer pageNumber, Integer articlesPerPage, Integer offsetNumber) {
        this.mPageNumber = pageNumber;
        this.mArticlesPerPage = articlesPerPage;
        this.mOffsetNumber = offsetNumber;

        this.mArticleListObserver = articleListObserver;
    }

    @Override
    public void makeNetworkRequest() {
        super.makeNetworkRequest();

        // Create the WordPress API
        LOWordpressApi wordpressApi = getRetrofit().create(LOWordpressApi.class);


        // Make the API call
        wordpressApi
                .getArticleList(mPageNumber,mArticlesPerPage,mOffsetNumber)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mArticleListObserver);

    }
}
