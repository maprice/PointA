package com.pointa.config;

import java.util.HashMap;
import java.util.Map;

import com.pointa.PointA.ServiceType;
import com.pointa.service.ProviderMetaData;

/**
 * Manages parsing and storing configuration data from PointAConfig.xml
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class ConfigManager{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = ConfigManager.class.getSimpleName();


	// ===========================================================
	// Fields
	// ===========================================================

	private final Map<ServiceType,ProviderMetaData[]> mProviders;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ConfigManager(){
		mProviders = new HashMap<ServiceType, ProviderMetaData[]>();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void parse(){
		// Populate mProviders
	}

	public ProviderMetaData getProviderMetaData(ServiceType pService, int pPriority){
		ProviderMetaData[] lServiceProvider = mProviders.get(pService);

		if(lServiceProvider == null){
			// Maybe do something smarter than returning null
			return null;
		}
		else if(lServiceProvider.length < pPriority){
			// Do something interesting
			return null;
		}


		return lServiceProvider[pPriority];
	}



}