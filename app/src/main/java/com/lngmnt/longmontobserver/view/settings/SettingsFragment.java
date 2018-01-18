package com.lngmnt.longmontobserver.view.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lngmnt.longmontobserver.BuildConfig;
import com.lngmnt.longmontobserver.R;
import com.lngmnt.longmontobserver.model.LongmontObserverAnalytics;

/**
 * Created by @sangeles on 12/30/17.
 */

public class SettingsFragment extends PreferenceFragment{
    private static final String TAG = SettingsFragment.class.getSimpleName();

    private static final String PREF_KEY_ABOUT_VERSION = "preference_key_about_version";
    private static final String PREF_KEY_SUBMIT_FEEDBACK = "preference_key_send_feedback";
    private static final String PREF_KEY_ACKNOWLEDGEMENTS = "preference_key_acknowledgements";

    private SharedPreferences mSharedPreferences;

    public static SettingsFragment newInstance() {

        Bundle args = new Bundle();

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onAttach");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onCreate");
        }

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.longmont_observer_preferences);

        // Set the About preferences summary
        Preference aboutVersionPref = (Preference) findPreference(PREF_KEY_ABOUT_VERSION);
        if (aboutVersionPref != null) {
            String version = getString(R.string.preference_title_about_version, BuildConfig.VERSION_NAME);
            aboutVersionPref.setSummary(version);
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onCreateView");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onViewCreated");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onActivityCreated");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onStart");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onResume");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onPause");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onStop");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onDestroyView");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onDestroy");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onDetach");
        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if(BuildConfig.DEBUG){
            Log.d(TAG, "onPreferenceTreeClick: ");
        }

        String prefKey = preference.getKey();
        switch (prefKey) {
            case PREF_KEY_SUBMIT_FEEDBACK:
                Activity activity = getActivity();
                if (activity != null) {

                    // track the click
                    LongmontObserverAnalytics.trackOpenSubmitFeedbackEvent(activity);

                    // Build
                    Intent sendEmailIntent = buildSendEmailIntent();
                    if (sendEmailIntent.resolveActivity(activity.getPackageManager()) != null) {
                        activity.startActivity(sendEmailIntent);
                    }
                }
                break;

        }

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    private static Intent buildSendEmailIntent() {

        String [] emailArray = {"contactus@longmontobserver.org"};

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, emailArray);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Longmont Observer Android App Feedback");
        return intent;
    }

}
