<%@page import="sql.UserDataManager"%>
<%@page import="sql.User"%>

<%
	User user = null;
	String fbID = request.getParameter("id");
		
	if (fbID != null) {
		user = UserDataManager.getUser(fbID);
	} else {
		String redirectURL = "/troho/404.html";
    	response.sendRedirect(redirectURL);
	}
%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/user.css">
<link href="css/header-bar.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">

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

					<p style = "font-size:24px"> <%= user.email %> </p>

					<p style = "font-size:18px"> Off-campus Housing
					</p>
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
						    <form method="post" action="demoform.asp">
						      <input type="range" class = "slider" id="points" value="5" min="1" max="10">
						    </form>
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
						    <form method="post" action="demoform.asp">
						      <input type="range" class = "slider" id="points" value="5" min="1" max="10">
						    </form>
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
						    <form method="post" action="demoform.asp">
						      <input type="range" class = "slider" id="points" value="5" min="1" max="10">
						    </form>
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
						    <form method="post" action="demoform.asp">
						      <input type="range" class = "slider" id="points" value="5" min="1" max="10">
						    </form>
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
						    <form method="post" action="demoform.asp">
						      <input type="range" class = "slider" id="points" value="5" min="1" max="10">
						    </form>
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

					
					<div class = "save-sliders">
						SAVE PREFERENCES
					</div>

				</div>
				

			</div>

		</div>

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
 	 	function initialize() {
   	 		var mapCanvas = document.getElementById('map-canvas');
    		var mapOptions = {
	          //center: new google.maps.LatLng(34.022228, -118.288829),
	          center: new google.maps.LatLng(34.0245, -118.285),
	          zoom: 15,
	          mapTypeId: google.maps.MapTypeId.ROADMAP
	        }

	        var map = new google.maps.Map(mapCanvas,mapOptions);

	        var gatewayMarker = new google.maps.Marker({
     			position: new google.maps.LatLng(34.022964, -118.279809),
     			map: map,
      			title: 'Gateway'
  			});

  			var iconMarker = new google.maps.Marker({
     			position: new google.maps.LatLng(34.018375, -118.282053),
     			map: map,
      			title: 'Icon'
  			});

  			var cardinalMarker = new google.maps.Marker({
     			position: new google.maps.LatLng(34.026244, -118.287239),
     			map: map,
      			title: 'Cardinal Gardens'
  			});

  			var lorenzoMarker = new google.maps.Marker({
     			position: new google.maps.LatLng(34.027460, -118.272983),
     			map: map,
      			title: 'Lorenzo'
  			});

  			var northMarker = new google.maps.Marker({
     			position: new google.maps.LatLng(34.020620, -118.281432),
     			map: map,
      			title: 'New North'
  			});

  			var parksideMarker = new google.maps.Marker({
     			position: new google.maps.LatLng(34.018883, -118.290713),
     			map: map,
      			title: 'Parkside'
  			});

  			var shrineMarker = new google.maps.Marker({
     			position: new google.maps.LatLng(34.024963, -118.279815),
     			map: map,
      			title: 'Shrine'
  			});

  			var westMarker = new google.maps.Marker({
     			position: new google.maps.LatLng(34.026785, -118.275799),
     			map: map,
      			title: 'W. 27th Place'
  			});

  			var webMarker = new google.maps.Marker({
     			position: new google.maps.LatLng(34.024719, -118.287704),
     			map: map,
      			title: 'W. 27th Place'
  			});

  			

  			/*var allMarkers = [gatewayMarker, northMarker, westMarker, iconMarker, shrineMarker,
  			parksideMarker, lorenzoMarker, cardinalMarker, webMarker];*/

  			var allMarkers = [];

  			allMarkers[0] = gatewayMarker;
  			allMarkers[1] = northMarker;
  			allMarkers[2] = westMarker;
  			allMarkers[3] = iconMarker;
  			allMarkers[4] = shrineMarker;
  			allMarkers[5] = parksideMarker;
  			allMarkers[6] = lorenzoMarker;
  			allMarkers[7] = cardinalMarker;
  			allMarkers[8] = webMarker;
  			

			/*google.maps.event.addListener(allMarkers[i], 'mouseover', function() {

			});*/
			//console.log(allMarkers[0]);
			for(var i = 0; i < allMarkers.length;++i) {

				/*google.maps.event.addListener(allMarkers[i], 'mouseover', function() {

				});*/

				(function(index,google) {
					

					google.maps.event.addListener(allMarkers[index], 'mouseover', function() {
							//console.log(allMarkers[0]);
						
							var scale = Math.pow(2, map.getZoom());
							var nw = new google.maps.LatLng(
							    map.getBounds().getNorthEast().lat(),
							    map.getBounds().getSouthWest().lng()
							);
							var worldCoordinateNW = map.getProjection().fromLatLngToPoint(nw);
							//var worldCoordinate = map.getProjection().fromLatLngToPoint(gatewayMarker.getPosition());
							var worldCoordinate = map.getProjection().fromLatLngToPoint(allMarkers[index].getPosition());
							var pixelOffset = new google.maps.Point(
							    Math.floor((worldCoordinate.x - worldCoordinateNW.x) * scale),
							    Math.floor((worldCoordinate.y - worldCoordinateNW.y) * scale)
							);

							var x = Math.floor((worldCoordinate.x - worldCoordinateNW.x) * scale) + 370;
							var y = Math.floor((worldCoordinate.y - worldCoordinateNW.y) * scale) + 520;

							console.log(x + "," + y);

					    //$(document).click(function(e){

					        //var x = e.pageX + 'px';
					        //var y = e.pageY + 'px';

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


					        $(document.body).append(div);  

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

					        $(document.body).append(div); 

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

					        $(document.body).append(div); 

					});

				}) (i,google);

				(function(index,google) {

					google.maps.event.addListener(allMarkers[index], 'mouseout', function() {
						//$(document).remove($('markerToRemove'));
						//$('markerToRemove').parent().remove('markerToRemove');
						$(".markerToRemove").remove();
					});

				}) (i,google);

			}
  		}

  		google.maps.event.addDomListener(window, 'load', initialize);

	</script>

</body>

</html>