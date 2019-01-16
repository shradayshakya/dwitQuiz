<%@include file="../includes/head.jsp"%>

    <link rel ="stylesheet" href="css/play.css">

<%@include file="../includes/nav.jsp"%>
<h5 class="text-danger text-center mt-3">${message}</h5>
<div class="container-fluid mt-3 d-flex flex-wrap justify-content-center">
    <c:forEach items="${categories}" var="category">
        <form action="quiz" method="post">
            <input type="hidden" name="pageRequest" value="start">
            <input type="hidden" name="categoryId" value="${category.id}">
            <input type="hidden" name="categoryName" value="${category.name}">
            <div class="card mt-3 m-2" style="width:280px">
            <div class="card-body">
                <h4 class="card-title text-center">${category.name}</h4>
                <p class="card-text">
                    <div class="form-check-inline">
                    <label class="form-check-label" for="easy">
                        <input type="radio" class="form-check-input" id="easy" name="${category.name}" value="1" checked>Easy
                    </label>
                   </div>
                    <div class="form-check-inline">
                        <label class="form-check-label" for="medium">
                            <input type="radio" class="form-check-input" id="medium" name="${category.name}" value="2">Medium
                        </label>
                    </div>
                    <div class="form-check-inline">
                        <label class="form-check-label" for="hard">
                            <input type="radio" class="form-check-input" id="hard" name="${category.name}" value="3">Hard
                        </label>
                    </div>
                    </p>
                <input type="submit" class="btn btn-dark d-flex mx-auto" value="Play">
            </div>
        </div>
        </form>
    </c:forEach>
</div>
</body>
</html>