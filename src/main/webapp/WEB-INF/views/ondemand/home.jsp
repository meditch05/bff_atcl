<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
	<title>Welcome</title>
	<%@ include file="../include/head.jsp"%>

	<script type="text/javascript" src="../resources/js/highcharts.js"></script>
	<script type="text/javascript" src="../resources/js/jquery.highchartTable.js"></script>
	<script type="text/javascript" src="../resources/js/highchart.exporting.js"></script>
	<script type="text/javascript" src="../resources/js/highchart.export-data.js"></script>

</head>

<body class="hold-transition skin-blue sidebar-mini fixed">
	<div class="wrapper">

		<%@ include file="../include/main_header.jsp"%>
		<%@ include file="../include/left_column.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h4>
					<b>OPMATE I/F</b><small>체크박스 선택 hostname / 계정별 점검실행</small>
				</h4>
			</section>

			<section class="content container-fluid">

				<div class="box">
					<div class="box-header bg-info">
						<h1 class="box-title">
							<span class="badge badge-info">OPMATE 등록 hostname / 계정 List</span>
						</h1>
					</div>

					<!-- /.box-header -->
					<div class="box-body ">
						<!-- 							<div class="chart" id="line-chart" style="height: 250px; margin:0"></div> -->
						<table class="table table-striped  table-bordered table-condensed"
							id="globalSearchTable" style="min-width: 775px; width: 100%;">
							<thead class="thead-light">
								<tr>
									<th style="min-width: 90px;">시스템명</th>
									<th style="min-width: 90px;">Hostname</th>
									<th style="min-width: 60px;">계정</th>
								</tr>
							</thead>
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