package com.pointa.service.crashreporter;

import com.pointa.service.PointAService;

/**
 * Adapter interface to convert concrete service implementations to abstract PointA implementation
 * @version 1.0
 * @since June 13, 2014
 *
 */

public interface CrashReporterAdapter extends PointAService{
	public void startSession();
	public void closeSession();
	public void flush();
}