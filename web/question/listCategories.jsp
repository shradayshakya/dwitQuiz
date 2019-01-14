<%@include file="../includes/head.jsp"%>

<%@include file="../includes/nav.jsp"%>

<div class="container-fluid mt-2">
    <div class="row">
        <h5 class="text-danger">${message}</h5>
    </div>
    <table class="table">
        <thead class="thead">
        <tr>
            <th scope="col">Category Name</th>
            <th scope="col">Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${categories}" var="category">
            <tr>
                <td>${category.name}</td>
                <td>${category.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
