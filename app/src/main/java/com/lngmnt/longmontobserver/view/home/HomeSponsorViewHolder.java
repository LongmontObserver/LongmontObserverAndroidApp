package com.lngmnt.longmontobserver.view.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.lngmnt.longmontobserver.R;
import com.lngmnt.longmontobserver.databinding.HomeArticleItemSponsorLayoutBinding;
import com.lngmnt.longmontobserver.utils.ViewUtils;

/**
 * Created by @sangeles on 1/20/18.
 */

public class HomeSponsorViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "HomeSponsorViewHolder";

    private HomeArticleItemSponsorLayoutBinding homeArticleItemSponsorLayoutBinding;

    public HomeSponsorViewHolder(HomeArticleItemSponsorLayoutBinding itemView) {
        super(itemView.getRoot());

        this.homeArticleItemSponsorLayoutBinding = itemView;
    }

    public void populateSponsorViewHolder(RequestManager glideRequestManager) {

        Context context = homeArticleItemSponsorLayoutBinding.homeArticleItemSponsorImageView.getContext();

        // Load the image
        glideRequestManager
                .load("https://longmontobserver.org/wp-content/uploads/2017/11/LO-website-front-page-banner.png")
                .apply(buildGlideRequestOptions(context))
                .into(homeArticleItemSponsorLayoutBinding.homeArticleItemSponsorImageView);
    }

    private static RequestOptions buildGlideRequestOptions(Context context) {

        Drawable placeHolder = context.getDrawable(R.drawable.ic_image);
        ViewUtils.setDrawableCompatTint(placeHolder, ContextCompat.getColor(context,R.color.gray_light));

        Drawable errorDrawable = context.getDrawable(R.drawable.ic_broken_image);
        ViewUtils.setDrawableCompatTint(errorDrawable, ContextCompat.getColor(context,R.color.gray_light));

        return new RequestOptions()
                .placeholder(placeHolder)
                .error(errorDrawable)
                .fitCenter();
    }
}
