<%--
  Created by IntelliJ IDEA.
  User: shinigami
  Date: 12/26/18
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Log in</title>
  <link rel="stylesheet" href="css/login.css">
  </head>
  <body>
  <form class="login" method="post" action="user">
    <h1 class="login-title">Log in</h1>
    <input type = "hidden" name = "pageRequest" value ="login">
    <input type="text" class="login-input" placeholder="Email Address" autofocus name = "email">
    <input type="password" class="login-input" placeholder="Password" name = "password">
    <input type="submit" value="Login" class="login-button">
    <h6 class="login-title"><a href="user?pageRequest=signup">Signup</a></h6>
    <h4 class="login-title">${message}</h4>
  </form>
  </body>
</html>
