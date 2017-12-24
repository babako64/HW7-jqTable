/**
 * Get data from rest method
 * 
 * @returns {Json} objects
 */

jQuery.ajax({
            url: "http://localhost:8080/HW7-jqTable/api/students",
            type: "GET",

            contentType: 'application/json; charset=utf-8',
            success: function(resultData) {
            	console.log(resultData);
            	
            	drawTable(resultData);
            },
            error : function(jqXHR, textStatus, errorThrown) {
            },

        });

/**
 * Create new rows in table
 * 
 * @param data
 * @returns
 */
function drawTable(data) {
	
    for (var i = 0; i < data.length; i++) {
        drawRow(data[i]);
    }
    $('#table1').Tabledit({
   	    url: 'http://localhost:8080/HW7-jqTable/api/students',
   	    eventType: 'dblclick',
   	   
   	    columns: {
   	        identifier: [0, 'id'],
   	        editable: [[1, 'name'], [2, 'dept'], [3, 'superVisorId']]
   	    },
   	    
    
   	});
    
    var row =$("<tr id='form1'><td><input type='text' class='form-control' id='id' placeholder='id'></td>" +
    		"<td><input type='text' class='form-control' id='name' placeholder='name'></td>" +
    		"<td><input type='text' class='form-control' id='dept' placeholder='dept'></td>" +
    		"<td><input type='text' class='form-control' id='sup' placeholder='supervisorid'></td>" +
    		"<td><input class='btn btn-success' type='button' value='Add'id='btn-add'></td>" +
    		"</tr>");
    $( "tr" ).first().after(row);
}

/**
 * Fill rows with fild
 * @param rowData
 * @returns
 */

function drawRow(rowData) {
    var row = $("<tr />")
 
    $("#table1").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
    row.append($("<td>" + rowData.id + "</td>"));
    row.append($("<td>" + rowData.name + "</td>"));
    row.append($("<td>" + rowData.dept + "</td>"));
    row.append($("<td>" + rowData.superVisorId + "</td>"));
   
}

