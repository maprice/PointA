package com.maprice.pointa.mock;

import android.util.Log;

import com.maprice.pointa.IPAnalyticsProvider;

public class MockAnalyticsProvider implements IPAnalyticsProvider {

	static final String LOG_TAG = MockAdProvider.class.getSimpleName();

	@Override
	public void initProvider() {
		Log.v(LOG_TAG, "MockAnalyticsProvider: initProvider");
	}

	@Override
	public void startProvider() {
		Log.v(LOG_TAG, "MockAnalyticsProvider: startProvider");	
	}

	@Override
	public void stopProvider() {
		Log.v(LOG_TAG, "MockAnalyticsProvider: stopProvider");
	}


	@Override
	public void trackScreen(String pScreen) {
		Log.v(LOG_TAG, "MockAnalyticsProvider: trackScreen");

	}

	@Override
	public void trackEvent(String pPhylum, String pKingdom, String pClass) {
		Log.v(LOG_TAG, "MockAnalyticsProvider: trackEvent");
	}

	@Override
	public void trackEvent(String pPhylum, String pKingdom, String pClass,
			long pValue) {
		Log.v(LOG_TAG, "MockAnalyticsProvider: trackEvent");
	}

	@Override
	public void trackPurchase(String pSKU, String pItemName,
			long pPriceInMicros, long pQuantity) {
		Log.v(LOG_TAG, "MockAnalyticsProvider: trackEvent");
	}

}
