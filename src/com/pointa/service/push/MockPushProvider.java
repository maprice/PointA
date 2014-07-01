package com.pointa.service.push;

import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Mock push interface used to simulate an analytics provider
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class MockPushProvider implements PushAdapter{

	// ===========================================================
	// Constants
	// ===========================================================
	static final String LOG_TAG = MockPushProvider.class.getSimpleName();

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
	public void setDefaultPushCallback(Context pContext,
			Class<? extends Activity> pActivity) {
		Log.v(LOG_TAG, "setDefaultPushCallback");
	}


}
