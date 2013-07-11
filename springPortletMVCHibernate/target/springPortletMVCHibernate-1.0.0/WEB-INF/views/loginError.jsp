<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page isELIgnored="false"%>

<portlet:renderURL var="loginFormGoBack">
</portlet:renderURL>
<portlet:renderURL var="loginFormURL">
	<portlet:param name="action" value="loginForm2" />
</portlet:renderURL>

sorry wrong User Id Password <br/>

<a href="${loginFormURL}">Go to Login Page</a>



