<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%
	String name = request.getParameter("name");
	if(name == null) name = "";
	String username = request.getParameter("username");
	if(username == null) username = "";
	String password = request.getParameter("password");
	if(password == null) password = "";
	String email = request.getParameter("email");
	if(email == null) email = "";
	
	String nameEr = (String) request.getAttribute("nameEr");
	String usernameEr = (String)request.getAttribute("usernameEr");
	String passwordEr = (String)request.getAttribute("passwordEr");
	String emailEr = (String)request.getAttribute("emailEr");

	String range = request.getParameter("range");
	String rangee = (String) request.getAttribute("rangee");
	if(range == null) range = "";
	if(rangee == null) rangee = "";
	
	String pop = request.getParameter("pop");
	if(pop == null) pop = "";
	String jazz = request.getParameter("jazz");
	if(jazz == null) jazz = "";
	String rock = request.getParameter("rock");
	if(rock == null) rock = "";
	String country = request.getParameter("country");
	if(country == null) country = "";
	String folk = request.getParameter("folk");
	if(folk == null) folk = "";
	String blues = request.getParameter("blues");
	if(blues == null) blues = "";
	String hiphop = request.getParameter("hiphop");
	if(hiphop == null) hiphop = "";
	String other = request.getParameter("other");
	if(other == null) other = "";
	
	String guitar_skill = request.getParameter("guitar_skill");
	if(guitar_skill == null) guitar_skill = "";
	String bass_skill = request.getParameter("bass_skill");
	if(bass_skill == null) bass_skill = "";
	String drum_skill = request.getParameter("drum_skill");
	if(drum_skill == null) drum_skill = "";
	String piano_skill = request.getParameter("piano_skill");
	if(piano_skill == null) piano_skill = "";
	String violin_skill = request.getParameter("violin_skill");
	if(violin_skill == null) violin_skill = "";
	String sax_skill = request.getParameter("sax_skill");
	if(sax_skill == null) sax_skill = "";
	String trump_skill = request.getParameter("trump_skill");
	if(trump_skill == null) trump_skill = "";
	String other_skill = request.getParameter("other_skill");
	if(other_skill == null) other_skill = "";
	
	String latitude = request.getParameter("latitude");
	if(latitude == null) latitude = "";
	String longitude = request.getParameter("longitude");
	if(longitude == null) longitude = "";

%>

