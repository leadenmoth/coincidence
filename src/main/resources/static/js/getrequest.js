$( document ).ready(function() {
		
		// Submit form
	$("#askBtn").click(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		var x = document.getElementById("chosenNumber").value;
		ajaxGetMessage(x);
	});
	
	//Random button returns 0 for random
	$("#randomBtn").click(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		var x = 0;
		ajaxGetMessage(x);
	});
	

});

function ajaxGetMessage(percent){
	$.ajax({
		type : "GET",
		url : "../message/" + percent,
		success: function(message){
				//Clearing contents of result div
				var editNode = document.getElementById("getResultDiv");
					while (editNode.firstChild) {
						editNode.removeChild(editNode.firstChild);
					};
				
				//Form a response string
				var stat = message.value + "% " + message.msg1 + "<br/>***<br/>" + message.value + "% " + message.msg2;					
				
				//Add the response message to the page
				$('#getResultDiv').append(stat);
					
				console.log("Success: ", message);
		        
		 },			
		//Error message
		error: function(e){
			$("#getResultDiv").html("<strong>Error</strong>");
			console.log("ERROR: ", e);
			}
	});
};