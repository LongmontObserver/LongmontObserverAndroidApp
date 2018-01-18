package com.lngmnt.longmontobserver.view.article;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.lngmnt.longmontobserver.BuildConfig;
import com.lngmnt.longmontobserver.R;
import com.lngmnt.longmontobserver.databinding.ActivityArticleBinding;
import com.lngmnt.longmontobserver.model.LongmontObserverAnalytics;
import com.lngmnt.longmontobserver.model.wordpress.ArticleList;
import com.lngmnt.longmontobserver.utils.ViewUtils;
import com.lngmnt.longmontobserver.view.HomeActivity;

/**
 * Created by @sangeles on 12/25/17.
 */

public class ArticleActivity extends AppCompatActivity {

    private static final String TAG = ArticleActivity.class.getSimpleName();

    private static final String KEY_ARG_ARTICLE_TITLE = "KEY_ARG_ARTICLE_TITLE";
    private static final String KEY_ARG_ARTICLE_AUTHOR = "KEY_ARG_ARTICLE_AUTHOR";
    private static final String KEY_ARG_ARTICLE_DATE = "KEY_ARG_ARTICLE_DATE";
    private static final String KEY_ARG_ARTICLE_URL = "KEY_ARG_ARTICLE_URL";
    private static final String KEY_ARG_ARTICLE_CATEGORY = "KEY_ARG_ARTICLE_CATEGORY";
    private static final String KEY_ARG_ARTICLE_FEATURED_IMAGE_URL = "KEY_ARG_ARTICLE_FEATURED_IMAGE_URL";
    private static final String KEY_ARG_ARTICLE = "KEY_ARG_ARTICLE";

    private ActivityArticleBinding activityArticleBinding;

    private RequestManager mGlideRequestManager;

    private ShareActionProvider mShareActionProvider;

    private WebView articleWebView;

    private String mArticleTitle;
    private String mArticleFeaturedImageURL;
    private String mArticleText;
    private String mArticleAuthor;
    private String mArticleDate;
    private String mArticleURL;
    private String mArticleCategory;

    public static Intent newInstanceIntent(Context context, ArticleList articleList) {
        Intent intent = new Intent(context, ArticleActivity.class);

        Bundle args = new Bundle();

        String articleTitle = articleList.getArticleTitle();
        if (!TextUtils.isEmpty(articleTitle)) {
            args.putString(KEY_ARG_ARTICLE_TITLE, articleTitle);
        }

        String imageUrl = articleList.getArticleFeaturedImageURL();
        if (!TextUtils.isEmpty(imageUrl)) {
            args.putString(KEY_ARG_ARTICLE_FEATURED_IMAGE_URL, imageUrl);
        }

        String articleText = articleList.getArticleContent();
        if (!TextUtils.isEmpty(articleText)) {
            args.putString(KEY_ARG_ARTICLE, articleText);
        }

        String author = articleList.getArticleAuthor();
        if (!TextUtils.isEmpty(author)) {
            args.putString(KEY_ARG_ARTICLE_AUTHOR, author);
        }

        String date = articleList.getFormattedDate();
        if (!TextUtils.isEmpty(date)) {
            args.putString(KEY_ARG_ARTICLE_DATE, date);
        }

        String url = articleList.getLink();
        if (!TextUtils.isEmpty(url)) {
            args.putString(KEY_ARG_ARTICLE_URL, url);
        }

        String category = articleList.getArticleCategory();
        if (!TextUtils.isEmpty(category)) {
            args.putString(KEY_ARG_ARTICLE_CATEGORY, category);
        }

        intent.putExtras(args);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onCreate");
        }

        // init the binding
        activityArticleBinding = DataBindingUtil.setContentView(this, R.layout.activity_article);

