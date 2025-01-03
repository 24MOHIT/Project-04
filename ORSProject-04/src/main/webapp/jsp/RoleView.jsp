<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.ctl.RoleCtl"%>
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
		<form action="<%=ORSView.ROLE_CTL%>" method="post">

			<jsp:useBean id="bean" class="in.co.rays.bean.RoleBean"
				scope="request" />

			<%
				if (bean != null && bean.getId() > 0) {
			%>
			<h1>
				<font color="navy">Update Role</font>
			</h1>
			<%
				} else {
			%>
			<h1>
				<font color="navy">Add Role</font>
			</h1>
			<%
				}
			%>
			
			<%
				if(ServletUtility.getSuccessMessage(request) != null){
			%>
			<h3>
			<span style="color: green"><%=ServletUtility.getSuccessMessage(request)%></span>
			<span style="color: red"><%=ServletUtility.getErrorMessage(request) %></span>
			</h3>
			<%
				}
			%>

			<!-- Hidden Fields -->
			<input type="hidden" name="id" value="<%=bean.getId()%>" /> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>" />
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>" /> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>" />
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>" />


			<table>
				<tr>
					<th align="left">Name<span style="color: red">*</span></th>
					<td><input type="text" name="Name" placeholder="Enter Name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("Name", request)%></td>
				</tr>
				<tr>
					<th align="left">Description<span style="color: red">*</span></th>
					<td><input type="text" name="Description"
						placeholder="Enter Name"
						value="<%=DataUtility.getStringData(bean.getDescription())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("Description", request)%></td>
				</tr>

			</table>

			<table>

				<%
					if (bean != null && bean.getId() > 0) {
				%>
				<td><input type="submit" name="operation"
					value="<%=RoleCtl.OP_UPDATE%>"></td>
				<td><input type="submit" name="operation"
					value="<%=RoleCtl.OP_CANCEL%>"></td>
				<%
					} else {
				%>

				<td><input type="submit" name="operation"
					value="<%=RoleCtl.OP_SAVE%>"></td>
				<td><input type="submit" name="operation"
					value="<%=RoleCtl.OP_RESET%>"></td>
				<%
					}
				%>

			</table>

		</form>
	</div>
</body>
</html>