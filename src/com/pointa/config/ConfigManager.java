package com.pointa.config;

import java.util.HashMap;
import java.util.Map;

import com.pointa.PointA.ServiceType;
import com.pointa.service.ProviderMetaData;

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

	private final Map<ServiceType,ProviderMetaData[]> mProviders;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ConfigManager(){
		mProviders = new HashMap<ServiceType, ProviderMetaData[]>();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void parse(Context defaultContext){
		// Populate mProviders
		Log.d(LOG_TAG,"Max: Launching Parse...");
		try {

			ServiceType curType;
			String curProvider;
			String curKey = "default key";
			String curValue = "default value";
			int curPriority = 1; // if unspecified, 1 is exactly what we want :)
			// if these are still default inside the map, something is wrong...

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();

			XmlResourceParser xrp = defaultContext.getResources().getXml(R.xml.config);

			InputStream inputStream = defaultContext.getResources().openRawResource(R.raw.config);

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
		          } else
		          {
		        	  Log.d(LOG_TAG, "Parser: Found an unrecognized thing");
		          }
		          eventType = xrp.next();
			}
			Log.d(LOG_TAG, "Parser: End of document");
			 */

			int eventType = xrp.next();
			Log.d(LOG_TAG, "Parser: Starting to read");

			while (eventType != XmlResourceParser.END_DOCUMENT)
			{

				if (eventType == XmlResourceParser.START_TAG && xrp.getName().trim().equalsIgnoreCase("service"))
				{
					Log.d(LOG_TAG, "Parser: Found a service!");
					eventType = xrp.next();
					if (eventType == XmlPullParser.START_TAG)
					{
						eventType = xrp.next();
						
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

						eventType = xrp.next();

						if (eventType != XmlResourceParser.END_TAG || !xrp.getName().trim().equalsIgnoreCase("type"))
						{
							Log.e(LOG_TAG, "Parser Error: Expected </type>, found something that isn't an end tag, or an end tag for something that is not type.");
						}

						eventType = xrp.next();

						if (eventType == XmlResourceParser.START_TAG && xrp.getName().trim().equalsIgnoreCase("provider"))
						{
							eventType = xrp.next();
							if (eventType == XmlResourceParser.TEXT)
							{
								Log.d(LOG_TAG, "Parser: Got provider: " + xrp.getText());
								curProvider = xrp.getText();
							}
							else
							{
								Log.e(LOG_TAG, "Parser Error: Expected provider name; found something that isn't text");
							}

							eventType = xrp.next();

							if (eventType != XmlResourceParser.END_TAG || xrp.getName().trim().equalsIgnoreCase("/provider"))
							{
								Log.e(LOG_TAG, "Parser Error: Expected </provider>, found " + xrp.getName() + "!");
							}

							eventType = xrp.next();

							if (eventType == XmlResourceParser.START_TAG && xrp.getName().trim().equalsIgnoreCase("priority"))
							{
								eventType = xrp.next();
								if (eventType == XmlResourceParser.TEXT)
								{
									Log.d(LOG_TAG, "Parser: Got priority: " + xrp.getText());
									curPriority = Integer.parseInt(xrp.getText());
								}
								else
								{
									Log.e(LOG_TAG, "Parser Error: Expected priority; found something that isn't text");
								}

								eventType = xrp.next();

								if (eventType != XmlResourceParser.END_TAG || xrp.getName().trim().equalsIgnoreCase("/priority"))
								{
									Log.e(LOG_TAG, "Parser Error: Expected </priority>");
								}

								// loop to populate parameters
								while (eventType != XmlResourceParser.END_DOCUMENT)
								{
									if (eventType == XmlResourceParser.END_TAG && xrp.getName().trim().equalsIgnoreCase("service"))
									{
										Log.d(LOG_TAG, "Parser: Found the end of a service");
										break;
									}
									eventType = xrp.next();
									
									if (eventType == XmlResourceParser.START_TAG)
									{
										curKey = xrp.getName();
										Log.d(LOG_TAG, "Parser: Got a key: " + xrp.getName());
									}
									
									eventType = xrp.next();
									
									if (eventType == XmlResourceParser.TEXT)
									{
										curValue = xrp.getText();
										Log.d(LOG_TAG, "Parser: Got a value: " + xrp.getText());
									}
									eventType = xrp.next();
									
									//got key and value; now insert into map
	
									
									
								}

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
				eventType = xrp.next();

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

	public ProviderMetaData getProviderMetaData(ServiceType pService, int pPriority){
		ProviderMetaData[] lServiceProvider = mProviders.get(pService);

		if(lServiceProvider == null){
			// Maybe do something smarter than returning null
			return null;
		}
		else if(lServiceProvider.length < pPriority){
			// Do something interesting
			return null;
		}


		return lServiceProvider[pPriority];
	}



}