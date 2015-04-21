<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sed" uri="/tld/sed_library" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href="${applicationPath}/resources/css/news.css" rel="stylesheet">

<div class="row">

    <!-- Blog Entries Column -->
    <div class="col-md-8">
        <c:choose>
            <c:when test="${not empty newsList}">
                <c:set var="next" value="${applicationPath}/news/${limit}/${offset - limit > 0 ? offset - limit : 0}"/>
                <c:set var="previous" value="${applicationPath}/news/${limit}/${offset + limit >= count ? count - offset - limit : offset + limit}"/>
                <c:choose>
                    <c:when test="${not empty query}">
                        <c:set var="next" value="${next}?query=${query}"/>
                        <c:set var="previous" value="${previous}?query=${query}"/>
                    </c:when>
                    <c:when test="${not empty category}">
                        <c:set var="next" value="${next}?category=${category}"/>
                        <c:set var="previous" value="${previous}?category=${category}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="next" value="${next}"/>
                        <c:set var="previous" value="${previous}"/>
                    </c:otherwise>
                </c:choose>
                <c:set var="disabledOlder" value="${offset + limit >= count ? 'return false':''}"/>
                <c:set var="disabledNewer" value="${offset - limit < 0 ? 'return false':''}"/>

                <!-- Pager -->
                <ul class="pager">
                    <li class="previous ${offset + limit >= count ? 'disabled':''}">
                        <a onclick="${disabledOlder}" href="${previous}">&larr; <spring:message code="label.news.older"/></a>
                    </li>
                    <li class="next ${offset - limit < 0 ? 'disabled':''}">
                        <a onclick="${disabledNewer}" href="${next}"><spring:message code="label.news.newer"/> &rarr;</a>
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

                    <hr>

                    <div class="foreign-html"><p>${news.simpleText}</p></div>
                    <a class="btn btn-primary" href="${applicationPath}/news/${news.id}">
                        <spring:message code="news.readMore"/>
                        &nbsp;<i class="fa fa-angle-right"></i></a>
                    <security:authorize access="hasRole('ADMIN')">
                        <a class="btn btn-danger" onclick="deleteNews('${news.id}');">
                            <span class="glyphicon glyphicon-trash"></span>
                            &nbsp;&nbsp;<spring:message code="news.delete"/>
                        </a>
                        <a class="btn btn-danger" onclick="fix('${!news.fixed}','${news.id}');">
                            <span class="glyphicon glyphicon-pushpin"></span>
                            <c:choose>
                                <c:when test="${news.fixed}">
                                    &nbsp;&nbsp;<spring:message code="news.unfix"/>
                                </c:when>
                                <c:otherwise>
                                    &nbsp;&nbsp;<spring:message code="news.fix"/>
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </security:authorize>
                    <c:if test="${news.fixed}">
                        <span class="glyphicon glyphicon-pushpin pull-right" title="Fixed"></span>
                    </c:if>
                    <hr>
                </c:forEach>

                <!-- Pager -->
                <ul class="pager">
                    <li class="previous ${offset + limit >= count ? 'disabled':''}">
                        <a onclick="${disabledOlder}" href="${previous}">&larr; <spring:message code="label.news.older"/></a>
                    </li>
                    <li class="next ${offset - limit < 0 ? 'disabled':''}">
                        <a onclick="${disabledNewer}" href="${next}"><spring:message code="label.news.newer"/> &rarr;</a>
                    </li>
                </ul>
            </c:when>
            <c:otherwise>
                <div class="text-center alert-warning alert">
                    <spring:message code="label.search.nothingFound"/>
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

        function fix(fix, id) {
            $.ajax({
                type: "POST",
                url: mainContextPath + "/news/" + id + "/fix",
                data: {fix: fix}
            }).done(function () {
                window.location.href = '<c:url value="/news/${limit}/${offset}"/>';
            });
        }
    </script>
</security:authorize>