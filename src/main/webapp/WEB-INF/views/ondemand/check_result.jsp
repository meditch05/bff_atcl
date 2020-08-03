<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
	<title>MW Ondemand Check 결과 ( OPMATE )</title>
	
	<script type="text/javascript">
		function GetSelected_SendNateOnBiz() {

			// Create an Array.
			var selected = new Array();
			// Reference the Table.
			var odm_table = document.getElementById("SEND_NATEONBIZ_TABLE");
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

			// Reference the DIV
			var result_table_div = document.getElementById("ODM_CHECK_RESULT_DIV");

			//Display the selected CheckBox values.
			if (selected.length > 0) {
				// alert("Selected values: []" + selected.join(",") + "]");

				////////////////////////////////////////////////////////////////////
				var form = document.createElement("form");

				form.setAttribute("charset", "UTF-8");
				form.setAttribute("method", "Post"); //Post 방식
				form.setAttribute("action", "/send/nateonbiz"); //요청 보낼 주소
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

				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "message");
				hiddenField.setAttribute("value", result_table_div.innerHTML );
				form.appendChild(hiddenField);
				
				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "title");
				hiddenField.setAttribute("value", "OPMATE점검결과");
				form.appendChild(hiddenField);

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

<body>
	
	<div style="width:670px; border:1px dotted grey;">
		<table id="SEND_NATEONBIZ_TABLE" class="auto" border="0" cellspacing="0" style="font-size:10pt; font-family:굴림체;">
			<tr>
				<td style="width:50px;">SK C&C</td>		
				<td><input type="checkbox" value="maybe96@sk.com" />최정희</td>
				<td><input type="checkbox" value="meditch05@sk.com" />장영오</td>
				<td><input type="checkbox" value="perfectdragon@sk.com" />김완용</td>
				<td><input type="checkbox" value="amlove@sk.com" />원장희</td>
				<td><input type="checkbox" value="ysko@sk.com" />고영석</td>
				<td><input type="checkbox" value="joonsik@sk.com" />엄준식</td>
				<td><input type="checkbox" value="barracuda0704@sk.com" />김명환</td>
				<td><input type="checkbox" value="bjkim@sk.com" />김범종</td>
				<td rowspan="2" align="right" style="width:80px;">
					<button style="float: center;" onclick="GetSelected_SendNateOnBiz();">쪽지발송</button>
				</td>
			</tr>
			<tr>
				<td>SKT</td>
				<td><input type="checkbox" value="skt.p025091@partner.sk.com" />최정희</td>
				<td><input type="checkbox" value="skt.p069528@partner.sk.com" />장영오</td>
				<td><input type="checkbox" value="skt.p065336@partner.sk.com" />김완용</td>
				<td><input type="checkbox" value="skt.p095322@partner.sk.com" />원장희</td>
				<td><input type="checkbox" value="skt.p078503@partner.sk.com" />고영석</td>
				<td><input type="checkbox" value="skt.p013902@partner.sk.com" />엄준식</td>
				<td><input type="checkbox" value="skt.p143070@partner.sk.com" />김명환</td>
				<td><input type="checkbox" value="sosme88@partner.sk.com" />김범종</td>
			</tr>
		</table>
	</div>
	
	<BR>
	
	<div id="ODM_CHECK_RESULT_DIV">
		<table class="auto" border="1" cellspacing="0" style="min-width: 775px; width: 100%; font-size:10pt; font-family:굴림체;">
			<thead class="thead-dark">
				<tr>
					<th class="text-center" bgcolor="#333333"><STRONG><font color="white">HOSTNAME</font></STRONG></th>
					<th class="text-center" bgcolor="#333333"><STRONG><font color="white">수행결과</font></STRONG></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultDetailList}">
					<tr>
						<td>${result.nodeId}</td>
						<td>${result.decodestr}</td>					
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>

</html>