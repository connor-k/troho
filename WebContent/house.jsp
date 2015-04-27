<%@page import="sql.HousingDataManager"%>
<%@page import="sql.Review"%>
<%@page import="sql.UserDataManager"%>
<%@page import="sql.HousingLocation"%>
<%@page import="sql.User"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page errorPage="404.html" %>


<%@page import="java.util.List"%>
<%@page import="java.lang.String"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% 
	String name = request.getParameter("name");
    HousingLocation location = HousingDataManager.getHousingLocation(name);
    if (name == null) {
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
			.star {
				display:inline-block;
				width:30px;
				height: auto;
			}
		</style>
	<%
	}
	%>
</head>

<body>


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
			<div id="log-out-sequence" onclick="logOut()">
				<div id="log-out-message"></div>
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
    			<div class="col-lg-1"></div>
    			<div class="col-lg-4 col-md-12 rating-categories-container">
    				<p class="rating">Amenities Rating</p>
                     <div class = "star-container" style = "display:inline-block; margin-left:15px; margin-top:3px; position:absolute;">
                        <% if (location != null) {
                     			for (int i = 0; i < location.amenitiesScore; i++) {
                     	%>
                        <img src = "./img/star.png" class = "star"/>
                        <%
                     			}
                     	}
                        %>
                     </div>
    			</div>
    			<div class="col-lg-4 col-md-12 rating-categories-container">
    				<p class="rating">Noise Rating</p>
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
                        <div class = "rating-tab" id="price-graph-tab" active>
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

                <div class = "col-lg-10 col-md-12" id="descriptionText" style="display:none">
                    <div class="row" id="firstRowDescription">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Location: <%=location.address %></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Price: <%if (location != null) out.print(new DecimalFormat("#.##").format(location.averageRent));  %>$/Month</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Rooms: Available to lease</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <p class="descriptionRow">Description: <% if (location != null) out.print(location.description); %></p>
                        </div>
                    </div>
                </div>
                
                
                <div class = "col-lg-10 col-md-12" id="amenitiesText" style="display:none">
                    <div class="row" id="firstRowDescription">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Amenities: <%=location.amenities %></p>
                        </div>
                    </div>
                    
                </div>
                
                <div class = "col-lg-10 col-md-12" id="priceGraph">
                    <div id="canvas-chart-container" style="width:100%">
                    	 <canvas id="chart1" height="100"> </canvas>
                    </div>
                    
                </div>
                
                <div class = "col-lg-10 col-md-12" id="floorPlan" style="display:none">
                    <div class="row" id="firstRowDescription">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Floor Plan: <img style="margin-left:25%;" src="<%=location.floorplanURL %>" height="300" width="400"></p>
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

            <div class = "row" id="rowWrite" style="display:none;">
                <div class = "add-review-button">
                    <div class = "col-lg-12" id="writeReview" style = " padding-top: 8px; padding-bottom: 40px; font-size:20px; color:white; text-align:center"><a>Write your own!</a></div>
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
	                            <div class = "filter-button" id="managementReviewTag">
	                                <div class = "filter-specifier">Management</div>
	                            </div>
	                        </div>
	
	                        <div class = "col-lg-2">
	                            <div class = "filter-button" id="noiseReviewTag"> 
	                                <div class = "filter-specifier">Noise</div>  
	                            </div>
	                        </div>
	
	                        <div class = "col-lg-2">
	                            <div class = "filter-button" id="locationReviewTag">   
	                                <div class = "filter-specifier">Location</div>
	                            </div>
	                        </div>
	
	                        <div class = "col-lg-2">
	                            <div class = "filter-button" id="chillnessReviewTag">  
	                                <div class = "filter-specifier">Chillness</div>
	                            </div>
	                        </div>
	
	                        <div class = "col-lg-2">
	                            <div class = "filter-button" id ="amenitiesReviewTag"> 
	                                <div class = "filter-specifier">Amenities</div>
	                            </div>
	                        </div>
	
	                        <div class = "col-lg-2">
	                            <div class = "filter-button" id="priceReviewTag">
	                                <div class = "filter-specifier">Price</div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
                	<div class = "col-lg-1 col-md-0" ></div>
            	</div>
            	
				<div class = "row"><div class = "col-lg-1"></div><textarea style="font-size:3em;"placeholder="Your comment here" class = "col-lg-10" id="comment"></textarea><div class = "col-lg-1"></div>
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
                            <div class = "filter-button selector" id="managementTag">
                                <div class = "filter-specifier">Management</div>
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button selector" id="noiseTag"> 
                                <div class = "filter-specifier" id="noiseTag">Noise</div>  
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button selector" id="locationTag">   
                                <div class = "filter-specifier">Location</div>
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button selector" id="chillnessTag">  
                                <div class = "filter-specifier">Chillness</div>
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button selector" id="amenitiesTag"> 
                                <div class = "filter-specifier">Amenities</div>
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button selector" id="priceTag">
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
	                    for (int i = location.reviews.length - 1; i >= 0; i--) {
	                    	System.out.println(i);
	                    	Review temp = location.reviews[i];
	                    	User user = UserDataManager.getUser(temp.facebookID);                    
                    %> 
                    <div class="col-lg-6 single-review">

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
                            <span style="padding-left: 75%; font-size:1.2em;"><%=temp.timeWritten %></span>
                        </div>

                        <p class="scrolling-description-row">Description: <%=temp.comment %></p>

                    </div>
                       <div class = "star-container col-lg-4" style = "display:inline-block; margin-left:15px; margin-top:3px; ">
                        	<br>       	
                        	<br>
	                      	Management Score
	                        <br>
	                        <% 
                     			for (int k = 0; k < temp.noiseScore; k++) {
	                     	%>
	                        	<img src = "./img/star.png" class = "star"/>
	                        <%
                   				}                     	
	                        %>
	                        <br>
	                        <br>
	                        Amenities Score
	                        <br>
	                        <% 
                     			for (int k = 0; k < temp.noiseScore; k++) {
	                     	%>
	                        	<img src = "./img/star.png" class = "star"/>
	                        <%
                   				}                     	
	                        %>
	                        <br>
	                        <br>
	                        Location Score
	                        <br>
	                        <% 
                     			for (int k = 0; k < temp.noiseScore; k++) {
	                     	%>
	                        	<img src = "./img/star.png" class = "star"/>
	                        <%
                   				}                     	
	                        %>
	                        <br>
	                        <br>
	                        Chill Score
	                        <br>
	                        <% 
                     			for (int k = 0; k < temp.noiseScore; k++) {
	                     	%>
	                        	<img src = "./img/star.png" class = "star"/>
	                        <%
                   				}                     	
	                        %>
	                        <br>
	                        <br>
	                        Noise Score
	                        <br>
	                        <% 
                     			for (int k = 0; k < temp.noiseScore; k++) {
	                     	%>
	                        	<img src = "./img/star.png" class = "star"/>
	                        <%
                   				}                     	
	                        %>
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
    </body>


	<script src="js/fbhouse.js">
	</script>

	<!-- ChartJS -->
	<script src="js/Chart.js"></script>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    
    
    <script>
		    var labelsData = [];
		    var avgRentData = [];
		    
		    <% 
		    	Object[] rentData = HousingDataManager.getRentOverTimeData(name);
		    	if (rentData != null){
		    		String[] years = (String[])rentData[0];
					Double[] averageRent = (Double[])rentData[1];
				
		    	
					for (int i = 0; i < years.length && i < averageRent.length; ++i) 
					{
					%>
						labelsData.push( <%=years[i]%>);
						avgRentData.push(<%=averageRent[i]%>);
			<%
					}
		    	}
		    %>
		    
		    console.log(labelsData);
		    console.log(avgRentData);
		    
		    
		    var lineChartData = {
		    	    labels: labelsData,
		    	    datasets: [
		    	        {
		    	            label: "Average Rent Over Years",
		    	            fillColor: "rgba(220,220,220,0.2)",
		    	            strokeColor: "rgba(220,220,220,1)",
		    	            pointColor: "rgba(220,220,220,1)",
		    	            pointStrokeColor: "#fff",
		    	            pointHighlightFill: "#fff",
		    	            pointHighlightStroke: "rgba(220,220,220,1)",
		    	            data: avgRentData
		    	        }
		    	    ]
		    	};
		    
		    var line_chart_options = {
		        	showScale: true,
		        	pointDot : true,
		            responsive: true,
		            scaleShowLabels: true
		        };
		    
			window.onload = function(){
			    var ctx = document.getElementById("chart1").getContext("2d");
				window.myLine = new Chart(ctx).Line(lineChartData, line_chart_options);
				$("#description-tab").click();
			}
    </script>
    
    
    <script>
    
	var hideShowSubmit = function(){
		FB.api('/me', function(response) {
			console.log("Calling api");
			var fbID = response.id;
			var houseName = $('#introText').text();
			var postData = {
				"fbID": fbID, 
				"houseName": houseName
				};
			console.log(postData);
			$.ajax({
				url: "/troho/UserReviewedHousing",
				type: "GET",
				data: JSON.stringify(postData),
				dataType: "JSON",
				success:function(data) {
					console.log("val = " + data.reviewBool + data.authBool);
					if(data.reviewBool === 'false' && data.authBool ==='true') {
						console.log("Bout to hide review");
						$("#rowWrite").toggle();
					}
				}
			});
		});
	};
		
    	$(document).ready(function() {
		   
		    
    		// Makes it so that you can recognize show, hide calls on div
    		(function ($) {
		        $.each(['show', 'hide'], function (i, ev) {
		          var el = $.fn[ev];
		          $.fn[ev] = function () {
		            this.trigger(ev);
		            return el.apply(this, arguments);
		          };
		        });
		      })(jQuery);
    		
 			var houseName = $('#introText').text();
 			sessionStorage.numberOfReviewsOnClient = 0;
 			
 			var posting = $.post( 'http://localhost:8080/troho/NumberOfReviews',houseName);
			posting.done( function( numberOfReviewsOnServer ) {
				
			  	sessionStorage.numberOfReviewsOnClient = numberOfReviewsOnServer;
			  	
			  	(function poll() {
					setTimeout(function() {
	 		 	
						var reviewsOnServer;
						
	 		 			var polling = $.post( 'http://localhost:8080/troho/NumberOfReviews',houseName);
	 					polling.done( function( reviewsOnServer ) {		
	 				  		console.log("reached poll");
	 				  		console.log("Client: " + sessionStorage.numberOfReviewsOnClient + " , " + 'Server' + reviewsOnServer);
	 				  		if(sessionStorage.numberOfReviewsOnClient !== reviewsOnServer) {
	 				  			var difference = reviewsOnServer - sessionStorage.numberOfReviewsOnClient;
	 				  			console.log("Difference of " + difference);
	 				  			console.log("Number of Reviews on Client: " + sessionStorage.numberOfReviewsOnClient);
	 				  			var arr = [];
	 				  			for(var i = 0; i < 6; i++) {
	 				  				arr.push(false);
	 				  			}
	 				  			
	 				  			var houseName = $('#introText').text();
	 			 		 		var postData = {
	 									'houseName': houseName, 
	 									'tags': [arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]]
	 									};
	 			 		 		$.ajax({
	 			 		 			url: '/troho/ReviewServlet',
	 								type: 'POST',
	 								data: JSON.stringify(postData),
	 								dataType: 'JSON',
	 								success:function(data) {
	 									var reviewsArr = data.reviews;
	 									var htmlText = '';
	 									
	 									console.log("Difference = " + difference);
	 									
	 									for (var i = 0; i < difference; i++) {
	 										
	 										console.log(i);
	 										htmlText +='<div class="col-lg-12 single-review"><div class = "reviewer-info-row"><div class = "reviewer-image-and-name"><div class = "reviewer-image-wrapper"><img src =' +  reviewsArr[i].userImg  + ' class = "reviewer-image"/></div><div class = "reviewer-username"><div class = "reviewer-username-row"><div class = "reviewer-username-cell">' + reviewsArr[i].name+'</div></div></div></div></div><p class="scrolling-description-row">Description: ' + reviewsArr[i].review + '</p></div>';
	 										htmlText += '<div class = "star-container col-lg-4" style = "display:inline-block; margin-left:15px; margin-top:3px; ">';
	 										
	 										htmlText += '<br><br>Management Score<br>'
	 			                     		for (var k = 0; k < reviewsArr[i].managementScore; k++) {
	 				                        	htmlText += '<img src = "./img/star.png" class = "star"/>'
	 			                     		}
	 										htmlText += '<br><br>Amenities Score<br>'
	 				                     		for (var k = 0; k < reviewsArr[i].amenitiesScore; k++) {
	 					                        	htmlText += '<img src = "./img/star.png" class = "star"/>'
	 				                     		}
	 										htmlText += '<br><br>Location Score<br>'
	 				                     		for (var k = 0; k < reviewsArr[i].locationScore; k++) {
	 					                        	htmlText += '<img src = "./img/star.png" class = "star"/>'
	 				                     		}
	 										htmlText += '<br><br>Chill Score<br>'
	 				                     		for (var k = 0; k < reviewsArr[i].chillScore; k++) {
	 					                        	htmlText += '<img src = "./img/star.png" class = "star"/>'
	 				                     		}
	 										htmlText += '<br><br>Noise Score<br>'
	 				                     		for (var k = 0; k < reviewsArr[i].noiseScore; k++) {
	 					                        	htmlText += '<img src = "./img/star.png" class = "star"/>'
	 				                     		}
	 										htmlText+='</div>';
	 									}
	 									
	 									htmlText += $('.reviews-container').html();
	 									
	 									$('.reviews-container').html(htmlText);	
	 									sessionStorage.numberOfReviewsOnClient = reviewsOnServer;
	 								}
	 			 		 		});
	 				  		}
	 					});
	 					
	 					poll();
	 		 	       
	 		 	    }, 2000);
			  	})()
			  	
			});
			
    	});
 				
 			$("#writeReview").on("click", function() {
 				$("#reviewRow").toggle();
 				$("#submitReview").toggle();
 			});
 			
 			$(".filter-button").click(function() {			
 	            $(this).toggleClass('active');
 	        });
 			
 			
 			// Handles Switching Between Different Tabs
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
 					var houseName = $("#introText").text();
 					var comment = null;
 					comment = $("#comment").val();
			 		var rent = 900;
			 		var managementPoints = $("#managementPoints").val();
			 		var amenitiesPoints = $("#amenitiesPoints").val();
			 		var locationPoints = $("#locationPoints").val();
			 		var noisePoints = $("#noisePoints").val();
			 		var chillFactorPoints = $("#chillFactorPoints").val();
			 		var arr = []; 
	 		 		if ($("#managementReviewTag").hasClass("active")) {
	 		 			arr.push(true);
	 		 		} else {
	 		 			arr.push(false);
	 		 		}
	 		 		if ($("#noiseReviewTag").hasClass("active")) {
	 		 			arr.push(true);
	 		 		} else {
	 		 			arr.push(false);
	 		 		}
	 		 		if ($("#locationReviewTag").hasClass("active")) {
	 		 			arr.push(true);
	 		 		} else {
	 		 			arr.push(false);
	 		 		}
	 		 		if ($("#chillnessReviewTag").hasClass("active")) {
	 		 			arr.push(true);
	 		 		} else {
	 		 			arr.push(false);
	 		 		}
	 		 		if ($("#amenitiesReviewTag").hasClass("active")) {
	 		 			arr.push(true);
	 		 		} else {
	 		 			arr.push(false);
	 		 		}
	 		 		if ($("#priceReviewTag").hasClass("active")) {
	 		 			arr.push(true);
	 		 		} else {
	 		 			arr.push(false);
	 		 		}
			 		var postData = {
							"housingname": houseName, 
							"fbID": fbID, 
							"review": comment,
							"ratings": [managementPoints, amenitiesPoints, locationPoints, noisePoints, chillFactorPoints], 
							"rent": rent,
							"tags":[arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]]
							};
					$.ajax({
						url: "/troho/SubmitReview",
						type: "POST",
						data: JSON.stringify(postData),
						dataType: "JSON"
 					});
 				});
 				$("#rowWrite").toggle(); 
 				$("#reviewRow").toggle();
 				$("#submitReview").toggle();
 		 	    // Preventing default action of the event
 			});
 		 	
 		 	$(".selector").on("click", function() {
 		 		console.log("pressed");
 		 		var arr = []; 
 		 		if ($("#managementTag").hasClass("active")) {
 		 			arr.push(true);
 		 		} else {
 		 			arr.push(false);
 		 		}
 		 		if ($("#noiseTag").hasClass("active")) {
 		 			arr.push(true);
 		 		} else {
 		 			arr.push(false);
 		 		}
 		 		if ($("#locationTag").hasClass("active")) {
 		 			arr.push(true);
 		 		} else {
 		 			arr.push(false);
 		 		}
 		 		if ($("#chillnessTag").hasClass("active")) {
 		 			arr.push(true);
 		 		} else {
 		 			arr.push(false);
 		 		}
 		 		if ($("#amenitiesTag").hasClass("active")) {
 		 			arr.push(true);
 		 		} else {
 		 			arr.push(false);
 		 		}
 		 		if ($("#priceTag").hasClass("active")) {
 		 			arr.push(true);
 		 		} else {
 		 			arr.push(false);
 		 		}
 		 		var houseName = $("#introText").text();
 		 		var postData = {
						"houseName": houseName, 
						"tags": [arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]]
						};
 		 		$.ajax({
 		 			url: "/troho/ReviewServlet",
					type: "POST",
					data: JSON.stringify(postData),
					dataType: "JSON",
					success:function(data) {
						var reviewsArr = data.reviews;
						var htmlText = "";
						for (var i = 0; i < reviewsArr.length; i++) {				
							htmlText += '<div class="col-lg-12 single-review"><div class = "reviewer-info-row">';
							htmlText += '<div class = "reviewer-image-and-name"><div class = "reviewer-image-wrapper"><img src =' +  reviewsArr[i].userImg  + ' class = "reviewer-image"/>';
							htmlText += '</div><div class = "reviewer-username"><div class = "reviewer-username-row"><div class = "reviewer-username-cell">' + reviewsArr[i].name+'</div></div></div></div>';
							htmlText += '<span style="padding-left: 75%; font-size:1.2em;">' + reviewsArr[i].timeWritten + '</span></div>';
							htmlText += '<p class="scrolling-description-row">Description: ' + reviewsArr[i].review + '</p></div>';	
							
							htmlText += '<div class = "star-container col-lg-4" style = "display:inline-block; margin-left:15px; margin-top:3px; ">';
							
							htmlText += '<br><br>Management Score<br>'
                     		for (var k = 0; k < reviewsArr[i].managementScore; k++) {
	                        	htmlText += '<img src = "./img/star.png" class = "star"/>'
                     		}
							htmlText += '<br><br>Amenities Score<br>'
	                     		for (var k = 0; k < reviewsArr[i].amenitiesScore; k++) {
		                        	htmlText += '<img src = "./img/star.png" class = "star"/>'
	                     		}
							htmlText += '<br><br>Location Score<br>'
	                     		for (var k = 0; k < reviewsArr[i].locationScore; k++) {
		                        	htmlText += '<img src = "./img/star.png" class = "star"/>'
	                     		}
							htmlText += '<br><br>Chill Score<br>'
	                     		for (var k = 0; k < reviewsArr[i].chillScore; k++) {
		                        	htmlText += '<img src = "./img/star.png" class = "star"/>'
	                     		}
							htmlText += '<br><br>Noise Score<br>'
	                     		for (var k = 0; k < reviewsArr[i].noiseScore; k++) {
		                        	htmlText += '<img src = "./img/star.png" class = "star"/>'
	                     		}
							htmlText+='</div>';
						}
						console.log(htmlText);
						$(".reviews-container").html(htmlText);	
					}
 		 		});
 		 		
 		 	});
 		 	
 		//});
    </script>

</html>

