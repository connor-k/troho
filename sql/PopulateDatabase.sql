USE Troho;

INSERT INTO HousingLocations (housingType, locationName, textAddress, description, imageURLs, floorplanURLs, gpsLatitude, gpsLongitude, minutesWalking, minutesBiking) VALUES (3, 'The Lorenzo', '325 W Adams Blvd', 'Luxurious apartments slightly far from USC campus.', './img/lorenzo.jpeg', 'path/to/floorplans', '0', '123', 10, 12);
INSERT INTO HousingLocations (housingType, locationName, textAddress, description, imageURLs, floorplanURLs, gpsLatitude, gpsLongitude, minutesWalking, minutesBiking) VALUES (3, 'Icon Plaza', '3584 S Figueroa St', 'Small apartment series close to campus', './img/iconplaza.jpeg', 'path/to/floorplans', '0', '123', 10, 12);
INSERT INTO HousingLocations (housingType, locationName, textAddress, description, imageURLs, floorplanURLs, gpsLatitude, gpsLongitude, minutesWalking, minutesBiking) VALUES (3, 'Tuscany', '3770 S. Figueroa St.', 'Apartments close to USC. Very big and quite Luxurious.', './img/tuscany.jpeg', 'path/to/floorplans', '0', '123', 10, 12);
INSERT INTO HousingLocations (housingType, locationName, textAddress, description, imageURLs, floorplanURLs, gpsLatitude, gpsLongitude, minutesWalking, minutesBiking) VALUES (3, 'Gateway Apartments', '124 30th St', 'Most convenient apartment series. You get what you pay for.', './img/gateway.jpeg', 'path/to/floorplans', '0', '123', 10, 12);
SELECT * FROM HousingLocations;

INSERT INTO Users (userName, housingKey, email, facebookID, isAdmin, verifiedEmail) VALUES ('Steve', 1, 'steve@asdf.asdf', '2weuhfdsu2j34f1d4', false, true);
INSERT INTO Users (userName, housingKey, email, facebookID, isAdmin, verifiedEmail) VALUES ('John', 2, 'john@asdf.asdf', '12rehfdsu2j34f1d4', false, true);
SELECT * FROM Users;

INSERT INTO Friends (facebookID, friendID) VALUES ('2weuhfdsu2j34f1d4', '12rehfdsu2j34f1d4');
INSERT INTO Friends (facebookID, friendID) VALUES ('12rehfdsu2j34f1d4', '2weuhfdsu2j34f1d4');
SELECT * FROM Friends;

INSERT INTO Surveys (facebookID, managementSurveyScore, amenitiesSurveyScore, locationSurveyScore, noiseSurveyScore, communityChillFactorSurveyScore) VALUES ('12rehfdsu2j34f1d4', '5', '5', '5', '5', '5');
INSERT INTO Surveys (facebookID, managementSurveyScore, amenitiesSurveyScore, locationSurveyScore, noiseSurveyScore, communityChillFactorSurveyScore) VALUES ('2weuhfdsu2j34f1d4', '10', '10', '5', '0', '0');
SELECT * FROM Surveys;

INSERT INTO Reviews (housingKey, facebookID, textComment, managementScore, amenitiesScore, locationScore, noiseScore, communityChillFactorScore, timeWritten) VALUES ('1', '2weuhfdsu2j34f1d4', 'my comment', '5', '5', '5', '4', '5', now());
SELECT * FROM Reviews;
INSERT INTO Reviews (housingKey, facebookID, textComment, managementScore, amenitiesScore, locationScore, noiseScore, communityChillFactorScore, timeWritten) VALUES ('3', '2weuhfdsu2j34f1d4', 'I love Gateway, its awesome!', '5', '5', '5', '4', '5', now());


