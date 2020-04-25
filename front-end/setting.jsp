<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String name = request.getParameter("name");
if(name == null) name = "";

String latitude = request.getParameter("latitude");
if(latitude == null) latitude = "";
String longitude = request.getParameter("longitude");
if(longitude == null) longitude = "";

%>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<title>Setting Page</title>
	<style>
		@import url('https://fonts.googleapis.com/css?family=Sen&display=swap');
		* {
			color: #b7472a;
			font-family: 'Sen', sans-serif;
		}
		body {
			background-image: url("back.jpg");
			background-size: cover;
			background-color: #5f6caf;
		}
		h1 {
			margin-left: auto;
			margin-right: auto;
		}
		#container {
			background-color: #edf7fa;
			opacity: 1;
			margin-top: 100px;
			margin-bottom: 100px;
			padding: 20px;
			width: 800px;
		}
		#map {
			width: 400px;
			margin: 20px;
		}
	</style>
</head>
<body>
	<div id="container" class="container text-center">
		<h1>Change Setting</h1>
		<form id="form">
			<div class="row">
				<label for="name" class="col-3 m-3">Your name:</label>
				<input type="text" class="col-5 m-3" id="name" placeholder="Previous name goes here">
			</div>
			<div class="row">
				<label for="style" class="col-3 m-3">Your music style:</label>
				<select class="form-control col-5 m-3" id="style" name="genre"> 
					<option value="">Previous selection goes here</option>
					<option value="pop">Pop</option>
					<option value="rock">Rock</option>
					<option value="jazz">Jazz</option>
					<option value="country">Country</option>
					<option value="folk">Folk</option>
					<option value="blues">Blues</option>
					<option value="hiphop">Hip Hop</option>
					<option value="other">Other</option>
				</select>
			</div>
			<div class="row">
				<label for="instrument" class="col-3 m-3">Instrument you play:</label>
				<select class="form-control col-5 m-3" id="instrument" name=instrument> 
					<option value="">Previous selection goes here</option>
					<option value="guitar">Guitar</option>
					<option value="bass">Bass</option>
					<option value="drum">Drum</option>
					<option value="piano">Piano</option>
					<option value="violin">Violin</option>
					<option value="saxophone">Saxophone</option>
					<option value="trumpet">Trumpet</option>
					<option value="other">Other</option>
				</select>
			</div>
			<div class="row">
				<label for="googleMap" class="col-3 m-3">Select your physical location from map:</label>
				<div id="googleMap"  style="width:800px;height:500px;"></div>
				<div id="current" value=<%=latitude %>>Nothing yet...</div>
				<div id ="hohoho" value=<%=longitude %>></div>
			</div>
			<div class="row justify-content-center">
				<a type="button" class="btn btn-info col-3 m-3" href="dashboard.html">Save</a>
			</div>
		</form>
	</div>
	
	<script>
		var btn = document.getElementById("mapButton");
		
		function myMap() {
			var map = new google.maps.Map(document.getElementById("googleMap"), {
			    zoom: 13,
			    center: new google.maps.LatLng(34.02116, -118.287352),
			});
			
			var myMarker = new google.maps.Marker({
			    position: new google.maps.LatLng(34.02116, -118.287352),
			    draggable: true
			});
			
			myMarker.setMap(map);
			
			
			google.maps.event.addListener(map, 'click', function(evt){

				myMarker.setPosition(evt.latLng);
				map.panTo(evt.latLng);
				
			    document.getElementById('current').innerHTML = '<p>Marker dropped: Current Lat: ' + evt.latLng.lat().toFixed(3) + ' Current Lng: ' + evt.latLng.lng().toFixed(3) + '</p>';
			    document.getElementById('current').value= evt.latLng.lat().toFixed(3);
			    document.getElementById('hohoho').value= evt.latLng.lng().toFixed(3);
			});

			/*google.maps.event.addListener(myMarker, 'dragstart', function(evt){
			    document.getElementById('current').innerHTML = '<p>Currently dragging marker...</p>';
			});*/
		}
	</script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBjf0f_NE8y77El9cgubT9PuHcuA_HYH-E&callback=myMap"></script>
</body>
</html>