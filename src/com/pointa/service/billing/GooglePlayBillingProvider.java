package com.pointa.service.billing;

import java.util.Map;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.pointa.service.PointAServiceFactory;
import com.pointa.service.billing.utils.IabHelper;
import com.pointa.service.billing.utils.IabResult;
import com.pointa.service.billing.utils.Inventory;
import com.pointa.service.billing.utils.Purchase;

public class GooglePlayBillingProvider implements BillingAdapter{


	static final String LOG_TAG = PointAServiceFactory.class.getSimpleName();


	//Array of 3 of hashmaps

	//List of consumable
	//HashMap<SKU, int quantity>

	//List of non-consumable
	//HashMap<SKU, bool owned>

	//List of subscriptions

	// SKUs for our products: the premium upgrade (non-consumable) and gas (consumable)
	static final String SKU_PREMIUM = "premium";

	// Consumable
	static final String SKU_GAS = "gas";

	// SKU for our subscription
	static final String SKU_INFINITE_GAS = "infinite_gas";

	// (arbitrary) request code for the purchase flow
	static final int RC_REQUEST = 10001;


	// The helper object
	IabHelper mHelper;

	String mPackageName;

	@Override
	public void init(Map<String, String> pParams, Application pApp)
			throws Exception {

		mPackageName = pApp.getPackageName();
		/* base64EncodedPublicKey should be YOUR APPLICATION'S PUBLIC KEY
		 * (that you got from the Google Play developer console). This is not your
		 * developer public key, it's the *app-specific* public key.
		 *
		 * Instead of just storing the entire literal string here embedded in the
		 * program,  construct the key at runtime from pieces or
		 * use bit manipulation (for example, XOR with some other string) to hide
		 * the actual key.  The key itself is not secret information, but we don't
		 * want to make it easy for an attacker to replace the public key with one
		 * of their own and then fake messages from the server.
		 */
		String base64EncodedPublicKey = "CONSTRUCT_YOUR_KEY_AND_PLACE_IT_HERE";

		// Some sanity checks to see if the developer (that's you!) really followed the
		// instructions to run this sample (don't put these checks on your app!)
		if (base64EncodedPublicKey.contains("CONSTRUCT_YOUR")) {
			throw new RuntimeException("Please put your app's public key in MainActivity.java. See README.");
		}
		if (pApp.getPackageName().startsWith("com.example")) {
			throw new RuntimeException("Please change the sample's package name! See README.");
		}

		// Create the helper, passing it our context and the public key to verify signatures with
		Log.d(LOG_TAG, "Creating IAB helper.");
		mHelper = new IabHelper(pApp, base64EncodedPublicKey);

		// enable debug logging (for a production application, you should set this to false).
		mHelper.enableDebugLogging(true);

		// Start setup. This is asynchronous and the specified listener
		// will be called once setup completes.
		Log.d(LOG_TAG, "Starting setup.");
		mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
			public void onIabSetupFinished(IabResult result) {
				Log.d(LOG_TAG, "Setup finished.");

				if (!result.isSuccess()) {
					// Oh noes, there was a problem.
					Log.e(LOG_TAG, "Problem setting up in-app billing: " + result);
					return;
				}

				// Have we been disposed of in the meantime? If so, quit.
				if (mHelper == null) return;

				// IAB is fully set up. Now, let's get an inventory of stuff we own.
				Log.d(LOG_TAG, "Setup successful. Querying inventory.");

			}
		});
	}



	// Listener that's called when we finish querying the items and subscriptions we own
	IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
		public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
			Log.d(LOG_TAG, "Query inventory finished.");

			// Have we been disposed of in the meantime? If so, quit.
			if (mHelper == null) return;

			// Is it a failure?
			if (result.isFailure()) {
				Log.e(LOG_TAG, "Failed to query inventory: " + result);
				return;
			}

			Log.d(LOG_TAG, "Query inventory was successful.");

			/*
			 * Check for items we own. Notice that for each purchase, we check
			 * the developer payload to see if it's correct! See
			 * verifyDeveloperPayload().
			 */

			/*
            // Do we have the premium upgrade?
            Purchase premiumPurchase = inventory.getPurchase(SKU_PREMIUM);
            mIsPremium = (premiumPurchase != null && verifyDeveloperPayload(premiumPurchase));
            Log.d(TAG, "User is " + (mIsPremium ? "PREMIUM" : "NOT PREMIUM"));

            // Do we have the infinite gas plan?
            Purchase infiniteGasPurchase = inventory.getPurchase(SKU_INFINITE_GAS);
            mSubscribedToInfiniteGas = (infiniteGasPurchase != null &&
                    verifyDeveloperPayload(infiniteGasPurchase));
            Log.d(TAG, "User " + (mSubscribedToInfiniteGas ? "HAS" : "DOES NOT HAVE")
                        + " infinite gas subscription.");
            if (mSubscribedToInfiniteGas) mTank = TANK_MAX;

            // Check for gas delivery -- if we own gas, we should fill up the tank immediately
            Purchase gasPurchase = inventory.getPurchase(SKU_GAS);
            if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                Log.d(TAG, "We have gas. Consuming it.");
                mHelper.consumeAsync(inventory.getPurchase(SKU_GAS), mConsumeFinishedListener);
                return;
            }

            updateUi();
            setWaitScreen(false);
            Log.d(TAG, "Initial inventory query finished; enabling main UI.");
			 */
		}
	};



	@Override
	public String[] queryInventory() {
		mHelper.queryInventoryAsync(mGotInventoryListener);
		return null;
	}
	/** Verifies the developer payload of a purchase. */
	boolean verifyDeveloperPayload(Purchase p) {
		String payload = p.getDeveloperPayload();

		/*
		 * TODO: verify that the developer payload of the purchase is correct. It will be
		 * the same one that you sent when initiating the purchase.
		 *
		 * WARNING: Locally generating a random string when starting a purchase and
		 * verifying it here might seem like a good approach, but this will fail in the
		 * case where the user purchases an item on one device and then uses your app on
		 * a different device, because on the other device you will not have access to the
		 * random string you originally generated.
		 *
		 * So a good developer payload has these characteristics:
		 *
		 * 1. If two different users purchase an item, the payload is different between them,
		 *    so that one user's purchase can't be replayed to another user.
		 *
		 * 2. The payload must be such that you can verify it even when the app wasn't the
		 *    one who initiated the purchase flow (so that items purchased by the user on
		 *    one device work on other devices owned by the user).
		 *
		 * Using your own server to store and verify developer payloads across app
		 * installations is recommended.
		 */

		return true;
	}

	// Callback for when a purchase is finished
	IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
		public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
			Log.d(LOG_TAG, "Purchase finished: " + result + ", purchase: " + purchase);

			// if we were disposed of in the meantime, quit.
			if (mHelper == null) return;

			if (result.isFailure()) {
				Log.e(LOG_TAG, "Error purchasing: " + result);
				return;
			}
			if (!verifyDeveloperPayload(purchase)) {
				Log.e(LOG_TAG,"Error purchasing. Authenticity verification failed.");
				return;
			}

			Log.d(LOG_TAG, "Purchase successful.");
			/*
            if (purchase.getSku().equals(SKU_GAS)) {
                // bought 1/4 tank of gas. So consume it.
                Log.d(TAG, "Purchase is gas. Starting gas consumption.");
                mHelper.consumeAsync(purchase, mConsumeFinishedListener);
            }
            else if (purchase.getSku().equals(SKU_PREMIUM)) {
                // bought the premium upgrade!
                Log.d(TAG, "Purchase is premium upgrade. Congratulating user.");
                alert("Thank you for upgrading to premium!");
                mIsPremium = true;
                updateUi();
                setWaitScreen(false);
            }
            else if (purchase.getSku().equals(SKU_INFINITE_GAS)) {
                // bought the infinite gas subscription
                Log.d(TAG, "Infinite gas subscription purchased.");
                alert("Thank you for subscribing to infinite gas!");
                mSubscribedToInfiniteGas = true;
                mTank = TANK_MAX;
                updateUi();
                setWaitScreen(false);
            }
        }
			 */
		}};

		// Called when consumption is complete
		IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
			public void onConsumeFinished(Purchase purchase, IabResult result) {
				Log.d(LOG_TAG, "Consumption finished. Purchase: " + purchase + ", result: " + result);

				// if we were disposed of in the meantime, quit.
				if (mHelper == null) return;

				// We know this is the "gas" sku because it's the only one we consume,
				// so we don't check which sku was consumed. If you have more than one
				// sku, you probably should check...
				if (result.isSuccess()) {
					// successfully consumed, so we apply the effects of the item in our
					// game world's logic, which in our case means filling the gas tank a bit
					Log.d(LOG_TAG, "Consumption successful. Provisioning.");
					//mTank = mTank == TANK_MAX ? TANK_MAX : mTank + 1;
					//saveData();
					//alert("You filled 1/4 tank. Your tank is now " + String.valueOf(mTank) + "/4 full!");
				}
				else {
					Log.e(LOG_TAG, "Error while consuming: " + result);
				}

				Log.d(LOG_TAG, "End consumption flow.");
			}
		};


		@Override
		public void consumeItem(String pSku) {
			// TODO Auto-generated method stub

		}
		@Override
		public void purchaseItem(String pSku) {
			// TODO Auto-generated method stub

		}
		@Override
		public Bundle getItemDetails(String pSku) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void pointActivityResult(int requestCode, int resultCode, Intent data) {
			Log.d(LOG_TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);
			if (mHelper == null) return;

			// Pass on the activity result to the helper for handling
			if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
				// not handled, so handle it ourselves (here's where you'd
				// perform any handling of activity results not related to in-app
				// billing...
				//super.onActivityResult(requestCode, resultCode, data);
			}
			else {
				Log.d(LOG_TAG, "onActivityResult handled by IABUtil.");
			}
		}

		@Override
		public void onDestroy() {
			// very important:
			Log.d(LOG_TAG, "Destroying helper.");
			if (mHelper != null) {
				mHelper.dispose();
				mHelper = null;
			}
		}
}