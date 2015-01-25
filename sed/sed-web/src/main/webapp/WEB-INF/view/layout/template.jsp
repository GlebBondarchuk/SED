<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="applicationPath" value="${pageContext.request.contextPath}" scope="application"/>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><spring:message code="label.title"/></title>

    <%--Page Icon--%>
    <link rel="shortcut icon" href="${applicationPath}/resources/image/main/favicon.png" type="image/x-icon">

    <!-- Bootstrap Core CSS -->
    <link href="${applicationPath}/resources/css/bootstrap/core/bootstrap.css" rel="stylesheet">
    <%--<link href="${applicationPath}/resources/css/bootstrap/core/bootstrap-theme.css" rel="stylesheet">--%>

    <!-- Custom Fonts -->
    <link href="${applicationPath}/resources/css/awesome/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- Custom CSS -->
    <link href="${applicationPath}/resources/css/bootstrap/formhelpers/language/bootstrap-formhelpers-countries.flags.css" rel="stylesheet">
    <link href="${applicationPath}/resources/css/modern-business.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="${applicationPath}/resources/js/dropBanner.js"></script>

    <%--&lt;%&ndash;page title&ndash;%&gt;--%>
    <%--<c:set var="title"><tiles:insertAttribute name="title"/></c:set>--%>
    <%--<title><spring:message code="${title}"/></title>--%>
</head>
<body onload="dropBanner()">
<%--Navigation--%>
<tiles:insertAttribute name="navigation-content"/>

<!-- Page Content -->
<div class="container">
    <tiles:insertAttribute name="primary-content"/>
    <hr>
    <footer>
        <tiles:insertAttribute name="footer-content"/>
    </footer>
</div>

<!-- jQuery -->
<script src="${applicationPath}/resources/js/jquery/jquery-1.11.2.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${applicationPath}/resources/js/bootstrap/core/bootstrap.min.js"></script>

</body>
</html>
