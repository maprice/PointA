package com.pointa.service.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.pointa.service.PointAService;

/**
 * Adapter interface to convert concrete service implementations to abstract PointA implementation
 * @version 1.0
 * @since June 13, 2014
 *
 */

public interface AdsAdapter extends PointAService{
	public void showAd(View RL, Activity a);
}
