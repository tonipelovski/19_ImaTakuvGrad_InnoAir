

$(document).ready(function(){
	const url = new URL(window.location.href);
	const vehicleNum = url.searchParams.get("vehicleNum");
	$("#vehicleNum").html("Превозно средство: " + vehicleNum) 	;
	
	$.ajax({
		type: "GET",
		url: 'http://192.168.43.117:8080/vehicles/' + vehicleNum,
		data: JSON,
		success: function (data) {
			const peopleCount = data.peopleCount;
			$('#peopleCount').html("Брой Хора: " + peopleCount)
			
			if(peopleCount <= 25)
			{
				$('#status').addClass("low")
			}
			else if(peopleCount <= 50)
			{
				$('#status').addClass("medium")
			}
			else
			{
				$('#status').addClass("full")
			}	
			$('#raw').attr('src', "data:image/png;base64," + data.src)
		}
	});		

})

