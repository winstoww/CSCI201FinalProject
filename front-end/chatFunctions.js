var socket;
var Message;
var USERID;
var RECIPIENT;
function connectToServer() {
	
	console.log(USERID);
	socket = new WebSocket("ws://localhost:8001/CSCI201L_Final_Project/ws?username="+ USERID);
	
	socket.onopen = function(event){
		
		//append the message to the chat window
		// this will need to be reworked so that it fills upward rather than down
		document.getElementById("chat-holder").innerHTML += "Connected!<br/>";
	}
	
	socket.onmessage = function(event){
		
		//parse incoming message JSON string into the object
		Message = JSON.parse(event.data);
		
		//this will need to be reworked so that it fills upward rather than down
		document.getElementById("chat-holder").innerHTML += Message.sender+ ": " +Message.text+ "<br/>";
	}
	
	socket.onclose = function(event){
		
		//this will need to be reworked so that it fills upward rather than down
		document.getElementById("chat-holder").innerHTML += "Disconnecting!";
	}
}

function sendMessage(){
	//construct message Variable below -- first String is from the text bar on the chat (chatform), sender is USERID, recipient is RECIPIENT
	Message= {text: document.chatform.messagebox.value, sender: USERID, recipient: RECIPIENT};
	
	//parse into String
	var mess = JSON.stringify(Message);
	document.getElementById("chat-holder").innerHTML += "Me: " + Message.text + "<br/>";
	//send message over socket
	socket.send(mess);
				
	return false;
}

function setUserAndRecipient(recip, userid){
	//Every chat button should onClick() send the user to this function
	
	//ajax call to get recipient's email goes here, set the value below
	
	RECIPIENT = recip;
	
	//ajax call to get session variable for user's email goes here, set value below
	
	USERID = userid;
	
	//now the web socket will connect to server with the recipient and user's usernames
	connectToServer();
	
	return false;
}