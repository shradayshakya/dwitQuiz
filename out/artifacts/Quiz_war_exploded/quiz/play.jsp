<%@include file="../includes/head.jsp"%>

    <link rel ="stylesheet" href="css/play.css">

<%@include file="../includes/nav.jsp"%>

<div class="hero-image">
    <div class="hero-text">
        <h1 style="font-size:50px">Welcome to Online Quiz</h1>
        <form action="quiz" method="post">
            <input type="hidden" name="pageRequest" value="start">
            ${(message!=null)?message:"<button class='btn btn-dark' type='submit'>Start</button>"}
        </form>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>
</body>
</html>