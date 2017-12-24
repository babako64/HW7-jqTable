jQuery.ajax({
            url: "http://localhost:8080/HW7-jqTable/api/teacher",
            type: "GET",

            contentType: 'application/json; charset=utf-8',
            success: function(resultData) {
            	console.log(resultData);
            	
            	drawTable(resultData);
            },
            error : function(jqXHR, textStatus, errorThrown) {
            },

        });

function drawTable(data) {
	
    for (var i = 0; i < data.length; i++) {
        drawRow(data[i]);
    }
    $('#table2').Tabledit({
   	    url: 'http://localhost:8080/HW7-jqTable/api/teacher',
   	    eventType: 'dblclick',
   	   
   	    columns: {
   	        identifier: [0, 'id'],
   	        editable: [[1, 'name'], [2, 'address']]
   	    },
   	    
    
   	});
    
    var row =$("<tr id='form2'><td><input type='text' class='form-control' id='id1' placeholder='id'></td>" +
    		"<td><input type='text' class='form-control' id='name1' placeholder='name'></td>" +
    		"<td><input type='text' class='form-control' id='address' placeholder='address'></td>" +
    		"<td><input class='btn btn-success' type='button' value='Add'id='btn-add'></td>" +
    		"</tr>");
    $( "tr" ).first().after(row);
}

function drawRow(rowData) {
    var row = $("<tr />")
 
    $("#table2").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
    row.append($("<td>" + rowData.id + "</td>"));
    row.append($("<td>" + rowData.name + "</td>"));
    row.append($("<td>" + rowData.address + "</td>"));
   
}

