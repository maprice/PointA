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
		Ads("ads", "Ads"),
		Analytics("analytics", "Analytics"),
		CrashReporter("crashreporter", "CrashReporter"),
		Rating("rating", "Rating"),
		Push("push", "Push");
		//...


		private ServiceType(final String pPackage, final String pClass) {
			this.mPackage = pPackage;
			this.mClass = pClass;
		}

		private final String mPackage;
		private final String mClass;
	
		public String getPackageName() {
			return mPackage;
		}
		
		public String getClassName() {
			return mClass;
		}
	}

	// ===========================================================
	// Fields
	// ===========================================================

	private static AdsAdapter mAdProvider;
	private static AnalyticsAdapter mAnalyticsProvider;
	private static CrashReporterAdapter mCrashReporterProvider;
	private static RatingAdapter mRatingProvider;
	private static PushAdapter mPushProvider;


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
		lConfigManager.parse(pApp);

		// Create factory
		PointAServiceFactory lFactory = new PointAServiceFactory(pApp);

		// Build providers
		mAdProvider = (AdsAdapter) lFactory.buildProvider(ServiceType.Ads, lConfigManager);
		mAnalyticsProvider = (AnalyticsAdapter) lFactory.buildProvider(ServiceType.Analytics, lConfigManager);
		mCrashReporterProvider = (CrashReporterAdapter) lFactory.buildProvider(ServiceType.CrashReporter, lConfigManager);
		mRatingProvider = (RatingAdapter) lFactory.buildProvider(ServiceType.Rating, lConfigManager);
		mPushProvider = (PushAdapter) lFactory.buildProvider(ServiceType.Push, lConfigManager);
	}

	public static AdsAdapter ads(){
		if(mAdProvider == null)
			Log.e(LOG_TAG, "You must call PointA.init() before accessing any services!");
		
		return mAdProvider;
	}

	public static AnalyticsAdapter analytics(){
		if(mAdProvider == null)
			Log.e(LOG_TAG, "You must call PointA.init() before accessing any services!");
		
		return mAnalyticsProvider;
	}

	public static CrashReporterAdapter crashReporter(){
		if(mAdProvider == null)
			Log.e(LOG_TAG, "You must call PointA.init() before accessing any services!");
		
		return mCrashReporterProvider;
	}

	public static RatingAdapter rating(){
		if(mAdProvider == null)
			Log.e(LOG_TAG, "You must call PointA.init() before accessing any services!");
		
		return mRatingProvider;
	}

	public static PushAdapter push(){
		if(mAdProvider == null)
			Log.e(LOG_TAG, "You must call PointA.init() before accessing any services!");
		
		return mPushProvider;
	}
}