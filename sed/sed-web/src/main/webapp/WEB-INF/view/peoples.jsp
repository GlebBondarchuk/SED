<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:forEach var="people" items="${peoples}" varStatus="status">
    <div class="row">
        <div class="col-md-2">
            <a>
                <c:choose>
                    <c:when test="${not empty people.user.photo}">
                        <img class="img-responsive img-hover" src="${people.user.photo}" alt="">
                    </c:when>
                    <c:otherwise>
                        <img class="img-responsive img-hover" height="200" width="150" src="http://placehold.it/300x400" alt="">
                    </c:otherwise>
                </c:choose>
            </a>
        </div>
        <div class="col-md-10">
            <h3>${people.user.name}</h3>
            <h4>${people.position}</h4>

            <c:if test="${not empty people.user.phone}">
                <p><i class="fa fa-phone"></i> ${people.user.phone}</p>
            </c:if>
            <c:if test="${not empty people.user.email}">
                <p><i class="fa fa-envelope-o"></i><a href="mailto:${people.user.email}"> ${people.user.email}</a></p>
            </c:if>
            <a class="btn btn-primary" href="<c:url value="/people/${people.user.login}"/>"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;<spring:message code="peoples.button.viewDetails"/></a>
        </div>
    </div>
    <c:if test="${not status.last}">
        <hr>
    </c:if>
</c:forEach>


