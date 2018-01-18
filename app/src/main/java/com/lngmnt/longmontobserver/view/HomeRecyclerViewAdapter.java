package com.lngmnt.longmontobserver.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.lngmnt.longmontobserver.R;
import com.lngmnt.longmontobserver.databinding.HomeArticleItemLayoutBinding;
import com.lngmnt.longmontobserver.model.wordpress.ArticleList;

import java.util.List;

/**
 * Created by @sangeles on 12/23/17.
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeArticleViewHolder> {

    private static final String TAG = "HomeRecyclerViewAdapter";

    public static final int VIEW_TYPE_ARTICLE = 1;
    public static final int VIEW_TYPE_LOADING = 2;

    private List<ArticleList> mArticleList;
    private RequestManager glideRequestManager;

    public HomeRecyclerViewAdapter(RequestManager requestManager, List<ArticleList> articleList ) {
        this.mArticleList = articleList;
        this.glideRequestManager = requestManager;

    }

    @Override
    public HomeArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Use the data binding lib to inflate the layout
        HomeArticleItemLayoutBinding layoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.home_article_item_layout,parent,false);

        return new HomeArticleViewHolder(layoutBinding);
    }

    @Override
    public void onBindViewHolder(HomeArticleViewHolder holder, int position) {
        final ArticleList articleList = mArticleList.get(position);

        // populate the home view holder
        holder.populateHomeViewHolder(articleList, glideRequestManager);
        switch (holder.getItemViewType()) {

        }

    }

    @Override
    public int getItemCount() {
        return mArticleList != null ? mArticleList.size() : 0;
    }


    public void refreshRecyclerViewData(List<ArticleList> updatedList) {
        this.mArticleList = updatedList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {


        return super.getItemViewType(position);
    }
}
