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


</head>

<body>

	<script src="js/customFB.js">
	</script>

	<!-- Page Header -->
	<div class="header">
		<div id="troho-logo">
			<img src="./img/new-troho.png" style="height: 80px" />
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
	
	<div class = "container-fluid">

		<div class = "row" style = "margin-top:160px;">

			<div class = "col-md-4"></div>

			<div class = "search-container col-md-4">
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
					            <input type="text" id = "fill-in" class="search-bar" placeholder="Find a house!" name="house" style = "color:#ffe9bb;z-index: -1; margin-left:-180px;position:absolute;">
					        </div>
					        
						</div>
						<div class = "image-container">
						<i class="glyphicon glyphicon-search search-image" aria-hidden="true"></i>
						</div>
					</div>
				</div>

			</div>

			<div class = "col-md-4"></div>

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
			
			<div class = "row">
				<div class = "col-lg-1">
				</div>

				
				<div class = "col-lg-10">
				<% %>
				<a href="/troho/house.jsp?name=Gateway%20Apartments">
					<div class = "col-lg-3"> 
						<div class = "house-card"></div>
						<p class = "house-title">Gateway</p>
						<div class = "star-container">
							<img src = "./img/star.png" class = "star"/>
							<img src = "./img/star.png" class = "star"/>
							<img src = "./img/star.png" class = "star"/>
						</div>
					</div>
				</a>
					<a href="/troho/house.jsp?name=Icon%20Plaza">
					<div class = "col-lg-3"> 
						<div class = "house-card"></div>
						<p class = "house-title">Icon Plaza</p>
						<div class = "star-container">
							<img src = "./img/star.png" class = "star"/>
							<img src = "./img/star.png" class = "star"/>
							<img src = "./img/star.png" class = "star"/>
						</div>
					</div>
					</a>
					<a href="/troho/house.jsp?name=Cardinal%20Gardens">
					<div class = "col-lg-3"> 
						<div class = "house-card"></div>
						<p class = "house-title">Cardinal Gardens</p>
						<div class = "star-container">
							<img src = "./img/star.png" class = "star"/>
							<img src = "./img/star.png" class = "star"/>
							<img src = "./img/star.png" class = "star"/>
						</div>
					</div>
					</a>
					<a href="/troho/house.jsp?name=Trojan%20Hall">
					<div class = "col-lg-3"> 
						<div class = "house-card"></div>
						<p class = "house-title">Trojan Hall</p>
						<div class = "star-container">
							<img src = "./img/star.png" class = "star"/>
							<img src = "./img/star.png" class = "star"/>
							<img src = "./img/star.png" class = "star"/>
							<img src = "./img/star.png" class = "star"/>
						</div>
					</div>
					</a>
				</div>
				<div class = "col-lg-1">
				</div>
			</div>

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
		
		$('#troho-logo').click(function() {
			var t = null;

			  $.getJSON('http://localhost:8080/troho/FirstServlet', function (data) {
			     	alert(data.test);
			     	console.log("finished");
			  });
		})
		
		$('#search').on('keydown', function(event) {
			console.log(event.keyCode);
			var fill = document.getElementById('fill-in');
			var c = String.fromCharCode(event.which).toLowerCase();
			
			var currentVal = $('#search').val();
			
			if(event.keyCode !== 16 && event.keyCode != 91 && event.keyCode != 93 ) {
				if(event.keyCode == 46 || event.keyCode == 8) {
					fill.value = currentVal.substring(0,currentVal.length-1) + 'test';
				} else {
					fill.value = currentVal + c + 'test';
				}
			}
			
			
			
		});
		
		$('#search').on('keyup', function(event) {
			var fill = document.getElementById('fill-in');
			var c = String.fromCharCode(event.which).toLowerCase();
			
			var currentVal = $('#search').val();
			
			if(currentVal.length < 1) {
				fill.value = "";
			}
			
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.open("POST","http://localhost:8080/troho/AutoFillGuess",true);
			xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xmlhttp.send(currentVal);

		});

    </script>

</body>


</html>