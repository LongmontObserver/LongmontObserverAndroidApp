package com.lngmnt.longmontobserver.model.network.request;

import com.lngmnt.longmontobserver.model.app.LongmontObserverConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by @sangeles on 12/29/17.
 */

public abstract class NetworkRequest {

    private Retrofit mRetroFit;


    public NetworkRequest() {
    }

    public void makeNetworkRequest() {
        mRetroFit = new Retrofit.Builder()
                .baseUrl(LongmontObserverConstants.LONGMONT_OBSERVER_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    protected Retrofit getRetrofit() {
        return mRetroFit;
    }

}
