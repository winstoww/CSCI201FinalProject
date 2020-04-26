<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%

	String user1 = "'Bob'";
	String user2 = "'Joe'";

%>

<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<title>Contact List</title>
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
		a, h2 {
			color: white;
		}
		#container {
			background-color: #edf7fa;
			opacity: 1;
			width: 950px;
			margin-left: 0px;
			padding: 100px;
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
		#chat {
			padding: 50px;
			width: 300px;
		}
		#chatbox {
			width: 250px;
			height: 350px;
			padding: 50px;
			background-color: white;
			border-radius: 5px;
		}
		#chat-title {
			padding-top: 50px;
		}
		#chat-holder {
			width: 200px;
			height: 300px;
			margin-top: 50px;
			background-color: white;
		}
		#plus {
			margin-left: 20px;
			padding: 3px;
			border-radius: 5px;
			/*background-color: white;*/
		}
	</style>
	<script src="chatFunctions.js"></script>
</head>
<body>
	<div id="container" class="container text-left float-left">
		<h1>Contact List</h1>
		<div class="users row">
			<h2 class="col-6 float-left">Name</h2> 
			<a type="button" class="btn btn-info col-2 m-3" href="">View</a>
			<a type="button" class="chatbtn btn btn-info col-2 m-3" onclick="setUserAndRecipient(<%=user1%>,<%=user2%>)">Chat</a>
		</div>
		<div class="users row">
			<h2 class="col-6 float-left">Name</h2> 
			<a type="button" class="btn btn-info col-2 m-3" href="">View</a>
			<a type="button" class="chatbtn btn btn-info col-2 m-3" onclick="setUserAndRecipient(<%=user2%>,<%=user1%>)">Chat</a><!-- send username and friend username to javascript funct to send to serversocket -->
		</div>
		<div class="users row">
			<h2 class="col-6 float-left">Name</h2> 
			<a type="button" class="btn btn-info col-2 m-3" href="">View</a>
			<a type="button" class="chatbtn btn btn-info col-2 m-3">Chat</a>
		</div>
		<div class="users row">
			<h2 class="col-6 float-left">Name</h2> 
			<a type="button" class="btn btn-info col-2 m-3" href="">View</a>
			<a type="button" class="chatbtn btn btn-info col-2 m-3">Chat</a>
		</div>
		<div class="users row">
			<h2 class="col-6 float-left">Name</h2> 
			<a type="button" class="btn btn-info col-2 m-3" href="">View</a>
			<a type="button" class="chatbtn btn btn-info col-2 m-3">Chat</a>
		</div>
	</div>
	<div id="chat" class="container text-left float-left">
		<h2 id="chat-title">Chat<i class="fas fa-plus" id="plus"></i></h2>
		<form name="chatform" onsubmit="return sendMessage()">
			<div id="chat-holder">
				
			</div>
			<textarea id="messagebox" placeholder="text messages here"></textarea>
			
			<button class="btn btn-info" type = "submit" name = "submit" value = "Send Message">Send</button>
			<!-- <input type = "submit" name = "submit" value = "Send Message"/>-->
		</form>
		<br>
		<h2><a href="dashboard.jsp">Back to Dashboard</a></h2>
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
		$(".chatbtn").on("click", function(event) {
			event.preventDefault();
			$("#form").slideDown(500);
			isSlide = false;
		});
	</script>
</body>
</html>