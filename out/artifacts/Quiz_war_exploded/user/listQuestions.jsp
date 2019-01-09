<%@include file="../includes/head.jsp"%>

<%@include file="../includes/nav.jsp"%>

<div class="container-fluid mt-2">
    <div class="row">
        <h5 class="text-danger">${message}</h5>
    </div>
    <table class="table">
        <thead class="thead">
        <tr>
            <th scope="col">Question</th>
            <th scope="col">Category</th>
            <th scope="col">Difficulty</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${questions}" var="question">
            <tr>
                <td>${question.question}</td>
                <td>${question.category}</td>
                <td>${question.difficultyLevel}</td>
                <td><a href = "question?pageRequest=editQuestionGet&id=${question.id}"><i class="fas fa-edit"></i></a></td>
                <td><a href = "question?pageRequest=deleteQuestion&id=${question.id}"><i class="fas fa-trash-alt"></i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
