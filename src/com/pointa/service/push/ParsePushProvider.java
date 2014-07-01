package com.pointa.service.push;

import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

/**
 * Parse push interface
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class ParsePushProvider implements PushAdapter{

	// ===========================================================
	// Constants
	// ===========================================================
	static final String LOG_TAG = ParsePushProvider.class.getSimpleName();

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
		
		String appId = "xuHDYHfrF0dU5bcjZQv9CdzgdBAMKlHKQnag2Jqr";
		String clientKey = "zLSAdgHMkXy41SeOpOfLsvrjQxB35fgXKqfkFuiA";
		
		Parse.initialize(pApp, appId, clientKey);
		
	}

	@Override
	public void setDefaultPushCallback(Context pContext, Class<? extends Activity> pActivity) {
		PushService.setDefaultPushCallback(pContext, pActivity);
		ParseInstallation.getCurrentInstallation().saveInBackground();
	}

}
