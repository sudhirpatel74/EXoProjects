<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page isELIgnored="false"%>

<script type="text/javascript">
	function editUser(userId , action) {
		/* alert("hi " + userId + " Path : " + urlPath );
		var form = document.getElementById("userList");
		console.log(urlPath);
		form.setAttribute("action",urlPath );
		form.submit();
 */
		alert("hi " + userId);
 		/* var hiddenId = "editUrl_"+userId;
 		alert(hiddenId);
 		alert(document.forms["userList"][hiddenId+""].value);
 		document.userList.action = document.forms["userList"][hiddenId+""].value;
 		document.userList.submit(); */
	}
	function deleteUser(userId) {
		alert("hi " + userId);
	}
</script>