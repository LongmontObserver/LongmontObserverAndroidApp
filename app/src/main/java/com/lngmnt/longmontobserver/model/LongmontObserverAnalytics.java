package com.lngmnt.longmontobserver.model;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.lngmnt.longmontobserver.BuildConfig;

/**
 * Created by @sangeles on 12/31/17.
 */

public class LongmontObserverAnalytics {
    private static final String TAG = "LongmontObserverAnalyti";

    private static class LONGMONT_OBSERVER_EVENT {
        static final String SHARE_ARTICLE = "SHARE_ARTICLE";
        static final String OPEN_ARTICLE = "OPEN_SUBMIT_FEEDBACK";
        static final String OPEN_SUBMIT_FEEDBACK = "OPEN_SUBMIT_FEEDBACK";
        static final String OPEN_SUPPORT_LO = "OPEN_SUPPORT_LO";
        static final String OPEN_SETTINGS_SCREEN = "OPEN_SETTINGS_SCREEN";

    }

    public static void trackOpenSettingsScreenEvent(Context context) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "trackOpenSettingsScreenEvent: ");
        }
        trackEvent(context, LONGMONT_OBSERVER_EVENT.OPEN_SETTINGS_SCREEN);
    }

    public static void trackOpenSupportLOEvent(Context context) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "trackOpenSupportLOEvent: ");
        }
        trackEvent(context, LONGMONT_OBSERVER_EVENT.OPEN_SUPPORT_LO);
    }

    public static void trackOpenSubmitFeedbackEvent(Context context) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "trackOpenSubmitFeedbackEvent: ");
        }
        trackEvent(context, LONGMONT_OBSERVER_EVENT.OPEN_SUBMIT_FEEDBACK);
    }

    public static void trackOpenArticleEvent(Context context) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "trackOpenArticleEvent: ");
        }
        trackEvent(context, LONGMONT_OBSERVER_EVENT.OPEN_ARTICLE);
    }

    public static void trackShareArticleEvent(Context context) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "trackShareArticleEvent: ");
        }
        trackEvent(context, LONGMONT_OBSERVER_EVENT.SHARE_ARTICLE);
    }

    private static void trackEvent(Context context, String event, Bundle bundle) {
        initFirebaseAnalytics(context).logEvent(event, bundle);
    }

    private static void trackEvent(Context context, String event) {
        initFirebaseAnalytics(context).logEvent(event, new Bundle());
    }

    private static FirebaseAnalytics initFirebaseAnalytics(Context context) {
        return FirebaseAnalytics.getInstance(context);
    }
}
