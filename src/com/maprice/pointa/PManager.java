package com.maprice.pointa;

public static class PManager {

	private static PManager sInstance;

	private static IPAdProvider sAdInstance;
	private static IPAnalyticsProvider sAnalyticsInstance;
	private static IPCrashReportProvider sCrashReportInstance;
	private static IPServerProvider sServerInstance;
	private static IPSocialProvider sSocialInstance;

	public PManager sharedInstance(){
		if(sInstance == null){
			sInstance = new PManager();
		}
		return sInstance;
	}

	public initPManager(Context pContext){
		//Get json object from XML

		String lAdProvider = "AdMob";
		sAdInstance = null; //getClassFromString(lAdProvider);

		String lAnalyticsProvider = "GoogleAnalytics";
		sAnalyticsInstance = null; //getClassFromString(lAnalyticsProvider);

		String lCrashReportProvider = "Crashlytics";
		sCrashReportInstance = null; //getClassFromString(lAnalyticsProvider);

		String lServerProvider = "Parse";
		sServerInstance = null; //getClassFromString(lServerProvider);

		String lSocialProvider = "Parse";
		sSocialInstance = null; //getClassFromString(lSocialProvider);


	}

	public IPAdProvider getAd(){
		if(sAdInstance == null){
			throw new RuntimeException("Need to specify an Ad provider in PointAConfig.xml");
		} else {
			return sAdInstance;
		}
	}

	public IPAnalyticsProvider getAnalytics(){
		if(sAnalyticsInstance == null){
			throw new RuntimeException("Need to specify an Ad provider in PointAConfig.xml");
		} else {
			return sAnalyticsInstance;
		}
	}

	public IPCrashReportProvider getCrashReporter(){
		if(sCrashReportInstance == null){
			throw new RuntimeException("Need to specify an Ad provider in PointAConfig.xml");
		} else {
			return sCrashReportInstance;
		}
	}

	public IPServerProvider getServerProvider(){
		if(sServerInstance == null){
			throw new RuntimeException("Need to specify an Ad provider in PointAConfig.xml");
		} else {
			return sServerInstance;
		}
	}

	public IPSocialProvider getSocial(){
		if(sSocialInstance == null){
			throw new RuntimeException("Need to specify an Ad provider in PointAConfig.xml");
		} else {
			return sSocialInstance;
		}
	}



}
