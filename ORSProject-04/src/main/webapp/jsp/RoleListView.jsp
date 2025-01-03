<%@page import="in.co.rays.model.RoleModel"%>
<%@page import="in.co.rays.ctl.RoleListCtl"%>
<%@page import="in.co.rays.ctl.UserListCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@page import="in.co.rays.bean.RoleBean"%>
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
		<form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">

			<jsp:useBean id="bean" class="in.co.rays.bean.RoleBean"
				scope="request"></jsp:useBean>

			<h1>Role List</h1>
			<%
				List list =  ServletUtility.getList(request);
				List roleList = (List) request.getAttribute("roleList");
				
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize)+ 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());
				
				Iterator it = list.iterator();
				
			%>
			
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> 
			<input type="hidden" name="pageSize" value="<%=pageSize%>">

			<table>
				<th>Role :</th>
				<td><%=HTMLUtility.getList("roleId", DataUtility.getStringData(bean.getId()), roleList)%></td>
				&nbsp;
				<th>Name :</th>
				<td><input type="text" name="Name" placeholder="Enter Name"></td>
				&nbsp;
				<td><input type="submit" name="operation" value="Search"></td>
				<td><input type="submit" name="operation" value="Reset"></td>
			</table>
			<br>

			<table border="1pxl" width="100%">
				<tr>
					<th><input type="checkbox"></th>
					<th>S.No</th>
					<th>Role Id</th>
					<th>Name</th>
					<th>Description</th>
					<th>Edit</th>
				</tr>

				<%
					
					while (it.hasNext()) {
						bean = (RoleBean) it.next();
						RoleModel model = new RoleModel();
						RoleBean  rolebean = model.findByPk(bean.getId());
				%>

				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getDescription()%></td>
					<td><a href="<%=ORSView.ROLE_CTL%>?id=<%=bean.getId()%> " <%if (userBean.getId() == bean.getId() && bean.getId() == RoleBean.ADMIN) {%>
						onclick="return false;" <%}%>>edit</a></td>

				</tr>

				<%
					}
				%>
			</table>

			<table width="100%">
				<br>
				<tr>
					<td align="left"><input type="submit" name="operation"
						value="<%=RoleListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>

					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_NEW%>"></td>

					<td><input type="submit" name="operation" value="Delete"></td>

					<td align="right"><input type="submit" name="operation"
						value="<%=RoleListCtl.OP_NEXT%>" <%=(nextPageSize != 0) ? "" : "disabled"%>></td>

				</tr>
			</table>
			

		</form>
	</div>
</body>
</html>