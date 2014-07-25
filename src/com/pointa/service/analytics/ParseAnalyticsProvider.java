package com.pointa.service.analytics;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;
import com.parse.Parse;
import com.parse.ParseAnalytics;

/**
 * Google analytics interface used to communicate with analytics provider
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class ParseAnalyticsProvider implements AnalyticsAdapter{

	// ===========================================================
	// Constants
	// ===========================================================
	static final String LOG_TAG = ParseAnalyticsProvider.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================


	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	public void init(Map<String, String> mParams, Application pApp) {
		String appId = mParams.get("appId");
		String clientKey = mParams.get("clientId");

		Parse.initialize(pApp, appId, clientKey);
	}

	@Override
	public void send(Context c, String category, String action, String label){
		Map<String, String> dimensions = new HashMap<String, String>();
		// What type of news is this?
		dimensions.put(action, label);
		// Is it a weekday or the weekend?

		ParseAnalytics.trackEvent(category, dimensions);
	}

	@Override
	public void send_onStart(Activity a){
		ParseAnalytics.trackAppOpened(a.getIntent());
	}

	@Override
	public void send_onStop(Activity a){
		ParseAnalytics.trackEvent("onStop");

	}
}