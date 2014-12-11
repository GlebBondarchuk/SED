<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    Language: <a href="${applicationPath}/login/?lang=en"><img src="${applicationPath}/resources/image/language/usa.png"/></a> |
    <a href="${applicationPath}/login/?lang=ru"><img src="${applicationPath}/resources/image/language/russia.png"/></a> |
    <a href="${applicationPath}/login/?lang=zh"><img src="${applicationPath}/resources/image/language/china.png"/></a>
</div>
<spring:message code="label.header"/>

