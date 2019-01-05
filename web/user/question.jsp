<%@include file="../includes/head.jsp"%>

<%@include file="../includes/nav.jsp"%>

<div class="container mt-5">
    <h2 class="text-center">Question ${currentRow}</h2>
    <div class="card">
        <div class="card-header d-flex justify-content-center">
            ${question.question}
        </div>
        <form action="user" method = "post">
            <input type = "hidden" name = "userId" value="${user.id}">
            <input type = "hidden" name = "questionId" value="${question.id}">
            <input type="hidden" name="pageRequest" value="nextQuestion">
            <input type="hidden" name="currentRow" value="${currentRow}">
        <div class="card-body">

            <div class="form-check">
                <input class="form-check-input" type="radio" value="1" id="defaultCheck1" name ="userAnswer">
                <label class="form-check-label" for="defaultCheck1">
                    ${question.optionOne}
                </label>
            </div>

            <div class="form-check">
                <input class="form-check-input" type="radio" value="2" id="defaultCheck2" name ="userAnswer">
                <label class="form-check-label" for="defaultCheck2">
                    ${question.optionTwo}
                </label>
            </div>

            <div class="form-check">
                <input class="form-check-input" type="radio" value="3" id="defaultCheck3" name ="userAnswer">
                <label class="form-check-label" for="defaultCheck3">
                    ${question.optionThree}
                </label>
            </div>

            <div class="form-check">
                <input class="form-check-input" type="radio" value="4" id="defaultCheck4" name ="userAnswer">
                <label class="form-check-label" for="defaultCheck4">
                    ${question.optionFour}
                </label>
            </div>
        </div>
        <div class="card-footer d-flex justify-content-center">
            <button class="btn  btn-dark" type="submit">${currentRow < 5 ? 'Next' : 'Finish'}</button>
        </div>
        </form>
    </div>
</div>

</body>
</html>

