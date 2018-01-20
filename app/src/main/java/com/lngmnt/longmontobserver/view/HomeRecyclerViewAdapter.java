package com.lngmnt.longmontobserver.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.lngmnt.longmontobserver.R;
import com.lngmnt.longmontobserver.databinding.HomeArticleItemLayoutBinding;
import com.lngmnt.longmontobserver.databinding.HomeArticleItemSponsorLayoutBinding;
import com.lngmnt.longmontobserver.model.wordpress.ArticleList;
import com.lngmnt.longmontobserver.view.home.HomeSponsorViewHolder;

import java.util.List;

/**
 * Created by @sangeles on 12/23/17.
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "HomeRecyclerViewAdapter";

    public static final int VIEW_TYPE_ARTICLE = 1;
    public static final int VIEW_TYPE_LOADING = 2;
    public static final int VIEW_TYPE_SPONSOR = 3;


    private List<ArticleList> mArticleList;
    private RequestManager glideRequestManager;

    public HomeRecyclerViewAdapter(RequestManager requestManager, List<ArticleList> articleList ) {
        this.mArticleList = articleList;
        this.glideRequestManager = requestManager;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case VIEW_TYPE_ARTICLE:
                // Use the data binding lib to inflate the layout
                HomeArticleItemLayoutBinding layoutBinding = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()), R.layout.home_article_item_layout,parent,false);

                viewHolder = new HomeArticleViewHolder(layoutBinding);

                break;

            case VIEW_TYPE_SPONSOR:

                HomeArticleItemSponsorLayoutBinding sponsorLayoutBinding = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),R.layout.home_article_item_sponsor_layout,parent,false);

                viewHolder = new HomeSponsorViewHolder(sponsorLayoutBinding);

                break;
        }

//        // Use the data binding lib to inflate the layout
//        HomeArticleItemLayoutBinding layoutBinding = DataBindingUtil.inflate(
//                LayoutInflater.from(parent.getContext()), R.layout.home_article_item_layout,parent,false);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        switch (getItemViewType(position)) {
            case VIEW_TYPE_ARTICLE:
                final ArticleList articleList = mArticleList.get(position);

                HomeArticleViewHolder articleViewHolder = (HomeArticleViewHolder)holder;
                articleViewHolder.populateHomeViewHolder(articleList,glideRequestManager);

                break;

            case VIEW_TYPE_SPONSOR:
                HomeSponsorViewHolder sponsorViewHolder = (HomeSponsorViewHolder)holder;
                sponsorViewHolder.populateSponsorViewHolder(glideRequestManager);

                break;
        }

//        // populate the home view holder
//        holder.populateHomeViewHolder(articleList, glideRequestManager);
//        switch (holder.getItemViewType()) {
//
//        }

    }

    @Override
    public int getItemCount() {

        if (mArticleList == null || mArticleList.isEmpty()) {
            return 0;
        }

        int articleSize = mArticleList.size();

        return articleSize + (articleSize / 5);

//        return mArticleList != null ? mArticleList.size() : 0;
    }


    public void refreshRecyclerViewData(List<ArticleList> updatedList) {
        this.mArticleList = updatedList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (position % 5 == 0) {
            return VIEW_TYPE_SPONSOR;
        } else {
            return VIEW_TYPE_ARTICLE;
        }

//        return VIEW_TYPE_ARTICLE;

//        return super.getItemViewType(position);
    }
}
