package com.pointa.service.analytics;

import android.app.Activity;
import android.content.Context;
import com.pointa.service.PointAService;


public interface AnalyticsAdapter extends PointAService {
	public void send(Context c, String category, String action, String label);
	public void send_onStart(Activity a);	
	public void send_onStop(Activity a);
}
