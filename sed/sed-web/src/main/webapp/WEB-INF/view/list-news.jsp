<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sed" uri="/tld/sed_library" %>

<div class="row">

    <!-- Blog Entries Column -->
    <div class="col-md-8">
        <c:choose>
            <c:when test="${not empty newsList}">
                <c:set var="newer" value="${applicationPath}/news/${limit}/${offset - limit > 0 ? offset - limit : 0}"/>
                <c:set var="older" value="${applicationPath}/news/${limit}/${offset + limit > count ? count - offset - limit : offset + limit}"/>
                <c:choose>
                    <c:when test="${not empty query}">
                        <c:set var="next" value="${newer}?query=${query}"/>
                        <c:set var="previous" value="${older}?query=${query}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="next" value="${newer}"/>
                        <c:set var="previous" value="${older}"/>
                    </c:otherwise>
                </c:choose>
                <c:set var="disabledOlder" value="${offset + limit > count ? 'return false':''}"/>
                <c:set var="disabledNewer" value="${offset - limit < 0 ? 'return false':''}"/>

                <!-- Pager -->
                <ul class="pager">
                    <li class="previous ${offset + limit > count ? 'disabled':''}">
                        <a onclick="${disabledOlder}" href="${previous}">&larr; Older</a>
                    </li>
                    <li class="next ${offset - limit < 0 ? 'disabled':''}">
                        <a onclick="${disabledNewer}" href="${next}">Newer &rarr;</a>
                    </li>
                </ul>

                <c:forEach var="news" items="${newsList}">
                    <h2>
                        <a href="${applicationPath}/news/${news.id}">${news.content.name}</a>
                    </h2>

                    <p class="lead">
                        by <a href="${applicationPath}/people/${news.creator.login}">${news.creator.name}</a>
                    </p>

                    <p><i class="fa fa-clock-o"></i>&nbsp;<spring:message code="news.postedOn"/>&nbsp;${news.createdDate}</p>
                    <%--<hr>--%>
                    <%--<a href="${applicationPath}/news/${news.id}">--%>
                    <%--<img class="img-responsive img-hover" src="${news.photo}" alt="">--%>
                    <%--</a>--%>
                    <%--<hr>--%>
                    <p>${news.simpleText}</p>
                    <a class="btn btn-primary" href="${applicationPath}/news/${news.id}">
                        <spring:message code="news.readMore"/>
                        &nbsp;<i class="fa fa-angle-right"></i></a>
                    <security:authorize access="hasRole('ADMIN')">
                        <a class="btn btn-danger" onclick="deleteNews('${news.id}');">
                            <span class="glyphicon glyphicon-trash"></span>
                            &nbsp;&nbsp;<spring:message code="news.delete"/>
                        </a>
                    </security:authorize>
                    <hr>
                </c:forEach>

                <!-- Pager -->
                <ul class="pager">
                    <li class="previous ${offset + limit > count ? 'disabled':''}">
                        <a onclick="${disabledOlder}" href="${previous}">&larr; Older</a>
                    </li>
                    <li class="next ${offset - limit < 0 ? 'disabled':''}">
                        <a onclick="${disabledNewer}" href="${next}">Newer &rarr;</a>
                    </li>
                </ul>
            </c:when>
            <c:otherwise>
                <div class="text-center alert-warning alert">
                    Nothing found
                </div>
            </c:otherwise>
        </c:choose>
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
                <!-- /.col-lg-6 -->
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
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
        </div>

        <!-- Side Widget Well -->
        <div class="well">
            <h4>Side Widget Well</h4>

            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus laudantium odit aliquam repellat
                tempore quos aspernatur vero.</p>
        </div>
        <security:authorize access="hasAnyRole('ADMIN','TEACHER')">
            <a href="${applicationPath}/news/add" class="btn btn-danger btn-primary" role="button">
                <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;
                <spring:message code="news.button.add"/>
            </a>
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
                        window.location.href = '<c:url value="/news/${limit}/${offset}"/>';
                    });
                }
            });
        }
    </script>
</security:authorize>