package com.pointa.service.analytics;

import java.util.Map;

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
	
	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public void send(Context c, String category, String action, String label){
		Log.v(LOG_TAG, "send");
	}
	
	public void send_onStart(Activity a){
		Log.v(LOG_TAG, "send_onStart Log");
	}
	
	public void send_onStop(Activity a){
		Log.v(LOG_TAG, "send_onStop Log");
	}
	
	@Override
	public void init(Map<String, String> mParams) {
		Log.v(LOG_TAG, "init");
	}

}
