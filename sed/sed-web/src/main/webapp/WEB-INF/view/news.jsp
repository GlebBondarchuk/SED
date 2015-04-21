<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sed" uri="/tld/sed_library" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<link href="${applicationPath}/resources/css/news.css" rel="stylesheet">

<div class="row">

    <!-- Blog Post Content Column -->
    <div class="col-lg-8">

        <!-- Blog Post -->

        <hr>

        <!-- Date/Time -->
        <p><i class="fa fa-clock-o"></i>&nbsp;<spring:message code="news.postedOn"/>&nbsp;${news.createdDate}</p>

        <hr>


        <!-- Post Content -->
        <p class="lead">${news.content.name}</p>
        <c:if test="${not empty news.photo}">
            <c:forTokens var="photo" items="${news.photo}" delims=",">
                <img class="img-responsive img-hover" src="${photo}" alt="">
                <hr>
            </c:forTokens>
        </c:if>
        <div class="foreign-html"><p>${news.content.html}</p></div>
        <hr>

    </div>

    <!-- Blog Sidebar Widgets Column -->
    <div class="col-md-4">

        <!-- Blog Search Well -->
        <div class="well">
            <h4><spring:message code="news.search"/></h4>

            <form action="${applicationPath}/news/${limit}/0" method="get">
                <div class="input-group">
                    <input name="query" type="text" class="form-control" value="${query}">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                        </span>
                </div>
                <!-- /.input-group -->
            </form>
        </div>


        <!-- Blog Categories Well -->
        <div class="well">
            <h4><spring:message code="news.categories"/></h4>
            <tiles:insertAttribute name="categories"/>
        </div>

        <!-- Side Widget Well -->
        <div class="well">
            <p>
                ${newsText.text}
                <security:authorize access="hasRole('ADMIN')">
                    <a href="${applicationPath}/text/${newsText.id}">
                        <i class="glyphicon glyphicon-edit"></i>
                    </a>
                </security:authorize>
            </p>
        </div>
        <security:authorize access="hasRole('ADMIN')">
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <a class="btn btn-danger btn-primary"
                       href="<c:url value="/news/${news.id}/edit"/>" role="button">
                        <span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;<spring:message code="news.edit"/>
                    </a>
                    <a class="btn btn-danger btn-primary"
                       onclick="deleteNews('${news.id}');" role="button">
                        <span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;<spring:message code="news.delete"/>
                    </a>
                </div>
            </div>
        </security:authorize>
    </div>

</div>
<!-- /.row -->


<security:authorize access="hasRole('ADMIN')">
    <script>
        function deleteNews(id) {
            bootbox.setDefaults({locale: '${sed:getLangLowerCase()}'});
            bootbox.confirm("<spring:message code='dialog.sure'/>", function (confirmed) {
                if (confirmed) {
                    $.ajax({
                        type: "POST",
                        url: mainContextPath + "/news/" + id + "/delete"
                    }).done(function () {
                        window.location.href = '<c:url value="/news"/>';
                    });
                }
            });
        }
    </script>
</security:authorize>
