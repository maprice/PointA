package com.pointa.service.rating;

import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * Mock crash reporter interface used to simulate a crash reporter provider
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class AmazonRatingProvider implements RatingAdapter{
	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = AmazonRatingProvider.class.getSimpleName();

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
		
		
	}

	@Override
	public void RateMyApp(Activity pActivity) {
		Uri uri = Uri.parse("amzn://apps/android?p=" + pActivity.getPackageName());
		Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
		try {
			pActivity.startActivity(goToMarket);
		} catch (ActivityNotFoundException e) {
		    Log.e(LOG_TAG, "Could not launch AmazonAppStore");
		}
	}
}