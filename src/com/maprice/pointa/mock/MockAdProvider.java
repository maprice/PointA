package com.maprice.pointa.mock;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;

import com.maprice.pointa.IPAdProvider;

public class MockAdProvider implements IPAdProvider {

	static final String LOG_TAG = MockAdProvider.class.getSimpleName();

	
	@Override
	public void initProvider() {
		Log.v(LOG_TAG, "MockAdProvider: initProvider");
	}

	@Override
	public void startProvider() {
		Log.v(LOG_TAG, "MockAdProvider: startProvider");	
	}

	@Override
	public void stopProvider() {
		Log.v(LOG_TAG, "MockAdProvider: stopProvider");
	}

	@Override
	public void showBannerAd(ViewGroup pContainer) {
		Log.v(LOG_TAG, "MockAdProvider: showBannerAd");
	}

	@Override
	public void hideBannerAd() {
		Log.v(LOG_TAG, "MockAdProvider: hideBannerAd");
	}

	@Override
	public void precacheBannerAd() {
		Log.v(LOG_TAG, "MockAdProvider: precacheBannerAd");
	}

	@Override
	public void precacheInterstitialAd(Activity pActivity) {
		Log.v(LOG_TAG, "MockAdProvider: precacheInterstitialAd");
	}

	@Override
	public void showInterstitialAd() {
		Log.v(LOG_TAG, "MockAdProvider: showInterstitialAd");
	}

	@Override
	public void hideInterstitialAd() {
		Log.v(LOG_TAG, "MockAdProvider: hideInterstitialAd");
	}

}
