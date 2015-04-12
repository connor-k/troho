USE Troho;

INSERT INTO HousingLocations (housingType, locationName, textAddress, description, imageURLs, floorplanURLs, gpsLatitude, gpsLongitude, distanceToCampus) VALUES ('0', 'First House', '123 30th St', 'A nice house.', 'path/to/images', 'path/to/floorplans', '0', '123', '10|12');
INSERT INTO HousingLocations (housingType, locationName, textAddress, description, imageURLs, floorplanURLs, gpsLatitude, gpsLongitude, distanceToCampus) VALUES ('1', 'Second House', '124 30th St', 'A marginally worse house.', 'path/to/images', 'path/to/floorplans', '0', '123', '10|12');
SELECT * FROM HousingLocations;

INSERT INTO Users (userName, email, facebookID) VALUES ('Steve', 'asdf@asdf.asdf', '2weuhfdsu2j34f');
INSERT INTO Users (userName, housingKey, email, facebookID) VALUES ('John', 2, 'asdf@asdf.asdf', '23rfsajkdf');
SELECT * FROM Users;

INSERT INTO Friends (userId, friendID) VALUES ('1', '2');
INSERT INTO Friends (userId, friendID) VALUES ('2', '1');
SELECT * FROM Friends;

INSERT INTO Surveys (userID, managementSurveyScore, amenitiesSurveyScore, locationSurveyScore, noiseSurveyScore, communityChillFactorSurveyScore) VALUES ('1', '10', '10', '5', '0', '0');
SELECT * FROM Surveys;

INSERT INTO Reviews (housingKey, userID, textComment, managementScore, amenitiesScore, locationScore, noiseScore, communityChillFactorScore, timeWritten) VALUES ('1', '2', 'my comment', '5', '5', '5', '4', '5', now());
SELECT * FROM Reviews;
