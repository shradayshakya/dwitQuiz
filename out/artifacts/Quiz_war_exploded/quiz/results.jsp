<%@include file="../includes/head.jsp"%>

<%@include file="../includes/nav.jsp"%>

<div class="container-fluid">
    <table class="table table-hover mt-2">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Question</th>
            <th scope="col">Correct Answer</th>
            <th scope="col">Your Answer</th>
            <th scope="col">Your Result</th>
        </tr>
        </thead>
        <c:forEach items="${categories}" var="category">
        <c:forEach begin="1" end="3" var="difficultyLevel">
            <c:set var="score" value="0" />
            <c:set var="total" value="0" />

        <c:forEach items="${results}" var="result">
        <c:if test="${(result.category==category.id && result.difficultyLevel== difficultyLevel)}">
            <c:set var="total" value="${total+1}" />
        <tr style="background-color: #e0e0e0">
            <td>${result.question}</td>
            <td>
                <c:choose>
                    <c:when test="${(result.answer ==1)}">
                        ${result.optionOne}
                    </c:when>
                    <c:when test="${(result.answer ==2)}">
                        ${result.optionTwo}
                    </c:when>
                    <c:when test="${(result.answer ==3)}">
                        ${result.optionThree}
                    </c:when>
                    <c:otherwise>
                        ${result.optionFour}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${result.userAnswer== 1}">
                        ${result.optionOne}
                    </c:when>
                    <c:when test="${result.userAnswer== 2}">
                        ${result.optionTwo}
                    </c:when>
                    <c:when test="${result.userAnswer== 3}">
                        ${result.optionThree}
                    </c:when>
                    <c:when test="${result.userAnswer== 4}">
                        ${result.optionFour}
                    </c:when>
                    <c:otherwise>
                        Invalid answer
                    </c:otherwise>
                </c:choose>
            </td>
            <td class="
                                 <c:choose>
                                 <c:when test= '${(result.userAnswer == result.answer)}'>
                                   ${"text-success"}<c:set var="score" value="${score+1}" />
                                </c:when>
                                 <c:otherwise>
                                  ${"text-danger"}
                                </c:otherwise>
                                </c:choose>
                                ">${(result.userAnswer==result.answer?"Correct":"Incorrect")}
            </td>
        </tr>
        </c:if>
        </c:forEach>
        <c:if test="${total>0}">
        <tr style="background-color:darkgrey">
            <td><strong>Category:</strong>${category.name}</td>
            <td><strong>Difficulty:</strong> <c:choose>
                <c:when test="${(difficultyLevel==1)}">Easy</c:when>
                <c:when test="${(difficultyLevel==2)}">Medium</c:when>
                <c:otherwise>Hard</c:otherwise>
            </c:choose>
            </td>
            <td>
                <strong>Total Correct Answers:</strong>${score}
            </td>
            <td>
                <strong>Total Incorrect Answers:</strong>${total-score}
            </td>
        </tr>
        <tr>
            <td></td>
        </tr>
        </c:if>
        </c:forEach>
        </c:forEach>
</div>
</tbody>
</table>
</body>
</html>

