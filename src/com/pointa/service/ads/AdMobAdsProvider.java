package com.pointa.service.ads;

import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.view.ViewGroup;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.InterstitialAd;
/**
 * GoogleAdMob Ad interface used to simulate an ad provider
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class AdMobAdsProvider implements AdsAdapter, AdListener{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = AdMobAdsProvider.class.getSimpleName();


	// ===========================================================
	// Fields
	// ===========================================================

	private String INTERSTITIAL_UNIT_ID;
	private String BANNER_UNIT_ID;
	private InterstitialAd mInterstitial;
	private AdView mBanner;
	private boolean showInterstitialImmediate;

	// ===========================================================
	// Constructors
	// ===========================================================

	//To be read from config file
	public AdMobAdsProvider(){}

	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	public void init(Map<String, String> mParams, Application pApp) {
		// To be read via config
		INTERSTITIAL_UNIT_ID = "ca-app-pub-3187351542402368/9192434536";
		BANNER_UNIT_ID = "ca-app-pub-3187351542402368/7715701332";

		showInterstitialImmediate = false;
	}

	@Override
	public void showBannerAd(ViewGroup pContainer, Activity pActivity){

		if(mBanner == null){
			mBanner = new AdView(pActivity, AdSize.BANNER, BANNER_UNIT_ID);
			mBanner.setAdListener(this);
		}

		if(mBanner.getParent() == null)
			pContainer.addView(mBanner);

		if(!mBanner.isReady()){
			AdRequest adRequest = new AdRequest();
			mBanner.loadAd(adRequest);
		}

	}



	@Override
	public void onReceiveAd(Ad ad) {
		if (ad == mInterstitial) {
			if(showInterstitialImmediate){
				mInterstitial.show();
				showInterstitialImmediate = false;
			}
		}
	}



	@Override
	public void precacheBannerAd(Activity pActivity) {
		if(mBanner == null){
			mBanner = new AdView(pActivity, AdSize.BANNER, BANNER_UNIT_ID);
			mBanner.setAdListener(this);
		}

		AdRequest adRequest = new AdRequest();
		//adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
		mBanner.loadAd(adRequest);
	}

	@Override
	public void precacheInterstitialAd(Activity pActivity) {
		// Create the interstitial
		if(mInterstitial == null){
			mInterstitial = new InterstitialAd(pActivity, INTERSTITIAL_UNIT_ID);

			// Set Ad Listener to use the callbacks below
			mInterstitial.setAdListener(this);
		}

		// Create ad request
		AdRequest adRequest = new AdRequest();

		// Begin loading your interstitial
		mInterstitial.loadAd(adRequest);

		showInterstitialImmediate = false;
	}

	@Override
	public void showInterstitialAd(Activity pActivity) {
		// Create the interstitial
		if(mInterstitial == null){
			mInterstitial = new InterstitialAd(pActivity, INTERSTITIAL_UNIT_ID);

			// Set Ad Listener to use the callbacks below
			mInterstitial.setAdListener(this);
		}

		if(mInterstitial.isReady()){
			mInterstitial.show();
		}
		else{
			// Create ad request
			AdRequest adRequest = new AdRequest();

			// Begin loading your interstitial
			mInterstitial.loadAd(adRequest);

			showInterstitialImmediate = true;
		}

	}

	@Override
	public void onDismissScreen(Ad arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailedToReceiveAd(Ad arg0, ErrorCode arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeaveApplication(Ad arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPresentScreen(Ad arg0) {
		// TODO Auto-generated method stub

	}
}