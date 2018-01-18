package com.lngmnt.longmontobserver.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.lngmnt.longmontobserver.BuildConfig;
import com.lngmnt.longmontobserver.R;
import com.lngmnt.longmontobserver.databinding.HomeArticleItemLayoutBinding;
import com.lngmnt.longmontobserver.model.LongmontObserverAnalytics;
import com.lngmnt.longmontobserver.model.wordpress.ArticleList;
import com.lngmnt.longmontobserver.model.wordpress.Embedded;
import com.lngmnt.longmontobserver.model.wordpress.Title;
import com.lngmnt.longmontobserver.model.wordpress.author.Author;
import com.lngmnt.longmontobserver.model.wordpress.media.Media;
import com.lngmnt.longmontobserver.utils.ViewUtils;
import com.lngmnt.longmontobserver.view.article.ArticleActivity;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by @sangeles on 12/23/17.
 */

public class HomeArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String TAG = "HomeArticleViewHolder";

    private HomeArticleItemLayoutBinding articleItemLayoutBinding;

    private ArticleList mArticleList;

    public HomeArticleViewHolder(HomeArticleItemLayoutBinding itemView) {
        //use the binding root view
        super(itemView.getRoot());
        this.articleItemLayoutBinding = itemView;

        // Add the click listener
        articleItemLayoutBinding.homeArticleListItemParentLayout.setOnClickListener(this);
    }

    public void populateHomeViewHolder(ArticleList articleList, RequestManager glideRequestManager) {

        this.mArticleList = articleList;

        mArticleList.getArticleCategory();

        // get the context
        Context context = articleItemLayoutBinding.homeArticleAuthorAndDateListItem.getContext();


        // Set the article title
        String articleTitle = articleList.getArticleTitle();
        articleItemLayoutBinding.homeArticleTitleListItem.setText(Html.fromHtml(articleTitle));


        // Set the article author and date
        String author = articleList.getArticleAuthor();
        String articleDate = articleList.getFormattedDate();

        String writtenByAndDate = context.getString(R.string.article_list_item_author_date_string, author, articleDate);
        articleItemLayoutBinding.homeArticleAuthorAndDateListItem.setText(writtenByAndDate);

        // Get the featured image url
        String featuredImageUrl = articleList.getFeaturedImageUrl();

        // Load the image
        glideRequestManager
                .load(featuredImageUrl)
                .apply(buildGlideRequestOptions(context))
                .into(articleItemLayoutBinding.homeArticleImageViewListItem);

        // Set the article category
        String articleCategory = articleList.getArticleCategory();
        articleItemLayoutBinding.homeArticleCategoryListItem.setText(!TextUtils.isEmpty(articleCategory) ? articleCategory : "");
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

    @Override
    public void onClick(View v) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onClick: home article list item clicked");
        }

        // track the click
        LongmontObserverAnalytics.trackOpenArticleEvent(v.getContext());

        // Open the article activity
        Intent intent = ArticleActivity.newInstanceIntent(v.getContext(), mArticleList);
        v.getContext().startActivity(intent);

    }


}
