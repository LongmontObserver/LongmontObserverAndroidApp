package com.lngmnt.longmontobserver.view.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lngmnt.longmontobserver.databinding.HomeArticleItemLoadingLayoutBinding;

/**
 * Created by @sangeles on 12/31/17.
 */

public class HomeLoadingViewHolder extends HomeViewHolder {

    private HomeArticleItemLoadingLayoutBinding loadingLayoutBinding;

    public HomeLoadingViewHolder(HomeArticleItemLoadingLayoutBinding binding) {
        super(binding.getRoot());

        this.loadingLayoutBinding = binding;
    }
}
