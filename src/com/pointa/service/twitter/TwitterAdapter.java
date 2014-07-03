package com.pointa.service.twitter;

import android.app.Activity;

import com.pointa.service.PointAService;

public interface TwitterAdapter extends PointAService{
	public void postTweet(String pTweet);
	public void loginToTwitter(final Activity pActivity);
	public void onCreate(Activity x);
	public void logoutFromTwitter();
}
