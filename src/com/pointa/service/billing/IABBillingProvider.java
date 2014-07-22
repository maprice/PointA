package com.pointa.service.billing;

import java.util.HashMap;
import java.util.Map;

import org.onepf.oms.OpenIabHelper;
import org.onepf.oms.appstore.googleUtils.IabHelper;
import org.onepf.oms.appstore.googleUtils.IabResult;

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

public class IABBillingProvider implements BillingAdapter{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = IABBillingProvider.class.getSimpleName();


	// ===========================================================
	// Fields
	// ===========================================================

	// SKUs for our products: the premium upgrade (non-consumable) and gas (consumable)
	static final String SKU_PREMIUM = "sku_premium";
	static final String SKU_GAS = "sku_gas";

	// SKU for our subscription (infinite gas)
	static final String SKU_INFINITE_GAS = "sku_infinite_gas";

	OpenIabHelper mHelper;

	static {
		OpenIabHelper.mapSku(SKU_PREMIUM, OpenIabHelper.NAME_AMAZON, "org.onepf.trivialdrive.amazon.premium");
		OpenIabHelper.mapSku(SKU_PREMIUM, OpenIabHelper.NAME_TSTORE, "tstore_sku_premium");
		OpenIabHelper.mapSku(SKU_PREMIUM, OpenIabHelper.NAME_SAMSUNG, "100000100696/000001003746");
		OpenIabHelper.mapSku(SKU_PREMIUM, "com.yandex.store", "org.onepf.trivialdrive.premium");
		OpenIabHelper.mapSku(SKU_PREMIUM, "Appland", "org.onepf.trivialdrive.premium");
		OpenIabHelper.mapSku(SKU_PREMIUM, OpenIabHelper.NAME_NOKIA, "1023608");
		OpenIabHelper.mapSku(SKU_PREMIUM, "SlideME", "slideme_sku_premium");

		OpenIabHelper.mapSku(SKU_GAS, OpenIabHelper.NAME_AMAZON, "org.onepf.trivialdrive.amazon.gas");
		OpenIabHelper.mapSku(SKU_GAS, OpenIabHelper.NAME_TSTORE, "tstore_sku_gas");
		OpenIabHelper.mapSku(SKU_GAS, OpenIabHelper.NAME_SAMSUNG, "100000100696/000001003744");
		OpenIabHelper.mapSku(SKU_GAS, "com.yandex.store", "org.onepf.trivialdrive.gas");
		OpenIabHelper.mapSku(SKU_GAS, "Appland", "org.onepf.trivialdrive.gas");
		OpenIabHelper.mapSku(SKU_GAS, OpenIabHelper.NAME_NOKIA, "1023609");
		OpenIabHelper.mapSku(SKU_GAS, "SlideME", "slideme_sku_gas");

		OpenIabHelper.mapSku(SKU_INFINITE_GAS, OpenIabHelper.NAME_AMAZON, "org.onepf.trivialdrive.amazon.infinite_gas");
		OpenIabHelper.mapSku(SKU_INFINITE_GAS, OpenIabHelper.NAME_TSTORE, "tstore_sku_infinite_gas");
		OpenIabHelper.mapSku(SKU_INFINITE_GAS, OpenIabHelper.NAME_SAMSUNG, "100000100696/000001003747");
		OpenIabHelper.mapSku(SKU_INFINITE_GAS, "com.yandex.store", "org.onepf.trivialdrive.infinite_gas");
		OpenIabHelper.mapSku(SKU_INFINITE_GAS, OpenIabHelper.NAME_NOKIA, "1023610");
		OpenIabHelper.mapSku(SKU_INFINITE_GAS, "SlideME", "slideme_sku_inifinite_gas");
	}

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	public void init(Map<String, String> mParams, Application pApp) {
		Log.v(LOG_TAG, "init");



		String base64EncodedPublicKey   = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA8A4rv1uXF5mqJGrtGkQ5PQGpyNIgcZhvRD3yNLC5T+NlIlvMlkuGUmgZnXHfPdORZT/s5QXa2ytjffOyDVgXpHrZ0J9bRoR+hePP4o0ANzdEY/ehkt0EsifB2Kjhok+kTNpikplwuFtIJnIyFyukcesPAXksu2LTQAEzYwlMeJ8W4ToDHw6U5gEXLZcMKiDVTFA0pb89wVfb76Uerv9c6lrydKZiTn/gxg8J1yrz7vNzX7IzoWPO0+pXLnkcgqtEHePF2DIW1D29GkNJOt6xH3IvyS4ZI+1xs3wuSg8vWq3fQP/XIVHZQOqd5pmJY0tdgzboHuqq3ebtNrBI6Ky0SwIDAQAB";
		String YANDEX_PUBLIC_KEY        = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs4NKNVt1lC97e5qr5qIK31WKh470ihgFdRSiV/8kdKtdk2gsLD70AFPFZ0py/OOyZflDjTOya809mU0lsWOxrrGZBRFqQKbvCPh9ZIMVZc79Uz0UZfjBy/n2h4bc0Z5VeBIsnDNh4DCD/XlHYwLIf6En+uPkKZwD3lG2JW4q4Hmuc3HYbuagv+hMexEG/umjbHTRq5rJ+rJ2LyYQs5Kdi/UZ5JKjsk9CuYrzMi9TqOqc9fDG19mfqqr4lfzvKneGIG11c3d1yUNX/MmSE43QYPPWNNKgGLha1AbS7RvtbWzEviiEZ0wjQkRSu4QAXhUurzK75eWDBN2KiJK9mlI1lQIDAQAB";
		String APPLAND_PUBLIC_KEY       = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC5idC9c24V7a7qCJu7kdIyOZskW0Rc7/q+K+ujEXsUaAdb5nwmlOJqpoJeCh5Fmq5A1NdF3BwkI8+GwTkH757NBZASSdEuN0pLZmA6LopOiMIy0LoIWknM5eWMa3e41CxCEFoMv48gFIVxDNJ/KAQAX7+KysYzIdlA3W3fBXXyGQIDAQAB";
		String SLIDEME_PUBLIC_KEY		= "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq6rFm2wb9smbcowrfZHYw71ISHYxF/tG9Jn9c+nRzFCVDSXjvedBxKllw16/GEx9DQ32Ut8azVAznB2wBDNUsSM8nzNhHeCSDvEX2/Ozq1dEq3V3DF4jBEKDAkIOMzIBRWN8fpA5MU/9m8QD9xkJDfP7Mw/6zEMidk2CEE8EZRTlpQ8ULVgBlFISd8Mt9w8ZFyeTyJTZhF2Z9+RZN8woU+cSXiVRmiA0+v2R8Pf+YNJb9fdV5yvM8r9K1MEdRaXisJyMOnjL7H2mZWigWLm7uGoUGuIg9HHi09COBMm3dzAe9yLZoPSG75SvYDsAZ6ms8IYxF6FAniNqfMOuMFV8zwIDAQAB";


		Log.d(LOG_TAG, "Creating IAB helper.");
		Map<String, String> storeKeys = new HashMap<String, String>();
		storeKeys.put(OpenIabHelper.NAME_GOOGLE, base64EncodedPublicKey);
		//	      storeKeys.put(OpenIabHelper.NAME_AMAZON, "Unavailable. Amazon doesn't support RSA verification. So this mapping is not needed"); //
		//	      storeKeys.put(OpenIabHelper.NAME_SAMSUNG,"Unavailable. SamsungApps doesn't support RSA verification. So this mapping is not needed"); //
		storeKeys.put("com.yandex.store", YANDEX_PUBLIC_KEY);
		storeKeys.put("Appland", APPLAND_PUBLIC_KEY);
		storeKeys.put("SlideME", SLIDEME_PUBLIC_KEY);

		mHelper = new OpenIabHelper(pApp, storeKeys);


	
		mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
			public void onIabSetupFinished(IabResult result) {
				if (!result.isSuccess()) {
					Log.e(LOG_TAG,"Problem setting up in-app billing: " + result);
					return;
				}
				Log.d(LOG_TAG, "Setup successful. Querying inventory.");
				mHelper.queryInventoryAsync(null);
			}
		});

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