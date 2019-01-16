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
            <th scope="col">Diplay</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${questions}" var="question">
            <tr>
                <td>${question.question}</td>
                <td>
                    <c:forEach items="${categories}" var="category">
                        <c:if test="${question.category==category.id}">
                            ${category.name}
                        </c:if>
                    </c:forEach>
                </td>
                <td>${question.difficultyLevel}</td>
                <td>
                    <form action="question" name="form${question.id}" method="post">
                        <input type="hidden" name='pageRequest' value='editDisplay'>
                        <input type="hidden" name='id' value=${question.id}>
                     <input type="checkbox" name = "display" value=${question.display == true?'false':'true'} id="${question.id}"  ${question.display == true?'checked':''}>
                    </form>
                </td>

                <td><a href = "question?pageRequest=editQuestionGet&id=${question.id}"><i class="fas fa-edit"></i></a></td>
                <td><a href = "question?pageRequest=deleteQuestion&id=${question.id}"><i class="fas fa-trash-alt"></i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script type='text/javascript'>
    // <![CDATA[
    jQuery(document).ready(function(){

        $('input:checkbox').change(function(e){
            var questionid = e.target.id;
            var formName = "form".concat(questionid);
            var selector = "[name ='"+formName+"']";
            //alert(selector);
            $(selector).submit();
        });

    });

    // ]]>
</script>
</body>
</html>
