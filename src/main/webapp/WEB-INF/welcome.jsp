<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>for all</title>
    <link href='./style/style2.css' rel='stylesheet' type='text/css'>
</head>
<body>
<div align='right'>
    <div style="display: inline-block; padding-right: 50px;">
        <a href="controller?command=logout">
            <button  class="btn btn-primary btn-block btn-large">FOR REGISTERED</button>
        </a>
    </div>
</div>
<div align='right'>
    <div style="display: inline-block; padding-right: 50px;">
<form method="post" action="controller">
    <input type="hidden" name="command" value="loginingPage"/>
    <button type="submit" name = "removeButt" value = 0
            class="btn btn-primary btn-block btn-large">REGISTRATION</button>
</form>
    </div>
</div>

<h1 align='center'>Rent cars</h1>
<div align='center' >

</div>

<!--<style type="text/css">
    body{
        margin: 0;
        background-image: url(./img/rent.jpeg);
        background-repeat: no-repeat;
    }
</style>-->

<div align='center'><img src='./img/rent.jpeg' alt='Hello'></div> 
</body>
</html>
