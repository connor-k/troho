DROP DATABASE if exists Troho;

CREATE DATABASE Troho;

USE Troho;

CREATE TABLE HousingLocations (
  locationID int(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  locationName varchar(50) NOT NULL,
  textAddress varchar(70) NOT NULL,
  description varchar(500) NOT NULL, 
  imageURLs varchar(500) NOT NULL,
  floorplanURLs varchar(500), # currently don't require these
  gpsLatitude varchar(10) NOT NULL, #TODO may want to change to a different identifier
  gpsLongitude varchar(10) NOT NULL,
  distanceToCampus varchar(10) NOT NULL,
  # Average ratings so we don't recalculate every time the page is viewed
  averageManagement varchar(4),
  averageAmenities varchar(4),
  averageLocation varchar(4),
  averageNoise varchar(4),
  averageCommunityChillFactor varchar(4),
  averageRent varchar(4)
);

CREATE TABLE Users (
  userID int(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  userName varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  currentLocationID int(10),
  facebookID varchar(50) NOT NULL, #TODO will probably need to change this
  FOREIGN KEY (currentLocationID) REFERENCES HousingLocations(locationID)
);

CREATE TABLE Friends (
  associationID int(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  userID int(10) NOT NULL,
  friendID int(10) NOT NULL,
  FOREIGN KEY (userID) REFERENCES Users(userID),
  FOREIGN KEY (friendID) REFERENCES Users(userID)
);

CREATE TABLE Surveys (
  surveyID int(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  userID int(10) NOT NULL,
  question1 int(1) NOT NULL,
  question2 int(1) NOT NULL,
  question3 int(1) NOT NULL,
  question4 int(1) NOT NULL,
  question5 int(1) NOT NULL,
  FOREIGN KEY (userID) REFERENCES Users(userID)
);

CREATE TABLE Reviews (
  reviewID int(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  locationID int(10) NOT NULL,
  userID int(10) NOT NULL,
  textComment varchar(500) NOT NULL,
  managementScore int(1) NOT NULL,
  amenitiesScore int(1) NOT NULL,
  locationScore int(1) NOT NULL,
  noiseScore int(1) NOT NULL,
  communityChillFactorScore int(1) NOT NULL,
  rentPaid int(4), # add NOT NULL if we want to enforce them saying
  timeWritten TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (locationID) REFERENCES HousingLocations(locationID),
  FOREIGN KEY (userID) REFERENCES Users(userID)
);
