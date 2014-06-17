package com.pointa.service.ads;

import java.util.Map;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
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
	private static String UNIT_ID;
	private static AdSize AD_SIZE;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	//To be read from config file
	public MockAdProvider(){
		UNIT_ID = "ca-app-pub-4039374914884378/7666844841";
		AD_SIZE = AdSize.BANNER;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	public void showAd(View v, Activity a){
		AdView adView = new AdView(a, AD_SIZE, UNIT_ID);
		((ViewGroup)v).addView(adView);
		adView.loadAd(new AdRequest());
	}

	@Override
	public void init(Map<String, String> mParams) {
		// TODO Auto-generated method stub
	}
}