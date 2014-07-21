package com.pointa.service.ads;

import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
/**
 * Mock ad interface used to simulate an ad provider
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class MockAdProvider implements AdsAdapter{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = MockAdProvider.class.getSimpleName();


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
	public void showBannerAd(ViewGroup pContainer, Activity pActivity) {
		Log.v(LOG_TAG, "showBannerAd");
	}


	@Override
	public void precacheBannerAd(Activity pActivity) {
		Log.v(LOG_TAG, "precacheBannerAd");	
	}


	@Override
	public void precacheInterstitialAd(Activity pActivity) {
		Log.v(LOG_TAG, "precacheInterstitialAd");	
	}


	@Override
	public void showInterstitialAd(Activity pActivity) {
		Log.v(LOG_TAG, "showInterstitialAd");	
	}


}