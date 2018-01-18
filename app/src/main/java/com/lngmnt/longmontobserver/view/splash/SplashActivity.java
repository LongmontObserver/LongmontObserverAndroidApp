package com.lngmnt.longmontobserver.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lngmnt.longmontobserver.BuildConfig;
import com.lngmnt.longmontobserver.view.HomeActivity;

/**
 * Created by @sangeles on 12/30/17.
 */

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onCreate");
        }

        Intent homeIntent = HomeActivity.newInstanceIntent(this);
        startActivity(homeIntent);

        finish();
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
