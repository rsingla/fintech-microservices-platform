<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form action="createprofile" method="POST" commandName="createprofile">
	<table>
		<tr>
			<td>First Name:</td>
			<td><form:input path="firstName" /></td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><form:input path="lastName" /></td>
		</tr>
		<tr>
			<td>Sex:</td>
			<td>Male: <form:radiobutton path="sex" value="M" /> <br />
				Female: <form:radiobutton path="sex" value="F" />
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Save Changes" /></td>
		</tr>
	</table>
</form:form>