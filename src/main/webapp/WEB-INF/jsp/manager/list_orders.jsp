<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Admin Page" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf" %>

		<tr>
			<td class="content">
			<%-- CONTENT --%>
                <h1 style="font-weight: bold; font-size: 20px; text-decoration: underline;">
                    <c:out value="${link}"/>
                </h1>
                </br>
			<c:choose>
			<c:when test="${fn:length(userOrderBeanList) == 0}">No such orders</c:when>

			<c:otherwise>
			<table id="list_order_table">
				<thead>
					<tr>
						<td>№</td>
						<td>FirstName</td>
						<td>FirstName</td>
						<td>LastName</td>
						<td>Bill</td>
						<td>Status</td>
					</tr>
				</thead>


				<c:forEach var="bean" items="${userOrderBeanList}">

					<tr>
						<td>${bean.id}</td>
						<td>${bean.userFirstName}</td>
						<td>${bean.userLastName}</td>
						<td>${bean.orderBill}</td>
						<td>${bean.statusName}</td>
					</tr>

				</c:forEach>
			</table>
			</c:otherwise>
			</c:choose>

			<%-- CONTENT --%>
			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf" %>

	</table>
</body>
</html>