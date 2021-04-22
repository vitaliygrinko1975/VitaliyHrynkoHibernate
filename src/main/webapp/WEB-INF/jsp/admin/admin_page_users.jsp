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

<h1 align='center'>Admin page users</h1>

<div align='center' >

    <table border='1'>
        <caption><h2>USERS</h2></caption>
        <tr>
            <td>№</td>
            <td>Login</td>
            <td>Password</td>
            <td>Name</td>
            <td>Lastname</td>
            <td>Roleid</td>
        </tr>
        <c:set var="k" value="0"/>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.getRole().getId()}</td>
            </tr>
        </c:forEach>
    </table>

    <div align='center'  style="display: inline-block;">
        <a href="controller?command=adminPageAddNewAdmin">
            <button  class="btn btn-primary btn-block btn-large">Add new Admin</button>
        </a>
    </div>
</div>
</body>
</html>