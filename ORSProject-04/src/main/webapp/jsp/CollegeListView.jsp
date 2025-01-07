<%@page import="in.co.rays.model.CollegeModel"%>
<%@page import="in.co.rays.ctl.CollegeListCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.bean.CollegeBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div align="center">
		<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="post">
			<h1>College List</h1>

			<jsp:useBean id="bean" class="in.co.rays.bean.CollegeBean"
				scope="request"></jsp:useBean>

			<%
				List list = ServletUtility.getList(request);
			%>
			<table>
				<tr>
					<th>Name :</th>
					<td><input type="text" name="name" placeholder="Enter Name"
						value="<%=ServletUtility.getParameter("name", request)%>"></td>

					<td><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_SEARCH%>"></td>
				</tr>
			</table>

			<table border="1px" width="100%">
				<tr>
					<th><input type="checkbox" id="selectall"></th>
					<th>S.No.</th>
					<th>Name</th>
					<th>Address</th>
					<th>State</th>
					<th>City</th>
					<th>Phone No.</th>
				</tr>



				<%
					Iterator it = list.iterator();

					while (it.hasNext()) {
						bean = (CollegeBean) it.next();
				%>

				<tr align="center">
					<td><input type="checkbox" name="ids" class="case"
						value="<%=bean.getId()%>"></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getAddress()%></td>
					<td><%=bean.getState()%></td>
					<td><%=bean.getCity()%></td>
					<td><%=bean.getPhoneno()%></td>
				</tr>

				<%
					}
				%>
			</table>
			<table>
				<td><input type="submit" name="operation"
					value="<%=CollegeListCtl.OP_NEW%>"></td>

				<td><input type="submit" name="operation"
					value="<%=CollegeListCtl.OP_DELETE%>"></td>
			</table>

		</form>
	</div>
</body>
</html>