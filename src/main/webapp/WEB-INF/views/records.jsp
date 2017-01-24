<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Time Tracker</title>
<link rel="stylesheet" href="css/themes/aristo/theme.css" />
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
<link rel="stylesheet" href="css/jquery-ui.css" />
<link rel="stylesheet" href="css/primeui.min.css" />
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/primeui.min.js"></script>
<script type="text/javascript">
		$(function() {
			var email;
			var restUrl = 'rest/records';
			
		    $('#container').puipanel();
		    
		    $('#dlg').puidialog({
		        minimizable: false,
		        maximizable: false,
		        responsive: true,
		        width: 400,
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
		                	$("#id_create_form").submit();
		                    $('#dlg').puidialog('hide');
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
							$('#tbltimetracker').puidatatable('setTotalRecords', ui.first + ui.rows + 1);
							$('#tbltimetracker').puidatatable('getPaginator').puipaginator('setPage', page, true);
						}
					});
				}
			});
		});
</script>
</head>
<body>
	<div id="container" style="margin-bottom:20px" title="Time Tracker">
		<label for="default">User Email:</label><input id="id_email" type="text" name="email" style="width: 300px;" placeholder="Type user's email here ..."/>
		<button id="id_search_btn" type="button">Search</button>
		<button id="id_create_btn" type="button">Create New Record</button>
		<br/><br/>
		<div id="tbltimetracker"></div>
	</div>
	
	<div id="dlg" title="Create New Record">
    	<form id="id_create_form" method="post" action="records" >
    		<table>
	    		<tr>
	    			<td><label for="start">Start time:</label></td><td><input type="text" name="start" placeholder="dd.MM.yyyy HH:mm"/></td>
	    		</tr>
	    		<tr>
	    			<td><label for="end">End time:</label></td><td><input type="text" name="end" placeholder="dd.MM.yyyy HH:mm" /></td>
	    		</tr>
	    		<tr>
	    			<td><label for="email">Email:</label></td><td><input type="text" name="email" style="width: 250px;" /></td>
	    		</tr>
    		</table>
    	</form>
	</div>
</body>
</html>