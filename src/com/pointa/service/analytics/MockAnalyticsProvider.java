package com.pointa.service.analytics;

import java.util.Map;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

/**
 * Mock analytics interface used to simulate an analytics provider
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class MockAnalyticsProvider implements AnalyticsAdapter{

	// ===========================================================
	// Constants
	// ===========================================================
	static final String LOG_TAG = MockAnalyticsProvider.class.getSimpleName();

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
	public void send(Context c, String category, String action, String label){
		Log.e(LOG_TAG, "send Log");
		tracker = EasyTracker.getInstance(c);
		tracker.send(MapBuilder.createEvent(category, action, label, null).build());
	}
	
	public void send_onStart(Activity a){
		Log.e(LOG_TAG, "send_onStart Log");
		EasyTracker.getInstance(a).activityStart(a);
	}
	
	public void send_onStop(Activity a){
		Log.e(LOG_TAG, "send_onStop Log");
		EasyTracker.getInstance(a).activityStop(a);
	}
	
	@Override
	public void init(Map<String, String> mParams) {
		// TODO Auto-generated method stub
	}

}