        // Init the variables we need from the bundle and for the activity
        if (savedInstanceState == null) {
            //init from the beginning
            Bundle args = getIntent().getExtras();
            if (args == null) {
                mArticleTitle = null;
                mArticleFeaturedImageURL = null;
                mArticleText = null;
                mArticleAuthor = null;
                mArticleDate = null;
                mArticleURL = null;
                mArticleCategory = null;
            } else {
                mArticleTitle = args.containsKey(KEY_ARG_ARTICLE_TITLE) ? args.getString(KEY_ARG_ARTICLE_TITLE) : null;
                mArticleFeaturedImageURL = args.containsKey(KEY_ARG_ARTICLE_FEATURED_IMAGE_URL) ? args.getString(KEY_ARG_ARTICLE_FEATURED_IMAGE_URL) : null;
                mArticleText = args.containsKey(KEY_ARG_ARTICLE) ? args.getString(KEY_ARG_ARTICLE) : null;
                mArticleAuthor = args.containsKey(KEY_ARG_ARTICLE_AUTHOR) ? args.getString(KEY_ARG_ARTICLE_AUTHOR) : null;
                mArticleDate = args.containsKey(KEY_ARG_ARTICLE_DATE) ? args.getString(KEY_ARG_ARTICLE_DATE) : null;
                mArticleURL = args.containsKey(KEY_ARG_ARTICLE_URL) ? args.getString(KEY_ARG_ARTICLE_URL) : null;
                mArticleCategory = args.containsKey(KEY_ARG_ARTICLE_CATEGORY) ? args.getString(KEY_ARG_ARTICLE_CATEGORY) : null;
            }
        } else {
            // Init from saved state
            mArticleTitle = savedInstanceState.containsKey(KEY_ARG_ARTICLE_TITLE) ? savedInstanceState.getString(KEY_ARG_ARTICLE_TITLE) : null;
            mArticleFeaturedImageURL = savedInstanceState.containsKey(KEY_ARG_ARTICLE_FEATURED_IMAGE_URL) ? savedInstanceState.getString(KEY_ARG_ARTICLE_FEATURED_IMAGE_URL) : null;
            mArticleText = savedInstanceState.containsKey(KEY_ARG_ARTICLE) ? savedInstanceState.getString(KEY_ARG_ARTICLE) : null;
            mArticleAuthor = savedInstanceState.containsKey(KEY_ARG_ARTICLE_AUTHOR) ? savedInstanceState.getString(KEY_ARG_ARTICLE_AUTHOR) : null;
            mArticleDate = savedInstanceState.containsKey(KEY_ARG_ARTICLE_DATE) ? savedInstanceState.getString(KEY_ARG_ARTICLE_DATE) : null;
            mArticleURL = savedInstanceState.containsKey(KEY_ARG_ARTICLE_URL) ? savedInstanceState.getString(KEY_ARG_ARTICLE_URL) : null;
            mArticleCategory = savedInstanceState.containsKey(KEY_ARG_ARTICLE_CATEGORY) ? savedInstanceState.getString(KEY_ARG_ARTICLE_CATEGORY) : null;
        }

