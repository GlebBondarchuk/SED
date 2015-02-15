<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:forEach var="people" items="${peoples}" varStatus="status">
    <div class="row">
        <div class="col-md-2">
            <a>
                <img class="img-responsive img-hover" src="${people.user.photo}" alt="">
            </a>
        </div>
        <div class="col-md-10">
            <h3>${people.user.name}</h3>
            <h4>${people.position}</h4>

            <c:if test="${not empty people.user.phone}">
                <p><i class="fa fa-phone"></i> ${people.user.phone}</p>
            </c:if>
            <c:if test="${not empty people.user.login}">
                <p><i class="fa fa-envelope-o"></i><a href="mailto:${people.user.email}"> ${people.user.email}</a></p>
            </c:if>
            <a class="btn btn-primary" href="<c:url value="${applicationPath}/people/${people.user.login}"/>"><spring:message code="peoples.button.viewDetails"/></a>
        </div>
    </div>
    <c:if test="${not status.last}">
        <hr>
    </c:if>
</c:forEach>


