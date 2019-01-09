</head>
<body>
<nav>
    <ul>
        <li>
            <a href="user?pageRequest=home">Home</a>
        </li>

        <c:if test="${sessionScope.user.role=='admin'}">
        <li>
            <a href="user?pageRequest=listUsers">Users</a>
        </li>

        <li>
            <a href="question?pageRequest=postQuestionGet">Post</a>
        </li>
        <li>
            <a href="question?pageRequest=listQuestions">Questions</a>
        </li>
        </c:if>

        <c:if test="${sessionScope.user.role== 'user'}">
        <li>
            <a href="question?pageRequest=play">Play</a>
        </li>

        <li>
            <a href="question?pageRequest=results&userId=${user.id}">Results</a>
        </li>
        </c:if>


        <li style="float:right">
            <span>${user.email}</span>
        </li>

        <li style="float:right">
            <a href="user?pageRequest=logout">Logout</a>
        </li>
    </ul>
</nav>



