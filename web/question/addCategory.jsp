<%@include file="../includes/head.jsp"%>

<%@include file="../includes/nav.jsp"%>


<div class="container-fluid mt-2">
    <h5 class="text-success text-center">${message}</h5>
    <form action="category" method="post">
        <input type="hidden" name="pageRequest" value="addCategoryPost">
        <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label">Category Name</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="name" placeholder="Enter name of the category" id ="name">
            </div>
        </div>
        <div class="form-group row">
            <label for="description" class="col-sm-2 col-form-label">Description</label>
            <div class="col-sm-10">
                <textarea name="description" id="description" class="form-control" placeholder="Enter description of the category"></textarea>
            </div>
        </div>

        <div class="form-group d-flex">
            <button type="submit" class="btn btn-dark mx-auto">Add</button>
        </div>
    </form>
</div>

</body>
</html>

