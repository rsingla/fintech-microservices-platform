<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Register</title>
</head>
<body>

<form action="createprofile" method="POST" commandName="employee">

  Day (between 1 and 31): <input type="number" name="day" min="1" max="31">
  Year (between 1900 and 2013): <input type="number" name="year" min="1900" max="2013">
  Month (between 1 and 31): <input type="month" name="month" min="1" max="2">
  
  <input type="email" name="email">
  <input type="email" name="confirmEmail">
  <input type="text" name="firstName">
  <input type="text" name="lastName">
  <input type="password" name="password">
  <input type="password" name="confirmPassword">
  
  <input type="submit">

</form>
</body>
</html>