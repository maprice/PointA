package com.pointa.service;

import java.util.Map;

/**
 * Container class of service provider specific data
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class ProviderMetaData{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = ProviderMetaData.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================

	final private String mName;
	final private Map<String, String> mParams;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ProviderMetaData(String pName, Map<String, String> pParams){
		mName = pName;
		mParams = pParams;
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public String getName(){
		return mName;
	}
	
	public Map<String, String> getParams(){
		return mParams;
	}

}