package com.pointa.service.crashreporter;

import java.util.Map;

import android.app.Application;

import com.bugsnag.MetaData;
import com.bugsnag.android.Bugsnag;



/**
 * BugSnag crash reporter interface
 * @version 1.0
 * @since June 20, 2014
 *
 */

public class BugSnagCrashReporterProvider implements CrashReporterAdapter{
	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = BugSnagCrashReporterProvider.class.getSimpleName();


	// ===========================================================
	// Fields
	// ===========================================================

	private String mAppId;
	private MetaData mMetaData;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	public void init(Map<String, String> mParams, Application pApp) {
		mAppId = mParams.get("appId");

		Bugsnag.register(pApp.getApplicationContext(), mAppId);
		
		mMetaData = new MetaData();
		
	}

	@Override
	public void leaveBreadCrub(String pBreadCrub) {
		Bugsnag.addToTab("Event at ",  String.valueOf(System.currentTimeMillis()), pBreadCrub);
	}

	@Override
	public void logException(Exception e) {
		Bugsnag.notify(e);
	}
	

	@Override
	public void logException(Exception pException, String pMessage) {
		Bugsnag.notify(pException, pMessage);
	}

	@Override
	public void setUsername(String pUsername) {
		Bugsnag.setUser("username", pUsername, "");
	}
	
	@Override
	public void addMetadata(String pKey, String pValue) {
		mMetaData.addToTab("pKey", "pValue", "");
	}

	@Override
	public void clearMetadata() {
		mMetaData = new MetaData();
	}

}