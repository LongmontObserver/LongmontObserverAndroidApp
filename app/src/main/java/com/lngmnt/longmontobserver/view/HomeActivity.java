package com.lngmnt.longmontobserver.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lngmnt.longmontobserver.BuildConfig;
import com.lngmnt.longmontobserver.R;
import com.lngmnt.longmontobserver.databinding.ActivityHomeBinding;
import com.lngmnt.longmontobserver.model.LongmontObserverAnalytics;
import com.lngmnt.longmontobserver.model.network.service.LOWordpressService;
import com.lngmnt.longmontobserver.model.wordpress.ArticleList;
import com.lngmnt.longmontobserver.utils.ViewUtils;
import com.lngmnt.longmontobserver.view.settings.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by @sangeles on 12/22/17.
 */

public class HomeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = HomeActivity.class.getSimpleName();


    private ActivityHomeBinding activityHomeBinding;

    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private SwipeRefreshLayout mSwipeRefreshlayout;


    public static Intent newInstanceIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        Bundle args = new Bundle();
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onCreate");
        }

        // init binding
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        // Set The Toolbar
        setSupportActionBar(activityHomeBinding.includedToolbar.toolbar);

        // Start by displaying a loading indicator
        updateHomeViewState(HomeViewState.LOADING);

        // Set up Navigation Drawer
        mNavigationView = activityHomeBinding.activityHomeNavigationView;

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, activityHomeBinding.activityHomeDrawerLayout,
                activityHomeBinding.includedToolbar.toolbar,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mActionBarDrawerToggle.syncState();
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        activityHomeBinding.activityHomeDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(this);

        // Get the recycler view
        RecyclerView activityHomeRecyclerView = activityHomeBinding.includedContentActivityHome.activityHomeRecyclerView;

        // Init Recycler view adapter and layout manager
        mLinearLayoutManager = new LinearLayoutManager(this);
        activityHomeRecyclerView.setLayoutManager(mLinearLayoutManager);

        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(Glide.with(this), new ArrayList<ArticleList>());
        activityHomeRecyclerView.setAdapter(homeRecyclerViewAdapter);

        // Set has fixed size on the recycler view
        activityHomeRecyclerView.setHasFixedSize(true);

        // Set the recycler view item divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        activityHomeRecyclerView.addItemDecoration(dividerItemDecoration);

        // Get swipe refresh and set a refresh listener to it
        mSwipeRefreshlayout = activityHomeBinding.includedContentActivityHome.activityHomeSwipeRefreshLayout;
        mSwipeRefreshlayout.setOnRefreshListener(this);

        activityHomeRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = mLinearLayoutManager.getChildCount();
                int totalItemCount = mLinearLayoutManager.getItemCount();

                int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= 20) {
                        Log.d(TAG, "onScrolled: LOAD MORE DATA");
                    }
            }
        });

        // Make the initial article list request
        checkInternetConnection();
    }



        private void checkInternetConnection() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "checkInternetConnection: ");
        }

            Single<Boolean> checkInternetConnectivity = ReactiveNetwork.checkInternetConnectivity();
            checkInternetConnectivity.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(isConnectedToTheInternet -> {
                        makeInitialArticleListRequest();
                    }, throwable -> {

                        // display the empty/error view state
                        displayEmptyHomeState();
                    });

        }


    private void makeInitialArticleListRequest() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "makeInitialArticleListRequest: ");
        }

        LOWordpressService service = new LOWordpressService();

        service.getLoWordpressApi().getArticleListEmbed(null, 20, null).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articleLists -> {
                    //refresh the data
                    homeRecyclerViewAdapter.refreshRecyclerViewData(articleLists);

                    displayRecyclerViewHomeState();

                }, throwable -> {
                    // Display the empty home state
                    displayEmptyHomeState();
                });

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

    private void displayRecyclerViewHomeState() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "displayRecyclerViewHomeState: ");
        }

        // update the view state to display the recylcer view
        updateHomeViewState(HomeViewState.WITH_DATA);

        if (mSwipeRefreshlayout.isRefreshing()) {
            // update the swipe pull down refresh
            mSwipeRefreshlayout.setRefreshing(false);
        }
    }

    private void displayEmptyHomeState() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "displayEmptyHomeState: ");
        }
        // update the view state to display the recylcer view
        updateHomeViewState(HomeViewState.EMPTY);

        if (mSwipeRefreshlayout.isRefreshing()) {
            // update the swipe pull down refresh
            mSwipeRefreshlayout.setRefreshing(false);
        }
    }

    private void updateHomeViewState (HomeViewState homeViewState) {
        Integer displayedChildViewResId = null;
        switch (homeViewState) {
            case EMPTY:
                displayedChildViewResId = R.id.activity_home_empty_layout_parent_container;
                break;
            case LOADING:
                displayedChildViewResId = R.id.view_flipper_default_loading_container;
                break;
            case WITH_DATA:
                displayedChildViewResId = R.id.activity_home_content_container;
                break;
            default:
                break;
        }

        // Get the view flipper
        ViewFlipper viewFlipper = activityHomeBinding.includedContentActivityHome.activityHomeViewFlipper;
        // Set the view flipper to the proper view
        if (displayedChildViewResId != null) {
            Integer displayChildViewResIdIndex = ViewUtils.getFlipperChildIndex(viewFlipper, displayedChildViewResId);
            if (displayChildViewResIdIndex != null) {
                viewFlipper.setDisplayedChild(displayChildViewResIdIndex);
            }
        }

    }

    @Override
    public void onRefresh() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onRefresh");
        }

        // Make the initial api call
//        makeInitialArticleListRequest();
        checkInternetConnection();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onNavigationItemSelected: ");
        }

        switch (item.getItemId()) {
            case R.id.menu_nav_drawer_settings:

                LongmontObserverAnalytics.trackOpenSettingsScreenEvent(this);

                // start the settings activity
                startActivity(SettingsActivity.newInstanceIntent(this));

                break;
            case R.id.menu_nav_drawer_support:

                LongmontObserverAnalytics.trackOpenSupportLOEvent(this);

                Uri webpage = Uri.parse("https://longmontobserver.org/support-us/");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
        }

        // Close the drawer if we can
        DrawerLayout drawerLayout = activityHomeBinding.activityHomeDrawerLayout;
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        return true;
    }

    private enum HomeViewState {
        LOADING, EMPTY, WITH_DATA
    }

}
