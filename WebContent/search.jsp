<!DOCTYPE html>
<html>

<head>

<link rel="stylesheet" href="css/search.css">
<link rel="stylesheet" href="css/header-bar.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script src="js/customFB.js">
	</script>
</head>


<body>

	<div class = "header">
		<div>
			<!-- <img src = "./img/troho-high-res.png" style = "height:80px"/> -->
			<img src = "./img/new-troho.png" style = "height:80px"/>
		</div>

		<div class = "log-div">
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
	
	<div class = "container-fluid" style = "background-color:#c05049">


		<div class = "row" style = "margin-top:80px;padding-top:80px;background-color:white">

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

		<div class = "row content" style = "font-size:36px; color:#FFCC00; text-align:center; padding: 30px;">
			Narrow it down
		</div>

		<div class = "option-container row">
			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Max Price</p>
				<ul class = "drop-down-box">

					<li>

						<div class = "drop-down-descriptor-wrapper">
							<div class = "drop-down-descriptor">
								<p style = "display:inline;">Any</p>
							</div>
							<i class="fa fa-lg fa-caret-down drop-down-button"></i>
						</div>

					</li>

					<li> <div class = "drop-down-option">$800</div>
					</li>

					<li> <div class = "drop-down-option">$1000</div>
					</li>

					<li> <div class = "drop-down-option">$1200</div>
					</li>

					<li> <div class = "drop-down-option">$1400</div>
					</li>

					<li> <div class = "drop-down-option">Any</div>
					</li>

				</ul>

			</div>

			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Distance from USC</p>
				<ul class = "drop-down-box">

					<li>

						<div class = "drop-down-descriptor-wrapper">
							<div class = "drop-down-descriptor">
								<p style = "display:inline;">Any</p>
							</div>
							<i class="fa fa-lg fa-caret-down drop-down-button"></i>
						</div>

					</li>

					<li> <div class = "drop-down-option">5 minutes walking</div>
					</li>

					<li> <div class = "drop-down-option">10 minutes walking</div>
					</li>

					<li> <div class = "drop-down-option">15 minutes walking</div>
					</li>

					<li> <div class = "drop-down-option">30 minutes walking</div>
					</li>

					<li> <div class = "drop-down-option">Any</div>
					</li>

				</ul>
			</div>

			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Housing Style</p>
				<ul class = "drop-down-box">

					<li>

						<div class = "drop-down-descriptor-wrapper">
							<div class = "drop-down-descriptor">
								<p style = "display:inline;">Any</p>
							</div>
							<i class="fa fa-lg fa-caret-down drop-down-button"></i>
						</div>

					</li>

					<li> <div class = "drop-down-option">Apartment</div>
					</li>

					<li> <div class = "drop-down-option">Dorm</div>
					</li>

					<li> <div class = "drop-down-option">House</div>
					</li>

					<li> <div class = "drop-down-option">Any</div>
					</li>

				</ul>
			</div>

			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Rating</p>
				<ul class = "drop-down-box">

					<li>

						<div class = "drop-down-descriptor-wrapper">
							<div class = "drop-down-descriptor">
								<p style = "display:inline;">Any</p>
							</div>
							<i class="fa fa-lg fa-caret-down drop-down-button"></i>
						</div>

					</li>

					<li> <div class = "drop-down-option">1 Star</div>
					</li>

					<li> <div class = "drop-down-option">2 Star</div>
					</li>

					<li> <div class = "drop-down-option">3 Star</div>
					</li>

					<li> <div class = "drop-down-option">4 Star</div>
					</li>
					
					<li> <div class = "drop-down-option">5 Star</div>
					</li>

					<li> <div class = "drop-down-option">Any</div>
					</li>

				</ul>
			</div>

		</div>

		<br>
	<!-- </div> -->

	<!-- <div class = "content"> -->
		<div class = "row" style = "font-size:36px; color:#FFCC00; text-align:center; padding:30px;">
			Pick your preferences
		</div>

		<div class = "option-container row">
			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Management</p>
				<ul class = "drop-down-box">

					<li>

						<div class = "drop-down-descriptor-wrapper">
							<div class = "drop-down-descriptor">
								<p style = "display:inline;">Any</p>
							</div>
							<i class="fa fa-lg fa-caret-down drop-down-button"></i>
						</div>

					</li>

					<li> <div class = "drop-down-option">$800</div>
					</li>

					<li> <div class = "drop-down-option">$1000</div>
					</li>

					<li> <div class = "drop-down-option">$1200</div>
					</li>

					<li> <div class = "drop-down-option">$1400</div>
					</li>

					<li> <div class = "drop-down-option">Any</div>
					</li>

				</ul>

			</div>

			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Amenities</p>
				<ul class = "drop-down-box">

					<li>

						<div class = "drop-down-descriptor-wrapper">
							<div class = "drop-down-descriptor">
								<p style = "display:inline;">Any</p>
							</div>
							<i class="fa fa-lg fa-caret-down drop-down-button"></i>
						</div>

					</li>

					<li> <div class = "drop-down-option">$800</div>
					</li>

					<li> <div class = "drop-down-option">$1000</div>
					</li>

					<li> <div class = "drop-down-option">$1200</div>
					</li>

					<li> <div class = "drop-down-option">$1400</div>
					</li>

					<li> <div class = "drop-down-option">Any</div>
					</li>

				</ul>
			</div>

			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Chill Factor</p>
				<ul class = "drop-down-box">

					<li>

						<div class = "drop-down-descriptor-wrapper">
							<div class = "drop-down-descriptor">
								<p style = "display:inline;">Any</p>
							</div>
							<i class="fa fa-lg fa-caret-down drop-down-button"></i>
						</div>

					</li>

					<li> <div class = "drop-down-option">$800</div>
					</li>

					<li> <div class = "drop-down-option">$1000</div>
					</li>

					<li> <div class = "drop-down-option">$1200</div>
					</li>

					<li> <div class = "drop-down-option">$1400</div>
					</li>

					<li> <div class = "drop-down-option">Any</div>
					</li>

				</ul>
			</div>

			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Location</p>
				<ul class = "drop-down-box">

					<li>

						<div class = "drop-down-descriptor-wrapper">
							<div class = "drop-down-descriptor">
								<p style = "display:inline;">Any</p>
							</div>
							<i class="fa fa-lg fa-caret-down drop-down-button"></i>
						</div>

					</li>

					<li> <div class = "drop-down-option">$800</div>
					</li>

					<li> <div class = "drop-down-option">$1000</div>
					</li>

					<li> <div class = "drop-down-option">$1200</div>
					</li>

					<li> <div class = "drop-down-option">$1400</div>
					</li>

					<li> <div class = "drop-down-option">Any</div>
					</li>

				</ul>
			</div>

		</div>

		<div style = "margin: 0px 0px !important; font-size:36px; color:#FFCC00; text-align:center; padding: 30px;">
			Results
		</div>

		<div class = "row content" style = "font-size:36px; text-align:center; padding: 30px;">

			<!--<div style = "font-size:36px; color:#FFCC00; text-align:center; padding: 30px;">
				RESULTS
			</div>-->
			<div class = "col-lg-1"></div>

			<div class = "col-lg-10 results-container">

				<div class = "col-lg-10 col-md-12">
                    
                    <div class="col-lg-12 single-result">

	                    <div class = "row">
	                    	<div class = "result-top-half">
	                    		<div class = "col-lg-4 house-result-image">
		                			<img src = "img/hawaiian5.jpg" style = "width:200px;margin-top:40px"/>
		                		</div>
		                    	<div class = "col-lg-8" style = "margin-top:50px; font-size:60px;">
		                    		<p style = "text-align:center;">Housing Name</p>
		                    		<p style = "text-align:center;">Address</p>
		                    	</div>
	                    	</div>
	                    </div>
						<!-- <br> -->
	                    <div class = "row" style = "padding-top:20px">
		                	<div class = "result-bottom-half">

			                	<div class = "row">
			                		<div class = "col-lg-4"> Price:
			                		</div>

			                		<div class = "col-lg-4"> Distance:
			                		</div>
			                	</div>
			                	<div class = "row">
			                		<div class = "col-lg-4"> Style:
			                		</div>

			                		<div class = "col-lg-4"> Rating:
			                		</div>
			                	</div>
		                		

		                	</div>
	                	</div>

                    </div>

                    <div class="col-lg-12 single-result">

	                    <div class = "row">
	                    	<div class = "result-top-half">
	                    		<div class = "col-lg-4 house-result-image">
		                			<img src = "img/hawaiian5.jpg" style = "width:200px;margin-top:40px"/>
		                		</div>
		                    	<div class = "col-lg-8" style = "margin-top:50px; font-size:60px;">
		                    		<p style = "text-align:center;">Housing Name</p>
		                    		<p style = "text-align:center;">Address</p>
		                    	</div>
	                    	</div>
	                    </div>
						<!-- <br> -->
	                    <div class = "row" style = "padding-top:20px">
		                	<div class = "result-bottom-half">

			                	<div class = "row">
			                		<div class = "col-lg-4"> Price:
			                		</div>

			                		<div class = "col-lg-4"> Distance:
			                		</div>
			                	</div>
			                	<div class = "row">
			                		<div class = "col-lg-4"> Style:
			                		</div>

			                		<div class = "col-lg-4"> Rating:
			                		</div>
			                	</div>
		                		

		                	</div>
	                	</div>

                    </div>

                </div>

			</div>

			<div class = "col-lg-1"></div>

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

	<script>

		$(".drop-down-descriptor-wrapper").click(function(){

  		 	var parent = $(this).parent().parent();

  		 	if(parent.hasClass('active')) {

				parent.find(".drop-down-option").css('opacity','0');
				parent.removeClass('active');

		 	} else {
		 		parent.addClass('active');
				parent.find(".drop-down-option").animate({
					opacity: 1
				}, {	
					duration: 200
				});
		 	
		 	}

		});

		$(".drop-down-option").click(function(){

			var parent = $(this).parent().parent();

			if(parent.hasClass('active')) {

				parent.removeClass('active');
				parent.find(".drop-down-option").css('opacity','0');

				$(parent).find('.drop-down-descriptor').find('p').text($(this).text());

			}
		});

	</script>

</body>

</html>