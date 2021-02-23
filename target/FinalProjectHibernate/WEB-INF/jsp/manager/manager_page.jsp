<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>Admin</title>
    <link href='./style/style2.css' rel='stylesheet' type='text/css'>
</head>
<body>
<div align='right'>
    <div style="display: inline-block; padding-right: 50px;">
        <a href="controller?command=logout">
            <button  class="btn btn-primary btn-block btn-large">Log out</button>
        </a>
    </div>
</div>



<h1 align='center'>Manager page</h1>

<div align='center' >

    <table border='1'>
        <caption><h2>ORDERS</h2></caption>
        <tr>
            <td>№</td>
            <td>Client</td>
            <td>Bill</td>
            <td>Status</td>
        </tr>
        <c:set var="k" value="0"/>
        <c:forEach var="bean" items="${userOrderBeanList}">
            <tr>
                <td>${bean.id}</td>
                <td>${bean.userFirstName} ${bean.userLastName}</td>
                <td>${bean.orderBill}</td>
                <td>${bean.statusName}</td>

                <%--<td><form method="post" action="controller">
                <input type="hidden" name="command" value="blockManagerCommand"/>
                <button type="submit" name = "blockButt" value = "${bean.id}"
                        class="btn btn-primary btn-block btn-large">BLOCKING</button>
                </form>
                </td>
                <td><form method="post" action="controller">
                    <input type="hidden" name="command" value="unBlockManagerCommand"/>
                    <button type="submit" name = "unblockButt" value = "${bean.id}"
                            class="btn btn-primary btn-block btn-large">UNBLOCKING</button>
                </form>
                </td>--%>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>