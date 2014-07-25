<a href="http://maprice.github.io/PointA/">
![logo](https://raw.github.com/maprice/PointA/gh-pages/images/pointA_logo.png)
</a>

##Overview

PointA is a open-source Android library that consolidates most of the common services that a typical Android application needs and provides a simple, user-friendly and safe interface for applications to interact with these services. This includes services like Ads, Analytics, and Push Notifications. The goal is to minimize the amount of redundant work and code duplication in implementing these services, provide a safe interface for application to interact with the services, and provide redundancy of services in case one of the serice provider fails.


##Supported Services and Service Providers

The following are the services and service providers that the PointA Library Support:

* Ads
  - Admob
  - AdSense (in progress)
  - Burstly (in progress)

* Analytics
  - Google Analytics
  - Parse Analytics

* Push Notifications
  - Parse  

* Rating
  - Amazon
  - Google Play

* Crash Reporter
  - BugSense
  - BugSnag
  - Crittercism

* Billing
  - Google Play
  - Amazon Market
  

##Instructions: Setting up the PointA Library

1. Clone the PointA Library from our GitHub to your local machine and add PointA as a submodule to your main project
2. Link PointA as a library to your application in your IDE
3. Download and install the Cclipse plugin
4. Run the plugin, which will allow you to configure your chosen services and providers
5. Click save - the plugin will automatically update the classpath and AndroidManifest as well as download jar files
6. The service function calls are now available through the PointA library


##Architecture of the PointA Project 

<a href="http://maprice.github.io/PointA/">
![logo](https://raw.github.com/maprice/PointA/gh-pages/images/PointA_Arch.png)
</a>
**Figure 1: Point A's Architecture**


PointA consists of three main components: the library, the Eclipse plugin, and the demo app. 

The library is the central component that contains all the code that adapts pointA function calls to provider-specific function calls, gracefully handles exceptions and invokes the user's specified backups when the original services are not functional.

The demo app is designed to test the user's chosen providers and services. It consists of a listview of all the services and the basic functionalities of each service such as “showAd” for ads or "logEvent" for analytics. The demo app tests the user's chosen services before the user integrates these function calls into their own app.

The Eclipse plugin is a simple GUI application that allows the user to specify their chosen proviers and services, automatically downloading jars and updating the classpath and Android manifest to acommodate these servies.


<a href="http://maprice.github.io/PointA/">
![logo](https://raw.github.com/maprice/PointA/gh-pages/images/pointA_Physical_Arch.png)
</a>
**Figure 2: Point A's physical organization**
