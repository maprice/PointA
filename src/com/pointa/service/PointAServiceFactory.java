package com.pointa.service;


import android.app.Application;
import android.util.Log;

import com.pointa.PointA.ServiceType;
import com.pointa.config.ConfigManager;
import com.pointa.service.ads.MockAdProvider;
import com.pointa.service.analytics.MockAnalyticsProvider;
import com.pointa.service.crashreporter.MockCrashReporterProvider;
import com.pointa.service.push.MockPushProvider;
import com.pointa.service.rating.MockRatingProvider;

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

	final private String fClassPathFormat = "com.pointa.service.%s.%sProvider";

	// ===========================================================
	// Fields
	// ===========================================================

	private final Application mApp;

	// ===========================================================
	// Constructors
	// ===========================================================

	public PointAServiceFactory(Application pApp) {
		mApp = pApp;
	}

	// ===========================================================
	// Methods
	// ===========================================================




	public PointAService buildProvider(ServiceType pService, ConfigManager pConfigManager) {
		PointAService lNewService = null;

		// Loop until successful init or no more providers exist
		for(int priority = 1; ; priority++){

			ProviderMetaData lMetaData = pConfigManager.getProviderMetaData(pService, priority);

			// We have no more providers to try, return a mock provider
			if(lMetaData == null){
				return buildMockProvider(pService);
			}

			// Build class from string
			try {
				Class<?> lProviderClass;

				String lFullClassName = String.format(fClassPathFormat, pService.toString(), lMetaData.getName());
				lProviderClass = Class.forName(lFullClassName);
				lNewService = (PointAService) lProviderClass.newInstance();

			} catch (ClassNotFoundException e) {
				Log.e(LOG_TAG, 
						"Could not find class " + 
								String.format(fClassPathFormat, pService.toString(), lMetaData.getName()) + 
								"\n Are you sure " +  
								lMetaData.getName() + 
								" is correctly written in the config.xml?"
						);
				Log.e(LOG_TAG, "Failed to initialize " + lMetaData.getName() + " falling to priority" + priority + 1);
				continue;
			} catch (InstantiationException e) {
				Log.e(LOG_TAG, "Failed to initialize " + lMetaData.getName() + " falling to priority" + priority + 1);
				continue;
			} catch (IllegalAccessException e) {
				Log.e(LOG_TAG, "Failed to initialize " + lMetaData.getName() + " falling to priority" + priority + 1);
				continue;
			}

			// Try to initilaze provider
			try {
				lNewService.init(null, mApp);
			} catch (Exception e) {
				e.printStackTrace();
				Log.e(LOG_TAG, "Failed to initialize " + lMetaData.getName() + " falling to priority" + priority + 1);
				continue;
			}

			return lNewService;
		}
	}

	private PointAService buildMockProvider(ServiceType pService){	
		PointAService lNewService = null;
		switch(pService){
		case Ads:
			lNewService = new MockAdProvider();
			break;
		case Analytics:
			lNewService = new MockAnalyticsProvider();
			break;
		case CrashReporter:
			lNewService = new MockCrashReporterProvider();
			break;
		case Rating:
			lNewService = new MockRatingProvider();
			break;
		case Push:
			lNewService = new MockPushProvider();
			break;
		}
		return lNewService;
	}

}