package com.pointa.service.ads;

import java.util.Map;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
/**
 * GoogleAdMob Ad interface used to simulate an ad provider
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class AdMobAdProvider implements AdsAdapter{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = AdMobAdProvider.class.getSimpleName();


	// ===========================================================
	// Fields
	// ===========================================================
	private static String UNIT_ID;
	private static AdSize AD_SIZE;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	//To be read from config file
	public AdMobAdProvider(){}

	// ===========================================================
	// Methods
	// ===========================================================
	
	@Override
	public void init(Map<String, String> mParams) {
		// To be read via config
		UNIT_ID = "ca-app-pub-4039374914884378/7666844841";
		AD_SIZE = AdSize.BANNER;
	}
	
	@Override
	public void showBannerAd(View v, Activity a){
		AdView adView = new AdView(a, AD_SIZE, UNIT_ID);
		((ViewGroup)v).addView(adView);
		adView.loadAd(new AdRequest());
	}

	@Override
	public void hideBannerAd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void precacheBannerAd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void precacheInterstitialAd(Activity pActivity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showInterstitialAd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideInterstitialAd() {
		// TODO Auto-generated method stub
		
	}
}