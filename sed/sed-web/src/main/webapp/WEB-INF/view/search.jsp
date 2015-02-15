<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-lg-8 col-lg-offset-2">
        <form action="${applicationPath}/search/do" method="get" name="searchForm">
            <div class="input-group">
                <input type="text" class="form-control" name="query" placeholder="Search..." value="${param.query}">
                <span id="mask" class="input-group-addon" onclick="document.searchForm.submit();"><span
                        class="glyphicon glyphicon-search"></span></span>
            </div>
        </form>
    </div>
</div>

<c:if test="${not empty param.query}">
    <hr>
    <div class="row">
        <div class="col-lg-12">
            <c:choose>
                <c:when test="${not empty results}">
                    <c:forEach var="result" items="${results}">
                        <div class="text-center alert alert-info">
                            <img height="40" width="30" src="${result.photo}"/> <a href="${applicationPath}${result.url}">${result.title}</a>
                        </div>
                        <hr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="text-center alert-warning alert">
                        Nothing found
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</c:if>