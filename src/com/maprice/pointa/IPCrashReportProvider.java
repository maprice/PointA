package com.maprice.pointa;

public interface IPCrashReportProvider extends IPProvider {

	public boolean hasPendingLogs();
	public void sendPendingLogs();
	public void printPendingLogs();
	
}
