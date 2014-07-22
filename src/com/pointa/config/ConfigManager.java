package com.pointa.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import com.maprice.pointa.R;
import com.pointa.PointA;
import com.pointa.PointA.ServiceType;
import com.pointa.service.ProviderMetaData;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Manages parsing and storing configuration data from PointAConfig.xml
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class ConfigManager{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = ConfigManager.class.getSimpleName();


	// ===========================================================
	// Fields
	// ===========================================================

	private final Map<ServiceType,ArrayList<ProviderMetaData>> mProviders;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ConfigManager(){
		mProviders = new HashMap<ServiceType, ArrayList<ProviderMetaData>>();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void parse(Context defaultContext){
		// Populate mProviders
		Log.d(LOG_TAG,"Max: Launching Parse...");
		try {

			ServiceType curType = null;
			String curProvider = "default provider";
			String curKey = "default key";
			String curValue = "default value";

			int curPriority = 1; // if unspecified, 1 is exactly what we want :)
			// if these are still default inside the map, something is wrong...

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			// XmlPullParser xpp = factory.newPullParser();

			XmlResourceParser xrp = defaultContext.getResources().getXml(R.xml.config);

			// InputStream inputStream = defaultContext.getResources().openRawResource(R.raw.config);

			Log.d(LOG_TAG, "Parser: input set");

			//Test input, make sure it's being read correctly
			/*
			BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
			String x = "";
			x = r.readLine();

			while(x!= null){
			Log.d(LOG_TAG, "Parser Raw Text: " + x);
			x = r.readLine();
			}

			while (eventType != XmlPullParser.END_DOCUMENT)
			{
				if(eventType == XmlPullParser.START_DOCUMENT) {
		              Log.d(LOG_TAG,"Parser: Start document");
		          } else if(eventType == XmlPullParser.START_TAG) {
		        	  Log.d(LOG_TAG,"Parser: Start tag "+xrp.getName());
		          } else if(eventType == XmlPullParser.END_TAG) {
		        	  Log.d(LOG_TAG,"Parser: End tag "+xrp.getName());
		          } else if(eventType == XmlPullParser.TEXT) {
		        	  Log.d(LOG_TAG,"Parser: Text "+xrp.getText());
		          } else {
		        	  Log.d(LOG_TAG, "Parser: Found an unrecognized thing");
		          }
		          eventType = xrp.next();
			}
			Log.d(LOG_TAG, "Parser: End of document");
			 */

			Log.d(LOG_TAG, "Parser: Starting to read");
			int eventType = xrp.next(); //<start document>
			eventType = xrp.next(); // <pointAconfig>

			while (eventType != XmlResourceParser.END_DOCUMENT)
			{
				eventType = xrp.next(); // <service>

				if (eventType == XmlResourceParser.START_TAG && xrp.getName().trim().equalsIgnoreCase("service"))
				{
					Log.d(LOG_TAG, "Parser: Found a service!");

					eventType = xrp.next(); //<type>

					if (eventType == XmlPullParser.START_TAG)
					{
						eventType = xrp.next(); // "type"

						Log.d(LOG_TAG,"Parser: Found a type:" + xrp.getText().trim() + "!");

						if (xrp.getText().trim().equalsIgnoreCase("ads"))
						{
							curType = ServiceType.Ads;
						}
						else if(xrp.getText().trim().equalsIgnoreCase("analytics"))
						{
							curType = ServiceType.Analytics;
						}
						else if(xrp.getText().trim().equalsIgnoreCase("crashreporter"))
						{
							curType = ServiceType.CrashReporter;
						}
						else if(xrp.getText().trim().equalsIgnoreCase("rating"))
						{
							curType = ServiceType.Rating;
						}
						else if(xrp.getText().trim().equalsIgnoreCase("push"))
						{
							curType = ServiceType.Push;
						}
						else
						{
							Log.e(LOG_TAG,"Parser Error: Found an unrecognized service type: " + xrp.getText());
						}

						eventType = xrp.next(); //</type>

						if (eventType != XmlResourceParser.END_TAG || !xrp.getName().trim().equalsIgnoreCase("type"))
						{
							Log.e(LOG_TAG, "Parser Error: Expected </type>, found something that isn't an end tag, or an end tag for something that is not type.");
						}

						eventType = xrp.next(); //<provider>

						if (eventType == XmlResourceParser.START_TAG && xrp.getName().trim().equalsIgnoreCase("provider"))
						{
							eventType = xrp.next(); // "provider"
							if (eventType == XmlResourceParser.TEXT)
							{
								Log.d(LOG_TAG, "Parser: Got provider: " + xrp.getText());
								curProvider = xrp.getText();
							}
							else
							{
								Log.e(LOG_TAG, "Parser Error: Expected provider name; found something that isn't text");
							}

							eventType = xrp.next(); // </provider>

							if (eventType != XmlResourceParser.END_TAG || xrp.getName().trim().equalsIgnoreCase("/provider"))
							{
								Log.e(LOG_TAG, "Parser Error: Expected </provider>, found " + xrp.getName() + "!");
							}

							HashMap<Integer, ProviderMetaData> curParams = new HashMap<Integer, ProviderMetaData>();
							HashMap<String, String> simpleParamsMap = new HashMap<String, String>();
							
							eventType = xrp.next(); // <priority>

							if (eventType == XmlResourceParser.START_TAG && xrp.getName().trim().equalsIgnoreCase("priority"))
							{
								eventType = xrp.next(); // "priority"
								if (eventType == XmlResourceParser.TEXT)
								{
									Log.d(LOG_TAG, "Parser: Got priority: " + xrp.getText());
									curPriority = Integer.parseInt(xrp.getText());
								}
								else
								{
									Log.e(LOG_TAG, "Parser Error: Expected priority; found something that isn't text");
								}

								eventType = xrp.next(); // </priority>

								if (eventType != XmlResourceParser.END_TAG || xrp.getName().trim().equalsIgnoreCase("/priority"))
								{
									Log.e(LOG_TAG, "Parser Error: Expected </priority>");
								}

								if (curParams.get(curPriority) != null)
								{
									Log.e(LOG_TAG, "Error: attempting to overwrite an existing priority.");
								}
								curParams.put(curPriority, new ProviderMetaData(curProvider, new HashMap<String, String>()));

								// loop to populate parameters
								while (eventType != XmlResourceParser.END_DOCUMENT)
								{	
									eventType = xrp.next(); // <key>

									if (eventType == XmlResourceParser.START_TAG)
									{
										curKey = xrp.getName();
										Log.d(LOG_TAG, "Parser: Got a key: " + xrp.getName());
									} 
									else if ( eventType == XmlResourceParser.END_TAG && xrp.getName().trim().equalsIgnoreCase("service"))
									{
										Log.d(LOG_TAG, "Parser: Found the end of a service: " + curProvider);
										break;
									}

									eventType = xrp.next(); // "value"

									if (eventType == XmlResourceParser.TEXT)
									{
										curValue = xrp.getText();
										Log.d(LOG_TAG, "Parser: Got a value: " + xrp.getText());
									}
									eventType = xrp.next(); // </key>

									if (curParams.get(curPriority).getParams() == null)
									{
										Log.d(LOG_TAG, "getParams is null");
									}
									curParams.get(curPriority).getParams().put(curKey, curValue);
									simpleParamsMap.put(curKey, curValue);
								}
								TreeMap<Integer, ProviderMetaData> sortedParams = new TreeMap<Integer, ProviderMetaData>();
								for (Integer i: curParams.keySet())
								{
									//sortedParams.put(i, curParams.get(i));
									sortedParams.put(i, new ProviderMetaData(curProvider, simpleParamsMap));
								}
								for (Integer i: curParams.keySet())
								{
									Log.d(LOG_TAG, "Inserting priority: " + i + ", thing: " + curProvider);
								}
								//mProviders.put(curType, sortedParams.values().toArray(new ProviderMetaData[sortedParams.size()]));
								// outdated code - only allows one provider per service
								
								Log.d(LOG_TAG, "curtype: " + curType.toString());
								Log.d(LOG_TAG, "curPriority: " + curPriority);

								if (mProviders.get(curType) == null)
								{
									mProviders.put(curType, new ArrayList<ProviderMetaData>());
								}
								
								
								mProviders.get(curType).add(new ProviderMetaData(curProvider, simpleParamsMap));
								//mProviders.get(curType)[curPriority] = new ProviderMetaData();
								//Log.d(LOG_TAG, "Array Value: " + mProviders.get(curType).get(curPriority));
							}

						}
						else {
							Log.e(LOG_TAG, "Parser Error: Expected <provider>, found something different");
						}

					} else
					{
						Log.e(LOG_TAG, "Parser: Error: Expected <service>, found instead: " + xrp.getName());
						//break; // maybe instead of breaking we just move on to the next service?
					}

				}

			}
			xrp.close();

		} catch (XmlPullParserException e) {
			Log.e(LOG_TAG, "Parser Error: XMLPullParser Exception");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(LOG_TAG, "Parser Error: IO Exception");
			e.printStackTrace();
		}



	}

	public void printmProviders()
	{
		Log.d(LOG_TAG, "Testing Printer: ");
		Log.d(LOG_TAG, "<pointAconfig>");
		//int providerIndex = 0;
		for (ServiceType serType: mProviders.keySet())
		{
			for (int providerIndex = 0; providerIndex < mProviders.get(serType).size(); providerIndex++)
			{
				Log.d(LOG_TAG, Integer.toString(providerIndex) + " of " + Integer.toString(mProviders.get(serType).size()));
				Log.d(LOG_TAG, "<service>");
				Log.d(LOG_TAG, "<type>");
				Log.d(LOG_TAG, serType.toString());
				Log.d(LOG_TAG, "</type>");
				Log.d(LOG_TAG, "<provider>");
				Log.d(LOG_TAG, mProviders.get(serType).get(providerIndex).getName());
				Log.d(LOG_TAG, "</provider>");
				Log.d(LOG_TAG, "<priority>");
				Log.d(LOG_TAG, Integer.toString(providerIndex));
				Log.d(LOG_TAG, "</priority>");

				for (String key: mProviders.get(serType).get(providerIndex).getParams().keySet())
					// oh lawd that's ugly
				{
					Log.d(LOG_TAG, "<" + key + ">");
					Log.d(LOG_TAG, mProviders.get(serType).get(providerIndex).getParams().get(key));
					Log.d(LOG_TAG, "</" + key + ">");
				}
				Log.d(LOG_TAG, "</service>");
			}
		}
		
		Log.e(LOG_TAG, "</pointAconfig>");
	}

	public ProviderMetaData getProviderMetaData(ServiceType pService, int pPriority){
		ArrayList<ProviderMetaData> lServiceProvider = mProviders.get(pService);

		if(lServiceProvider == null){
			// Maybe do something smarter than returning null
			return null;
		}
		else if(lServiceProvider.size() < pPriority){
			// Do something interesting
			return null;
		}


		return lServiceProvider.get(pPriority);
	}



}