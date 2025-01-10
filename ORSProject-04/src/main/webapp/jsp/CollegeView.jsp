<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.CollegeCtl"%>
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


	<form action="<%=ORSView.COLLEGE_CTL%>" method="post">
		<div align="center">
			<jsp:useBean id="bean" class="in.co.rays.bean.CollegeBean"
				scope="request"></jsp:useBean>

			<%
				if (bean != null && bean.getId() > 0) {
			%>
			<h1>Update College</h1>
			<%
				} else {
			%>

			<h1>Add College</h1>
			<%
				}
			%>

			<%
				if (ServletUtility.getSuccessMessage(request) != null) {
			%>
			<h3>
				<span style="color: green"><%=ServletUtility.getSuccessMessage(request)%></span>
				<span style="color: red"><%=ServletUtility.getErrorMessage(request)%></span>
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
					<th>Name <span style="color: red">*</span></th>
					<td><input type="text" name="name"
						value="<%=DataUtility.getStringData(bean.getName())%>"
						placeholder="Enter Name"></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("name", request)%></td>

				</tr>
				<tr>
					<th>Address <span style="color: red">*</span></th>
					<td><input type="text" name="address"
						value="<%=DataUtility.getStringData(bean.getAddress())%>"
						placeholder="Enter Address"></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("address", request)%></td>
				</tr>
				<tr>
					<th>State <span style="color: red">*</span></th>
					<td><input type="text" name="state"
						value="<%=DataUtility.getStringData(bean.getState())%>"
						placeholder="Enter State"></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("state", request)%></td>

				</tr>

				<tr>
					<th>City <span style="color: red">*</span></th>
					<td><input type="text" name="city"
						value="<%=DataUtility.getStringData(bean.getCity())%>"
						placeholder="Enter City"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("city", request)%></td>

				</tr>

				<tr>
					<th>Phone No <span style="color: red">*</span></th>
					<td><input type="text" name="phoneno"
						value="<%=DataUtility.getStringData(bean.getPhoneno())%>"
						placeholder="Enter PhoneNo"></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("phoneno", request)%></td>

				</tr>
			</table>
			<table>
			
			<%
				if (bean != null && bean.getId() > 0) {
			%>
			<td><input type="submit" name="operation"
					value="<%=CollegeCtl.OP_UPDATE%>"></td>
				<td><input type="submit" name="operation"
					value="<%=CollegeCtl.OP_CANCEL%>"></td>
			<%
				} else {
			%>

			<td><input type="submit" name="operation"
					value="<%=CollegeCtl.OP_SAVE%>"></td>
				<td><input type="submit" name="operation"
					value="<%=CollegeCtl.OP_RESET%>"></td>
			<%
				}
			%>
			
			
				

			</table>

		</div>
	</form>
</body>
</html>