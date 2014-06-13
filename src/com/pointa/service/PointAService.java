package com.pointa.service;

import java.util.Map;

/**
 * Base interface all services will implement
 * @version 1.0
 * @since June 13, 2014
 *
 */

public interface PointAService {

	/**
	 * Initialisation function called directly after construction
	 * 
	 * @param pParams
	 *            Map containing parameters specified in PointAConfig.xml
	 */
	public void init(Map<String, String> pParams);
}
