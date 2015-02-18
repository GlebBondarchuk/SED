<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sed" uri="/tld/sed_library" %>

<div class="row">

    <!-- Blog Post Content Column -->
    <div class="col-lg-8">

        <!-- Blog Post -->

        <hr>

        <!-- Date/Time -->
        <p><i class="fa fa-clock-o"></i> Posted on ${news.createdDate}</p>

        <hr>

        <!-- Preview Image -->
        <img class="img-responsive" src="${news.photo}" alt="">

        <hr>

        <!-- Post Content -->
        <p class="lead">${news.content.name}</p>

        <p>${news.content.html}</p>

        <hr>

    </div>

    <!-- Blog Sidebar Widgets Column -->
    <div class="col-md-4">

        <!-- Blog Search Well -->
        <div class="well">
            <h4>Blog Search</h4>

            <div class="input-group">
                <input type="text" class="form-control">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
                        </span>
            </div>
            <!-- /.input-group -->
        </div>

        <!-- Blog Categories Well -->
        <div class="well">
            <h4>Blog Categories</h4>

            <div class="row">
                <div class="col-lg-6">
                    <ul class="list-unstyled">
                        <li><a href="#">Category Name</a>
                        </li>
                        <li><a href="#">Category Name</a>
                        </li>
                        <li><a href="#">Category Name</a>
                        </li>
                        <li><a href="#">Category Name</a>
                        </li>
                    </ul>
                </div>
                <div class="col-lg-6">
                    <ul class="list-unstyled">
                        <li><a href="#">Category Name</a>
                        </li>
                        <li><a href="#">Category Name</a>
                        </li>
                        <li><a href="#">Category Name</a>
                        </li>
                        <li><a href="#">Category Name</a>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- /.row -->
        </div>

        <!-- Side Widget Well -->
        <div class="well">
            <h4>Side Widget Well</h4>

            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus laudantium odit aliquam repellat
                tempore quos aspernatur vero.</p>
        </div>
        <security:authorize access="hasRole('ADMIN')">
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <a class="btn btn-danger btn-primary"
                       href="<c:url value="${applicationPath}/news/${news.id}/edit"/>" role="button">
                        <span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;<spring:message code="people.button.editPage"/>
                    </a>
                    <a class="btn btn-danger btn-primary"
                       onclick="deleteNews();" role="button">
                        <span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;<spring:message code="people.button.deleteTab"/>
                    </a>
                </div>
            </div>
        </security:authorize>
    </div>

</div>
<!-- /.row -->

<security:authorize access="hasRole('ADMIN')">

    <script>
        function deleteNews() {
            bootbox.setDefaults({locale: '${sed:getLangLowerCase()}'});
            bootbox.confirm("<spring:message code="dialog.sure"/>", function (confirmed) {
                if (confirmed) {
                    $.ajax({
                        type: "POST",
                        url: "<c:url value="${applicationPath}/news/${news.id}/delete"/>"
                    }).done(function () {
                        window.location.href = "<c:url value="${applicationPath}/news"/>";
                    });
                }
            });
        }
    </script>
</security:authorize>
