package com.lngmnt.longmontobserver.view.settings;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.lngmnt.longmontobserver.BuildConfig;
import com.lngmnt.longmontobserver.R;
import com.lngmnt.longmontobserver.databinding.ActivitySettingsBinding;

/**
 * Created by @sangeles on 12/30/17.
 */

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = SettingsActivity.class.getSimpleName();


    private ActivitySettingsBinding activitySettingsBinding;

    public static Intent newInstanceIntent(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);

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

        activitySettingsBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings);

        // set toolbar
        setSupportActionBar(activitySettingsBinding.activitySettingsToolbarInclude.toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ViewGroup mParentFragmentContainer = activitySettingsBinding.activitySettingsFragmentContainer;

        if (savedInstanceState == null) {
            if (mParentFragmentContainer != null) {

                // Create the Settings fragment
                SettingsFragment settingsFragment = SettingsFragment.newInstance();
                String fragmentTag = settingsFragment.getClass().getSimpleName();

                // Add the chat fragment to the parent container
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(mParentFragmentContainer.getId(), settingsFragment, fragmentTag);
                fragmentTransaction.commit();
            }
        }
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
}
