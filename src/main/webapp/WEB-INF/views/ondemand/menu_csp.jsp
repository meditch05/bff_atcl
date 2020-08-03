<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Menu For CSP</title>
	</head>

	<body>
		<h1>NateOnBiz 보내기(RestAPI)</h1>
	    
	    <form id="caller" method="post" action="./send_memo">       
			<table>
				<tr>
					<td><button type="submit">Let RestAPI Call</button><td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" /></td>
				</tr>	
				<tr>
					<td>받는 사람</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea cols="100" rows="50"  name="msg"></textarea></td>
				</tr>								
			</table>        
    	</form>
	</body>

</html>