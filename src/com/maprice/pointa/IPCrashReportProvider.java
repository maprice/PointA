package com.maprice.pointa;

// test commit

public interface IPCrashReportProvider extends IPProvider {

	public boolean hasPendingLogs();
	public void sendPendingLogs();
	public void printPendingLogs();
	
}
