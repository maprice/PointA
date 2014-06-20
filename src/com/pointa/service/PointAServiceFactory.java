package com.pointa.service;

import com.pointa.PointA.ServiceType;
import com.pointa.config.ConfigManager;
import com.pointa.service.ads.AdMobAdProvider;
import com.pointa.service.analytics.GoogleAnalyticsProvider;
import com.pointa.service.crashreporter.MockCrashReporter;

/**
 * Factory class used to handle construction of PointAServices
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class PointAServiceFactory{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = PointAServiceFactory.class.getSimpleName();


	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================


	// ===========================================================
	// Methods
	// ===========================================================


	public PointAService buildProvider(ServiceType pService, ConfigManager pConfigManager) {
		PointAService lNewService;
		
		ProviderMetaData lMetaData = pConfigManager.getProviderMetaData(pService, 0);
		
		// Use metaData string name to build appropriate class
		switch(pService){
		case Ads:
			lNewService = new AdMobAdProvider();
			break;
		case Analytics:
			lNewService = new GoogleAnalyticsProvider();
			break;
		case CrashReporter:
			lNewService = new MockCrashReporter();
		default:
			// Possibly throw an exception
			return null;

		}
		
		lNewService.init(null);
		
		return lNewService;
	}
}