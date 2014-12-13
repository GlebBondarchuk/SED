<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="applicationPath" value="${pageContext.request.contextPath}" scope="application"/>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%--css styles--%>
    <link rel="stylesheet" href="${applicationPath}/resources/css/main.css" />

    <%--page icon--%>
    <link rel="shortcut icon" href="${applicationPath}/resources/image/main/favicon.png" type="image/x-icon">

    <%--page title--%>
    <c:set var="title"><tiles:insertAttribute name="title"/></c:set>
    <title><spring:message code="${title}"/></title>

</head>
<body>
<tiles:insertAttribute name="header-content"/>
<tiles:insertAttribute name="menu-content"/>
<div class="page-content">
    <tiles:insertAttribute name="primary-content"/>
</div>
<tiles:insertAttribute name="footer-content"/>
</body>
</html>
