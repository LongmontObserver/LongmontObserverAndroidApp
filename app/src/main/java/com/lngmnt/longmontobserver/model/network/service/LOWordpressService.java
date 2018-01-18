package com.lngmnt.longmontobserver.model.network.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by @sangeles on 12/22/17.
 */

public class LOWordpressService {


    private LOWordpressApi loWordpressApi;

    public LOWordpressService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://longmontobserver.org")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loWordpressApi = retrofit.create(LOWordpressApi.class);

    }

    public LOWordpressApi getLoWordpressApi() {
        return loWordpressApi;
    }

}
