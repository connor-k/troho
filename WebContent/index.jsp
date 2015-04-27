<%@ page errorPage="404.html" %>
<%@page import="sql.HousingDataManager"%>
<%@page import="sql.HousingLocation"%>
<%@page import="java.lang.String"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>

	<!-- Custom CSS -->
	<link rel="stylesheet" href="css/index.css">
	<link rel="stylesheet" href="css/header-bar.css">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	
<!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"> -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script> -->

    <title>Troho</title>

</head>

<body>

	<script src="js/customFB.js">
	</script>

	<!-- Page Header -->
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
	
	<div class = "container-fluid">

		<div class = "row" style = "margin-top:160px;">

			<div class = "col-xl-4"></div>

			<div class = "search-container col-xl-4">
				<div style = "color:#ffcc00; font-size:30px; text-align:center">We help Trojans find Housing</div>
				<br>
				<br>
				<div style = "width:100%;text-align:center">
					<div>
						<div>
							<img src = "./img/troco-no-text.png" class = "house-image"/>
						</div>
							<!-- <input type="text" name="search" placeholder="Find a house"> -->
						<div>
					        <div class="bar">
					            <input type="text" id = "search" class="search-bar" placeholder="Find a house!" name="house">
					            <input type="text" id = "fill-in" class="search-bar" placeholder="Find a house!" name="house" style = "color:#ffe9bb;z-index: -1; margin-left:-242px;position:absolute;">
					        </div>
					        
						</div>
						<div class = "image-container">
						<i class="glyphicon glyphicon-search search-image" aria-hidden="true"></i>
						</div>
					</div>
				</div>

			</div>

			<div class = "col-xl-4"></div>

		</div>

	</div>
	
	<div class = "content">
		<div class = "container-fluid">
		

			<div class = "row">
				<br>
				<br>
				<p class = "housing-options" style = "color:#ffcc00">Browse Housing Below</p>
				<br>
			</div>

			<br>
			<% 
				HousingLocation[] houses = HousingDataManager.getAllHousingLocations();
				for (int i = 0; i < houses.length/4; i++) {
					
			%>
				<div class = "row" >
					<div class = "col-lg-1"></div>
					
					<div class = "col-lg-10">
					
					<% 
						for (int j = 0; j < 4; j++) {
							HousingLocation location = houses[j + i*4];				
					%>
					
					
						<div class = "col-lg-3 col-md-6" style="margin-bottom:30px;"> 
							<div class = "house-card">
							<a href="/troho/house.jsp?name=<%= location.locationName%>">
								<img src="<%=location.imageURL%>" height="200" width="200" style = "border-radius:5px"></img>
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
					<div class = "col-lg-1">
					</div>
				</div>
			<%
			} 
			%>

		</div>

		<br>
		<br>
		<br>

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

    <script>
		$(window).load(function() {
		  //var htmlString = $( this ).html();
		  $("#search").focus();
		 
		});
		
		$('#search').on('keydown', function(event) {
			var fill = document.getElementById('fill-in');			
			var currentVal = document.getElementById('search');
			
			var originalChar = String.fromCharCode(event.keyCode);
			var inputCharacter = (event.shiftKey) ? originalChar.toUpperCase() : originalChar.toLowerCase();
			var string = currentVal.value + inputCharacter;
			
			if(event.keyCode !== 16 && event.keyCode != 91 && event.keyCode != 93 ) {
				
				if(event.keyCode == 46 || event.keyCode == 8) {
					console.log(string.length);
					if(string.length <= 2) {
						fill.value = " ";
					} else {
						
						var posting = $.post( 'http://localhost:8080/troho/AutoFillGuess', string.substring(0,string.length-2));
						
						posting.done( function( data ) {
							
						  	if(data.length > 1) {
						  		fill.value = data;
						  	} else {
						  		fill.value = " ";
						  	}
						  	
						});
						
					}
				} else {
					var posting = $.post( 'http://localhost:8080/troho/AutoFillGuess', string);
					
					posting.done( function( data ) {
						
					  	if(data.length > 1) {
					  		fill.value = data;
					  	} else {
					  		fill.value = " ";
					  	}
					  	
					});
				}
			}
		
		});
		
		$('#search').bind('keypress', function(e) {
			 var code = e.keyCode || e.which;
			 if(code == 13) { //Enter keycode
				 var currentVal = $('#search').val();
				var site = "/troho/search.jsp?search=" + currentVal + "#" + "submitSearch";
			 	window.open(site,"_self")
			 }
		});
    </script>

</body>


</html>