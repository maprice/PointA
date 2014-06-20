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
	public void startSession() {
		Log.v(LOG_TAG, "startSession");
	}


	@Override
	public void closeSession() {
		Log.v(LOG_TAG, "closeSession");
	}


	@Override
	public void flush() {
		Log.v(LOG_TAG, "flush");
	}
}