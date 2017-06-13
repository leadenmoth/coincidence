$( document ).ready(function() {
	
	var url = window.location;
	
		// Submit form
    $("#numberForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		var x = document.getElementById("chosenNumber").value;
		ajaxGet(x);
	});
	
	//Random button returns 0 for random
	$("#askBtn").click(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		var x = 0;
		ajaxGet(x);
	});
	
	// DO GET
	function ajaxGet(value){
		$.ajax({
			type : "GET",
			url : url,
			success: function(result){
					//Clearing contents of result div
					var editNode = document.getElementById("getResultDiv");
						while (editNode.firstChild) {
    						editNode.removeChild(editNode.firstChild);
						};
					
					var statList = "";
					$.each(result.data, function(i, stat){
						
						//Form a response string
						if (match) {
							var stat = value + "% of Russians " + stat.msg1 + "<br>  and " + stat.msg2;
						}else{
							var stat = "<p>We don't have anything for that one yet, so here's some random trivia:</p>" + value + "% of Russians " + stat.msg1 + "<br>  and " + stat.msg2;
						};
						
						//Add the response message to the page
						$('#getResultDiv').append(stat);
						
					console.log("Success: ", result);
			        });
			 },			
			//Error message
			error: function(e){
				$("#getResultDiv").html("<strong>Error</strong>");
				console.log("ERROR: ", e);
				}
		});
	};
});