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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
 
public class Twitter4JTwitterProvider implements TwitterAdapter {
    // Constants
    /**
     * Register your here app https://dev.twitter.com/apps/new and get your
     * consumer key and secret
     * */
    static String TWITTER_CONSUMER_KEY = "7Tsi0Hlu227dbYf1fo0AP5dr2";
    static String TWITTER_CONSUMER_SECRET = "geUvTeutweRhWsdJJjLcxrmNhY0n3kajRlXJ8FLHIhGDPF6I7T";
 
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

    // Progress dialog
    ProgressDialog pDialog;
 
    // Twitter
    private static Twitter twitter;
    private static RequestToken requestToken;
     
    // Shared Preferences
    private static SharedPreferences mSharedPreferences;
     
    // Internet Connection detector
    private ConnectionDetector cd;
     
    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
 
    @Override
    public void init(Map<String, String> pParams, Application pApp) {
       // super.onCreate(savedInstanceState);
      //(R.layout.activity_main);
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
         
        cd = new ConnectionDetector(pApp.getApplicationContext());
 
        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            Log.e("Internet Connection Error","Please connect to working Internet connection");
            // stop executing code by return
            return;
        }
         
        // Check if twitter keys are set
        if(TWITTER_CONSUMER_KEY.trim().length() == 0 || TWITTER_CONSUMER_SECRET.trim().length() == 0){
            // Internet Connection is not present
           Log.e("Twitter oAuth tokens", "Please set your twitter oauth tokens first!");
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
						
		          
					} catch (Exception e) {
						e.printStackTrace();
						Log.e("poop", "Already Logged into twitter");
					}
				}
			});
			thread.start();
            
  
        } else {
            // user already logged into twitter
           Log.e("pplb",
                    "Already Logged into twitter");
        }
    }
 
    /**
     * Function to update status
     * */
    class updateTwitterStatus extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
  
           Log.e("sadf" , "Updating to twitter...");
  
        }
 
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
                String access_token = mSharedPreferences.getString(PREF_KEY_OAUTH_TOKEN, "");
                // Access Token Secret
                String access_token_secret = mSharedPreferences.getString(PREF_KEY_OAUTH_SECRET, "");
                 
                AccessToken accessToken = new AccessToken(access_token, access_token_secret);
                Twitter twitter = new TwitterFactory(builder.build()).getInstance(accessToken);
                 
                // Update status
                twitter4j.Status response = twitter.updateStatus(status);
                 
                Log.d("Status", "> " + response.getText());
            } catch (TwitterException e) {
                // Error in updating status
                Log.d("Twitter Update Error", e.getMessage());
            }
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog and show
         * the data in UI Always use runOnUiThread(new Runnable()) to update UI
         * from background thread, otherwise you will get error
         * **/
        protected void onPostExecute(String file_url) {

              
                    Log.e("asdf", "Status tweeted successfully");
                    // Clearing EditText field
           
                
         
        }
 
    }
 
    /**
     * Function to logout from twitter
     * It will just clear the application shared preferences
     * */
    @Override
    public void logoutFromTwitter() {
        // Clear the shared preferences
        Editor e = mSharedPreferences.edit();
        e.remove(PREF_KEY_OAUTH_TOKEN);
        e.remove(PREF_KEY_OAUTH_SECRET);
        e.remove(PREF_KEY_TWITTER_LOGIN);
        e.commit();
 
        // After this take the appropriate action
        // I am showing the hiding/showing buttons again
        // You might not needed this code
      
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
		 // Call update status function
        // Get the status from EditText
        String status = pTweet;

        // Check for blank text
        if (status.trim().length() > 0) {
            // update status
            new updateTwitterStatus().execute(status);
        } else {
            // EditText is empty
            Log.e("asdf", "Please enter status message");
        }
	}


	@Override
	public void onCreate(Activity pActivity) {
		
        /** This if conditions is tested once is
         * redirected from twitter page. Parse the uri to get oAuth
         * Verifier
         * */
        if (!isTwitterLoggedInAlready()) {
            Uri uri = pActivity.getIntent().getData();
            if (uri != null && uri.toString().startsWith(TWITTER_CALLBACK_URL)) {
                // oAuth verifier
                String verifier = uri
                        .getQueryParameter(URL_TWITTER_OAUTH_VERIFIER);
 
                try {
                    // Get the access token
                    AccessToken accessToken = twitter.getOAuthAccessToken(
                            requestToken, verifier);
 
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
 
                    Log.e("Twitter OAuth Token", "> " + accessToken.getToken());
 
                 
                } catch (Exception e) {
                    // Check log for login errors
                    Log.e("Twitter Login Error", "> " + e.getMessage());
                }
            }
        }
	}


 
}