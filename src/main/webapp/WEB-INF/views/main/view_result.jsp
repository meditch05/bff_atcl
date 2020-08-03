<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
	<title>MSA RestAPI Call viewer( inner kubernetes )</title>
	<%@ include file="../include/head.jsp"%>

	<script type="text/javascript" src="../resources/js/highcharts.js"></script>
	<script type="text/javascript" src="../resources/js/jquery.highchartTable.js"></script>
	<script type="text/javascript" src="../resources/js/highchart.exporting.js"></script>
	<script type="text/javascript" src="../resources/js/highchart.export-data.js"></script>

</head>

<body class="hold-transition skin-blue sidebar-mini fixed">
	<div class="wrapper">

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h4>MSA RestAPI Call viewer( inner kubernetes )</h4>
			</section>

			<section class="content container-fluid">
				<div class="box">

					<!-- /.box-header -->
					<div class="box-body ">
						<!-- <div class="chart" id="line-chart" style="height: 250px; margin:0"></div> -->
						<table class="table table-striped table-bordered table-condensed" id="ODM_CHECK_OPMATE" style="min-width: 775px; width: 100%; font-size:10pt; font-family:굴림체;">
							<thead class="thead-dark">
								<tr>
									<th class="text-center">사번</th>
									<th class="text-center">월급</th>
									<th class="text-center">입사일</th>
									<th class="text-center">퇴사일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="result" items="${resultList}">
									<tr>
										<td>${result.empno}</td>
										<td>${result.salary}</td>								
										<td>${result.fromdate}</td>
										<td>${result.todate}</td>
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