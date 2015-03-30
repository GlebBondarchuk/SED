<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script src="${applicationPath}/resources/js/sed-wysihtml.js"></script>
<div class="row">
    <div class="col-lg-12">
        <h4>
            <input id="contentName" type="text" class="form-control" placeholder="Enter Tab Name..." value="${content.name}">
        </h4>
        <textarea id="htmlContent" style="resize: vertical" rows="20" class='html-editable form-control'
                  title="" placeholder="Enter Text Here...">
            <c:if test="${not empty content}">
                ${content.html}
            </c:if>
        </textarea>
    </div>
</div>
<hr>
<div class="row">
    <div class="col-lg-12">
        <c:choose>
            <c:when test="${empty content.id}">
                <button onclick="addTab();" class="btn btn-danger btn-primary" type="button"><span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;<spring:message code="people.button.save"/></button>
            </c:when>
            <c:otherwise>
                <button onclick="editTab();" class="btn btn-danger btn-primary" type="button"><span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;<spring:message code="people.button.saveChanges"/></button>
            </c:otherwise>
        </c:choose>
        <a class="btn btn-danger btn-primary" href="<c:url value="/people/${login}/edit"/>" role="button"><span class="glyphicon glyphicon-remove-circle"></span>&nbsp;&nbsp;<spring:message code="people.button.cancel"/></a>
    </div>
</div>
<script>
    function addTab() {
        $.ajax({
            type: "POST",
            url: "<c:url value="/people/${login}/addTab"/>",
            data: {contentName: $("#contentName").val(), content: $("#htmlContent").val()}
        }).done(function () {
            window.location.href = "<c:url value="/people/${login}/edit"/>";
        })
    }

    function editTab() {
        $.ajax({
            type: "POST",
            url: "<c:url value="/people/${login}/edit/tab/${content.id}"/>",
            data: {contentName: $("#contentName").val(), content: $("#htmlContent").val()}
        }).done(function () {
            window.location.href = "<c:url value="/people/${login}/edit"/>";
        })
    }
</script>