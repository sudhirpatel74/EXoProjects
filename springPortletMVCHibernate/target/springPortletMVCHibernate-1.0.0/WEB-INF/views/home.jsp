<%@page import="org.apache.pluto.tags.ActionURLTag"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="org.scalsys.springPortletMVCHibernate.SchemaModel.User"%>
<%@include file="init.jsp"%>

<portlet:renderURL var="loginURL">
</portlet:renderURL>
<portlet:renderURL var="createUserURL">
	<portlet:param name="action" value="createUser"/>
</portlet:renderURL>


<h1>Hello world!</h1>

<P>The time on the server is ${serverTime}.</P>

<table border="1">
	<tr>
		<th>ID</th>
		<th>User Name</th>
		<th>Password</th>
		<th colspan="2">operations</th>
	</tr>

	<c:forEach var="user" items="${usersList}">
		<portlet:renderURL var="editUserURL">
			<portlet:param name="action" value="editUserInfo" />
			<portlet:param name="userId" value="${user.userId}" />
		</portlet:renderURL>
		<portlet:actionURL var="deleteUserURL">
			<portlet:param name="action" value="deleteUserInfo" />
			<portlet:param name="userId" value="${user.userId}" />
		</portlet:actionURL>

		<tr align="center">
			<td><c:out value="${user.userId}" /></td>
			<td><c:out value="${user.username}" /></td>
			<td><c:out value="${user.password}" /></td>
			<td><a href="${editUserURL}"> <input type="button"
					value="Edit" onclick="editUser(${user.userId})" /></a></td>
			<td>
				<form id="userList" name="userList" method="post"
					action="${deleteUserURL}" htmlEscape="false">
					<input type="submit" value="Remove" />
				</form>
			</td>

		</tr>

	</c:forEach>
</table>

<a href="${loginURL }">go to Login Page</a><br>
<%-- htmlEscape="false" use this field while populate pojo from <form:form> because by default it will add amp; so use this attribute --%>
<a href="${createUserURL}">Create New User</a>

<%@include file="home_js.jsp"%>
