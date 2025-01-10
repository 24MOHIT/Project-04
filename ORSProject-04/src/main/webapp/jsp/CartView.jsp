<%@page import="in.co.rays.ctl.CartCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
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

	<form action="<%=ORSView.CART_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.CartBean"
			scope="request" />

		<div align="center">

			<%
				if (bean != null && bean.getId() > 0) {
			%>

			<h1>Update Cart</h1>

			<%
				} else {
			%>

			<h1>Add Cart</h1>

			<%
				}
			%>

			<h3>
				<span style="color: green"><%=ServletUtility.getSuccessMessage(request)%></span>
			</h3>
			<input type="hidden" name="id" value="<%=bean.getId()%>" />
			<table>

				<tr>
					<th align="left">CustomerName <span style="color: red">*</span></th>
					<td><input type="text" name="customername"
						value="<%=DataUtility.getStringData(bean.getCustomername())%>"
						placeholder="Enter Name" /></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("customername", request)%></font></td>


				</tr>

				<tr>
					<th align="left">Product <span style="color: red">*</span></th>

					<td>
						<%
							HashMap<String, String> product = new HashMap<>();
							product.put("Carriage", "Carriage");
							product.put("Roadster", "Roadster");
							product.put("Wagon", "Wagon");
						%><%=HTMLUtility.getList("product", bean.getProduct(), product)%>
					</td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("product", request)%></font></td>



				</tr>


				<tr>
					<th align="left">Transaction Date <span style="color: red">*</span></th>
					<td><input type="text" id="update" name="transactiondate"
						value="<%=DataUtility.getStringData(bean.getTransactiondate())%>"
						placeholder="Enter Mobileno" /></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("transactiondate", request)%></font></td>


				</tr>

				<tr>
					<th align="left">Quantity Order <span style="color: red">*</span></th>
					<td><input type="text" name="quantityorder"
						value="<%=DataUtility.getStringData(bean.getQuantityorder())%>"
						placeholder="Enter Mobileno" /></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("quantityorder", request)%></font></td>


				</tr>
			</table>

			<table>
				<%
					if (bean != null && bean.getId() > 0) {
				%>
				<td></td>
				<td colspan="3"><input type="submit" name="operation"
					value="<%=CartCtl.OP_UPDATE%>" /> <input type="submit"
					name="operation" value="<%=CartCtl.OP_CANCEL%>" /></td>
				<%
					} else {
				%>
				<td></td>
				<td colspan="3"><input type="submit" name="operation"
					value="<%=CartCtl.OP_SAVE%>" /> <input type="submit"
					name="operation" value="<%=CartCtl.OP_RESET%>" /></td>
				<%
					}
				%>
			</table>

		</div>
	</form>
</body>
</html>