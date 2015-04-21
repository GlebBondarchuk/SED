<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script src="${applicationPath}/resources/js/bootstrap/validator/validator.js"></script>

<c:choose>
    <c:when test="${edit}">
        <c:set var="action" value="/admin/news-url/edit"/>
    </c:when>
    <c:otherwise>
        <c:set var="action" value="/admin/news-url/edit"/>
    </c:otherwise>
</c:choose>

<div class="row">
    <form class="form-horizontal col-lg-12" role="form" action="${applicationPath}${action}" method="post" data-toggle="validator">
        <input id="id" type="hidden" name="id" class="form-control" value="${url.id}"/>
        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.newsUrl.url"/></label>

            <div class="col-sm-10">
                <input id="text" type="text" name="url" class="form-control" value="${url.url}" required/>
                <span class="help-block with-errors hidden"></span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.newsUrl.searchWords"/></label>

            <div class="col-sm-10">
                <textarea maxlength="500" style="resize: vertical;" rows="4" class="form-control" name="searchWords" required>${url.searchWords}</textarea>
                <span class="help-block with-errors hidden"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.newsUrl.disabled"/></label>

            <div class="col-sm-10">
                <input  name="disabled" type="checkbox" ${url.disabled ? 'checked' : ''}/>
                <span class="help-block with-errors hidden"></span>
            </div>
        </div>

        <button class="btn btn-danger btn-primary pull-right" type="submit" name="Save">
            <span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;
            <spring:message code="button.save"/>
        </button>
    </form>
</div>