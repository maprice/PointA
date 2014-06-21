package com.pointa;


import android.app.Application;
import android.util.Log;

import com.pointa.config.ConfigManager;
import com.pointa.service.PointAServiceFactory;
import com.pointa.service.ads.AdsAdapter;
import com.pointa.service.analytics.AnalyticsAdapter;
import com.pointa.service.crashreporter.CrashReporterAdapter;
import com.pointa.service.rating.RatingAdapter;

/**
 * PointA Facade used for all library interaction
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class PointA {

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  PointA.class.getSimpleName();
	
	public enum ServiceType{
		Ads,
		Analytics,
		CrashReporter,
		Rating
		//...
	}

	// ===========================================================
	// Fields
	// ===========================================================

	private static AdsAdapter mAdProvider;
	private static AnalyticsAdapter mAnalyticsProvider;
	private static CrashReporterAdapter mCrashReporterProvider;
	private static RatingAdapter mRatingProvider;


	// ===========================================================
	// Constructors
	// ===========================================================

	private PointA(){ }

	// ===========================================================
	// Methods
	// ===========================================================

	public static void init(Application pApp){
		Log.v(LOG_TAG, "PointA init");
		// Create our config manager
		ConfigManager lConfigManager = new ConfigManager();

		// Read config file
		lConfigManager.parse();

		PointAServiceFactory lFactory = new PointAServiceFactory(pApp);

		// Do we want to have separate building methods instead?
		mAdProvider = (AdsAdapter) lFactory.buildProvider(ServiceType.Ads, lConfigManager);
		mAnalyticsProvider = (AnalyticsAdapter) lFactory.buildProvider(ServiceType.Analytics, lConfigManager);
		mCrashReporterProvider = (CrashReporterAdapter) lFactory.buildProvider(ServiceType.CrashReporter, lConfigManager);
		mRatingProvider = (RatingAdapter) lFactory.buildProvider(ServiceType.Rating, lConfigManager);
	}

	public static AdsAdapter ads(){
		return mAdProvider;
	}

	public static AnalyticsAdapter analytics(){
		return mAnalyticsProvider;
	}

	public static CrashReporterAdapter crashReporter(){
		return mCrashReporterProvider;
	}
	
	public static RatingAdapter rating(){
		return mRatingProvider;
	}
}