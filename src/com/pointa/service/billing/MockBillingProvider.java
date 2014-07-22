package com.pointa.service.billing;

import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
/**
 * Mock ad interface used to simulate an ad provider
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class MockBillingProvider implements BillingAdapter{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = MockBillingProvider.class.getSimpleName();


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
	public String[] queryInventory() {
		Log.v(LOG_TAG, "queryInventory");
		return null;
	}


	@Override
	public void consumeItem(String pSku) {
		Log.v(LOG_TAG, "consumeItem");	
	}


	@Override
	public void purchaseItem(Activity pActivity, String pSku) {
		Log.v(LOG_TAG, "purchaseItem");	
	}


	@Override
	public Bundle getItemDetails(String pSku) {
		Log.v(LOG_TAG, "getItemDetails");
		return null;
	}


	@Override
	public boolean onActivityResult(int requestCode, int resultCode,
			Intent data) {
		Log.v(LOG_TAG, "onActivityResult");	
		return false;
	}


	@Override
	public void onDestroy() {
		Log.v(LOG_TAG, "onDestroy");
	}


}