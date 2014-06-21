package com.pointa.service.crashreporter;

import com.pointa.service.PointAService;

/**
 * Adapter interface to convert concrete service implementations to abstract PointA implementation
 * @version 1.0
 * @since June 13, 2014
 *
 */

public interface CrashReporterAdapter extends PointAService{
	
	
	/**
	 * Logs event that will be associated with next crash.  E.g "Pressed button"
	 * 
	 * @param pBreadCrub
	 *            Bread crub to be logged
	 */
	public void leaveBreadCrub(String pBreadCrub);
	
	
	/**
	 * Logs non-fatal exception
	 * 
	 * @param pException
	 *            Exception to be logged
	 */
	public void logException(Exception pException);
	
	
	/**
	 * Logs non-fatal exception with message
	 * 
	 * @param pException
	 *            Exception to be logged
	 *            
	 * @param pMessage
	 *            Message to be logged
	 */
	public void logException(Exception pException, String pMessage);
	
	
	/**
	 * Associates user with current crash reports
	 * 
	 * @param pUsername
	 *            Unique identifier of user
	 */
	public void setUsername(String pUsername);
	
	
	/**
	 * Add extra custom information to the crash reports
	 * @param pKey
	 *            Key/title of extra information.  E.g "Level"
	 *            
	 * @param pValue
	 *            Value of extra information.      E.g "Third Level"
	 *
	 */
	public void addMetadata(String pKey, String pValue);
	
	/**
	 * Removes extra information added with addMetadata
	 *
	 */
	public void clearMetadata();
}