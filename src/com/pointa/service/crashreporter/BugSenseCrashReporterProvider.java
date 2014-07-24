package com.pointa.service.crashreporter;

import java.util.Map;

import android.app.Application;
import android.content.Context;

import com.bugsense.trace.BugSenseHandler;



/**
 * BugSense crash reporter interface
 * @version 1.0
 * @since June 20, 2014
 *
 */

public class BugSenseCrashReporterProvider implements CrashReporterAdapter{
	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = BugSenseCrashReporterProvider.class.getSimpleName();


	// ===========================================================
	// Fields
	// ===========================================================

	private String mAppId;
	private Context mContext;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	public void init(Map<String, String> mParams, Application pApp) {
		mContext = pApp.getApplicationContext();
		mAppId = mParams.get("appId");


		BugSenseHandler.initAndStartSession(mContext, mAppId);
	}


	@Override
	public void leaveBreadCrub(String pBreadCrub) {
		BugSenseHandler.leaveBreadcrumb(pBreadCrub);
	}

	@Override
	public void logException(Exception e) {
		BugSenseHandler.sendException(e);		
	}
	
	@Override
	public void logException(Exception pException, String pMessage) {
		BugSenseHandler.sendExceptionMessage("Message: ", pMessage, pException);
	}

	@Override
	public void setUsername(String pUsername) {
		BugSenseHandler.setUserIdentifier(pUsername);
	}


	@Override
	public void addMetadata(String pKey, String pValue) {
		BugSenseHandler.addCrashExtraData(pKey, pValue);
	}


	@Override
	public void clearMetadata() {
		BugSenseHandler.clearCrashExtraData();
	}



	
	
}