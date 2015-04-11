<%@page import="com.java.Data.HousingDataManager"%>
<%@page import="com.java.Data.Review"%>

<%@page import="java.util.List"%>



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
    <link href="css/home.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Indie Flower' rel='stylesheet' type='text/css'>
    <link href="http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

</head>

<body>

    <div class = "header">
        <div>
            <img src = "./img/new-troho.png" style = "height:80px"/>
        </div>

        <div class = "log-div">
            <div id = "log-in">Log In</div>
            <div id = "log-in-line"></div>
            <div id = "sign-up">Sign Up</div>
        </div>

    </div>

    <br>
    
	   <!--<div class="row">
            <div class="col-lg-12">
				<div id="introText">
					<p>Gateway Apartments</p>
				</div>
			</div>
		</div>-->
			<!--<div class="col-sm-12 line-across"> 
			</div>-->
            <!--<div id="introText">
                    <p>Gateway Apartments</p>
                </div>-->
    <div>
        <div id = "top-text-wrap">
            <p id = "introText">Gateway Apartments</p>
                    <!-- </div> -->
                    <!-- <div> -->
            <p id="addressText">3335 S. Figueroa St.</p>
        </div>

        <div style = "-webkit-filter: blur(0px);">
        <div class = "housing-image-card" style = "-webkit-filter:grayscale(1);">
            <div style = "-webkit-filter: blur(2px);">
            <!-- <div id="introText"> -->
            <!-- <div id="introText"> -->
            
            </div>
        </div>
    </div>
            		<!--<div class="row">
			<div class="col-sm-12">
				<p id="addressText">3335 S. Figueroa St.</p>
			</div>
		</div>-->
    <!-- /.container -->

    <!-- <a name="about"></a> -->
    <!--<div class = "house-images-table">
        <div class = "housing-image-container">
            <div class="housing-image">
                
            </div>
        </div>
    </div>-->

    <div class = "rating-master-container">

    	<div class="container" style = "padding:40px 0;">
    		<div class="row">
    			<div class="col-lg-12" style = "padding:20px;">
    				<p id="ratingTextBig">Overall Rating</p>
                    <div class = "star-container">
                        <img src = "./img/star.png" class = "star"/>
                        <img src = "./img/star.png" class = "star"/>
                        <img src = "./img/star.png" class = "star"/>
                     </div>
    			</div>
    		</div>
            <br>
    		<div class="row">
    			<div class="col-lg-4 col-md-12 rating-categories-container" style = "margin-left:-55px" >
    				<p class="rating">Management Rating</p>
                     <div class = "star-container" style = "display:inline-block; margin-left:15px; margin-top:3px; position:absolute;">
                        <img src = "./img/star.png" class = "star"/>
                        <img src = "./img/star.png" class = "star"/>
                        <img src = "./img/star.png" class = "star"/>
                        <img src = "./img/star.png" class = "star"/>
                        <img src = "./img/star.png" class = "star"/>
                     </div>
    			</div>
    			<div class="col-lg-4 col-md-12 rating-categories-container">
    				<p class="rating">Location Rating</p>
                     <div class = "star-container" style = "display:inline-block; margin-left:15px; margin-top:3px; position:absolute;">
                        <img src = "./img/star.png" class = "star"/>
                        <img src = "./img/star.png" class = "star"/>
                        <img src = "./img/star.png" class = "star"/>
                     </div>
    			</div>
    			<div class="col-lg-4 col-md-12 rating-categories-container">
    				<p class="rating">Chill Rating</p>
                     <div class = "star-container" style = "display:inline-block; margin-left:15px; margin-top:3px; position:absolute;">
                        <img src = "./img/star.png" class = "star"/>
                        <img src = "./img/star.png" class = "star"/>
                        <img src = "./img/star.png" class = "star"/>
                     </div>
    			</div>
    		</div>
    	</div>

        <div class = "container-fluid">

            <div class = "row">

                <div class = "col-lg-1 col-md-0" ></div>

                <div class = "col-lg-10 col-md-12">

                    <!-- <div class = "col-lg-2 rating-tab" style = "margin-left:12%"> -->
                        <!-- Description -->
                    <!-- </div> -->

                    <div class = "col-lg-3 col-md-3">
                        <div class = "rating-tab">
                        Description
                        </div>
                    </div>

                    <div class = "col-lg-3 col-md-3">
                        <div class = "rating-tab">
                        Amenities
                        </div>
                    </div>

                    <div class = "col-lg-3 col-md-3">
                        <div class = "rating-tab">
                        Price Graph
                        </div>
                    </div>

                    <div class = "col-lg-3 col-md-3">
                        <div class = "rating-tab">
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
                            <p class="descriptionRow">Price: 1000$/Month</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <p class="descriptionRow">Bedrooms: Available</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <p class="descriptionRow">Description: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Description: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                        </div>
                    </div>
                </div>

                 <div class = "col-lg-1 col-md-0" ></div>
                
            </div>

        </div>

        <div class = "container-fluid">

            <div class = "row">
                <div class = "col-lg-12" style = "padding-top: 80px; padding-bottom: 40px; font-size:36px; color:white; text-align:center">Reviews</div>
            </div>

            <div class = "row">
                <div class = "col-lg-1 col-md-0" ></div>
                <div class = "col-lg-10 col-md-12 filter-row" style = "padding: 0px">
                    <div class = "filter-row">
                        <div class = "col-lg-2">
                            <div class = "filter-button">
                                Management   
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button"> 
                                Noise  
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button">   
                                Location
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button">  
                                Chill 
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button"> 
                                <p>Amenities</p>
                            </div>
                        </div>

                        <div class = "col-lg-2">
                            <div class = "filter-button">
                                Price   
                            </div>
                        </div>
                    </div>
                </div>
                <div class = "col-lg-1 col-md-0" ></div>
            </div>

            <div class = "row">
                <div class = "col-lg-1 col-md-0" ></div>

                <div class = "col-lg-10 col-md-12 reviews-container">
                 <div class="col-lg-12 single-review">
						
						
                        <div class = "reviewer-info-row">

                            <div class = "reviewer-image-and-name">
                                <div class = "reviewer-image">
                                </div>

                                <div class = "reviewer-username">
                                    Calvin LeGassick
                                </div>
                            </div>

                        </div>

                        <p class="scrolling-description-row">Description: Lorem Ipsum</p>
                    </div>
                     <% 
						HousingDataManager hdm = new HousingDataManager();
					 	Review[] list = hdm.getReviews(0);
					 	if (list != null) { 
						 	for (int i = 0; i < list.length; i++)
						  	{
						 		Review s = list[i];
					 %>
                    <div class="col-lg-12 single-review">
						
						
                        <div class = "reviewer-info-row">

                            <div class = "reviewer-image-and-name">
                                <div class = "reviewer-image">
                                </div>

                                <div class = "reviewer-username">
                                    Calvin LeGassick
                                </div>
                            </div>

                        </div>

                        <p class="scrolling-description-row">Description: <%out.println(s); %></p>
                    </div>
                  	<%
							}
					 	}
					%>

                <!--     <div class="col-lg-12 single-review">

                        <div class = "reviewer-info-row">
                            
                        </div>

                        <p class="scrolling-description-row">Description: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Description: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.Description: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                    </div> -->

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

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>

