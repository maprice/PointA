package com.pointa.service.ads;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.pointa.service.PointAService;

/**
 * Adapter interface to convert concrete service implementations to abstract PointA implementation
 * @version 1.0
 * @since June 13, 2014
 *
 */

public interface AdsAdapter extends PointAService{
	public void showBannerAd(ViewGroup pContainer, Activity pActivity);
	
	public void precacheBannerAd(Activity pActivity);
	public void precacheInterstitialAd(Activity pActivity);
	
	public void showInterstitialAd(Activity pActivity);
	
}
