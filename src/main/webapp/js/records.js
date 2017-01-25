$(function() {
	var email;
	var restUrl = 'rest/records';
	
    $('#container').puipanel();
    $('#idmessage').puimessages();
    
    $('#dlg').puidialog({
        minimizable: false,
        maximizable: false,
        responsive: true,
        width: 540,
        minWidth: 300,
        modal: true,
        buttons: [
			{
			    text: 'Cancel',
			    icon: 'fa-close',
			    click: function() {
			        $('#dlg').puidialog('hide');
			    }
			},
            {
                text: 'Save',
                icon: 'fa-check',
                click: function() {
                	if ($("#id_create_form").valid()) {
	                	$("#id_create_form").submit();
	                    $('#dlg').puidialog('hide');
                	}
                }
            }
        ]
    });
	
    $('#id_create_btn').puibutton({
	    click: function(event) {
	    	$('#dlg').puidialog('show');
	    }
	});
    
	$('#id_search_btn').puibutton({
	    click: function(event) {
	    	email = $('#id_email').val();
	    	$('#tbltimetracker').puidatatable('reload');
	    }
	});
	
	$('#tbltimetracker').puidatatable({
		caption: 'Time Tracker Records',
		paginator: {
			rows: 10,
			totalRecords: 11
		},
		lazy: true,
		columns: [
			{field: 'start', headerText: 'Start'},
			{field: 'end', headerText: 'End'},
			{field: 'email', headerText: 'Email'}
		],
		datasource: function(callback, ui) {
			var params = { offset: ui.first, lenght: ui.rows };
			if (email) {
			    params.email = email;
			} 
			
			$.ajax({
				type: "GET",
				url: restUrl + '?' + $.param(params),
				dataType: "json",
				context: this,
				success: function(response) {
					callback.call(this, response);
					
					// little hack because I don't know the total record count
					var page = $('#tbltimetracker').puidatatable('getPaginator').puipaginator('option', 'page');
					$('#tbltimetracker').puidatatable('setTotalRecords', ui.first + 11);
					$('#tbltimetracker').puidatatable('getPaginator').puipaginator('setPage', page, true);
				},
				error: function (request, status, error) {
			        $('#idmessage').puimessages('show', 'error', {summary: error, detail: request.responseText } );
			    }
			});
		}
	});
	
	$.validator.addMethod("dateFormat", function(value) {
	    var regex = /^([1-9]|0[1-9]|[12][0-9]|3[01])\.([1-9]|0[1-9]|1[012])\.(19|20)\d\d\s([01]\d|2[0-3]):([0-5]\d)$/;
	    return regex.test(value);

	}, 'Invalid format'); 
	
	$("#id_create_form").validate({
	    rules: {
	    	start : {required:true, dateFormat:true},
	    	end : {required:true, dateFormat:true},
	    	email: {required:true}
	    }
	});
	
});