        // We need the article text - so if we don't have it finish the activity.
        // TODO: 1/3/18 Video articles that have no text will trigger the below finish()
        if (mArticleText == null || TextUtils.isEmpty(mArticleText)) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "onCreate: finishing activity since article text is null or empty");
            }
            finish();
        }

        // Set and init the toolbar
        setSupportActionBar(activityArticleBinding.activityArticleToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }

        // Start the loading process
        updateArticleViewState(ArticleViewState.LOADING);

        // Init the Glide request manager
        mGlideRequestManager = Glide.with(this);


        // Load the image
        if (!TextUtils.isEmpty(mArticleFeaturedImageURL)) {

        }

        mGlideRequestManager.load(mArticleFeaturedImageURL)
                .apply(buildGlideRequestOptions(this))
                .into(activityArticleBinding.activityArticleCollapsingImageView);

        // Set the article title
        activityArticleBinding.activityArticleArticleTitleTv.setText(Html.fromHtml(mArticleTitle));

        // Set the article date and author
        activityArticleBinding.activityArticleArticleAuthorDateTv.setText(getString(R.string.article_list_item_author_date_string, mArticleAuthor, mArticleDate));

        // Set the article category
        activityArticleBinding.activityArticleCategoryListItem.setText(mArticleCategory);

        // Set up the web view
        articleWebView = activityArticleBinding.activityArticleArticleWebView;
        WebSettings webSettings = articleWebView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);

        articleWebView.setScrollbarFadingEnabled(false);

        articleWebView.loadData(createArticleHtmlString(mArticleText), "text/html", "UTF-8");

        articleWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (BuildConfig.DEBUG) {
                    Log.e(TAG, "onReceivedError: " + error.toString());
                }

                // Display error screen
                updateArticleViewState(ArticleViewState.ERROR);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onPageStarted: ");
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onPageFinished: ");
                }

                if (view.getProgress() == 100 ) {
                    Log.d(TAG, "onPageFinished: progress is 100");
                }

                updateArticleViewState(ArticleViewState.WITH_DATA);

            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onLoadResource: ");
                }


            }
        });

    }

    private static RequestOptions buildGlideRequestOptions(Context context) {

        Drawable errorDrawable = context.getDrawable(R.drawable.ic_broken_image);
        ViewUtils.setDrawableCompatTint(errorDrawable, ContextCompat.getColor(context,R.color.gray_light));

        return new RequestOptions()
                .error(errorDrawable)
                .fitCenter();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onStart");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onResume");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onPause");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onStop");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onDestroy");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onOptionsItemSelected");
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.menu_article_activity_share:
                LongmontObserverAnalytics.trackShareArticleEvent(this);
            default:
                if(BuildConfig.DEBUG){
                    Log.w(TAG, "onOptionsItemSelected: default selected");
                }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onCreateOptionsMenu: ");
        }

        getMenuInflater().inflate(R.menu.menu_article_activity, menu);

        MenuItem shareItem = menu.findItem(R.id.menu_article_activity_share);

        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        // Set the share action provider intent
        mShareActionProvider.setShareIntent(createShareIntent());

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle savedBundle) {

        savedBundle.putString(KEY_ARG_ARTICLE, mArticleText);
        savedBundle.putString(KEY_ARG_ARTICLE_CATEGORY, mArticleCategory);
        savedBundle.putString(KEY_ARG_ARTICLE_URL, mArticleURL);
        savedBundle.putString(KEY_ARG_ARTICLE_DATE, mArticleDate);
        savedBundle.putString(KEY_ARG_ARTICLE_AUTHOR, mArticleAuthor);
        savedBundle.putString(KEY_ARG_ARTICLE_FEATURED_IMAGE_URL, mArticleFeaturedImageURL);
        savedBundle.putString(KEY_ARG_ARTICLE_TITLE, mArticleTitle);

        super.onSaveInstanceState(savedBundle);
    }

    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, mArticleURL);
        return shareIntent;
    }

    private void updateArticleViewState (ArticleViewState articleViewState) {
        Log.d(TAG, "updateArticleViewState: ");
        Integer displayedChildViewResId = null;
        switch (articleViewState) {
            case ERROR:
                displayedChildViewResId = R.id.activity_article_empty_layout_parent_container;
                break;
            case LOADING:
                displayedChildViewResId = R.id.view_flipper_default_loading_container;
                break;
            case WITH_DATA:
                displayedChildViewResId = R.id.activity_article_article_parent_container;
                break;
            default:
                break;
        }

        // Get the view flipper
        ViewFlipper viewFlipper = activityArticleBinding.activityArticleViewFlipper;
        // Set the view flipper to the proper view
        if (displayedChildViewResId != null) {
            Integer displayChildViewResIdIndex = ViewUtils.getFlipperChildIndex(viewFlipper, displayedChildViewResId);
            if (displayChildViewResIdIndex != null) {
                viewFlipper.setDisplayedChild(displayChildViewResIdIndex);
            }
        }

    }

    private enum ArticleViewState {
        LOADING, WITH_DATA, ERROR
    }


    private String createArticleHtmlString(String articleText) {
        return "<html>\n" +
                "        <head>\n" +
                "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"/>\n" +
                "        </head>\n" +
                "            <body>" + articleText + "</body>\n" +
                "        </html>";
    }
}
