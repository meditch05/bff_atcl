<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
	<title>MW Ondemand check ( OPMATE )</title>
	<%@ include file="../include/head.jsp"%>

	<script type="text/javascript" src="../resources/js/highcharts.js"></script>
	<script type="text/javascript" src="../resources/js/jquery.highchartTable.js"></script>
	<script type="text/javascript" src="../resources/js/highchart.exporting.js"></script>
	<script type="text/javascript" src="../resources/js/highchart.export-data.js"></script>
	
	<script type="text/javascript">
	    function GetSelected_CallPost() {
		
	        // Create an Array.
	        var selected = new Array();	 
	        // Reference the Table.
	        var odm_table = document.getElementById("ODM_CHECK_OPMATE");	 
	        // Reference all the CheckBoxes in Table.
	        var checks = odm_table.getElementsByTagName("INPUT");
	        // Checked count()
	        var count = 0; 
	        // Loop and push the checked CheckBox value in Array.
	        for (var i = 0; i < checks.length; i++) {	        	
	            if (checks[i].checked) {
	                selected.push(checks[i].value);
	                // alert("Alert : " + i);   // 1 ~ N
	            }
	        }	 
	        //Display the selected CheckBox values.
	        if ( selected.length > 0 ) {
	            // alert("Selected values: []" + selected.join(",") + "]");
	            
	            ////////////////////////////////////////////////////////////////////
	            var form = document.createElement("form");
	            
	            form.setAttribute("charset", "UTF-8");
	            form.setAttribute("method", "Post");  //Post 방식
	            form.setAttribute("action", "/check/result"); //요청 보낼 주소
	            form.setAttribute("target", "_blank"); // Window 창 팝업 방식
	            
	    	    for (var i = 0; i < checks.length; i++) {	        	
	    	       if (checks[i].checked) {
	    	          // alert("Alert : " + i);   // 1 ~ N
	    	          count++;
	    	          
	    	          var hiddenField = document.createElement("input");
	                  hiddenField.setAttribute("type", "hidden");
	                  hiddenField.setAttribute("name", "checked_value");
	                  hiddenField.setAttribute("value", checks[i].value);
	                  form.appendChild(hiddenField);
	    	       }
	    	    }	
	    	        
	            document.body.appendChild(form);
	            form.submit();

	            // checkbox Clear All
	            // alert("Check cout(" + count + "/" + checks.length + ")" + selected.join(",") + "]");
	    	    for (var i = 0; i < checks.length; i++) {	        	
		    	   checks[i].checked = false;
		        }	
	            
	        }
		};
	</script>

</head>

<body class="hold-transition skin-blue sidebar-mini fixed">
	<div class="wrapper">

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h4>Ondemand Check by OPMATE</h4>
			</section>

			<section class="content container-fluid">
				<div class="box">
					<div class="box-header bg-info">
						<h1 class="box-title">
							<button style="margin-right: 50px; float: center;" onclick="GetSelected_CallPost();" class="btn btn-primary  btn-sm ">점검실행</button>
							<span class="badge badge-info">OPMATE Ondemand Check 후 Button 클릭</span>
						</h1>
					</div>

					<!-- /.box-header -->
					<div class="box-body ">
						<!-- <div class="chart" id="line-chart" style="height: 250px; margin:0"></div> -->
						<table class="table table-striped table-bordered table-condensed" id="ODM_CHECK_OPMATE" style="min-width: 775px; width: 100%; font-size:10pt; font-family:굴림체;">
							<thead class="thead-dark">
								<tr>
									<th class="text-center">체크</th>
									<th class="text-center">DOMAIN</th>
									<th class="text-center">SERVICE</th>
									<th class="text-center">HOSTNAME</th>
									<th class="text-center">ACCOUNT</th>
									<th class="text-center">OPMATE_TASK_ID</th>
									<th class="text-center">CHECK_SCRIPT</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="result" items="${resultList}">
									<tr>
										<td><input type="checkbox" name="checkService" id="checkService" value="${result.task_id}|${result.hostname}" /></td>
										<td>${result.domain}</td>
										<td>${result.service}</td>								
										<td>${result.hostname}</td>
										<td>${result.account}</td>
										<td>${result.task_id}</td>
										<td>${result.script}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

			</section>
		</div>

		<%@ include file="../include/main_footer.jsp"%>
	</div>
	<%@ include file="../include/plugin_js.jsp" %>

</body>

</html>