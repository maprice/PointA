package com.pointa.service.push;

import android.app.Activity;
import android.content.Context;

import com.pointa.service.PointAService;

/**
 * Adapter interface to convert concrete service implementations to abstract PointA implementation
 * @version 1.0
 * @since July 1, 2014
 *
 */

public interface PushAdapter extends PointAService{

	/**
	 * When the client receives a push message, a notification will appear in the system tray. When the
	 * user taps the notification, they will enter the application through a new instance of
	 * pActivity.
	 * 
	 * @param pActivity
	 *            The activity to launch to.  (Usually your main activity)
	 */
	public void setDefaultPushCallback(Context pContext, Class<? extends Activity> pActivity);
}
