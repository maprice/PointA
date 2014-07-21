package com.pointa;


import android.app.Application;
import android.util.Log;

import com.pointa.config.ConfigManager;
import com.pointa.service.PointAServiceFactory;
import com.pointa.service.ads.AdsAdapter;
import com.pointa.service.analytics.AnalyticsAdapter;
import com.pointa.service.crashreporter.CrashReporterAdapter;
import com.pointa.service.push.PushAdapter;
import com.pointa.service.rating.RatingAdapter;
import com.pointa.service.social.twitter.TwitterAdapter;

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
		Rating,
		Push,
		Twitter
		//...
	}

	// ===========================================================
	// Fields
	// ===========================================================

	private static AdsAdapter mAdProvider;
	private static AnalyticsAdapter mAnalyticsProvider;
	private static CrashReporterAdapter mCrashReporterProvider;
	private static RatingAdapter mRatingProvider;
	private static PushAdapter mPushProvider;
	private static TwitterAdapter mTwitterProvider;
	

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

		// Create factory
		PointAServiceFactory lFactory = new PointAServiceFactory(pApp);

		// Build providers
		mAdProvider = (AdsAdapter) lFactory.buildProvider(ServiceType.Ads, lConfigManager);
		mAnalyticsProvider = (AnalyticsAdapter) lFactory.buildProvider(ServiceType.Analytics, lConfigManager);
		mCrashReporterProvider = (CrashReporterAdapter) lFactory.buildProvider(ServiceType.CrashReporter, lConfigManager);
		mRatingProvider = (RatingAdapter) lFactory.buildProvider(ServiceType.Rating, lConfigManager);
		mPushProvider = (PushAdapter) lFactory.buildProvider(ServiceType.Push, lConfigManager);
		mTwitterProvider = (TwitterAdapter)  lFactory.buildProvider(ServiceType.Twitter, lConfigManager);
	}

	public static AdsAdapter ads(){
		// Add null error checking
		return mAdProvider;
	}

	public static AnalyticsAdapter analytics(){
		// Add null error checking
		return mAnalyticsProvider;
	}

	public static CrashReporterAdapter crashReporter(){
		// Add null error checking
		return mCrashReporterProvider;
	}
	
	public static RatingAdapter rating(){
		// Add null error checking
		return mRatingProvider;
	}
	
	public static PushAdapter push(){
		// Add null error checking
		return mPushProvider;
	}
	
	public static TwitterAdapter twitter(){
		// Add null error checking
		return mTwitterProvider;
	}
}