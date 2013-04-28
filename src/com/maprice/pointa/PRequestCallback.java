package com.maprice.pointa;

public interface PRequestCallback <Result> {
	
	/**
	 * Called after action has successfully completed
	 * 
	 * @param pResult
	 *            The class that calls onComplete
	 */
	public void onComplete(Result pResult);

	/**
	 * Called if something goes wrong
	 *            
	 * @param pErrorMessage
	 *            A message describing the error
	 */
	public void onError(String pErrorMessage);

}
