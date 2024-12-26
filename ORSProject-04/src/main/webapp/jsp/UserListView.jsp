<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.bean.RoleBean"%>
<%@page import="in.co.rays.bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@ include file="Header.jsp" %>

	<%List list = (List)ServletUtility.getList(request); %>

	<form action="<%=ORSView.USER_LIST_CTL%>" method="get">
		<h1 align="center">User List</h1>

		<table border="1pxl" width="100%">
		<tr>
			<th>FirstName</th>
			<th>LastName</th>
			<th>Login</th>
			<th>DOB</th>
			<th>MobileNo</th>
			<th>Gender</th>
			<th>Role</th>
			<th>CreatedBy</th>
			<th>ModifiedBy</th>
			<th>CreatedDatetime</th>
			<th>ModifiedDatetime</th>
			<th>Edit</th>
		</tr>
		
		<%
		Iterator it = list.iterator();
		while(it.hasNext()){
			
			UserBean bean = (UserBean)it.next();
		
		%>
		
		<tr align="center">
		<td><%=bean.getFirstName() %></td>
		<td><%=bean.getLastName() %></td>
		<td><%=bean.getLogin() %></td>
		<td><%=bean.getDob() %></td>
		<td><%=bean.getMobileNo() %></td>
		<td><%=bean.getGender() %></td>
		<td><%=bean.getRoleId() %></td>
		<td><%=bean.getCreatedBy() %></td>
		<td><%=bean.getModifiedBy() %></td>
		<td><%=bean.getCreatedDatetime() %></td>
		<td><%=bean.getModifiedDatetime() %></td>
		</tr>
				
		<%
		}
		%>
		
		</table>

	</form>
</body>
</html>