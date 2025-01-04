<%@page import="in.co.rays.ctl.StudentCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
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

	<form action="<%=ORSView.STUDENT_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.StudentBean"
			scope="request" />
		<div align="center">

			<h1>Add Student</h1>


			<%
				List collegelist = (List) request.getAttribute("collegelist");
			%>

			<h3>
				<span style="color: green"><%=ServletUtility.getSuccessMessage(request)%></span>
				<span style="color: red"><%=ServletUtility.getErrorMessage(request)%></span>
			</h3>

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
					<th>FirstName <span style="color: red">*</span></th>
					<td><input type="text" name="firstname"
						placeholder="Enter FirstName"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("firstname", request)%></font></td>

				</tr>

				<tr>
					<th>Lastname <span style="color: red">*</span></th>
					<td><input type="text" name="lastname"
						placeholder="Enter LastName"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("lastname", request)%></font></td>

				</tr>

				<tr>
					<th>DOB <span style="color: red">*</span></th>
					<td><input type="text" id="udate" name="dob"
						placeholder="Enter DOB"
						value="<%=DataUtility.getStringData(bean.getDob())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font></td>

				</tr>

				<tr>
					<th align="left">Gender<span style="color: red">*</span></th>
					<td>
						<%
							HashMap<String, String> genderMap = new HashMap<>();
							genderMap.put("male", "Male");
							genderMap.put("female", "Female");
						%> <%=HTMLUtility.getList("gender", bean.getGender(), genderMap)%>

					</td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font></td>


				</tr>

				<tr>
					<th>MobileNo <span style="color: red">*</span></th>
					<td><input type="text" name="mobileno"
						placeholder="Enter MobileNo"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("mobileno", request)%></font></td>

				</tr>

				<tr>
					<th>Email <span style="color: red">*</span></th>
					<td><input type="text" name="email" placeholder="Enter Email"
						value="<%=DataUtility.getStringData(bean.getEmail())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("email", request)%></font></td>

				</tr>

				<tr>
					<th align="left">CollegeName <span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("collegeid", DataUtility.getStringData(bean.getCollegeId()), collegelist)%></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("collegeid", request)%></font></td>

				</tr>

			</table>
			<table>
				<td><input type="submit" name="operation"
					value="<%=StudentCtl.OP_SAVE%>"></td>
				<td><input type="submit" name="operation"
					value="<%=StudentCtl.OP_RESET%>"></td>

			</table>

		</div>
	</form>
</body>
</html>