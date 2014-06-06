package com.pointa;


import com.pointa.ads.IAds;
import com.pointa.ads.MockAdProvider;
import com.pointa.analytics.IAnalytics;
import com.pointa.analytics.MockAnalyticsProvider;
import com.pointa.crashreporter.ICrashReporter;
import com.pointa.crashreporter.MockCrashReporter;

public class PointA {

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  PointA.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================

	private static IAds mAdProvider;
	private static IAnalytics mAnalyticsProvider;
	private static ICrashReporter mCrashReporterProvider;

	// ===========================================================
	// Constructors
	// ===========================================================

	private PointA(){ }

	// ===========================================================
	// Methods
	// ===========================================================

	public static void init(){
		// Read config file

		// For now just make everything mock
		mAdProvider = new MockAdProvider();
		mAnalyticsProvider = new MockAnalyticsProvider();
		mCrashReporterProvider = new MockCrashReporter();
	}

	public static IAds ads(){
		return mAdProvider;
	}

	public static IAnalytics analytics(){
		return mAnalyticsProvider;
	}

	public static ICrashReporter crashReporter(){
		return mCrashReporterProvider;
	}
}