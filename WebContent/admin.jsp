<%@page import="sql.HousingDataManager"%>
<%@page import="sql.HousingLocation"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
</head>
<body>

	<select name="dropdown">
		<option value="1">Apartment</option>
		<option value="2">Dorm</option>
		<option value="3">House</option>
	</select>

	<br><br><br><br> 
	
	Housing Name: <br>
	<input type="text" name="housingName">

	<br><br><br><br>
	
	Address: <br>
	<input type="text" name="address">

	<br><br><br><br>
	
	Description: <br>
	<textarea name="description" rows="10" cols="30">
	Please enter a description.
	</textarea>

	<br><br><br><br>
	
	Image Icon URL: <br>
	<input type="text" name="iconURL">

	<br><br><br><br>
	
	Floor Plan URL: <br>
	<input type="text" name="floorPlanURL">

	<br><br><br><br>
	
	GPS Latitude: <br>
	<input type="text" name="gpsLatitude">

	<br><br><br><br>
	
	GPS Longitude: <br>
	<input type="text" name="gpsLongitude">

	<br><br><br><br>
	
	Minutes Walking:<br>
	<input type="text" name="minutesWalking">

	<br><br><br><br>
	
	Minutes Biking:<br>
	<input type="text" name="minutesBiking">

	<br><br><br><br>
	<button onClick="createNewHousingLocation()">Create New Housing Location</button>

	<script>
		function createNewHousingLocation(){
			var housingName = document.getElementById("housingName").value;
 	 		var address = document.getElementById("address").value;
 	 		var description = document.getElementById("description").value;
 	 		var iconURL = document.getElementById("iconURL").value;
 	 		var floorPlanURL = document.getElementById("floorPlanURL").value; 
 	 		var gpsLatitude = document.getElementById("gpsLatitude").value;
 	 		var gpsLongitude = document.getElementById("gpsLongitude").value; 
 	 		var minutesWalking = document.getElementById("minutesWalking").value;
 	 		var minutesBiking = document.getElementById("minutesBiking").value; 
 	 		
			$.ajax({
				  url: "/troho/CreateHousingLocation",
				  type: "POST",
				  data: {housingName : housingName, address : address, description : description, iconURL : iconURL, floorPlanURL : floorPlanURL,
					  gpsLatitude : gpsLatitude, gpsLongitude : gpsLongitude, minutesWalking : minutesWalking, minutesBiking : minutesBiking  },
				  dataType: "JSON"
				});
		}
	</script>

</body>
</html>