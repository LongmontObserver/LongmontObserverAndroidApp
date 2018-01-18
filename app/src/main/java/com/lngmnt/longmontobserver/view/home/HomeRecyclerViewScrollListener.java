package com.lngmnt.longmontobserver.view.home;

import android.support.v7.widget.RecyclerView;

/**
 * Created by @sangeles on 12/29/17.
 */

public class HomeRecyclerViewScrollListener extends RecyclerView.OnScrollListener {


    public HomeRecyclerViewScrollListener() {
        super();
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);


        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();


    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }
}
