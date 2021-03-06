DROP DATABASE if exists Troho;

CREATE DATABASE Troho;

USE Troho;

CREATE TABLE HousingLocations (
  housingKey int(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  housingType int(1) NOT NULL,
  locationName varchar(50) NOT NULL,
  textAddress varchar(70) NOT NULL,
  description varchar(500) NOT NULL,
  amenities varchar(500) NOT NULL, 
  imageURLs varchar(500) NOT NULL,
# Currently don't require floorplan
  floorplanURLs varchar(500),
  gpsLatitude varchar(12) NOT NULL,
  gpsLongitude varchar(12) NOT NULL,
  minutesWalking int(2) NOT NULL,
  minutesBiking int(2) NOT NULL
);

CREATE TABLE Users (
  facebookID varchar(50) PRIMARY KEY NOT NULL,
  name varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  imageURL varchar(500) NOT NULL,
  housingKey int(10),
  isAdmin bool NOT NULL,
  verifiedEmail bool NOT NULL,
  validationKey varchar(50),
  FOREIGN KEY (housingKey) REFERENCES HousingLocations(housingKey)
);

CREATE TABLE Friends (
  associationID int(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  facebookID varchar(50) NOT NULL,
  friendID varchar(50) NOT NULL,
  FOREIGN KEY (facebookID) REFERENCES Users(facebookID),
  FOREIGN KEY (friendID) REFERENCES Users(facebookID)
);

CREATE TABLE Surveys (
  surveyID int(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  facebookID varchar(50) NOT NULL,
  managementSurveyScore int(1) NOT NULL,
  amenitiesSurveyScore int(1) NOT NULL,
  locationSurveyScore int(1) NOT NULL,
  noiseSurveyScore int(1) NOT NULL,
  communityChillFactorSurveyScore int(1) NOT NULL,
  FOREIGN KEY (facebookID) REFERENCES Users(facebookID)
);

CREATE TABLE Reviews (
  reviewID int(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  housingKey int(10) NOT NULL,
  facebookID varchar(50) NOT NULL,
  textComment varchar(500) NOT NULL,
  managementScore int(1) NOT NULL,
  amenitiesScore int(1) NOT NULL,
  locationScore int(1) NOT NULL,
  noiseScore int(1) NOT NULL,
  communityChillFactorScore int(1) NOT NULL,
  rentPaid int(4),
  tag1 bool,
  tag2 bool,
  tag3 bool,
  tag4 bool,
  tag5 bool,
  tag6 bool,
  timeWritten TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (housingKey) REFERENCES HousingLocations(housingKey),
  FOREIGN KEY (facebookID) REFERENCES Users(facebookID)
);
