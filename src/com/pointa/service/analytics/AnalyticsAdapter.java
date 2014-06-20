package com.pointa.service.analytics;

import android.app.Activity;
import android.content.Context;
import com.pointa.service.PointAService;

/**
 * Adapter interface to convert concrete service implementations to abstract PointA implementation
 * @version 1.0
 * @since June 13, 2014
 *
 */

public interface AnalyticsAdapter extends PointAService {
	public void send(Context c, String category, String action, String label);
	public void send_onStart(Activity a);	
	public void send_onStop(Activity a);
}
