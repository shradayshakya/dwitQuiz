<%@include file="../includes/head.jsp"%>

<%@include file="../includes/nav.jsp"%>

<div class="container-fluid mt-2">
    <h5 class="text-success text-center">${message}</h5>
    <form action="question" method="post">
        <input type = "hidden" name = "id" value = "${question.id}">
        <input type="hidden" name="pageRequest" value="editQuestionPost">
        <div class="form-group row">
            <label for="question" class="col-sm-2 col-form-label">Question</label>
            <div class="col-sm-10">
                <input type="text" value="${question.question}" class="form-control" name="question" placeholder="Enter the question" id ="question">
            </div>
        </div>
        <div class="form-group row">
            <label for="optionone" class="col-sm-2 col-form-label">1st Option</label>
            <div class="col-sm-10">
                <input type="text" value="${question.optionOne}" class="form-control" id="optionone"  name="optionone" placeholder="Enter first option">
            </div>
        </div>

        <div class="form-group row">
            <label for="optiontwo" class="col-sm-2 col-form-label">2nd Option</label>
            <div class="col-sm-10">
                <input type="text" value="${question.optionTwo}" class="form-control" id="optiontwo"  name="optiontwo" placeholder="Enter second option">
            </div>
        </div>


        <div class="form-group row">
            <label for="optionthree" class="col-sm-2 col-form-label">3rd Option</label>
            <div class="col-sm-10">
                <input type="text" value="${question.optionThree}" class="form-control" id="optionthree"  name="optionthree" placeholder="Enter third option">
            </div>
        </div>

        <div class="form-group row">
            <label for="optionfour"  class="col-sm-2 col-form-label">4th Option</label>
            <div class="col-sm-10">
                <input type="text" value="${question.optionFour}" class="form-control" id="optionfour"  name="optionfour" placeholder="Enter fourth option">
            </div>
        </div>


        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Correct answer</label>
            <div class="col-sm-10">
                <select name="answer" class="form-control">
                    <option> Select an option</option>
                    <option ${question.answer == 1 ? 'selected' : ''} value="1" >1</option>
                    <option ${question.answer == 2 ? 'selected' : ''} value="2">2</option>
                    <option ${question.answer == 3 ? 'selected' : ''} value="3">3</option>
                    <option ${question.answer == 4 ? 'selected' : ''} value="4">4</option>
                </select>
            </div>
        </div>

        <div class="form-group row">
            <label for="category" class="col-sm-2 col-form-label">Category</label>
            <div class="col-sm-10">
                <input type="text" value="${question.category}" class="form-control" id="category"  name="category" placeholder="Enter the category">
            </div>
        </div>


        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Difficulty</label>
            <div class="col-sm-10">
                <div class="form-check form-check-inline">
                    <input ${question.difficultyLevel == 1 ? 'checked' : ''} class="form-check-input" type="radio" name="difficulty" id="inlineRadio1" value="1">
                    <label class="form-check-label" for="inlineRadio1">Easy</label>
                </div>
                <div class="form-check form-check-inline">
                    <input ${question.difficultyLevel == 2 ? 'checked' : ''}  class="form-check-input" type="radio" name="difficulty" id="inlineRadio2" value="2">
                    <label class="form-check-label" for="inlineRadio2">Medium</label>
                </div>
                <div class="form-check form-check-inline">
                    <input ${question.difficultyLevel == 3 ? 'checked' : ''} class="form-check-input" type="radio" name="difficulty" id="inlineRadio3" value="3">
                    <label class="form-check-label" for="inlineRadio3">Hard</label>
                </div>
            </div>
        </div>

        <div class="form-group d-flex">
            <button type="submit" class="btn btn-dark mx-auto">Update</button>
        </div>
    </form>
</div>

</body>
</html>
