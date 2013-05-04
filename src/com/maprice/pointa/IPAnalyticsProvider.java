package com.maprice.pointa;

public interface IPAnalyticsProvider extends IPProvider {

	public void trackScreen(String pScreen);
	
	public void trackEvent(String pPhylum, String pKingdom, String pClass);
	
	public void trackEvent(String pPhylum, String pKingdom, String pClass, long pValue);
	
	public void trackPurchase(String pSKU, String pItemName, long pPriceInMicros, long pQuantity);
}