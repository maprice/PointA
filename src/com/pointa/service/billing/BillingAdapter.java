package com.pointa.service.billing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.pointa.service.PointAService;

public interface BillingAdapter extends PointAService{

	public String[] queryInventory();
	public void consumeItem(String pSku);
	public void purchaseItem(Activity pActivity, String pSku);
	public Bundle getItemDetails(String pSku);

	public boolean onActivityResult(int requestCode, int resultCode, Intent data);

	public void onDestroy();
}
