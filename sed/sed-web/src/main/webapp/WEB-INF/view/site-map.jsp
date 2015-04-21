<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="jumbotron">
    <ul id="accordion">
        <c:forEach var="parent" items="${parents}">
            <security:authorize access="${parent.authorizedOnly ? 'isAuthenticated()' : 'permitAll()'}">
                <li>
                    <c:choose>
                        <c:when test="${parent.children.size() == 0}">
                            <a href="${applicationPath}${parent.url}" target="_blank">${parent.text}</a>
                            <security:authorize access="hasRole('ADMIN')">
                                <a class="text-danger" href="${applicationPath}/map/edit/${parent.id}"><spring:message code="label.siteMap.edit"/></a>
                                <a class="text-danger" href="${applicationPath}/map/delete/${parent.id}"><spring:message code="label.siteMap.delete"/></a>
                            </security:authorize>
                        </c:when>
                        <c:otherwise>
                            ${parent.text}
                            <security:authorize access="hasRole('ADMIN')">
                                <a class="text-danger" href="${applicationPath}/map/edit/${parent.id}"><spring:message code="label.siteMap.edit"/></a>
                                <a class="text-danger" href="${applicationPath}/map/delete/${parent.id}"><spring:message code="label.siteMap.delete"/></a>
                            </security:authorize>
                            <ul id="${parent.id}" class="panel-collapse">
                                <c:forEach var="child" items="${parent.children}">
                                    <security:authorize access="${child.authorizedOnly ? 'isAuthenticated()' : 'permitAll()'}">
                                        <li>
                                            <c:choose>
                                                <c:when test="${child.children.size() == 0}">
                                                    <a href="${applicationPath}${child.url}" target="_blank">${child.text}</a>
                                                    <security:authorize access="hasRole('ADMIN')">
                                                        <a class="text-danger" href="${applicationPath}/map/edit/${child.id}"><spring:message code="label.siteMap.edit"/></a>
                                                        <a class="text-danger" href="${applicationPath}/map/delete/${child.id}"><spring:message code="label.siteMap.delete"/></a>
                                                    </security:authorize>
                                                </c:when>
                                                <c:otherwise>
                                                    ${child.text}
                                                    <security:authorize access="hasRole('ADMIN')">
                                                        <a class="text-danger" href="${applicationPath}/map/edit/${child.id}"><spring:message code="label.siteMap.edit"/></a>
                                                        <a class="text-danger" href="${applicationPath}/map/delete/${child.id}"><spring:message code="label.siteMap.delete"/></a>
                                                    </security:authorize>
                                                    <ul id="${child.id}" class="panel-collapse">
                                                        <c:forEach var="child" items="${child.children}">
                                                            <security:authorize access="${child.authorizedOnly ? 'isAuthenticated()' : 'permitAll()'}">
                                                                <li>
                                                                    <c:choose>
                                                                        <c:when test="${child.children.size() == 0}">
                                                                            <a href="${applicationPath}${child.url}" target="_blank">${child.text}</a>
                                                                            <security:authorize access="hasRole('ADMIN')">
                                                                                <a class="text-danger" href="${applicationPath}/map/edit/${child.id}"><spring:message code="label.siteMap.edit"/></a>
                                                                                <a class="text-danger"
                                                                                   href="${applicationPath}/map/delete/${child.id}"><spring:message code="label.siteMap.delete"/></a>
                                                                            </security:authorize>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            ${child.text}
                                                                            <security:authorize access="hasRole('ADMIN')">
                                                                                <a class="text-danger" href="${applicationPath}/map/edit/${child.id}"><spring:message code="label.siteMap.edit"/></a>
                                                                                <a class="text-danger"
                                                                                   href="${applicationPath}/map/delete/${child.id}"><spring:message code="label.siteMap.delete"/></a>
                                                                            </security:authorize>
                                                                            <ul id="${child.id}" class="panel-collapse">
                                                                                <c:forEach var="child" items="${child.children}">
                                                                                    <security:authorize
                                                                                            access="${child.authorizedOnly ? 'isAuthenticated()' : 'permitAll()'}">
                                                                                        <li>
                                                                                            <a href="${applicationPath}${child.url}"
                                                                                               target="_blank">${child.text}</a>
                                                                                            <security:authorize access="hasRole('ADMIN')">
                                                                                                <a class="text-danger"
                                                                                                   href="${applicationPath}/map/edit/${child.id}"><spring:message code="label.siteMap.edit"/></a>
                                                                                                <a class="text-danger"
                                                                                                   href="${applicationPath}/map/delete/${child.id}"><spring:message code="label.siteMap.delete"/></a>
                                                                                            </security:authorize>
                                                                                        </li>
                                                                                    </security:authorize>
                                                                                </c:forEach>
                                                                            </ul>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                            </security:authorize>
                                                        </c:forEach>
                                                    </ul>
                                                </c:otherwise>
                                            </c:choose>
                                        </li>
                                    </security:authorize>
                                </c:forEach>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </li>
            </security:authorize>
        </c:forEach>
    </ul>
</div>
<security:authorize access="hasRole('ADMIN')">
    <a class="btn btn-danger btn-primary" href="${applicationPath}/map/add" role="button">
        <span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<spring:message code="label.siteMap.addNew"/>
    </a>
</security:authorize>