<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	
	<title>Survey Page</title>
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
		<h1>Preference Survey</h1>
		<form id="form" action="RegisterServlet" method="GET">
			<div class="row">
				<label for="name" class="col-3 m-3">Your name:</label><font color="red"><%=nameEr%></font>
				<input type="text" class="col-5 m-3" id="name" name="name" placeholder="Enter your name" value=<%=name %>>
			</div>
			
			<div class="row">
				<label for="name" class="col-3 m-3">Username</label><font color="red"><%=usernameEr%></font>
				<input type="text" class="col-5 m-3" id="username" name="username" placeholder="Enter your name" value=<%=username %>>
			</div>
			
			<div class="row">
				<label for="name" class="col-3 m-3">Password</label><font color="red"><%=passwordEr%></font>
				<input type="password" class="col-5 m-3" id="password" name="password" placeholder="Enter your name" value=<%=password %>>
			</div>
			
			<div class="row">
				<label for="name" class="col-3 m-3">Email</label><font color="red"><%=emailEr%></font>
				<input type="email" class="col-5 m-3" id="email" name="email" placeholder="Enter your name" value=<%=email %>>
			</div>
			
			<div class= "row">
				<label for= "genres" id="genre_pref" class="col-3 m-3">Genre Preferences:</label>
				<label class="col-5 m-3" id="genre_disclaimer"> Right indicates stronger preference</label>
			</div>
			
			<div class="row">
				<label for= "genre" class="like col-3 m-3">How much do you like Pop</label>
				<input type="range" id="range_pop" class="like col-5 m-3" name="range_pop" name="vol" min="1" max="10" value=<%=pop %>/>
			</div>
			
			<div class="row">
				<label for= "genre" class="like col-3 m-3">How much do you like Rock</label>
				<input type="range" id="range_rock" class="like col-5 m-3" name="range_rock" name="vol" min="1" max="10" value=<%=rock %>/>
			</div>
		
			<div class="row">
				<label for= "genre" class="like col-3 m-3">How much do you like Jazz</label>
				<input type="range" id="range_jazz" class="like col-5 m-3" name="range_jazz" name="vol" min="1" max="10" value=<%=jazz %>/>
			</div>
			
			<div class="row">
				<label for= "genre" class="like col-3 m-3">How much do you like Country</label>
				<input type="range" id="range_country" class="like col-5 m-3" name="range_country" name="vol" min="1" max="10" value=<%=country %> />
			</div>
			
			<div class="row">
				<label for= "genre" class="like col-3 m-3">How much do you like Folk</label>
				<input type="range" id="range_folk" class="like col-5 m-3" name="range_folk" name="vol" min="1" max="10"value=<%=folk %> />
			</div>
			
			<div class="row">
				<label for= "genre" class="like col-3 m-3">How much do you like Blues</label>
				<input type="range" id="range_blues" class="like col-5 m-3" name="range_blues" name="vol" min="1" max="10"value=<%=blues %> />
			</div>
			
			<div class="row">
				<label for= "genre" class="like col-3 m-3">How much do you like Hip Hop</label>
				<input type="range" id="range_hiphop" class="like col-5 m-3" name="range_hiphop" name="vol" min="1" max="10" value=<%=hiphop%>/>
			</div>

			<div class="row">
				<label for= "genre" class="like col-3 m-3">How much do you like Other Genres</label>
				<input type="range" id="range_other" class="like col-5 m-3" name="range_other" name="vol" min="1" max="10"value=<%=other %> />
			</div>
		
		
			<div class="row">
				<label for="instrument" class="col-3 m-3" >Instrument you play:</label>
				<select class="form-control col-5 m-3" id="instrument" name="instrument" multiple> 
					<option class="ins_select" value="unselected">----Select as many as apply---</option>
					<option class="ins_select" value="guitar">Guitar</option>
					<option class="ins_select" value="bass">Bass</option>
					<option class="ins_select" value="drum">Drum</option>
					<option class="ins_select" value="piano">Piano</option>
					<option class="ins_select" value="violin">Violin</option>
					<option class="ins_select" value="saxophone">Saxophone</option>
					<option class="ins_select" value="trumpet">Trumpet</option>
					<option class="ins_select" value="other">Other</option>
				</select>
			</div>
					
			
			<div class= "row">
				<label for= "skills" id="skill_prompt" class="col-3 m-3">Skill Level:</label>
				<label class="col-5 m-3" id="skill_disclaimer"> Right indicates stronger preference</label>
			</div>
			

			<div class="row">
				<label for= "skill" class="skill col-3 m-3">Skill level playing Guitar</label>
				<input type="range" id="range_guitar" class="skill col-5 m-3" name="range_guitar" name="vol" min="1" max="10" value=<%=guitar_skill%>/>
			</div>
			
			<div class="row">
				<label for= "skill" class="skill col-3 m-3">Skill level playing Bass</label>
				<input type="range" class="skill col-5 m-3" name="range_bass" name="vol" min="1" max="10" value=<%=bass_skill%>/>
			</div>
			
			<div class="row">
				<label for= "skill" class="skill col-3 m-3">Skill level playing Drum</label>
				<input type="range" class="skill col-5 m-3 "name="range_drum" name="vol" min="1" max="10"  value=<%=drum_skill%>/>
			</div>
			
			<div class="row">
				<label for= "skill" class="skill col-3 m-3">Skill level playing Piano</label>
				<input type="range" class="skill col-5 m-3" name="range_piano" name="vol" min="1" max="10"  value=<%=piano_skill%>/>
			</div>
			
			<div class="row">
				<label for= "skill" class="skill col-3 m-3">Skill level playing Violin</label>
				<input type="range" class="skill col-5 m-3" name="range_violin" name="vol" min="1" max="10"  value=<%=violin_skill%> />
			</div>
			
			<div class="row">
				<label for= "skill" class="skill col-3 m-3">Skill level playing Saxophone</label>
				<input type="range" class="skill col-5 m-3" name="range_saxophone" name="vol" min="1" max="10"  value=<%=sax_skill%>/>
			</div>
			
			<div class="row">
				<label for= "skill" class="skill col-3 m-3">Skill level playing Trumpet</label>
				<input type="range" class="skill col-5 m-3" name="range_trumpet" name="vol" min="1" max="10"  value=<%=trump_skill%>/>
			</div>
			
			<div class="row">
				<label for= "skill" class="skill col-3 m-3">Skill level playing Other Instruments</label>
				<input type="range" class="skill col-5 m-3" name="range_other_instruments" name="vol" min="1" max="10"  value=<%=other_skill%>/>
			</div>
			
			<div class="row">
				<label for="googleMap" class="col-3 m-3">Select your physical location from map:</label>
				<div id="googleMap"  style="width:800px;height:500px;"></div>
				<div id="current" value=<%=latitude %>>Nothing yet...</div>
				<div id ="hohoho" value=<%=longitude %>></div>
			</div>
			<div class="row justify-content-center">
			<!--  	<a type="button" class="btn btn-info col-3 m-3" href="dashboard.html">Submit</a> -->
				<button type="submit" class="btn btn-info col-3 m-3" value= "Submit">Submit</button>
			</div>
			
		</form>
	</div>


	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	
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

	<script>
	

		$(".skill").hide();
		$("#skill_prompt").hide();
		$("#skill_disclaimer").hide();

		let skills = document.querySelectorAll(".skill");
		let ins_select = document.querySelectorAll(".ins_select");

		$("#instrument").on("change", function() {
			let selection;
			for (let j=0; j<ins_select.length; j++) {
				if (ins_select[j].value == $(this).val()) {
					selection = j;
				}
			}
			$("#skill_prompt").show();
			$("#skill_disclaimer").show();
			// console.log("selected: " + $(this).val());
			for (let i=0; i<skills.length; i++) {
				let curr = skills[i];
				// $(curr).hide();
				if (i==(selection*2-1)) {
					let prev = skills[i-1];
					// console.log("select: " + select);
					console.log("curr: " + curr.name);
					console.log("prev: " + prev.name);
					$(curr).show();
					$(prev).show();
				}
			}
		});



	</script>
	
	</body>
</html>
