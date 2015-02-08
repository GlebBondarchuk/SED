<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:set var="title"><tiles:insertAttribute name="title"/></c:set>
<c:set var="subtitle"><tiles:insertAttribute name="subtitle"/></c:set>

<!-- Page Heading/Breadcrumbs -->

<div class="row">
    <div class="col-lg-12">

        <h1 class="page-header">
            <c:if test="${not empty title}">
                <spring:message code="${title}"/>
                <small><spring:message code="${subtitle}"/></small>
            </c:if>
        </h1>

        <ol class="breadcrumb">
            <li><a href="${applicationPath}/"><spring:message code="label.nav.homepage"/></a>
            </li>
            <c:if test="${not empty title}">
                <li class="active"><spring:message code="${title}"/></li>
            </c:if>
        </ol>
    </div>
</div>
<!-- /.row -->


