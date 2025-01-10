<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.ctl.PatientCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
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

	<form action="<%=ORSView.PATIENT_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.PatientBean"
			scope="request" />

		<div align="center">

			<%
				if (bean != null && bean.getId() > 0) {
			%>

			<h1>Update Patient</h1>

			<%
				} else {
			%>

			<h1>Add Patient</h1>

			<%
				}
			%>

			<h3>
				<span style="color: green"><%=ServletUtility.getSuccessMessage(request)%></span>
			</h3>
			<input type="hidden" name="id" value="<%=bean.getId()%>" />
			<table>

				<tr>
					<th align="left">Name <span style="color: red">*</span></th>
					<td><input type="text" name="name"
						value="<%=DataUtility.getStringData(bean.getName())%>"
						placeholder="Enter Name" /></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font></td>


				</tr>

				<tr>
					<th align="left">Visit Date <span style="color: red">*</span></th>
					<td><input type="text"
						value="<%=DataUtility.getStringData(bean.getVisitdate())%>"
						name="visitdate" id="update" placeholder="Enter Date" /></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("visitdate", request)%></font></td>


				</tr>

				<tr>
					<th align="left">Mobile <span style="color: red">*</span></th>
					<td><input type="text" id="update" name="mobile"
						value="<%=DataUtility.getStringData(bean.getMobile())%>"
						placeholder="Enter Mobileno" /></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font></td>


				</tr>

				<tr>
					<th align="left">Decease <span style="color: red">*</span></th>

					<td>
						<%
							HashMap<String, String> decease = new HashMap<>();
							decease.put("Peralise", "Peralise");
							decease.put("HeartAttack", "HeartAttack");
							decease.put("Brain", "Brain");
						%><%=HTMLUtility.getList("decease", bean.getDecease(), decease)%>
					</td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("decease", request)%></font></td>



				</tr>

			</table>
			<table>
				<%
					if (bean != null && bean.getId() > 0) {
				%>
				<td></td>
				<td colspan="3"><input type="submit" name="operation"
					value="<%=PatientCtl.OP_UPDATE%>" /> <input type="submit"
					name="operation" value="<%=PatientCtl.OP_CANCEL%>" /></td>
				<%
					} else {
				%>
				<td></td>
				<td colspan="3"><input type="submit" name="operation"
					value="<%=PatientCtl.OP_SAVE%>" /> <input type="submit"
					name="operation" value="<%=PatientCtl.OP_RESET%>" /></td>
				<%
					}
				%>
			</table>

		</div>
	</form>
</body>
</html>