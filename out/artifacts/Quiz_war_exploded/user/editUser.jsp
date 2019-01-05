<%@include file="../includes/head.jsp"%>

<%@include file="../includes/nav.jsp"%>
<div class="container-fluid mt-2">
    <form action="user" method="post">
        <input type="hidden" name="pageRequest" value="editUserPost">
        <input type="hidden" name="id" value="${user.id}">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Name</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" value = "${user.name}"  name="name" placeholder="Name">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" value = "${user.email}"  name="email" placeholder="Email">
            </div>
        </div>
        <div class="form-group row">
            <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputPassword"  value = "${user.password}" name="password" placeholder="Password">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Role</label>
            <div class="col-sm-10">
                <input type="text" class="form-control"  value = "${user.role}" name="role" placeholder="Role">
            </div>
        </div>

        <div class="form-group d-flex">
            <button type="submit" class="btn btn-dark mx-auto">Update</button>
        </div>
    </form>
</div>


</body>
</html>
