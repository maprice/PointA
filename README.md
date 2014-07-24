<a href="http://maprice.github.io/PointA/">
![logo](https://raw.github.com/maprice/PointA/gh-pages/images/pointA_logo.png)
</a>

##Overview

PointA is a open-source Android library that consolidates most of the common services that a typical Android application needs and provides a simple, user-friendly and safe interface for application to interact with these services. This includes services like Ads, Analytics, Push Notifications, Facebook/Twitter. The goal is to minimize the amount of redundant work and code duplication in implementing these services, provide a safe interface for application to interact with the services, and provide redundancy of services in case one of the serice provider fails.


##Supported Services and Service Providers

The following are the services and service providers that the PointA Library Support:

* Ads
  - Admob
  - AdSense
  - Burstly

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
  

##Instructions on Setting up PointA Library

1. Clone the PointA Library to the local machine and add PointA as a submodule to their main project
2. Link PointA as a library to their application on Android
3. Download and install the eclipse plugin
4. Run the plugin, which will bring up a GUI and configure the services(e.g. service providers and priorities)
5. Click save, the plugin will automatically download the necessarily files and take care all dependencies for the services
6. The service function calls are now available to the users through the PointA Library


##Architecture of the PointA Project 

====INSERT component architecture image here ======


From a high level perspective, the PointA project consists of three main components: the PointA library, the PointA Eclipse Plugin and the PointA Android App. As shown in the component diagram, the most crucial component of the  project is the PointA library, and its main purpose is to abstract away all the details of these service providers and provide one simple and user-friendly interface for users use these service providers.  

The PointA Android app is a simple android application that utilizes the services that the PointA library provides (e.g. ads, crash reporting, analytics, etc.). It consists of a listview of all the services and the basic functionalities of each service such as “showAd” for ads or logEvent for analytics. In order for our PointA library to know what services need to be supported and what providers are needed. We need a way for the users to inform the PointA library of the data specific to each application’s configuration. We accomplish this through the Eclipse plugin and a config.xml file, which minimizes the dependencies between the PointA Library and the Eclipse Plugin. 

The Eclipse plugin is an Eclipse extensible application that allows users to input configuration data through a GUI, which the plugin will then write it to an xml file. It also takes care of other dependencies such as downloading jars, setting up build path and modify/generate the appropriate xml files. Therefore the plugin updates the PointA library as shown in the component diagram through user inputs.


#Design of the PointA Library


===== INSERT POINTA LIB CLASS DIAGRAM HERER ======




















