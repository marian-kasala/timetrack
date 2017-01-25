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
	<link rel="stylesheet" href="css/timetrack.css" />
	<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="js/primeui.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="js/records.js"></script>
</head>
<body>
	<div id="container" style="margin-bottom:20px" title="Time Tracker">
		<label for="default">User Email:</label><input id="id_email" type="text" name="email" style="width: 300px;" placeholder="Type user's email here ..."/>
		<button id="id_search_btn" type="button">Search</button>
		<button id="id_create_btn" type="button">Create New Record</button>
		<br/><br/>
		<div id="tbltimetracker"></div>
		<div id="idmessage"></div>
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