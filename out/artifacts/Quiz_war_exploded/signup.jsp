<%--
  Created by IntelliJ IDEA.
  User: shinigami
  Date: 12/27/18
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
</head>
<body>

<div class="container-fluid mt-5">
    <h3 class="text-center text-danger">SIGN UP</h3>
    <form action="user" method="post" class="mt-3">
        <input type="hidden" name="pageRequest" value="register">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Name</label>
            <div class="col-sm-10">
                <input type="text" class="form-control"  name="name" placeholder="Name">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <input type="text" class="form-control"  name="email" placeholder="Email">
            </div>
        </div>
        <div class="form-group row">
            <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputPassword"  name="password" placeholder="Password">
            </div>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-danger mx-auto">Register</button>
            <a href="index.jsp" class="btn btn-danger mx-auto">Cancel</a>
        </div>
    </form>
</div>


<footer id="sticky">&copy&nbspDeerwalk</footer>
</body>
</html>
