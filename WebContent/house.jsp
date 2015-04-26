<%@page import="sql.HousingDataManager"%>
<%@page import="sql.Review"%>
<%@page import="sql.UserDataManager"%>
<%@page import="sql.HousingLocation"%>
<%@page import="sql.User"%>


<%@page import="java.util.List"%>
<%@page import="java.lang.String"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% 
	String name = request.getParameter("name");
    HousingLocation location = HousingDataManager.getHousingLocation(name);
    if (name == null) {
    	System.out.println("In if");
 		String redirectURL = "/troho/404.html";
    	response.sendRedirect(redirectURL);
    }
%>


<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Troho</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/landing-page.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
    <link href="css/header-bar.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Indie Flower' rel='stylesheet' type='text/css'>
    <link href="http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
	<% if(location != null) {
		
	%>
		<style>
			.housing-image-card {
		   		 position: relative;
			  	background: url(<%=location.imageURL%>) no-repeat center center;
			    background-size: cover;
			    box-shadow: 0px 2px 5px rgba(0,0,0,.2);
			    min-height: 550px;
			    max-height: 650px;
			    width: auto;
			    margin: 60px auto 0px auto;
			
			}
		</style>
	<%
	}
	%>
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
				<div id="welcome-message"></div>
				<img id="profile-image" src=" "></img>
			</div>
		</div>
	</div>


	<br>
    <div>
        <div id = "top-text-wrap">
            <p id = "introText"><%= name %></p>
                    <!-- </div> -->
                    <!-- <div> -->
            <p id="addressText"><%= location.address %></p>
        </div>

        <div style = "-webkit-filter: blur(0px);">
	        <div class = "housing-image-card" style = "-webkit-filter:grayscale(1);">
	            <div style = "-webkit-filter: blur(2px);">
	            <!-- <div id="introText"> -->
	            <!-- <div id="introText"> -->
	            
	            </div>
	        </div>
	    </div>
    </div>

    <div class = "rating-master-container">

    	<div class="container" style = "padding:40px 0;">
    		<div class="row">
    			<div class="col-lg-12" style = "padding:20px;">
    				<p id="ratingTextBig">Overall Rating</p>
                    <div class = "star-container">
                        <% if (location != null) {
                        	System.out.println("Management Score " + location.managementScore);                 
                     			for (int i = 0; i < location.overallScore; i++) {
                     	%>
                        <img src = "./img/star.png" class = "star"/>
                        <%
                     			}
                     	}
                        %>
                     </div>
    			</div>
    		</div>
            <br>
    		<div class="row">
    			<div class="col-lg-4 col-md-12 rating-categories-container" style = "margin-left:-55px" >
    				<p class="rating">Management Rating</p>
                     <div class = "star-container" style = "display:inline-block; margin-left:15px; margin-top:3px; position:absolute;">
                     	<% if (location != null) {
                     			for (int i = 0; i < location.managementScore; i++) {
                     	%>
                        <img src = "./img/star.png" class = "star"/>
                        <%
                     			}
                     	}
                        %>
                     </div>
    			</div>
    			<div class="col-lg-4 col-md-12 rating-categories-container">
    				<p class="rating">Location Rating</p>
                     <div class = "star-container" style = "display:inline-block; margin-left:15px; margin-top:3px; position:absolute;">
                        <% if (location != null) {
                     			for (int i = 0; i < location.locationScore; i++) {
                     	%>
                        <img src = "./img/star.png" class = "star"/>
<!--                         <img src = "./img/star.png" class = "star"/>
                        <img src = "./img/star.png" class = "star"/>
                        <img src = "./img/star.png" class = "star"/>
                        <img src = "./img/star.png" class = "star"/> -->
                        <%
                     			}
                     	}
                        %>
                     </div>
    			</div>
    			<div class="col-lg-4 col-md-12 rating-categories-container">
    				<p class="rating">Chill Rating</p>
                     <div class = "star-container" style = "display:inline-block; margin-left:15px; margin-top:3px; position:absolute;">
                        <% if (location != null) {
                     			for (int i = 0; i < location.noiseScore; i++) {
                     	%>
                        <img src = "./img/star.png" class = "star"/>
                        <%
                     			}
                     	}
                        %>
                     </div>
    			</div>
    		</div>
    	</div>

        <div class = "container-fluid">

            <div class = "row">

                <div class = "col-lg-1 col-md-0" ></div>

                <div class = "col-lg-10 col-md-12">
                    <div class = "col-lg-3 col-md-3">
                        <div class = "rating-tab" id="description-tab">
                        Description
                        </div>
                    </div>

                    <div class = "col-lg-3 col-md-3">
                        <div class = "rating-tab" id="amenities-tab">
                        Amenities
                        </div>
                    </div>

                    <div class = "col-lg-3 col-md-3">
                        <div class = "rating-tab" id="price-graph-tab">
                        Price Graph
                        </div>
                    </div>

                    <div class = "col-lg-3 col-md-3">
                        <div class = "rating-tab" id="floor-plan-tab">
                        Floor Plan
                        </div>
                    </div>

                </div>

                <div class = "col-lg-1 col-md-0" ></div>

            </div>
            

            <div class = "row">
                <div class = "col-lg-1 col-md-0" ></div>

                <div class = "col-lg-10 col-md-12" id="descriptionText">
                    <div class="row" id="firstRowDescription">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Location: Northside of Campus</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Price: <%if (location != null) out.print(location.averageRent);  %>$/Month</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Bedrooms: Available</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <p class="descriptionRow">Description:<% if (location != null) out.print(location.description); %></p>
                        </div>
                    </div>
                </div>
                
                
                <div class = "col-lg-10 col-md-12" id="amenitiesText" style="display:none">
                    <div class="row" id="firstRowDescription">
                        <div class="col-lg-8">
                            <p class="descriptionRow">AMENITIES: Northside of Campus</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Price: <%if (location != null) out.print(location.averageRent);  %>$/Month</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Bedrooms: Available</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <p class="descriptionRow">Description:<% if (location != null) out.print(location.description); %></p>
                        </div>
                    </div>
                </div>
                
                <div class = "col-lg-10 col-md-12" id="priceGraph" style="display:none">
             <%--        <div class="row" id="firstRowDescription">
                        <div class="col-lg-8">
                            <p class="descriptionRow">PRICE GRAPH: Northside of Campus</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Price: <%if (location != null) out.print(location.averageRent);  %>$/Month</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Bedrooms: Available</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <p class="descriptionRow">Description:<% if (location != null) out.print(location.description); %></p>
                        </div>
                    </div> --%>
                    <div id="canvas-holder1">
                    	 <canvas id="chart1" width="300" height="30" />
                    </div>
                    
                </div>
                
                <div class = "col-lg-10 col-md-12" id="floorPlan" style="display:none">
                    <div class="row" id="firstRowDescription">
                        <div class="col-lg-8">
                            <p class="descriptionRow">FLOOR PLAN: Northside of Campus</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Price: <%if (location != null) out.print(location.averageRent);  %>$/Month</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Bedrooms: Available</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <p class="descriptionRow">Description:<% if (location != null) out.print(location.description); %></p>
                        </div>
                    </div>
                </div>
                
                
                
                
                
                
                
                
                
                
                
                
                 <div class = "col-lg-1 col-md-0" ></div>
                
            </div>

        </div>

        <div class = "container-fluid">

            <div class = "row">
                <div class = "col-lg-12" style = "padding-top: 40px; font-size:40px; color:white; text-align:center">Reviews</div>
            </div>

            <div class = "row">
                <div class = "add-review-button">
                    <div class = "col-lg-12" id="writeReview" style = "padding-top: 8px; padding-bottom: 40px; font-size:20px; color:white; text-align:center"><a>Write your own!</a></div>
                </div>
            </div>
            
			<div id ="reviewRow">
				<div style = "background-color: #c05049;text-align:center;padding:20px">
					<div style = "color:#ffcc00;font-size:36px;">Ratings</div>
	
					<div class = "preferences-wrapper">
						<div class = "slider-wrapper" style="padding-top:10%;">
							<p>Management</p>
							
							<div data-role="main" class="ui-content">
							    <form method="post" action="demoform.asp">
							      <input type="range" class = "slider" id="managementPoints" value="5" min="1" max="5">
							    </form>
						  	</div>
	
						  	<div style = "width:100%;">
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
							  	</div>
						  	</div>
							
						</div>
	
						<div class = "slider-wrapper">
							<p>Amenities</p>
							<div data-role="main" class="ui-content">
							    <form method="post" action="demoform.asp">
							      <input type="range" class = "slider" id="amenitiesPoints" value="5" min="1" max="5">
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
	
							  	
							  	</div>
						  	</div>
						</div>
	
						<div class = "slider-wrapper">
							<p>Location</p>
							<div data-role="main" class="ui-content">
							    <form method="post" action="demoform.asp">
							      <input type="range" class = "slider" id="locationPoints" value="5" min="1" max="5">
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
	
							  		
							  	</div>
						  	</div>
						</div>
	
						<div class = "slider-wrapper">
							<p>Noise</p>
							<div data-role="main" class="ui-content">
							    <form method="post" action="demoform.asp">
							      <input type="range" class = "slider" id="noisePoints" value="5" min="1" max="5">
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
							  	</div>
						  	</div>
						</div>
	
						<div class = "slider-wrapper">
							<p>Chill Factor</p>
							<div data-role="main" class="ui-content">
							    <form method="post" action="demoform.asp">
							      <input type="range" class = "slider" id="chillFactorPoints" value="5" min="1" max="5">
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
							  	</div>
						  	</div>
						</div>
	
						
			<!-- 			<div class = "save-sliders">
							SAVE PREFERENCES
						</div> -->
	
					</div>
				</div>
				
				<div class = "row">
	                <div class = "col-lg-1 col-md-0" ></div>
	                <div class = "col-lg-10 col-md-12 filter-row" style = "padding: 0px">
	                    <div class = "filter-row">
	                        <div class = "col-lg-2">
	                            <div class = "filter-button">
	                                <div class = "filter-specifier">Management</div>
	                            </div>
	                        </div>
	
	                        <div class = "col-lg-2">
	                            <div class = "filter-button"> 
	                                <div class = "filter-specifier">Noise</div>  
	                            </div>
	                        </div>
	
	                        <div class = "col-lg-2">
	                            <div class = "filter-button">   
	                                <div class = "filter-specifier">Location</div>
	                            </div>
	                        </div>
	
	                        <div class = "col-lg-2">
	                            <div class = "filter-button">  
	                                <div class = "filter-specifier">Chillness</div>
	                            </div>
	                        </div>
	
	                        <div class = "col-lg-2">
	                            <div class = "filter-button"> 
	                                <div class = "filter-specifier">Amenities</div>
	                            </div>
	                        </div>
	
	                        <div class = "col-lg-2">
	                            <div class = "filter-button">
	                                <div class = "filter-specifier">Price</div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
                	<div class = "col-lg-1 col-md-0" ></div>
            	</div>
            	
				<div class = "row">
					<div class = "col-lg-1"></div>
					<textarea class = "col-lg-10" id="comment">
		                
		            </textarea> 
		            <div class = "col-lg-1"></div>
	            </div>
				
			</div>
			
			<div class = "row" style="display:none;" id="submitReview">
                <div class = "add-review-button">
                    <div class = "col-lg-12"  style = "padding-top: 8px; padding-bottom: 40px; font-size:20px; color:white; text-align:center"><a>Submit</a></div>
                </div>
            </div>
			
			</div>
			
            <div class = "row">
                <div class = "col-lg-1 col-md-0" ></div>
                <div class = "col-lg-10 col-md-12 filter-row" style = "padding: 0px">
                    <div class = "filter-row">
                        <div class = "col-lg-2">
                            <div class = "filter-button">
                                <div class = "filter-specifier">Management</div>
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button"> 
                                <div class = "filter-specifier">Noise</div>  
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button">   
                                <div class = "filter-specifier">Location</div>
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button">  
                                <div class = "filter-specifier">Chillness</div>
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button"> 
                                <div class = "filter-specifier">Amenities</div>
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button">
                                <div class = "filter-specifier">Price</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class = "col-lg-1 col-md-0" ></div>
            </div>

            <div class = "row">
                <div class = "col-lg-1 col-md-0" ></div>

                <div class = "col-lg-10 col-md-12 reviews-container">
                    <!-- insert for loop here -->
                    <%
                    if (location !=null && location.reviews != null) {
	                    System.out.println("size: " + location.reviews.length);
	                    for (int i =0; i < location.reviews.length; i++) {
	                    	Review temp = location.reviews[i];
	                    	User user = UserDataManager.getUser(temp.facebookID);
                    
                    %> 
                    <div class="col-lg-12 single-review">

                        <div class = "reviewer-info-row">

                            <div class = "reviewer-image-and-name">
                                
                                <div class = "reviewer-image-wrapper">
                                <img src = "<%=user.imageURL %>" class = "reviewer-image"/>
                                </div>


                                <div class = "reviewer-username">
                                    <div class = "reviewer-username-row">
                                        <div class = "reviewer-username-cell">
                                        <%=user.name %>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <p class="scrolling-description-row">Description: <%=temp.comment %></p>
                    </div>

					<%
						}  
                    }
					
					%>
                    </div>
                 <div class = "col-lg-1 col-md-0" ></div>
            </div>
        </div>
    </div>

    <div class = "footer">
        <div class = "logo-wrapper">
            <img src = "./img/new-troho.png" style = "height:200px;width:auto">
        </div>
    </div>


	<!-- ChartJS -->
	<script src="js/Chart.js"></script>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    
    
    <script>
    
    
    
    
    
    
    
    
    
    
    
    
    window.onload = function() {
        var ctx1 = document.getElementById("chart1").getContext("2d");
        window.myLine = new Chart(ctx1).Line(lineChartData, {
        	showScale: false,
        	pointDot : true,
            responsive: true
        });

    };
    </script>
    

    <script>
 
 		$(document).ready(function() {
 			$("#writeReview").on("click", function() {
 				$("#reviewRow").toggle();
 				$("#submitReview").toggle();
 			});
 			
 			$(".filter-button").click(function() {
 				
 	            $(this).toggleClass('active');
 	            
 	        });
 			
 			$(".rating-tab").click(function() {
 			       $('.active').removeClass('active')
 			        $(this).addClass('active');
 			       
 			      if($(this).attr('id') == "description-tab")
 			    	{
 			    	 	$("#descriptionText").show();
 			    	 	$("#amenitiesText").hide();
 			    	 	$("#priceGraph").hide();
 			    	 	$("#floorPlan").hide();
 			    	}
 			      else if($(this).attr('id') == "amenities-tab")
 			    	  {
 			    	 	$("#amenitiesText").show();	
 			    	  	$("#descriptionText").hide();
 			    	 	$("#priceGraph").hide();
 			    	 	$("#floorPlan").hide();
 			    	  }
 			      else if($(this).attr('id') == "price-graph-tab")
 			    	  {
 			    	 	$("#priceGraph").show();
 			    	  	$("#descriptionText").hide();
 			    	 	$("#amenitiesText").hide();
 			    	 	$("#floorPlan").hide();
 			    	  }
 			      else if($(this).attr('id') == "floor-plan-tab")
 			    	  {
 			    	 	$("#floorPlan").show();
 			    	  	$("#descriptionText").hide();
 			    	 	$("#amenitiesText").hide();
 			    	 	$("#priceGraph").hide();
 			    	  }
 			      else
 			    	  {}
 			       
 			    });
 			
 		 	$("#submitReview").on("click", function() {
 				console.log("hello");
 				
 				FB.api('/me', function(response) {
 					var fbID = response.id;
 					console.log(fbID);				
 					var houseName = $("#introText").text();
 					var comment = null;
 					comment = $("#comment").val();
			 		var rent = 900;
			 		var managementPoints = $("#managementPoints").val();
			 		var amenitiesPoints = $("#amenitiesPoints").val();
			 		var locationPoints = $("#locationPoints").val();
			 		var noisePoints = $("#noisePoints").val();
			 		var chillFactorPoints = $("#chillFactorPoints").val();
			 		var postData = {
							"housingname": houseName, 
							"fbID": fbID, 
							"review": comment,
							"ratings": [managementPoints, amenitiesPoints, locationPoints, noisePoints, chillFactorPoints], 
							"rent": rent,
							};
					$.ajax({
						url: "/troho/SubmitReview",
						type: "POST",
						data: JSON.stringify(postData),
						dataType: "JSON"
 					});
 				});
 			}); 
 		});
    </script>

</body>
</html>

