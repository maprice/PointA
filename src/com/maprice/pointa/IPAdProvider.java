package com.maprice.pointa;

import android.support.v4.view.ViewGroupCompat;

public interface IPAdProvider extends IPProvider {

	public void showBannerAd();
	public void hideBannerAd();
	
	public void showInterstitialAd();
	public void hideInterstitialAd();
}
