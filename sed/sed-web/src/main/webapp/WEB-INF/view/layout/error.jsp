<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-lg-12">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
            ${exception}

        </div>
        <c:if test="${not empty referer}">
            <a class="btn btn-primary" href="${referer}">&larr;&nbsp;&nbsp;Back</a>
        </c:if>
    </div>
</div>