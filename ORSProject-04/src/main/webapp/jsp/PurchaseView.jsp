<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.ctl.PurchaseCtl"%>
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

	<form action="<%=ORSView.PURCHASE_CTL%>" method="post">
		<jsp:useBean id="bean" class="in.co.rays.bean.PurchaseBean"
			scope="request" />

		<div align="center">

			<%
				if (bean != null && bean.getId() > 0) {
			%>

			<h1>Update Purchase</h1>

			<%
				} else {
			%>

			<h1>Add Purchase</h1>

			<%
				}
			%>

			<h3>
				<span style="color: green"><%=ServletUtility.getSuccessMessage(request)%></span>
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
					<th align="left">Quantity <span style="color: red">*</span></th>
					<td><input type="text" name="quantity"
						value="<%=DataUtility.getStringData(bean.getQuantity())%>"
						placeholder="Enter Quantity" /></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("quantity", request)%></font></td>


				</tr>

				<tr>
					<th align="left">Price <span style="color: red">*</span></th>
					<td><input type="text" name="price"
						value="<%=DataUtility.getStringData(bean.getPrice())%>"
						placeholder="Enter Price" /></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("price", request)%></font></td>


				</tr>

				<tr>
					<th align="left">PurchaseDate <span style="color: red">*</span></th>
					<td><input type="text" id="update" name="purchasedate"
						value="<%=DataUtility.getStringData(bean.getPurchasedate())%>"
						placeholder="Enter PurchaseDate" /></td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("purchasedate", request)%></font></td>


				</tr>

				<tr>
					<th align="left">OrderType <span style="color: red">*</span></th>

					<td>
						<%
							HashMap<String, String> ordertypeMap = new HashMap<>();
							ordertypeMap.put("Hp", "Hp");
							ordertypeMap.put("Dell", "Dell");
							ordertypeMap.put("Asus", "Asus");
						%> <%=HTMLUtility.getList("ordertype", bean.getOrdertype(), ordertypeMap)%>
					</td>

					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("ordertype", request)%></font></td>


				</tr>

			</table>

			<table>
				<%
					if (bean != null && bean.getId() > 0) {
				%>
				<td></td>
				<td colspan="3"><input type="submit" name="operation"
					value="<%=PurchaseCtl.OP_UPDATE%>" /> <input type="submit"
					name="operation" value="<%=PurchaseCtl.OP_CANCEL%>" /></td>
				<%
					} else {
				%>
				<td></td>
				<td colspan="3"><input type="submit" name="operation"
					value="<%=PurchaseCtl.OP_SAVE%>" /> <input type="submit"
					name="operation" value="<%=PurchaseCtl.OP_RESET%>" /></td>
				<%
					}
				%>
			</table>


		</div>

	</form>
</body>
</html>