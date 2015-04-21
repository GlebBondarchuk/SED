<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
    <c:forEach var="list" items="${categories}">
        <div class="col-lg-6">
            <ul class="list-unstyled">
                <c:forEach var="value" items="${list}">
                    <li>
                        <a href="<c:url value="/news/${limit}/0?category=${value}"/>">
                            <c:choose>
                                <c:when test="${value == category}">
                                    <strong>${value}</strong>
                                </c:when>
                                <c:otherwise>
                                    ${value}
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>
    <div class="col-lg-6">
        <a href="<c:url value="/news/"/>"><spring:message code="label.news.allCategories"/></a>
    </div>
</div>