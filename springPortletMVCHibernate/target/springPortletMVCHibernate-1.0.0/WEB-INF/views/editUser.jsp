<%@include file="init.jsp"%>

<portlet:actionURL var="userCreationFormURL">
	<portlet:param name="action" value="userCreationAction" />
</portlet:actionURL>
<portlet:actionURL var="userUpdateFormURL">
	<portlet:param name="action" value="userUpdateAction" />
</portlet:actionURL>


<form:form commandName="user" method="POST"
	action='${userCreationFormURL}' name="formUserCreation"
	htmlEscape="false">
	<table>
		<tr>
			<td colspan="3">Fill up following detials</td>
		</tr>
		<tr>
			<td>UserId</td>
			<td><form:input path="id" name="id" /></td>
			<td></td>
		</tr>
		<tr>
			<td>User Name</td>
			<td><form:input path="username" name="username" /></td>
			<td></td>
		</tr>
		<tr>
			<td>password</td>
			<td><form:password path="password" name="password" /></td>
			<td></td>
		</tr>
		<tr>

			<td colspan="3"><input type="submit" value="submit" /> <%-- <form:button path="submit" id="submitForm" value="Create User" name="createUser"/> --%>
			</td>

		</tr>
	</table>
</form:form>
