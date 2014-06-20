package com.pointa.service.rating;

import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

/**
 * Mock analytics interface used to simulate an analytics provider
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class MockRatingProvider implements RatingAdapter{

	// ===========================================================
	// Constants
	// ===========================================================
	static final String LOG_TAG = MockRatingProvider.class.getSimpleName();

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
		Log.v(LOG_TAG, "init");
	}

	@Override
	public void RateMyApp(Activity pActivity) {
		Log.v(LOG_TAG, "rateMyApp");
	}

}
