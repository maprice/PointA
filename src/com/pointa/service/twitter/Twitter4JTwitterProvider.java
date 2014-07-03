package com.pointa.service.twitter;

import java.util.Map;


import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Twitter4JTwitterProvider implements TwitterAdapter {
	// Constants
	/**
	 * Register your here app https://dev.twitter.com/apps/new and get your
	 * consumer key and secret
	 * */
	static String TWITTER_CONSUMER_KEY = "7Tsi0Hlu227dbYf1fo0AP5dr2"; // place
																		// your
																		// cosumer
																		// key
																		// here
	static String TWITTER_CONSUMER_SECRET = "geUvTeutweRhWsdJJjLcxrmNhY0n3kajRlXJ8FLHIhGDPF6I7T"; // place
																									// your
																									// consumer
																									// secret
																									// here

	// Preference Constants
	static String PREFERENCE_NAME = "twitter_oauth";
	static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
	static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
	static final String PREF_KEY_TWITTER_LOGIN = "isTwitterLogedIn";

	static final String TWITTER_CALLBACK_URL = "oauth://t4jsample";

	// Twitter oauth urls
	static final String URL_TWITTER_AUTH = "auth_url";
	static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
	static final String URL_TWITTER_OAUTH_TOKEN = "oauth_token";

	// Login button
	Button btnLoginTwitter;
	// Update status button
	Button btnUpdateStatus;
	// Logout button
	Button btnLogoutTwitter;
	// EditText for update
	EditText txtUpdate;
	// lbl update
	TextView lblUpdate;
	TextView lblUserName;

	// Progress dialog
	ProgressDialog pDialog;

	// Twitter
	private static Twitter twitter;
	private static RequestToken requestToken;
	private AccessToken accessToken;
	private User user;

	// Shared Preferences
	private static SharedPreferences mSharedPreferences;

	// Internet Connection detector
	private ConnectionDetector cd;

	// Alert Dialog Manager
	AlertDialogManager alert = new AlertDialogManager();

	Application mApp;
	
	@Override
	public void init(Map<String, String> pParams, Application pApp) {
		mApp = pApp;
		cd = new ConnectionDetector(pApp.getApplicationContext());

		// Check if Internet present
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(pApp,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}

		// Check if twitter keys are set
		if (TWITTER_CONSUMER_KEY.trim().length() == 0
				|| TWITTER_CONSUMER_SECRET.trim().length() == 0) {
			// Internet Connection is not present
			alert.showAlertDialog(pApp,
					"Twitter oAuth tokens",
					"Please set your twitter oauth tokens first!", false);
			// stop executing code by return
			return;
		}


		// Shared Preferences
		mSharedPreferences = pApp.getApplicationContext().getSharedPreferences(
				"MyPref", 0);

	
		
		
		
		
	}


	/**
	 * Function to login twitter
	 * */
	@Override
	public void loginToTwitter(final Activity pActivity) {
		// Check if already logged in
		if (!isTwitterLoggedInAlready()) {
			ConfigurationBuilder builder = new ConfigurationBuilder();
			builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
			builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);
			Configuration configuration = builder.build();

			TwitterFactory factory = new TwitterFactory(configuration);
			twitter = factory.getInstance();

			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						requestToken = twitter
								.getOAuthRequestToken(TWITTER_CALLBACK_URL);
						pActivity.startActivity(new Intent(
								Intent.ACTION_VIEW, Uri.parse(requestToken
										.getAuthenticationURL())));
						
		                // Shared Preferences
	                    Editor e = mSharedPreferences.edit();
	 
	                    // After getting access token, access token secret
	                    // store them in application preferences
	                    e.putString(PREF_KEY_OAUTH_TOKEN, accessToken.getToken());
	                    e.putString(PREF_KEY_OAUTH_SECRET,
	                            accessToken.getTokenSecret());
	                    // Store login status - true
	                    e.putBoolean(PREF_KEY_TWITTER_LOGIN, true);
	                    e.commit(); // save changes

					} catch (Exception e) {
						e.printStackTrace();
						Log.e("poop", "Already Logged into twitter");
					}
				}
			});
			thread.start();
		} else {
			// user already logged into twitter
			Toast.makeText(pActivity.getApplicationContext(),
					"Already Logged into twitter", Toast.LENGTH_LONG).show();

		}
	}



	/**
	 * Check user already logged in your application using twitter Login flag is
	 * fetched from Shared Preferences
	 * */
	private boolean isTwitterLoggedInAlready() {
		// return twitter login status from Shared Preferences
		return mSharedPreferences.getBoolean(PREF_KEY_TWITTER_LOGIN, false);
	}

	@Override
	public void postTweet(String pTweet) {
			new updateTwitterStatus().execute(pTweet);
	}
 

	/**
	 * Function to logout from twitter It will just clear the application shared
	 * preferences
	 * */
	@Override
	public void logoutFromTwitter() {
		// Clear the shared preferences
		Editor e = mSharedPreferences.edit();
		e.remove(PREF_KEY_OAUTH_TOKEN);
		e.remove(PREF_KEY_OAUTH_SECRET);
		e.remove(PREF_KEY_TWITTER_LOGIN);
		e.commit();
	}
	

	/**
	 * Function to update status
	 * */
	class updateTwitterStatus extends AsyncTask<String, String, String> {

		/**
		 * getting Places JSON
		 * */
		protected String doInBackground(String... args) {
			Log.d("Tweet Text", "> " + args[0]);
			String status = args[0];
			try {
				ConfigurationBuilder builder = new ConfigurationBuilder();
				builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
				builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);

				// Access Token
				String access_token = mSharedPreferences.getString(
						PREF_KEY_OAUTH_TOKEN, "");
				// Access Token Secret
				String access_token_secret = mSharedPreferences.getString(
						PREF_KEY_OAUTH_SECRET, "");

				AccessToken accessToken = new AccessToken(access_token,
						access_token_secret);
				Twitter twitter = new TwitterFactory(builder.build())
						.getInstance(accessToken);

				// Update status
				twitter4j.Status response = twitter.updateStatus(status);

				Log.d("Status", "> " + response.getText());
			} catch (TwitterException e) {
				// Error in updating status
				Log.d("Twitter Update Error", e.getMessage());
				e.printStackTrace();
			}
			return null;
		}

	}


	@Override
	public void onCreate(Activity x) {
		// TODO Auto-generated method stub
		
	}
	
}