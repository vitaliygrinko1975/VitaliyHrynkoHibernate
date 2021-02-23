<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%--=========================================================================== 
Here we use a table layout.
Class page corresponds to the '.page' element in included CSS document.
===========================================================================--%> 
	<table id="main-container">

<%--=========================================================================== 
This is the HEADER, containing a top menu.
header.jspf contains all necessary functionality for it.
Just included it in this JSP document.
===========================================================================--%> 

		<%-- HEADER --%>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<%-- HEADER --%>

<%--=========================================================================== 
This is the CONTENT, containing the main part of the page.
===========================================================================--%>

	<%--<body style="background:url(./img/rent.jpeg)">--%>
	    <tr >
						<td class="content center">
			<%-- CONTENT --%>

<%--===========================================================================
Defines the web form.
===========================================================================--%> 
				<form id="login_form" action="controller" method="post">

<%--=========================================================================== 
Hidden field. In the query it will act as command=login.
The purpose of this to define the command name, which have to be executed 
after you submit current form. 
===========================================================================--%> 
					<input type="hidden" name="command" value="login"/>

					<fieldset >
						<legend>Login</legend>
						<input name="login"/><br/>
					</fieldset><br/>
					<fieldset>
						<legend>Password</legend>
						<input type="password" name="password"/>
					</fieldset><br/>
					
					<input type="submit" value="Login">
				</form>

				<form method="post" action="controller">
					<input type="hidden" name="command" value="loginingPage"/>
					<button type="submit" name = "removeButt" value = 0
							class="btn btn-primary btn-block btn-large">REGISTRATION</button>
				</form>
			<%-- CONTENT --%>

			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>
</body>
</html>