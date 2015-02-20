<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:set var="pageTitle"><tiles:insertAttribute name="title"/></c:set>
<c:set var="subtitle"><tiles:insertAttribute name="subtitle"/></c:set>

<!-- Page Heading/Breadcrumbs -->

<div class="row">
    <div class="col-lg-12">

        <h1 class="page-header">
            <c:choose>
                <c:when test="${not empty title}">
                    ${title}
                </c:when>
                <c:otherwise>
                    <c:if test="${not empty pageTitle}">
                        <spring:message code="${pageTitle}"/>
                        <small><spring:message code="${subtitle}"/></small>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </h1>

        <ol class="breadcrumb">
            <li><a href="${applicationPath}/"><spring:message code="label.nav.homepage"/></a>
            </li>
            <c:choose>
                <c:when test="${not empty pageTitle}">
                    <li class="active"><spring:message code="${pageTitle}"/></li>
                </c:when>
                <c:otherwise>
                    <li class="active">${title}</li>
                </c:otherwise>
            </c:choose>
        </ol>
    </div>
</div>
<!-- /.row -->


