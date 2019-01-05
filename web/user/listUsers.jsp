<%@include file="../includes/head.jsp"%>

<%@include file="../includes/nav.jsp"%>

<div class="container-fluid mt-2">
    <div class="row">
        <h5 class="text-danger">${message}</h5>
    </div>
    <table class="table">
        <thead class="thead">
        <tr>
            <th scope="col">Email</th>
            <th scope="col">Password</th>
            <th scope="col">Role</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.role}</td>
            <td><a href = "user?pageRequest=editUserGet&id=${user.id}"><i class="fas fa-edit"></i></a></td>
            <td><a href = "user?pageRequest=deleteUser&id=${user.id}"><i class="fas fa-trash-alt"></i></a></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>

