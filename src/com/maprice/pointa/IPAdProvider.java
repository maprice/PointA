package com.maprice.pointa;

import android.app.Activity;
import android.view.ViewGroup;

public interface IPAdProvider extends IPProvider {

	public void showBannerAd(ViewGroup pContainer);
	public void hideBannerAd();
	
	public void precacheBannerAd();
	public void precacheInterstitialAd(Activity pActivity);
	
	public void showInterstitialAd();
	public void hideInterstitialAd();
}
