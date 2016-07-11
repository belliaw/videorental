			
Restful API - Video Rental Web Service
================================

Introduction
------------

The application presented in this repository mainly consists 
of a RESTful api which exposes 6 methods. 

The technologies implemented in this project are Spring MVC
and Spring Data, which is configured to connect to a PostGRES
database.

License
-------

The application may be downloaded, modified and used freely for
educational, research and commercial purposes without any obligation 
whatsoever to the author. 

Version
-------

The version presented in this Repository is 1.0.0

Requirements
------------

The web application is to expose, among other methods,
two restFUL web methods in which a user can use to either
rent or return videos as part of a video rental system.

The application provides one API module for access which is:
* org.vr.videorental.api.Controller

GitHub
------ 

The github repository for this appliction is located on 
https://github.com/belliaw/video-rental

Configuration
-------------

The application has its data source configurable via the
resources/configuration.properties file. 

Note: The intitial db mode parameter is set to 'create'.
Once the schema is created, it is suggested that this parameter
is changed to 'validate' so that the application does not drop
and re-create the schema with the consequence of losing data.

Installation
------------

The application may be cloned from https://github.com/belliaw/video-rental,
afterwhich the command 'mvn clean install' needs to be run for the
app to compile and generate the necessary artificats required on deployment.
The application has been tested on Tomcat 7, and required an installed version
of Maven in order to compile.

Running the application
-----------------------

The application may run on a Tomcat web application server, and requires
a working instance of PostGRES database (9.5 recommended). Upon first deployment,
the application creates all the required tables, given that the database is
created beforehand. The default name of the database, as found in the 
'configuration.properties' file is called 'rentaldb'. This may be changed 
to the user's individual requirements. 

API Reference
-------------

The RESTful api of this application consists of 6 methods:

* {URL}/videorental/customer/ - POST creates a new customer:
```
{"name":"George Clooney"}
```

* {URL}/videorental/film/ - POST creates a new film:
```
{"name":"Alice in Wonderland", "rentalType":"Basic", "filmType":"Regular Film"}
```

* {URL}/videorental/customer/ - GET gets all customers:

* {URL}/videorental/film/ - GET gets all films:

* {URL}/videorental/rent/ - POST creates a new film rental record:
```
{
"customerName" : "William Bellia",
"filmNames" : [
  
        "Ben Hur", "Alice in Wonderland"
],
"issueDate" : "04-07-2016",
"daysToRent" : "2"
}
```
* {URL}/videorental/return/ - POST return rented films:
```
{
"customerName" : "William Bellia",
"filmNames" : [
  
        "Ben Hur", "Alice in Wonderland"
]
}
```

Future Enhancements
-------------

The application does not have any effective error handling. A future
enhancement of it would require a proper structured error handling
mechanism such as the efficient use of try/catch blocks etc.

Another enhancement would be that of implementing a DB Thread Pool.
Currently the application does not make use of the application
server's thread pool. Using a library such as HikariCP would
greatly enhance the DB handling mechanism and keep the application's
portability.

Authors
-------------

Application has been developed and deployed by William Bellia.
Contact: william.bellia85@gmail.com

