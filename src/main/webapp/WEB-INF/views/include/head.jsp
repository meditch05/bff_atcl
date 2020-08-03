  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>MW Portal</title>
  
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!--   <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.4.3.1.css"> -->
  <!-- Font Awesome -->
  <link rel="stylesheet" href="../bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="../bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../dist/css/AdminLTE.min.css"> 
  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect. -->
  <link rel="stylesheet" href="../dist/css/skins/_all-skins.min.css">

  <link rel="stylesheet" type="text/css" href="../bower_components/select2/dist/css/select2.min.css" />
  <!--   <link rel="stylesheet" type="text/css" href="../resources/css/jquery.dataTables.min.css"> -->
  <link rel="stylesheet" type="text/css" href="../bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="../resources/css/scroller.dataTables.min.css">
  
  <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css"/> -->
  <!-- <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css" type="text/css"/> -->
  <!-- <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.6/css/buttons.bootstrap.min.css" type="text/css"/> -->
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <!--   <link rel="stylesheet" -->
  <!--         href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600.800,300italic,400italic,600italic"> -->
        
	<script src="../bower_components/jquery/dist/jquery.min.js"></script>
	
	<link rel="shortcut icon" href="../favicon.ico?v=2" type="image/x-icon">
	
<!-- Cutoms -->
<style>

.thead-dark th {
	color: #fff;
	background-color: #343a40;
	border-color: #454d55;
	font-size: 0.9em;
}

.table .thead-light th {
	color: #4950.8;
	background-color: #e9ecef;
	border-color: #dee2e6
}

.table > tbody > tr > td {
     vertical-align: 2px;
}

.table > tbody > tr > th {
     vertical-align: 2px;
}



/* .table  td { */
/* 	text-align: center;  */
/* 	margin-top:10px; */
/* } */

output {font-size:0.8em;}

/* Style buttons */
.buttonload {
	background-color: rgba(255, 255, 255, 0); /* Green background */
	border: none; /* Remove borders */
	color: black; /* White text */
	padding: 12px 16px; /* Some padding */
	font-size: 12px /* Set a font size */
}

.space {
	margin: 0;
	padding: 0;
	height: 30px;
}
 
table.table-condensed>thead>tr>th, .table-condensed>tbody>tr>th, .table-condensed>tfoot>tr>th, .table-condensed>thead>tr>td, .table-condensed>tbody>tr>td, .table-condensed>tfoot>tr>td{
    padding: 3px;
}

body{ 

	overflow-x: scroll;
}

.highcharts-data-table table {
    border-collapse: collapse;
    border-spacing: 0;
    background: white;
    min-width: 100%;
    margin-top: 10px;
    font-family: sans-serif;
    font-size: 0.8em;
    text-align: center;
}
.highcharts-data-table thead,  .highcharts-data-table tbody{
    text-align: center;
}
.highcharts-data-table td, .highcharts-data-table th, .highcharts-data-table caption {
    border: 1px solid silver;
    padding: 0.5em;
    text-align: center;
}
.highcharts-data-table tr:nth-child(even), .highcharts-data-table thead tr {
    background: #f8f8f8;
    text-align: center;
}

.dataTables_length {
/* 	position: absolute; */
    margin-top: 2%;
    font-size: 	0.8em;
}

.dataTables_info {
    font-size: 0.8em;
}

.dataTables_paginate {
/*     position: absolute; */
    text-align: center; 
    vertical-align:top;
    margin-top:5%;
    font-size: 0.8em;
}
.pagination > li > a, .pagination > li > span{background-color:#DCEBFF}
.pagination > li.active > a, .pagination > li.active > span{background-color:green}

/* Chrome, Safari용 스크롤 바 */
::-webkit-scrollbar {width: 8px; height: 8px; border: 3px solid #fff; }
::-webkit-scrollbar-button:start:decrement, ::-webkit-scrollbar-button:end:increment {display: block; height: 10px; }
::-webkit-scrollbar-track {background: #efefef; -webkit-border-radius: 10px; border-radius:10px; -webkit-box-shadow: inset 0 0 4px rgba(0,0,0,.2)}
::-webkit-scrollbar-thumb {height: 50px; width: 50px; background: rgba(0,0,0,.2); -webkit-border-radius: 8px; border-radius: 8px; -webkit-box-shadow: inset 0 0 4px rgba(0,0,0,.1)}
</style>

