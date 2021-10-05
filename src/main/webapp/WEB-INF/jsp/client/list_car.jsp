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
<%--<h1 align='center'>Rent cars</h1>--%>

<div align='center' >
    <caption><h2>Cars</h2></caption>
        <div align='center'>
            <div style="display: inline-block; padding-right: 50px;">
                <a href="controller?command=sortedUpPrice">
                    <button  class="btn btn-primary btn-block btn-large">Sort by price ></button>
                </a>
            </div>
            <div align='center'>
                <div style="display: inline-block; padding-right: 50px;">
                    <a href="controller?command=sortedDownPrice">
                        <button  class="btn btn-primary btn-block btn-large">Sort by price <</button>
                    </a>
                </div>
            <div align='center'>
                <div style="display: inline-block; padding-right: 50px;">
                    <a href="controller?command=sortedUpName">
                        <button  class="btn btn-primary btn-block btn-large">Sort by name ></button>
                    </a>
                </div>
                <div align='center'>
                    <div style="display: inline-block; padding-right: 50px;">
                        <a href="controller?command=sortedDownName">
                            <button  class="btn btn-primary btn-block btn-large">Sort by name <</button>
                        </a>
                    </div>
       <table border='1'>
        <tr>
            <td>№</td>
            <td>CARS</td>
            <td>PRICE</td>
            <td>CLASS</td>
        </tr>
        <c:set var="k" value="0"/>
        <c:forEach var="car" items="${carsItems}">
            <c:set var="k" value="${k+1}"/>
            <tr>
                <td><c:out value="${k}"/></td>
                <td>${car.name}</td>
                <td>${car.price}</td>
                <td>${car.category}</td>
                <td>
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="makeOrderCommand"/>
                        <button type="submit" name ="makeOrderButt" value = "${car.id}"
                                class="btn btn-primary btn-block btn-large">make an orders</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
                    <div align='center'>
                        <div style="display: inline-block; padding-right: 50px;">
                        <form method='post' action="controller">
                            <input type="hidden" name="command" value="selectByClass"/>
                            Choose a car by class: <input type='text' name='selectClass'   required='required'/>
                            <button type='submit' name = 'Butt' value = '0' class='btn btn-primary btn-block btn-large'>ENTER</button>
                        </form>
                    </div>
                </div>
            </body>
</html>


<%--<div align='center'  style="display: inline-block;">--%>
<%--<a href="controller?command=addpage">--%>
<%--<button  class="btn btn-primary btn-block btn-large">Add new car</button>--%>
<%--</a>--%>
<%--</div>--%>