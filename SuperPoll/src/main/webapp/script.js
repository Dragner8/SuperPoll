

$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/SuperPoll/api/polls"
    }).then(function(data) {
       $('.greeting-id').append(data[0].id);
												
	   $('.greeting-id').append(data[2].id);
       $('.TEXT').append(data.text);
	   console.log("I have friends!", data);
    });
});




$(function() {
    // GET/READ
    $('#get-button').on('click', function() {
        $.ajax({
            url: 'http://localhost:8080/SuperPoll/api/polls',
            
            success: function(response) {
                var tbodyEl = $('tbody');

                tbodyEl.html('');

                response.forEach(function(product) {
                    tbodyEl.append('\
                        <tr>\
                            <td class="id">' + product.id + '</td>\
                            <td><input type="text" class="name" value="' + product.text + '"></td>\
                            <td>\
                                <button class="update-button">UPDATE/PUT</button>\
                                <button class="delete-button" >DELETE</button>\
                            </td>\
                        </tr>\
                    ');
                });
            }
        });
    });
	
	
  // UPDATE/PUT
    $('table').on('click', '.update-button', function() {
        var rowEl = $(this).closest('tr');
        var id = rowEl.find('.id').text();
        var newName = rowEl.find('.name').val();

        $.ajax({
            url: 'http://localhost:8080/SuperPoll/api/polls/' + id,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({ id: id, text: newName }),
            success: function(response) {
                console.log(response);
                $('#get-button').click();
            }
        });
    });
	
    // CREATE/POST
    $('#create-form').on('submit', function(event) {
        event.preventDefault();

        var createInput = $('#create-input');

        $.ajax({
            url: 'http://localhost:8080/SuperPoll/api/polls/',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ text: createInput.val() }),
            success: function(response) {
                console.log(response);
                createInput.val('');
                $('#get-button').click();
            }
        });
    });
    
    
	// DELETE
    $('table').on('click', '.delete-button', function() {
        var rowEl = $(this).closest('tr');
        var id = rowEl.find('.id').text();

        $.ajax({
            url: 'http://localhost:8080/SuperPoll/api/polls/' + id,
				 
			  type: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            success: function() {
                console.log("usuniete");
                $('#get-button').click();
            }
        });
    });
	
});


$.ajax({
  type: 'GET',
  url: 'http://rest.learncode.academy/api/johnbob/friends',
  success: function(data) {
   // console.log("I have friends!", data); //returns all of johnbob's friends
  }
});