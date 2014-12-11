<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    Language: <a href="?lang=en"><img src="${applicationPath}/resources/image/language/usa.png"/></a> |
    <a href="?lang=ru"><img src="${applicationPath}/resources/image/language/russian.png"/></a> |
    <a href="?lang=zh"><img src="${applicationPath}/resources/image/language/chinese.png"/></a>
</div>
<spring:message code="label.header"/>

