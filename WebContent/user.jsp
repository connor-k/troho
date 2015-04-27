<%@page import="sql.UserDataManager"%>
<%@page import="sql.User"%>
<%@page import="Trie.PreferenceCalculator"%>
<%@page import="sql.HousingLocation"%>
<%@page import="sql.HousingDataManager"%>
<%@ page errorPage="404.html" %>

<%
	User user = null;
	String fbID = request.getParameter("id");
		
	if (fbID != null) {
		user = UserDataManager.getUser(fbID);
	} else {
		String redirectURL = "/troho/404.html";
    	response.sendRedirect(redirectURL);
	}
	
	String currentHousingLocationName = null;
	if(user.currentLocation == null) {
		currentHousingLocationName = "Housing Location Not Set";
	}
	else {
		currentHousingLocationName = user.currentLocation.locationName;
	}
	
	String stringUSCVerifiedEmail = null;
	
	System.out.println("Verified Email: " + user.verifiedEmail);
	
	if(user.verifiedEmail) {
		stringUSCVerifiedEmail = user.email;
	}
	else {
		stringUSCVerifiedEmail = "Email Not Verified";
	}
	System.out.println(stringUSCVerifiedEmail);
	
%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/user.css">
<link href="css/header-bar.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet">


</head>

<body>

	<script src="js/customFB.js">
	</script>
	
	<div class="header">
		<div id="troho-logo">
			<a href="index.jsp"><img id="home-logo" src="./img/new-troho.png" /></a>
		</div>

		<div class="log-div">
			<div id="log-in-sequence" onclick="logIn()" style="display: none">
				<div id="log-in-message">Log in with Facebook</div>
				<img id="log-in-button" src="./img/FacebookIcon.png" />
			</div>
			<div id="user-sequence" onclick="goToUser()">
				<div class="welcome" id="welcome-message"></div>
				<img id="profile-image" src=" "></img>
			</div>
			<div id="log-out-sequence" onclick="logOut()">
				<div id="log-out-message"></div>
			</div>
		</div>
	</div>

	<br>

	<div class = "container-fluid">
		<div class = "row" style = "margin-top:120px; min-height:380px;">
			<div class = "col-sm-4 user-image-container">
				<img id="user-image" src="<%= user.imageURL %>">	
			</div>

			<div class = "col-sm-8 user-info-container">
				<div style = "text-align:center">
					<p style = "font-size:40px"> <%= user.name %> </p>

					<div id="post-verified-email-address"><p style = "font-size:24px"> <%=stringUSCVerifiedEmail%> </p></div>

					<form id="email-form-input" class="form-inline" value="0">
						<div class="form-group">
							<label for="verification-email-input">USC Email</label> <input type="email"
								class="form-control" id="verification-email-input"
								placeholder="tommytrojan@usc.edu">
						</div>
						
						<button type="button" onclick="sendVerificationEmail()" class="btn btn-default">Send
							Verification</button>
					</form>

					<div>
						<div class="btn-group btn-group-lg" style="margin: 10px">
							<button type="button" class="btn btn-default dropdown-toggle"
								data-toggle="dropdown">
								<span id="top-level-name" style="font-size: 18px"><%=currentHousingLocationName%></span>
								<span class="caret"></span>
							</button>
							<ul id="scrollable-list-housing-locations"
								class="dropdown-menu scrollable-menu" role="menu">
								<%
									HousingLocation[] houses = HousingDataManager
											.getAllHousingLocations();

									for (int i = 0; i < houses.length; i++) {
								%>
								<li style="font-size: 18px"><a
									onclick="setHousingLocation(this)" href="#"><%=houses[i].locationName%></a></li>
								<%
									}
								%>
							</ul>
						</div>

					</div>

				</div>

			</div>
		</div>

		
		<div class = "row">
			<div style = "background-color: #c05049; text-align:center;padding:20px">
				<div style = "color:#ffcc00;font-size:36px;margin:20px 0px;">Friend Map</div>

				<div style = "text-align:center;">
					<div id="map-canvas" style = "display:inline-block;width:500px;height:400px;background-color: #CCC;"></div>
				</div>
			</div>
		</div>

		<div class = "row">
			<div style = "background-color: #c05049;text-align:center;padding:20px">
				<div style = "color:#ffcc00;font-size:36px;">Set Preferences</div>

				<div class = "preferences-wrapper">
					<div class = "slider-wrapper">
						<p>Management</p>
						
						<div data-role="main" class="ui-content">
						      <input type="range" name="management" class = "slider" id="management" value="<%=user.managementSurveyScore%>" min="1" max="10">
					  	</div>

					  	<div style = "width:100%">
						  	<div class = "slider-ticks">
						  		<div class = "tick">1
						  		</div>

						  		<div class = "tick">2
						  		</div>

						  		<div class = "tick">3
						  		</div>

						  		<div class = "tick">4
						  		</div>

						  		<div class = "tick">5
						  		</div>

						  		<div class = "tick">6
						  		</div>

						  		<div class = "tick">7
						  		</div>

						  		<div class = "tick">8
						  		</div>

						  		<div class = "tick">9
						  		</div>

						  		<div class = "tick">10
						  		</div>
						  	</div>
					  	</div>
						
					</div>

					<div class = "slider-wrapper">
						<p>Amenities</p>
						<div data-role="main" class="ui-content">
						      <input type="range" name="amenities" class = "slider" id="amenities" value="<%=user.amenitiesSurveyScore%>" min="1" max="10">
					  	</div>

					  	<div style = "width:100%">
						  	<div class = "slider-ticks">
						  		<div class = "tick">1
						  		</div>

						  		<div class = "tick">2
						  		</div>

						  		<div class = "tick">3
						  		</div>

						  		<div class = "tick">4
						  		</div>

						  		<div class = "tick">5
						  		</div>

						  		<div class = "tick">6
						  		</div>

						  		<div class = "tick">7
						  		</div>

						  		<div class = "tick">8
						  		</div>

						  		<div class = "tick">9
						  		</div>

						  		<div class = "tick">10
						  		</div>
						  	</div>
					  	</div>
					</div>

					<div class = "slider-wrapper">
						<p>Location</p>
						<div data-role="main" class="ui-content">
						      <input type="range" name="location" class = "slider" id="location" value="<%=user.locationSurveyScore%>" min="1" max="10">
					  	</div>

					  	<div style = "width:100%">
						  	<div class = "slider-ticks">
						  		<div class = "tick">1
						  		</div>

						  		<div class = "tick">2
						  		</div>

						  		<div class = "tick">3
						  		</div>

						  		<div class = "tick">4
						  		</div>

						  		<div class = "tick">5
						  		</div>

						  		<div class = "tick">6
						  		</div>

						  		<div class = "tick">7
						  		</div>

						  		<div class = "tick">8
						  		</div>

						  		<div class = "tick">9
						  		</div>

						  		<div class = "tick">10
						  		</div>
						  	</div>
					  	</div>
					</div>

					<div class = "slider-wrapper">
						<p>Noise</p>
						<div data-role="main" class="ui-content">
						      <input type="range" class = "slider" id="noise" value="<%=user.noiseSurveyScore%>" min="1" max="10">
					  	</div>

					  	<div style = "width:100%">
						  	<div class = "slider-ticks">
						  		<div class = "tick">1
						  		</div>

						  		<div class = "tick">2
						  		</div>

						  		<div class = "tick">3
						  		</div>

						  		<div class = "tick">4
						  		</div>

						  		<div class = "tick">5
						  		</div>

						  		<div class = "tick">6
						  		</div>

						  		<div class = "tick">7
						  		</div>

						  		<div class = "tick">8
						  		</div>

						  		<div class = "tick">9
						  		</div>

						  		<div class = "tick">10
						  		</div>
						  	</div>
					  	</div>
					</div>

					<div class = "slider-wrapper">
						<p>Chill Factor</p>
						<div data-role="main" class="ui-content">
						      <input type="range" name="chill-factor" class = "slider" id="chill-factor" value="<%=user.communityChillFactorSurveyScore%>" min="1" max="10">
					  	</div>

					  	<div style = "width:100%">
						  	<div class = "slider-ticks">
						  		<div class = "tick">1
						  		</div>

						  		<div class = "tick">2
						  		</div>

						  		<div class = "tick">3
						  		</div>

						  		<div class = "tick">4
						  		</div>

						  		<div class = "tick">5
						  		</div>

						  		<div class = "tick">6
						  		</div>

						  		<div class = "tick">7
						  		</div>

						  		<div class = "tick">8
						  		</div>

						  		<div class = "tick">9
						  		</div>

						  		<div class = "tick">10
						  		</div>
						  	</div>
					  	</div>
					</div>

					<div class = "save-sliders" onClick="setPrefences()">
						SAVE PREFERENCES
					</div>

				</div>

			</div>

		</div>
		
		<div class="row"  style = "background-color: #c05049;">
			<div style = "background-color: #c05049;text-align:center;padding:20px">
				<div style = "color:#ffcc00;font-size:36px; margin-bottom:25px;">Recommendations</div>
				<% 
					HousingLocation[] housesRecommendations = PreferenceCalculator.findPreferences(fbID);
					for (int j = 0; j < housesRecommendations.length; j++) {
						HousingLocation location = housesRecommendations[j];				
				%>
					
				
					<div class = "col-lg-3 col-md-6" style="margin-bottom:30px;"> 
						<div class = "house-card">
						<a href="/troho/house.jsp?name=<%= location.locationName%>">
							<img src="<%=location.imageURL%>" height="200" width="200"></img>
						</a>
						</div>
							<p class = "house-title">
							<a href="/troho/house.jsp?name=<%= location.locationName%>" style="color:white;">
								<%=location.locationName %>
							</a>
							</p>
						<div class = "star-container" style="">
							<%
							for (int k = 0; k < location.overallScore; k++) {
							%>
								<img src = "./img/star.png" class = "star"/>
							<%
							}
							%>
						</div>
					</div>
				
				<%
					}
				%>	
			</div>
		</div>
		<%         
			if (user != null && user.isAdmin) {
		%>
			<div class = "row" style = "background-color: #c05049;">
		        <div class = "add-admin-button">
		            <div class = "col-lg-12"  style = "padding-top: 8px; padding-bottom: 40px; font-size:20px; color:white; text-align:center"><a href="/troho/admin.jsp">Admin Page</a></div>
		        </div>
	        </div>

		<%
			}
		%>

	</div>

	<div class = "footer">
		<div class = "logo-wrapper">
			<img src = "./img/new-troho.png" style = "height:200px;width:auto">
		</div>
	</div>

		 <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Google Maps -->
    <script src="https://maps.googleapis.com/maps/api/js"></script>
    
    <script>
    
    	window.onload = function() {
	    	if(<%=user.verifiedEmail%>) {
	    		document.getElementById("email-form-input").style.display = "none";
	    	}
	    	else {
	    		document.getElementById("email-form-input").style.display = "inline";
	    	}
    	} 
    
    	function sendVerificationEmail() {
	 	 		var uscEmail = document.getElementById("verification-email-input").value;
	 	 		console.log(uscEmail);
	 	 		
	 	 		FB.api('/me', function(response) {
					var fbID = response.id;
	 	 		
	 	 		$.ajax({
					  url: "/troho/SendUserVerificationEmail",
					  type: "POST",
					  data: {fbID : fbID, uscEmail : uscEmail},
					  dataType: "JSON"
					});
	 	 		});
		    }
    
    	function setHousingLocation(e){

 	 		document.getElementById("top-level-name").innerText = e.innerHTML;
 	 		var currLocation = e.innerHTML;
 	 		FB.api('/me', function(response) {
				var fbID = response.id;
				sessionStorage.user = fbID;
				sessionStorage.test = 5;
 	 		
 	 			$.ajax({
					  url: "/troho/UpdateUserHousingLocation",
					  type: "POST",
					  data: {fbID : fbID, currentHousingLocation : currLocation},
					  dataType: "JSON"
				});
 	 		});
		}
    
    	function setPrefences() {
 	 		var location = document.getElementById("location").value;
 	 		var chillFactor = document.getElementById("chill-factor").value;
 	 		var management = document.getElementById("management").value;
 	 		var amenities = document.getElementById("amenities").value;
 	 		var noise = document.getElementById("noise").value; 
 	 		
			FB.api('/me', function(response) {
				var fbID = response.id;
 	 		
			$.ajax({
				  url: "/troho/SetUserPreferences",
				  type: "POST",
				  data: {fbID : fbID, location : location, amenities : amenities, chillFactor : chillFactor, management : management, noise : noise},
				  dataType: "JSON"
				});
			});
 	 	}
    
    
    	function initialize() {
   	 		var mapCanvas = document.getElementById('map-canvas');
    		var mapOptions = {
	          center: new google.maps.LatLng(34.0245, -118.285),
	          zoom: 15,
	          mapTypeId: google.maps.MapTypeId.ROADMAP
	        }

	        var map = new google.maps.Map(mapCanvas,mapOptions);
    		var allMarkers = [];
    		
    		<%HousingLocation[] housingLocations =  HousingDataManager.getAllHousingLocations();
	   		for(int i = 0; i < housingLocations.length; i++) {
	   			HousingLocation location = housingLocations[i];
	   		
	   		%>
   		  		var marker = new google.maps.Marker({
   		  			position: new google.maps.LatLng(<%=location.gpsLatitude%> , <%=location.gpsLongitude%>),
   	     			map: map,
   	      			title: '<%=location.locationName%>'
   		  		});
    		  		
    		  	allMarkers.push(marker);	
   		  		
    		<%}%>
    		
    		

			/*for(var i = 0; i < allMarkers.length;++i) {

				(function(index,google) {
					
					google.maps.event.addListener(allMarkers[index], 'mouseover', function() {
						
							var scale = Math.pow(2, map.getZoom());
							var nw = new google.maps.LatLng(
							    map.getBounds().getNorthEast().lat(),
							    map.getBounds().getSouthWest().lng()
							);
							var worldCoordinateNW = map.getProjection().fromLatLngToPoint(nw);
							
							var worldCoordinate = map.getProjection().fromLatLngToPoint(allMarkers[index].getPosition());
							var pixelOffset = new google.maps.Point(
							    Math.floor((worldCoordinate.x - worldCoordinateNW.x) * scale),
							    Math.floor((worldCoordinate.y - worldCoordinateNW.y) * scale)
							);

							var x = Math.floor((worldCoordinate.x - worldCoordinateNW.x) * scale);
							var y = Math.floor((worldCoordinate.y - worldCoordinateNW.y) * scale);

							console.log(x + "," + y);

					        var img = $('<img src="./img/CalvinHackSC.jpg" alt="myimage" class = "friend-circle" />');
					        var div = $('<div>').css({
					            "position": "absolute",                    
					            "left": x,
					            "top": y,
					            "background-color":"white",
					            "overflow":"hidden",
					            "height":"60px",
					            "width":"60px",
					            "border-radius":"30px"
					        });

					        div.append(img);
					        div.addClass('markerToRemove');


					        $('#map-canvas').append(div);  

					        x = x + 65;

					        var img = $('<img src="./img/CalvinHackSC.jpg" alt="myimage" class = "friend-circle" />');
					        var div = $('<div>').css({
					            "position": "absolute",                    
					            "left": x,
					            "top": y,
					            "background-color":"white",
					            "overflow":"hidden",
					            "height":"60px",
					            "width":"60px",
					            "border-radius":"30px"
					        });

					        div.append(img);
					        div.addClass('markerToRemove');     

					        $('#map-canvas').append(div); 

					        x = x + 65;

					        var img = $('<img src="./img/CalvinHackSC.jpg" alt="myimage" class = "friend-circle" />');
					        var div = $('<div>').css({
					            "position": "absolute",                    
					            "left": x,
					            "top": y,
					            "background-color":"white",
					            "overflow":"hidden",
					            "height":"60px",
					            "width":"60px",
					            "border-radius":"30px"
					        });

					        div.append(img);
					        div.addClass('markerToRemove');     

					        $('#map-canvas').append(div); 

					});

				}) (i,google);

				(function(index,google) {

					google.maps.event.addListener(allMarkers[index], 'mouseout', function() {
						//$(document).remove($('markerToRemove'));
						//$('markerToRemove').parent().remove('markerToRemove');
						$(".markerToRemove").remove();
					});

				}) (i,google);

			}*/
			
			var facebookID = <%=fbID%>;
			
			$.ajax({
				url: "/troho/FriendMapInfo",
				type: "POST",
				data: {'facebookID' : facebookID},
				dataType: "JSON",
				success:function(data) {
					
					for (var location in data) {
						if (data.hasOwnProperty(location)) {
							
							var index;
							for(var i = 0; i < allMarkers.length; i++) {
								if(allMarkers[i].title == location) {
									index = i;
								}
							}
						
							var marker = allMarkers[index];
														
							for(i = 0; i < data[location].length; ++i) {
												
								google.maps.event.addListener(marker, 'mouseover', function() {
									
									var scale = Math.pow(2, map.getZoom());
									var nw = new google.maps.LatLng(
									    map.getBounds().getNorthEast().lat(),
									    map.getBounds().getSouthWest().lng()
									);
									var worldCoordinateNW = map.getProjection().fromLatLngToPoint(nw);
									
									var worldCoordinate = map.getProjection().fromLatLngToPoint(allMarkers[index].getPosition());
									var pixelOffset = new google.maps.Point(
									    Math.floor((worldCoordinate.x - worldCoordinateNW.x) * scale),
									    Math.floor((worldCoordinate.y - worldCoordinateNW.y) * scale)
									);
	
									var x = Math.floor((worldCoordinate.x - worldCoordinateNW.x) * scale) + 35*i;
									var y = Math.floor((worldCoordinate.y - worldCoordinateNW.y) * scale) - 50;
								
									
							        var img = $('<img src=' + data[location] + ' alt="myimage" class = "friend-circle" />');
							        var div = $('<div>').css({
							            "position": "absolute",                    
							            "left": x,
							            "top": y,
							            "background-color":"white",
							            "overflow":"hidden",
							            "height":"60px",
							            "width":"60px",
							            "border-radius":"30px"
							        });
	
							        div.append(img);
							        div.addClass('markerToRemove');
							        $('#map-canvas').append(div);
							        
								});
								
								google.maps.event.addListener(marker, 'mouseout', function() {
									$(".markerToRemove").remove();
								});
							}	
						
						}
					}
				}
			});
  		}

  		google.maps.event.addDomListener(window, 'load', initialize);
  		
  		$(window).load(function() {
  			
  		  
  		});

	</script>

</body>

</html>