<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@page isELIgnored="false"%>
<%-- <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %> --%>

<portlet:actionURL var="loginURL">
	<portlet:param name="action" value="loginAction" />
	<portlet:param name="param1" value="this is value of Param1"/>
	<portlet:param name="param2" value="this is value of Param2"/>
</portlet:actionURL>

<%
request.setAttribute("userId", 1);
%>
${exampleOfModelAttribute}

<br/>
Using ModelMap Bean getting attribute : ${ModelMapAttribute1}

<form method="POST" action="${loginURL}">
	<%-- <form:form method="POST" commandName="Login" action="${modifyURL}"> --%>
	<%-- <form:form method="POST" commandName="Login" action="<%= modifyURL.toString() %>"> --%>
	<table>
		<tr>
			<td></td>
			<td></td>
			<td>
				<%-- <form:errors/>  --%>
			</td>
		</tr>
		<tr>
			<td>Email :</td>

			<td><input type="text" name="email" /></td>
			<td>
				<%-- <form:errors path="email" cssClass="error" /> --%>
			</td>
		</tr>
		<tr>
			<td>Password :</td>
			<td><input type="password" name="password" /></td>
			<td>
				<%-- <form:errors path="password" cssClass="error" /> --%>
			</td>
		</tr>
		<tr>
			<td colspan="3"><input type="submit" value="Login"></td>
		</tr>
	</table>
</form>