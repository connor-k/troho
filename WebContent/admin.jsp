<%@page import="sql.HousingDataManager"%>
<%@page import="sql.HousingLocation"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Admin Page</title>
	</head>
	
	<!-- jQuery -->
	<script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

<body>

	<select id="housingType" >
		<option value="1">Apartment</option>
		<option value="2">Dorm</option>
		<option value="3">House</option>
	</select>

	<br><br><br><br> 
	
	Housing Name: <br>
	<input class="adminInput" type="text" id="housingName" name="Housing Name">

	<br><br><br><br>
	
	Address: <br>
	<input class="adminInput" type="text" id="address" name="Address">

	<br><br><br><br>
	
	Description: <br>
	<textarea class="adminInput" id="description" rows="10" cols="30" name="Description of Housing Location">
	</textarea>

	<br><br><br><br>
	
	Amenities: <br>
	<textarea class="adminInput" id="amenities" rows="10" cols="30" name="Amenities of Housing Location">
	</textarea>

	<br><br><br><br>
	
	Image Icon URL: <br>
	<input class="adminInput" type="text" id="iconURL" name="Image Icon URL">

	<br><br><br><br>
	
	Floor Plan URL: <br>
	<input class="adminInput" type="text" id="floorPlanURL" name="Floor Plan URL">

	<br><br><br><br>
	
	GPS Latitude: <br>
	<input class="adminInput" type="text" id="gpsLatitude" name="GPS Latitude">

	<br><br><br><br>
	
	GPS Longitude: <br>
	<input class="adminInput" type="text" id="gpsLongitude" name="GPS Longitude">

	<br><br><br><br>
	
	Minutes Walking:<br>
	<input class="adminInput" type="text" id="minutesWalking" name="Minutes Walking">

	<br><br><br><br>
	
	Minutes Biking:<br>
	<input class="adminInput" type="text" id="minutesBiking" name="Minutes Biking">

	<br><br><br><br>
	
	Avereage Rent:<br>
	<input class="adminInput" type="text" id="rent" name="Average Rent">

	<br><br><br><br>
	<button onClick="createNewHousingLocation()">Create New Housing Location</button>

	<script>

		function createNewHousingLocation() {

			var elements = document.getElementsByClassName("adminInput");
			for (var i = 0; i < elements.length; i++) {
				if (!elements[i].value) {
					alert("You are missing input for: " + elements[i].name);
					return;
				}

				if (i > 6) {
					if (isNaN(elements[i].value)) {
						alert("Numerical input is required for: "
								+ elements[i].name);
						return;
					}
				}
			}

			var housingType = document.getElementById("housingType").value;
			var housingName = document.getElementById("housingName").value;
			var address = document.getElementById("address").value;
			var description = document.getElementById("description").value;
			var amenities = document.getElementById("amenities").value;
			var iconURL = document.getElementById("iconURL").value;
			var floorPlanURL = document.getElementById("floorPlanURL").value;
			var gpsLatitude = document.getElementById("gpsLatitude").value;
			var gpsLongitude = document.getElementById("gpsLongitude").value;
			var minutesWalking = document.getElementById("minutesWalking").value;
			var minutesBiking = document.getElementById("minutesBiking").value;
			var rent = document.getElementById("rent").value;

			$.ajax({
				url : "/troho/CreateHousingLocation",
				type : "POST",
				data : {
					housingType : housingType,
					housingName : housingName,
					address : address,
					description : description,
					amenities : amenities,
					iconURL : iconURL,
					floorPlanURL : floorPlanURL,
					gpsLatitude : gpsLatitude,
					gpsLongitude : gpsLongitude,
					minutesWalking : minutesWalking,
					minutesBiking : minutesBiking,
					rent : rent,
				},
				dataType : "JSON"
			});
		}
	</script>

</body>
</html>