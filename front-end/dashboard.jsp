<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<title>Dashboard Page</title>
	<style>
		@import url('https://fonts.googleapis.com/css?family=Sen&display=swap');
		* {
			color: #b7472a;
			font-family: 'Sen', sans-serif;
		}
		body {
			background-image: url("disc.jpg");
			background-repeat: cover;
			background-color: #5f6caf;
		}
		h1 {
			margin-bottom: 50px;
		}
		a,h2 {
			color: white;
		}
		#container {
			background-color: #edf7fa;
			margin-left: 0px;
			padding: 100px;
			width: 950px;
			margin: 0px;
		}
		#sidebar {
			width: 300px;
			margin: 0px;
			padding-left: 50px;
			padding-top: 100px;
		}
		.users {
			border-radius: 2%;
			background-color: #ffb677;
			width: 700px;
			height: 80px;
			margin-top: 20px;
			padding: 10px;
			color: white;
		}
		#plus {
			margin-left: 20px;
			padding: 3px;
			border-radius: 5px;
			/*background-color: white;*/
		}
		#form {
			width: 300px;
		}
	</style>
</head>
<body>
	<div id="container" class="container text-left float-left">
		<h1>Potential Matches</h1>
		<div id="match1" class="users row">
			<h2 class="col-6 float-left">Name</h2> 
			<a type="button" class="btn btn-info col-2 m-3" href="">View</a>
			<a type="button" class="addbtn btn btn-info col-2 m-3" href="">Add</a>
		</div>
		<div id="match2" class="users row">
			<h2 class="col-6 float-left">Name</h2> 
			<a type="button" class="btn btn-info col-2 m-3" href="">View</a>
			<a type="button" class="addbtn btn btn-info col-2 m-3" href="">Add</a>
		</div>
		<div id="match3" class="users row">
			<h2 class="col-6 float-left">Name</h2> 
			<a type="button" class="btn btn-info col-2 m-3" href="">View</a>
			<a type="button" class="addbtn btn btn-info col-2 m-3" href="">Add</a>
		</div>
		<div id="match4" class="users row">
			<h2 class="col-6 float-left">Name</h2> 
			<a type="button" class="btn btn-info col-2 m-3" href="">View</a>
			<a type="button" class="addbtn btn btn-info col-2 m-3" href="">Add</a>
		</div>
		<div id="match5" class="users row">
			<h2 class="col-6 float-left">Name</h2> 
			<a type="button" class="btn btn-info col-2 m-3" href="">View</a>
			<a type="button" class="addbtn btn btn-info col-2 m-3" href="">Add</a>
		</div>
	</div>
	<div id="sidebar" class="container text-left float-left">
		<div id="setting">
			<h2>Settings<i class="fas fa-plus" id="plus"></i></h2>
			<br><br>
			<ul id="form">
				<li><h4><a href="contact.jsp">View Contact List</a></h4></li>
				<li><h4><a href="login.jsp">Log Out</a></h4></li><!-- logout redirect to a servlet -->
			</ul>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<script src="https://use.fontawesome.com/releases/v5.12.1/js/all.js" data-auto-replace-svg="nest"></script>

	<script>
		let isSlide = true;
		$("#form").slideUp(1);
		$("#plus").on("click", function(event) {
			if (!isSlide) {
				$("#form").slideUp(500);
				isSlide = true;
			} else {
				$("#form").slideDown(500);
				isSlide = false;
			}
		});
		$(".addbtn").on("click", function(event) {
			alert("Successfully added user XXX to contact list!");
		});
	</script>
</body>
</html>