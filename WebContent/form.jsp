<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.java.facebook.FBConnection"%>
<%
	FBConnection fbConnection = new FBConnection();
	
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

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

<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '1434163956877430',
      xfbml      : true,
      version    : 'v2.3'
    });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
</script>
<!-- <form name="registration" action="/FirstServletProject/FirstServlet" method="POST">
 -->
 <form id="register" name="registration" method="POST">	
 	First name:
 	<br>
	<input type="text" name="firstname">
	<br>
	Last name:<br>
	<input type="text" name="lastname">
	<input type="submit" value="Submit" />
</form>
<div class="fb-like" data-share="true" data-width="450" data-show-faces="true">
</div>

<div style="margin: 0 auto; background-image: url(./img/fbloginbckgrnd.jpg); height: 360px; width: 610px;">
		<a href="<%=fbConnection.getFBAuthUrl()%>"> 
		<img style="margin-top: 138px;" src="./img/facebookloginbutton.png" />
		</a>
	</div>
</body>

<script>
 
$(function() {
    $('#register').submit(function() {
    //	alert("hello1");
    	var dataString = '&firstname=' + $('input[name=firstname]').val() + 
   						 '&lastname=' + $('input[name=lastname]').val();
    	console.log("Sending data " + dataString);
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/FirstServletProject/FirstServlet",
            success: function() {
            	console.log("hello");
                alert('Form Successfully Submitted');
            },  
            error: function() {
            	console.log("fail");
                alert('There was an error submitting the form');
            },
            data: dataString
        });
        return false;
    }); 
});
</script>

</html>