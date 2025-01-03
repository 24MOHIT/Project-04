<%@page import="java.util.List"%>
<%@page import="javax.management.relation.RoleList"%>
<%@page import="in.co.rays.ctl.BaseCtl"%>
<%@page import="in.co.rays.ctl.UserCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
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

	<form action="<%=ORSView.USER_CTL%>" method="post">
		<jsp:useBean id="bean" class="in.co.rays.bean.UserBean"
			scope="request" />

		<%
			List roleList = (List) request.getAttribute("roleList");
		%>

		<div align="center">

			<%
				if (bean != null && bean.getId() > 0) {
			%>
			<h1>
				<font color="navy">Update User</font>
			</h1>
			<%
				} else {
			%>
			<h1>
				<font color="navy">Add User</font>
			</h1>
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
			<br>
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
					<th align="left">First Name<span style="color: red">*</span></th>
					<td><input type="text" name="firstName"
						placeholder="Enter First Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font></td>

				</tr>
				<tr>
					<th align="left">Last Name<span style="color: red">*</span></th>
					<td><input type="text" name="lastName"
						placeholder="Enter Last Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Login Id<span style="color: red">*</span></th>
					<td><input type="text" name="login"
						placeholder="Enter Email ID"
						value="<%=DataUtility.getStringData(bean.getLogin())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Password<span style="color: red">*</span></th>
					<td><input type="password" name="password"
						placeholder="Enter Password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Confirm Pass<span style="color: red">*</span></th>
					<td><input type="password" name="confirmPassword"
						placeholder="Confirm Password"
						value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>

				</tr>
				<tr>
					<th align="left">DOB<span style="color: red"">*</span></th>
					<td><input style="width: 98%" type="date" name="dob"
						placeholder="Select Date of Birth"
						value="<%=DataUtility.getStringData(bean.getDob())%>" /></td>
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
					<th align="left">Role <span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("roleId", DataUtility.getStringData(bean.getRoleId()), roleList)%></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("roleId", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Mobile No<span style="color: red">*</span></th>
					<td><input type="text" name="mobileNo"
						placeholder="Enter Mobile No."
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>

				</tr>
				<tr>
					<%
						if(bean != null && bean.getId() > 0){
					%>
					<td></td>
					<td colspan="3"><input type="submit" name="operation"
						value="<%=UserCtl.OP_UPDATE%>" /> <input type="submit"
						name="operation" value="<%=UserCtl.OP_CANCEL%>" /></td>
					
					<%
						}else{
					%>
					<td></td>
					<td colspan="3"><input type="submit" name="operation"
						value="<%=UserCtl.OP_SAVE%>" /> <input type="submit"
						name="operation" value="<%=UserCtl.OP_RESET%>" /></td>
						
					<%
						}
					%>
				</tr>
			</table>
		</div>
	</form>

</body>
</html>