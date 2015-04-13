USE Troho;

INSERT INTO HousingLocations (housingType, locationName, textAddress, description, imageURLs, floorplanURLs, gpsLatitude, gpsLongitude, minutesWalking, minutesBiking) VALUES ('0', 'First House', '123 30th St', 'A nice house.', 'path/to/images', 'path/to/floorplans', '0', '123', '10','12');
INSERT INTO HousingLocations (housingType, locationName, textAddress, description, imageURLs, floorplanURLs, gpsLatitude, gpsLongitude, minutesWalking, minutesBiking) VALUES ('1', 'Second House', '124 30th St', 'A marginally worse house.', 'path/to/images', 'path/to/floorplans', '0', '123', '10','12');
SELECT * FROM HousingLocations;

INSERT INTO Users (userName, email, facebookID) VALUES ('Steve', 'steve@asdf.asdf', '2weuhfdsu2j34f1d4');
INSERT INTO Users (userName, housingKey, email, facebookID) VALUES ('John', 2, 'john@asdf.asdf', '12rehfdsu2j34f1d4');
SELECT * FROM Users;

INSERT INTO Friends (facebookID, friendID) VALUES ('2weuhfdsu2j34f1d4', '12rehfdsu2j34f1d4');
INSERT INTO Friends (facebookID, friendID) VALUES ('12rehfdsu2j34f1d4', '2weuhfdsu2j34f1d4');
SELECT * FROM Friends;

INSERT INTO Surveys (facebookID, managementSurveyScore, amenitiesSurveyScore, locationSurveyScore, noiseSurveyScore, communityChillFactorSurveyScore) VALUES ('12rehfdsu2j34f1d4', '5', '5', '5', '5', '5');
INSERT INTO Surveys (facebookID, managementSurveyScore, amenitiesSurveyScore, locationSurveyScore, noiseSurveyScore, communityChillFactorSurveyScore) VALUES ('2weuhfdsu2j34f1d4', '10', '10', '5', '0', '0');
SELECT * FROM Surveys;

INSERT INTO Reviews (housingKey, facebookID, textComment, managementScore, amenitiesScore, locationScore, noiseScore, communityChillFactorScore, timeWritten) VALUES ('1', '2weuhfdsu2j34f1d4', 'my comment', '5', '5', '5', '4', '5', now());
SELECT * FROM Reviews;
