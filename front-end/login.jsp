<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
		String username = (String) request.getParameter("username");
		if(username == null) username = "";
		
		String password = (String) request.getParameter("password");
		if(password == null) password = "";
		
		String login_message = (String) request.getAttribute("texte");
		if(login_message == null) login_message = "";
		
%>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<title>Login Page</title>
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
			padding: 20px;
			width: 600px;
		}
	</style>
</head>
<body>
	<div id="container" class="container text-center">
		<div class="row">
			<h1>Welcome to MusicFerret!</h1>
			<form id="form" action="UserLoginServlet" method="GET">
				<font color="red"><%=login_message%></font>
				<input type="text" class="col-7 m-3" id="username" name=username; placeholder="Enter Username" value="<%=username %>">
				<input type="text" class="col-7 m-3" id="password" name=password; placeholder="Enter Password" value="<%=password %>">
				<button type="submit" name="submit" class="btn btn-info col-7 m-3" value="submit">Log In</button>
				<a type="button" class="btn btn-info col-7 m-3" href="survey.jsp">Create Account</a>
				<a type="button" class="btn btn-info col-7 m-3" href="survey.jsp">Start As Guest</a>
			</form>
		</div>
	</div>
</body>
</html>