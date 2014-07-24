package com.pointa.service.analytics;

import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

/**
 * Google analytics interface used to communicate with analytics provider
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class GoogleAnalyticsProvider implements AnalyticsAdapter{

	// ===========================================================
	// Constants
	// ===========================================================
	static final String LOG_TAG = GoogleAnalyticsProvider.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================
	private static Tracker tracker;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	public void init(Map<String, String> mParams, Application pApp) {

		
		
	}
	
	@Override
	public void send(Context c, String category, String action, String label){
		Log.v(LOG_TAG, "send Log");
		tracker = EasyTracker.getInstance(c);
		tracker.send(MapBuilder.createEvent(category, action, label, null).build());
	}
	
	@Override
	public void send_onStart(Activity a){
		Log.v(LOG_TAG, "send_onStart Log");
		EasyTracker.getInstance(a).activityStart(a);
	}
	
	@Override
	public void send_onStop(Activity a){
		Log.v(LOG_TAG, "send_onStop Log");
		EasyTracker.getInstance(a).activityStop(a);
	}
}