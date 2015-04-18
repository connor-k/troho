<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="css/home.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"> -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script> -->
</head>

<body>

		<div class = "header">
		<div>
			<!-- <img src = "./img/troho-high-res.png" style = "height:80px"/> -->
			<img src = "./img/new-troho.png" style = "height:80px"/>
		</div>

		<div class = "log-div">
			<div id = "log-in">Log in with Facebook</div>
			<img src = "./img/FacebookIcon.png" style = "height:40px;border-radius:10px;"/>
			<!-- <div id = "log-in-line"></div> -->
			<!-- <div id = "sign-up"></div> -->
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
					<a href="/troho/house.jsp?name=Icon Plaza">
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
					<a href="/troho/house.jsp?name=Cardinal Gardens">
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
					<a href="/troho/house.jsp?name=Trojan Hall">
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

    </script>

</body>


</html>