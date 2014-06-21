package com.pointa.service.crashreporter;

import java.util.Map;

import android.app.Application;
import android.util.Log;

/**
 * Mock crash reporter interface used to simulate a crash reporter provider
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class MockCrashReporter implements CrashReporterAdapter{
	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = MockCrashReporter.class.getSimpleName();



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
	public void leaveBreadCrub(String pBreadCrub) {
		Log.v(LOG_TAG, "leaveBreadCrub");
	}


	@Override
	public void logException(Exception e) {
		Log.v(LOG_TAG, "logException");
	}

	@Override
	public void logException(Exception pException, String pMessage) {
		Log.v(LOG_TAG, "logException");
	}

	@Override
	public void setUsername(String pUsername) {
		Log.v(LOG_TAG, "setUsername");
	}


	@Override
	public void addMetadata(String pKey, String pValue) {
		Log.v(LOG_TAG, "addMetadata");
	}


	@Override
	public void clearMetadata() {
		Log.v(LOG_TAG, "clearMetadata");
	}


}