USE Troho;

INSERT INTO HousingLocations (housingType, locationName, textAddress, description, imageURLs, floorplanURLs, gpsLatitude, gpsLongitude, minutesWalking, minutesBiking) VALUES (1, 'The Lorenzo', '325 W Adams Blvd', 'Luxurious apartments slightly far from USC campus.', './img/LocationImages/lorenzo.jpeg', './img/LocationFloorplans/lorenzo.png', '34.027462', '-118.272988', 20, 10);
INSERT INTO HousingLocations (housingType, locationName, textAddress, description, imageURLs, floorplanURLs, gpsLatitude, gpsLongitude, minutesWalking, minutesBiking) VALUES (1, 'Icon Plaza', '3584 S Figueroa St', 'Small apartment series close to campus', './img/LocationImages/iconplaza.jpeg', './img/LocationFloorplans/iconplaza.png', '34.0184869', '-118.2824651', 5, 3);
INSERT INTO HousingLocations (housingType, locationName, textAddress, description, imageURLs, floorplanURLs, gpsLatitude, gpsLongitude, minutesWalking, minutesBiking) VALUES (1, 'Tuscany', '3770 S. Figueroa St.', 'Apartments close to USC. Very big and quite Luxurious.', './img/LocationImages/tuscany.jpeg', './img/LocationImages/tuscany.png', '34.017273', '-118.28239', 7, 4);
INSERT INTO HousingLocations (housingType, locationName, textAddress, description, imageURLs, floorplanURLs, gpsLatitude, gpsLongitude, minutesWalking, minutesBiking) VALUES (1, 'Gateway Apartments', '124 30th St', 'Most convenient apartment series. You get what you pay for.', './img/LocationImages/gateway.jpeg', './img/LocationFloorplans/gateway.jpeg', '34.022865', '-118.279812', 6, 4);
INSERT INTO HousingLocations (housingType, locationName, textAddress, description, imageURLs, floorplanURLs, gpsLatitude, gpsLongitude, minutesWalking, minutesBiking) VALUES (2, 'Pardee Tower', '614 Hellman Way', 'Pardee Tower was built as part of USC’s commitment to the Olympic Village for the 1984 Summer Games. This eight-floor coed residence hall, close to Doheny Library and McCarthy Quad, alternates genders by floor. Each floor has 18 double rooms, a common bathroom and a laundry facility.', './img/LocationImages/pardee.jpeg', './img/LocationFloorplans/pardee.jpeg', '34.020011', '-118.282598', 3, 1);
SELECT * FROM HousingLocations;

INSERT INTO Users (name, housingKey, email, facebookID, imageURL, isAdmin, verifiedEmail) VALUES ('Steve Johnson', 1, 'steve@asdf.asdf', '10205327756926953', 'http://sparksc.org/img/coreteam/NeelBhoopalam.jpg', false, true);
INSERT INTO Users (name, housingKey, email, facebookID, imageURL, isAdmin, verifiedEmail) VALUES ('Johnny Wang', 2, 'john@asdf.asdf', '12rehfdsu2j34f1d4','http://sparksc.org/img/coreteam/NeelBhoopalam.jpg', false, true);
INSERT INTO Users (name, housingKey, email, facebookID, imageURL, isAdmin, verifiedEmail) VALUES ('Tommy Trojan', 2, 'john@asdf.asdf', '2weuhfdsu2j34f1d4','http://sparksc.org/img/coreteam/NeelBhoopalam.jpg', false, false);
SELECT * FROM Users;

INSERT INTO Friends (facebookID, friendID) VALUES ('2weuhfdsu2j34f1d4', '12rehfdsu2j34f1d4');
INSERT INTO Friends (facebookID, friendID) VALUES ('12rehfdsu2j34f1d4', '2weuhfdsu2j34f1d4');
SELECT * FROM Friends;

INSERT INTO Surveys (facebookID, managementSurveyScore, amenitiesSurveyScore, locationSurveyScore, noiseSurveyScore, communityChillFactorSurveyScore) VALUES ('12rehfdsu2j34f1d4', '5', '5', '5', '5', '5');
INSERT INTO Surveys (facebookID, managementSurveyScore, amenitiesSurveyScore, locationSurveyScore, noiseSurveyScore, communityChillFactorSurveyScore) VALUES ('2weuhfdsu2j34f1d4', '10', '10', '5', '0', '0');
SELECT * FROM Surveys;

INSERT INTO Reviews (housingKey, facebookID, textComment, managementScore, amenitiesScore, locationScore, noiseScore, communityChillFactorScore, timeWritten) VALUES ('1', '2weuhfdsu2j34f1d4', 'my comment', '5', '5', '5', '4', '5', now());
INSERT INTO Reviews (housingKey, facebookID, textComment, managementScore, amenitiesScore, locationScore, noiseScore, communityChillFactorScore, timeWritten) VALUES ('4', '2weuhfdsu2j34f1d4', 'I love Gateway, its awesome!', '5', '5', '5', '4', '5', now());
SELECT * FROM Reviews;
