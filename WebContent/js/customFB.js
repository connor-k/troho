// This is called with the results from from FB.getLoginStatus().
		function statusChangeCallback(response) {
			console.log('statusChangeCallback');
			console.log(response);
			// The response object is returned with a status field that lets the
			// app know the current login status of the person.
			// Full docs on the response object can be found in the documentation
			// for FB.getLoginStatus().
			if (response.status === 'connected') {
				// Logged into your app and Facebook.
			
				updateHeaderSuccessfulLogIn();
			} else if (response.status === 'not_authorized') {
				// The person is logged into Facebook, but not your app.
				updateHeaderNotLoggedIn();
			} else {
				// The person is not logged into Facebook, so we're not sure if
				// they are logged into this app or not.
				updateHeaderNotLoggedIn();
			}
		}

		// This function is called when someone finishes with the Login
		// Button.  See the onlogin handler attached to it in the sample
		// code below.
		function checkLoginState() {
			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});
		}

		window.fbAsyncInit = function() {
			FB.init({
				appId : '1434163956877430',
				cookie : true, // enable cookies to allow the server to access 
				// the session
				status : true,
				xfbml : true, // parse social plugins on this page
				version : 'v2.3'
			});

			// Now that we've initialized the JavaScript SDK, we call 
			// FB.getLoginStatus().  This function gets the state of the
			// person visiting this page and can return one of three states to
			// the callback you provide.  They can be:
			//
			// 1. Logged into your app ('connected')
			// 2. Logged into Facebook, but not your app ('not_authorized')
			// 3. Not logged into Facebook and can't tell if they are logged into
			//    your app or not.
			//
			// These three cases are handled in the callback function.

			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});

		};
		
		function updateFriends(response)
		{
			var list = document.getElementById("friends");
			list.innerHTML = '';
			console.log(response.data.length);
			for(i=0; i < response.data.length; i++)
			{
				var name = response.data[i].name;
				console.log(name);
				list.innerHTML = list.innerHTML + name + "<br>";
			}
		}

		// Load the SDK asynchronously
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));

		// Here we run a very simple test of the Graph API after login is
		// successful.  See statusChangeCallback() for when this call is made.
		function updateHeaderSuccessfulLogIn() {
			document.getElementById('log-in-sequence').style.display = "none";
			var name = null;
			var imgURL = null;
			var fbID = null;
			var email = null;
			FB.api('/me', function(response) {
				document.getElementById('user-sequence').style.display = "inline";
				document.getElementById('welcome-message').innerHTML = 'Welcome ' + response.name + '!';
				name = response.name;
				fbID = response.id;
				email = response.email;
				FB.api('/me/picture?type=large', function(response) {
					console.log("Large " + response.data.url);
	 				imgURL = response.data.url;
	 				document.getElementById('profile-image').setAttribute("src", imgURL);
	 				createUser(name, imgURL, fbID, email);
				});
			});
		}
		
		function updateHeaderNotLoggedIn() {
			document.getElementById('user-sequence').style.display = "none";
			document.getElementById('log-in-sequence').style.display = "inline";
		}
		
		function logIn() {
			FB.login(function(response){
				  checkLoginState();
				}, {scope: 'public_profile,user_friends, email'});
		}
		function goToUser() {
			FB.api('/me', function(response) {
				var site = "/troho/user.jsp?id=" + response.id;
				window.open(site,"_self")
			});
		}
		
		function createUser(name, imgURL, fbID, email) {
			$.ajax({
				  url: "/troho/CreateUser",
				  type: "POST",
				  data: {name : name, url:imgURL, fbID: fbID, email:email},
				  dataType: "JSON"
				});
		}
