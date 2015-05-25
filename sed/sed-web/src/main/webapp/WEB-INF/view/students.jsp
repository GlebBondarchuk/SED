<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:forEach var="student" items="${students}" varStatus="status">
    <div class="row">
        <div class="col-md-12">
            <div class="col-md-1">
                <a>
                    <img class="img-responsive img-hover" width="60" height="80" src="${student.user.photo}" alt="">
                </a>
            </div>
            <div class="col-md-11">
                <div class="col-md-3">
                    <h4>${student.user.name}</h4>
                    <h5><spring:message code="label.students.course"/>: ${student.course}</h5>
                    <h5><spring:message code="label.students.group"/>: ${student.group}</h5>
                </div>
                <div class="col-md-3">
                    <c:if test="${not empty student.user.phone}">
                        <h5><i class="fa fa-phone"></i> ${student.user.phone}</h5>
                    </c:if>
                    <c:if test="${not empty student.user.email}">
                        <h5><i class="fa fa-envelope-o"></i><a href="mailto:${student.user.email}"> ${student.user.email}</a></h5>
                    </c:if>
                </div>
                <security:authorize access="hasAnyRole('ADMIN','TEACHER')">
                    <a href="<c:url value="/student/${student.user.login}/edit"/>">
                        <span class="glyphicon glyphicon-edit"></span>
                    </a>
                </security:authorize>
            </div>
        </div>
    </div>
    <c:if test="${not status.last}">
        <hr>
    </c:if>
</c:forEach>
<%--</c:forEach>--%>


