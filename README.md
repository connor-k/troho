![Troho](github-images/troho.png)

Troho by Troco
==============

Troho is a website that makes finding student housing easy.  It's a central location where all students can view their options, get recommendations, see where their friends live, and write honest reviews.

It was written for the CSCI 201 Final Project in Spring 2015.

#### Team members:
@arushs @CalvinLeGassick @connor-k @neelb @rachelvw @vshubin

#### Other notable features:

 + 3 types of users: guests, registered users, and admins
    + Users sign up with Facebook and verify their @usc.edu email via an auto-generated url
    + Guests can view locations and see reviews, but can't write their own reviews, get recommendations, or see where their friends live
    + Admins have the additional ability to create new housing locations
 + Searches for housing locations are auto-completed and have filters for location, price, average review, and other important criteria
 + Each housing location has a description, list of amenities, possible floor plans, user reviews, and a graph showing the cost of rent over time
 + New reviews posted by other users are updated in real-time without the need to refresh the page
 + Locations are recommended based on a survey that ranks the importance of the management, amenities, location, noise, and chill factor to the user
 + When a user hovers over a housing location on the housing map, their Facebook friends' (that also use Troho) profile pictures appear over that location

#### To make it all work, we used:
 + Apache Tomcat
 + JSP & Servlets (this route was required by the project, and affected many of our other choices)
 + MySQL
 + JSON
 + Javascript
 + HTML/CSS
 + Bootstrap
 + Ajax
 + Commons Mail
 + Chart.js
 + Facebook Login API
 + Google Maps API

#### To test the site:
 1. Download the source
 2. Create a war archive of the source (via jar or Eclipse EE)
 3. Install MySQL and start the localhost server instance
    + For some mock data, either run the ```sql/CreateDatabase.sql``` script or change the file path in ```sql/Startup.java``` lines 86-87
 4. Install Tomcat
    + Remember to configure the MySQL driver
 5. Move/copy the war file to the webapps folder of your Tomcat installation, and start the server
 6. Go to localhost in your browser, celebrate your success
