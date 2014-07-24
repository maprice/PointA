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
**Figure1: Architecture of PointA Project**


PointA consists of three main components: the library, the Eclipse plugin, and the demo app. 

The library is the central component that contains all the code that adapts pointA function calls to provider-specific function calls, gracefully handles exceptions and invokes the user's specified backups when the original services are not functional.

The demo app is designed to test the user's chosen providers and services. It consists of a listview of all the services and the basic functionalities of each service such as “showAd” for ads or "logEvent" for analytics. The demo app tests the user's chosen services before the user integrates these function calls into their own app.

The Eclipse plugin is a simple GUI application that allows the user to specify their chosen proviers and services, automatically downloading jars and updating the classpath and Android manifest to acommodate these servies.


<a href="http://maprice.github.io/PointA/">
![logo](https://raw.github.com/maprice/PointA/gh-pages/images/pointA_Physical_Arch.png)
</a>
**Figure2: Physical Architecture of PointA Project**


From the physical perspective, all of the PointA project code will be open sourced and stored in Github repositories. As shown on the physical diagram, in order for a developer to deploy an app using our PointA Library, he/she will pull the Eclipse Plugin code and the PointA Library code from the repository, the user will then link PointA as a library to their application and as well installing the Eclipse Plugin.  

The user then can enter all the configuration data through the provided GUI. Upon clicking “submit” button on GUI, the plugin will automatically download all the jar files, which are 3rd party libraries stored on the service providers’ servers. The plugin will then put the jar files into the appropriate folders and set up any other dependencies PointA Library needs to use these services. The app developer can then access all those services through the PointA library and once the app is deployed on the mobile phone, it will access the 3rd party services stored on these service provider’s servers though the network. 






