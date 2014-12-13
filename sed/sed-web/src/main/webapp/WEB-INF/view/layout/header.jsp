<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<security:authorize access="isAnonymous()">
    <a href="${applicationPath}/login">Sign In</a>
</security:authorize>
<security:authorize access="isAuthenticated()">
    Welcome, <security:authentication property="principal.username"/>
    <a href="${applicationPath}/j_spring_security_logout">Sign Out</a>
</security:authorize>
<div>
    Language: <a href="?lang=en"><img src="${applicationPath}/resources/image/language/usa.png"/></a> |
    <a href="?lang=ru"><img src="${applicationPath}/resources/image/language/russian.png"/></a> |
    <a href="?lang=zh"><img src="${applicationPath}/resources/image/language/chinese.png"/></a>
</div>
<div>
    <h2><spring:message code="label.title"/></h2>
</div>


