<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:forEach items="${students}" varStatus="status" step="3">
    <div class="row">
        <c:set var="student" value="${students.get(status.index)}"/>
        <div class="col-md-4">
            <div class="col-md-4">
                <a>
                    <img class="img-responsive img-hover" width="60" height="80" src="http://fpmi.bsu.by/sm_full.aspx?guid=25893" alt="">
                </a>
            </div>
            <div class="col-md-8">
                <h3>${student.user.name}</h3>
                <h4>Course: ${student.course} Group: ${student.group}</h4>

                    <%--<c:if test="${not empty student.user.phone}">--%>
                <p><i class="fa fa-phone"></i> +375292921166</p>
                    <%--</c:if>--%>
                    <%--<c:if test="${not empty student.user.email}">--%>
                <p><i class="fa fa-envelope-o"></i><a href="mailto:gleb.exadel@mail.com"> gleb.exadel@mail.com</a></p>
                    <%--</c:if>--%>
                <a class="btn btn-primary" href="<c:url value="${applicationPath}/student/${student.user.login}"/>"><spring:message
                        code="peoples.button.viewDetails"/></a>
            </div>
        </div>
        <hr class="visible-sm">
        <c:set var="student" value="${students.get(status.index + 1)}"/>
        <div class="col-md-4">
            <div class="col-md-4">
                <a>
                    <img class="img-responsive img-hover" width="60" height="80" src="http://fpmi.bsu.by/sm_full.aspx?guid=25893" alt="">
                </a>
            </div>
            <div class="col-md-8">
                <h3>${student.user.name}</h3>
                <h4>Course: ${student.course} Group: ${student.group}</h4>

                    <%--<c:if test="${not empty student.user.phone}">--%>
                <p><i class="fa fa-phone"></i> +375292921166</p>
                    <%--</c:if>--%>
                    <%--<c:if test="${not empty student.user.email}">--%>
                <p><i class="fa fa-envelope-o"></i><a href="mailto:gleb.exadel@mail.com"> gleb.exadel@mail.com</a></p>
                    <%--</c:if>--%>
                <a class="btn btn-primary" href="<c:url value="${applicationPath}/student/${student.user.login}"/>"><spring:message
                        code="peoples.button.viewDetails"/></a>
            </div>
        </div>
        <hr class="visible-sm">
        <c:set var="student" value="${students.get(status.index + 2)}"/>
        <div class="col-md-4">
            <div class="col-md-4">
                <a>
                    <img class="img-responsive img-hover" width="60" height="80" src="http://fpmi.bsu.by/sm_full.aspx?guid=25893" alt="">
                </a>
            </div>
            <div class="col-md-8">
                <h3>${student.user.name}</h3>
                <h4>Course: ${student.course} Group: ${student.group}</h4>

                    <%--<c:if test="${not empty student.user.phone}">--%>
                <p><i class="fa fa-phone"></i> +375292921166</p>
                    <%--</c:if>--%>
                    <%--<c:if test="${not empty student.user.email}">--%>
                <p><i class="fa fa-envelope-o"></i><a href="mailto:gleb.exadel@mail.com"> gleb.exadel@mail.com</a></p>
                    <%--</c:if>--%>
                <a class="btn btn-primary" href="<c:url value="${applicationPath}/student/${student.user.login}"/>"><spring:message
                        code="peoples.button.viewDetails"/></a>
            </div>
        </div>
    </div>
    <c:if test="${not status.last}">
        <hr>
    </c:if>
</c:forEach>

<%--</c:forEach>--%>


