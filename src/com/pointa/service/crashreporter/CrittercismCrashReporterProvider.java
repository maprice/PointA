package com.pointa.service.crashreporter;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;

import com.crittercism.app.Crittercism;



/**
 * Crittercism crash reporter interface
 * @version 1.0
 * @since June 20, 2014
 *
 */

public class CrittercismCrashReporterProvider implements CrashReporterAdapter{
	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = CrittercismCrashReporterProvider.class.getSimpleName();


	// ===========================================================
	// Fields
	// ===========================================================

	private String mAppId;
	private JSONObject mMetaData;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	public void init(Map<String, String> mParams, Application pApp) {
		mAppId = "53a50d7bbb94751a22000005";

		Crittercism.initialize(pApp.getApplicationContext(), mAppId);
		
		mMetaData = new JSONObject();
	}

	@Override
	public void leaveBreadCrub(String pBreadCrub) {
		Crittercism.leaveBreadcrumb(pBreadCrub);
	}

	@Override
	public void logException(Exception e) {
		Crittercism.logHandledException(e);
	}

	@Override
	public void setUsername(String pUsername) {
		Crittercism.setUsername(pUsername);		
	}
	
	@Override
	public void addMetadata(String pKey, String pValue) {
		try {
			mMetaData.put(pKey, pValue);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		Crittercism.setMetadata(mMetaData);
	}

	@Override
	public void clearMetadata() {
		mMetaData = new JSONObject();
		Crittercism.setMetadata(mMetaData);
	}
}