<%@include file="../includes/head.jsp"%>

<%@include file="../includes/nav.jsp"%>

<h5 class="text-warning text-center mt-3">You scored ${score} out of ${total}</h5>

<c:forEach items="${results}" var="result">
    <div class="card mt-3">
        <div class="card-header d-flex justify-content-center">
            ${result.question}
        </div>
            <div class="card-body">

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="defaultCheck1" ${(result.answer == 1)? 'checked':''}>
                    <label class="form-check-label" for="defaultCheck1">
                      ${result.optionOne}
                    </label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="defaultCheck2" ${(result.answer == 2)? 'checked':''}>
                    <label class="form-check-label" for="defaultCheck2">
                            ${result.optionTwo}
                    </label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="defaultCheck3" ${(result.answer == 3)? 'checked':''}>
                    <label class="form-check-label" for="defaultCheck3">
                            ${result.optionThree}
                    </label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="defaultCheck4" ${(result.answer == 4)? 'checked':''}>
                    <label class="form-check-label" for="defaultCheck4">
                            ${result.optionFour}
                    </label>
                </div>

                <div class="card-footer d-flex justify-content-center">
                    <p class="${(result.userAnswer == result.answer)? 'text-success':'text-danger'}">Your answer ${result.userAnswer}</p>
                </div>
            </div>
</div>
</c:forEach>

</body>
</html>

