$( document ).ready(function() {
	
	var url = window.location;
	
		// Submit form
    $("#numberForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		var x = document.getElementById("chosenNumber").value;
		ajaxPost(x);
	});
	
	//Random button returns 0 for random
	$("#askBtn").click(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		var x = 0;
		ajaxPost(x);
	});
    
    
    function ajaxPost(value){
    	
    	// Process number
    	var formData = {}
		formData["value"] = value;
    	
    	// DO POST
    	$.ajax({
			type : "GET",
			url : "/message/" + value,
			success : function(formData) {
				if(formData){
				console.log("Post successful! Value: " + formData);
				} else{
					$("#getResultDiv").html("<strong>Error</strong>");
				};
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    	
    }
});