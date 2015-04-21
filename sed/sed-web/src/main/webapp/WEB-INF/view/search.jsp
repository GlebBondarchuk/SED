<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:set var="next" value="${applicationPath}/search/do/${limit}/${offset - limit > 0 ? offset - limit : 0}"/>
<c:set var="previous" value="${applicationPath}/search/do/${limit}/${offset + limit >= count ? count - offset - limit : offset + limit}"/>
<c:choose>
    <c:when test="${not empty query}">
        <c:set var="next" value="${next}?query=${query}"/>
        <c:set var="previous" value="${previous}?query=${query}"/>
    </c:when>
    <c:otherwise>
        <c:set var="next" value="${next}"/>
        <c:set var="previous" value="${previous}"/>
    </c:otherwise>
</c:choose>
<c:set var="disabledOlder" value="${offset + limit >= count ? 'return false':''}"/>
<c:set var="disabledNewer" value="${offset - limit < 0 ? 'return false':''}"/>


<div class="row">
    <div class="col-lg-8 col-lg-offset-2">
        <form action="${applicationPath}/search/do/${limit}/${offset}" method="get" name="searchForm">
            <div class="input-group">
                <input type="text" class="form-control" name="query" placeholder="Search..." value="${param.query}">
                <span id="mask" class="input-group-addon" onclick="document.searchForm.submit();"><span
                        class="glyphicon glyphicon-search"></span></span>
            </div>
        </form>
    </div>
</div>

<c:if test="${not empty results}">
    <!-- Pager -->
    <ul class="pager">
        <li class="previous ${offset + limit >= count ? 'disabled':''}">
            <a onclick="${disabledOlder}" href="${previous}">&larr; <spring:message code="label.search.previous"/></a>
        </li>
        <li class="next ${offset - limit < 0 ? 'disabled':''}">
            <a onclick="${disabledNewer}" href="${next}"><spring:message code="label.search.next"/> &rarr;</a>
        </li>
    </ul>
</c:if>

<c:if test="${not empty param.query}">
    <hr>
    <div class="row">
        <div class="col-lg-12">
            <c:choose>
                <c:when test="${not empty results}">
                    <c:forEach var="result" items="${results}">
                        <div class="alert alert-info">
                            <c:choose>
                                <c:when test="${not empty result.photo}">
                                    <img height="40" width="30" src="${result.photo}"/><a href="${applicationPath}${result.url}">&nbsp;&nbsp;${result.title}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${applicationPath}${result.url}">
                                        &nbsp;&nbsp;
                                        <c:choose>
                                            <c:when test="${not empty result.title}">
                                                ${result.title}
                                            </c:when>
                                            <c:otherwise>
                                                ${applicationPath}${result.url}
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <hr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="text-center alert-warning alert">
                        <spring:message code="label.search.nothingFound"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</c:if>

<c:if test="${not empty results}">
    <!-- Pager -->
    <ul class="pager">
        <li class="previous ${offset + limit >= count ? 'disabled':''}">
            <a onclick="${disabledOlder}" href="${previous}">&larr; <spring:message code="label.search.previous"/></a>
        </li>
        <li class="next ${offset - limit < 0 ? 'disabled':''}">
            <a onclick="${disabledNewer}" href="${next}"><spring:message code="label.search.next"/> &rarr;</a>
        </li>
    </ul>
</c:if>
