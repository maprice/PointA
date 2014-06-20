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
		mAppId = "e84e0e8c";


		BugSenseHandler.initAndStartSession(mContext, mAppId);
	}

	@Override
	public void startSession(){
		BugSenseHandler.startSession(mContext);
	}

	@Override
	public void closeSession(){
		BugSenseHandler.closeSession(mContext);
	}

	@Override
	public void flush(){
		BugSenseHandler.flush(mContext);
	}
}