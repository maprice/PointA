package com.pointa.service.analytics;

import java.util.Map;

import com.google.analytics.tracking.android.Tracker;

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
	
	@Override
	public void init(Map<String, String> mParams) {
		// TODO Auto-generated method stub
		
		//GoogleAnalytics an;
	}

}
