<!DOCTYPE html>
<html>

<head>

<link rel="stylesheet" href="css/search.css">
<link rel="stylesheet" href="css/header-bar.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script src="js/customFB.js">
</script>
<style>
.add-review-button {
    width:200px;
    height:40px;
    margin:auto;
    position: relative;
    //background-color:white;
    background-color: #ffcc00;
    box-shadow: 0px 2px 5px rgba(0,0,0,.2);
    border-radius: 5px;
    margin-top:50px;

}
</style>
</head>


<body>

	<div class="header">
		<div id="troho-logo">
			<a href="index.jsp"><img id="home-logo" src="./img/new-troho.png" /></a>
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

		<div class = "option-container row" style="height:330px;">
			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Max Price</p>
				<div class="btn-group">
	                <button type="button" value ="10000" id="priceBox" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Any <span class="caret"></span></button>
	                <ul class="dropdown-menu scrollable-menu" id="priceBoxIn" role="menu">
	                    <li value="800" class="listElement"><a >800</a></li>
	                    <li value="1000" class="listElement"><a >1000</a></li>
	                    <li value="1200" class="listElement"><a >1200</a></li>
	                    <li value="1400" class="listElement"><a >1400</a></li>
	                    <li value="10000" class="listElement"><a >Any</a></li>
	                </ul>
            	</div>

			</div>

			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Distance from USC</p>
				<div class="btn-group">
	                <button type="button" value ="1000" id="distanceBox" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Any <span class="caret"></span></button>
	                <ul class="dropdown-menu scrollable-menu" id="distanceBoxIn" role="menu">
	                    <li value="5" class="listElement"><a >5 minutes walking</a></li>
	                    <li value="10" class="listElement"><a >10 minutes walking</a></li>
	                    <li value="15" class="listElement"><a >15 minutes walking</a></li>
	                    <li value="30" class="listElement"><a >30 minutes walking</a></li>
	                    <li value="1000" class="listElement"><a >Any</a></li>
	                </ul>
            	</div>
			</div>

			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Housing Style</p>
				<div class="btn-group">
	                <button type="button" value ="0" id="styleBox" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Any <span class="caret"></span></button>
	                <ul class="dropdown-menu scrollable-menu" id="styleBoxIn" role="menu">             	                    
	                    <li value="1" class="listElement"><a >Apartment</a></li>
	                    <li value="2" class="listElement"><a >Dorm</a></li>
	                    <li value="3" class="listElement"><a >House</a></li>
	                    <li value="0" class="listElement"><a >Any</a></li>
	                </ul>
            	</div>
			</div>

			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Rating</p>
				<div class="btn-group">
	                <button type="button" value ="0" id="ratingBox" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Any <span class="caret"></span></button>
	                <ul class="dropdown-menu scrollable-menu" id="ratingBoxIn" role="menu">
	                    <li value="1" class="listElement"><a >1 Star</a></li>
	                    <li value="2" class="listElement"><a >2 Star</a></li>
	                    <li value="3" class="listElement"><a >3 Star</a></li>
	                    <li value="4" class="listElement"><a >4 Star</a></li>
	                    <li value="5" class="listElement"><a >5 Star</a></li>
	                    <li value="0" class="listElement"><a >Any</a></li>
	                </ul>
            	</div>
			</div>

		</div>

		<br>
	<!-- </div> -->

	<!-- <div class = "content"> -->
		<div class = "row" style = "font-size:36px; color:#FFCC00; text-align:center; padding:30px;">
			Pick your preferences
		</div>

		<div class = "option-container row" style="height:330px;">
			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Management</p>
				<div class="btn-group">
	                <button type="button" value ="0" id="managementBox" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Any <span class="caret"></span></button>
	                <ul class="dropdown-menu scrollable-menu" id="managementBoxIn" role="menu">
	                    <li value="1" class="listElement"><a >1 Star</a></li>
	                    <li value="2" class="listElement"><a >2 Star</a></li>
	                    <li value="3" class="listElement"><a >3 Star</a></li>
	                    <li value="4" class="listElement"><a >4 Star</a></li>
	                    <li value="5" class="listElement"><a >5 Star</a></li>
	                    <li value="0" class="listElement"><a>Any</a></li>
	                </ul>
            	</div>

			</div>

			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Amenities</p>
				<div class="btn-group">
	                <button type="button" value ="0" id="amenitiesBox" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Any <span class="caret"></span></button>
	                <ul class="dropdown-menu scrollable-menu" id="amenitiesBoxIn" role="menu">
	                    <li value="1" class="listElement"><a >1 Star</a></li>
	                    <li value="2" class="listElement"><a >2 Star</a></li>
	                    <li value="3" class="listElement"><a >3 Star</a></li>
	                    <li value="4" class="listElement"><a >4 Star</a></li>
	                    <li value="5" class="listElement"><a >5 Star</a></li>
	                    <li value="0" class="listElement"><a >Any</a></li>
	                </ul>
            	</div>
			</div>

			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Chill Factor</p>
					<div class="btn-group">
	                <button type="button" value ="0" id="chillBox" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Any <span class="caret"></span></button>
	                <ul class="dropdown-menu scrollable-menu" id="chillBoxIn" role="menu">
	                    <li value="1" class="listElement"><a >1 Star</a></li>
	                    <li value="2" class="listElement"><a >2 Star</a></li>
	                    <li value="3" class="listElement"><a >3 Star</a></li>
	                    <li value="4" class="listElement"><a >4 Star</a></li>
	                    <li value="5" class="listElement"><a >5 Star</a></li>
	                    <li value="0" class="listElement"><a >Any</a></li>
	                </ul>
            	</div>

			</div>

			<div class = "col-lg-3 search-element-box">
				<p class = "search-descriptor">Location</p>
				<div class="btn-group">
	                <button type="button" value ="0" id ="locationBox" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Any <span class="caret"></span></button>
	                <ul class="dropdown-menu scrollable-menu" id ="locationBoxIn" role="menu">
	                    <li value="1" class="listElement"><a >1 Star</a></li>
	                    <li value="2" class="listElement"><a >2 Star</a></li>
	                    <li value="3" class="listElement"><a >3 Star</a></li>
	                    <li value="4" class="listElement"><a >4 Star</a></li>
	                    <li value="5" class="listElement"><a >5 Star</a></li>
	                    <li value="0" class="listElement"><a >Any</a></li>
	                </ul>
            	</div>
			</div>

		</div>
		
		<div class = "row" id="submitSearch">
	        <div class = "add-review-button">
	            <div class = "col-lg-12"  style = "padding-top: 8px; padding-bottom: 40px; font-size:20px; color:white; text-align:center"><a>Submit</a></div>
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

				<div class = "col-lg-10 col-md-12 result"> 
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
		
		$(document).ready(function() {
			$("#submitSearch").on("click", function() {
				var managementScore = $("#managementBox").val();
		 		var amenitiesScore = $("#amenitiesBox").val();
		 		var locationScore = $("#locationBox").val();
		 		var ratingScore = $("#ratingBox").val();
		 		var communityChillFactorScore = $("#chillBox").val();
		 		var maxPrice = $("#priceBox").val();
		 		var maxDistance = $("#distanceBox").val();
		 		var type = -1;
		 		type = $("#styleBox").val();
		 		var searchWords = $("#search").val();
		 		console.log("Search Words " + searchWords);
		 		var postData = {
						"managementScore": managementScore, 
						"amenitiesScore": amenitiesScore, 
						"locationScore": locationScore,
						"minRating": ratingScore, 
						"communityChillFactorScore": communityChillFactorScore,
						"maxPrice": maxPrice,
						"maxDistance":maxDistance,
						"housingType":type,
						"searchWords":searchWords
						};
		 		console.log(postData);
		 		$.ajax({
					url: "/troho/SearchFilter",
					type: "POST",
					data: JSON.stringify(postData),
					dataType: "JSON",
					success:function(data) {
						var houses = data.searchArray;
						var htmlText="";
						for (i = 0; i < houses.length; i++) {
							
							htmlText+='<div class="col-lg-12 single-result"><div class = "row"><div class = "result-top-half"><div class = "col-lg-4 house-result-image"><img src = ' + houses[i].imageURL + ' style = "width:200px;margin-top:40px"/></div><div class = "col-lg-8" style = "margin-top:50px; font-size:60px;"><p style = "text-align:center;">' +houses[i].locationName  + '</p>';
			                htmlText+='<p style = "text-align:center;">' + houses[i].housingAddress + '</p></div></div></div><div class = "row" style = "padding-top:20px">';
			                htmlText += '<div class = "result-bottom-half"><div class = "row"><div class = "col-lg-4"> Price: ' + houses[i].price;
				            htmlText += '</div><div class = "col-lg-4"> Distance:' + houses[i].distance + 'minutes walking</div></div><div class = "row">';
				            htmlText += '<div class = "col-lg-4"> Style: '+ houses[i].housingType + '</div><div class = "col-lg-4"> Rating:' + houses[i].rating;
				            htmlText += '</div></div></div></div></div>'
						}
						$(".result").html(htmlText);	
					}
				});
 			});
			
			
			$('#locationBoxIn > .listElement').on("click", function(){
 			   $("#locationBox").text($(this).text());  
 			  $("#locationBox").val($(this).val());
			});
			
			$('#chillBoxIn > .listElement').on("click", function(){
 			   $("#chillBox").text($(this).text()); 
 			  $("#chillBox").val($(this).val());
			});
			
			$('#amenitiesBoxIn > .listElement').on("click", function(){
 			   $("#amenitiesBox").text($(this).text());
 			   $("#amenitiesBox").val($(this).val());
			});
			
			$('#managementBoxIn > .listElement').on("click", function(){
 			   $("#managementBox").text($(this).text());  
 			  $("#managementBox").val($(this).val());
			});
			
			$('#styleBoxIn > .listElement').on("click", function(){
 			   $("#styleBox").text($(this).text());  
 			  $("#styleBox").val($(this).val());
 			  console.log( $("#styleBox").val());
			});
			
			$('#ratingBoxIn > .listElement').on("click", function(){
 			   $("#ratingBox").text($(this).text()); 
 			  $("#ratingBox").val($(this).val());
			});
			
			$('#distanceBoxIn > .listElement').on("click", function(){
 			   $("#distanceBox").text($(this).text());  
 			  $("#distanceBox").val($(this).val());
			});
			
			$('#priceBoxIn > .listElement').on("click", function(){
				$("#priceBox").text($(this).text()); 
				$("#priceBox").val($(this).val());
			});
			
		});

	</script>

</body>

</html>