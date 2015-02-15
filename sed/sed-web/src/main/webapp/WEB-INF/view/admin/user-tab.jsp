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
                <input onclick="addTab();" class="btn btn-danger btn-primary" type="button" value="<spring:message code="people.button.save"/>"/>
            </c:when>
            <c:otherwise>
                <input onclick="editTab();" class="btn btn-danger btn-primary" type="button" value="<spring:message code="people.button.saveChanges"/>"/>
            </c:otherwise>
        </c:choose>
        <a class="btn btn-danger btn-primary" href="<c:url value="${applicationPath}/people/${login}/edit"/>" role="button"><spring:message code="people.button.cancel"/></a>
    </div>
</div>
<script>
    function addTab() {
        $.ajax({
            type: "POST",
            url: "<c:url value="${applicationPath}/people/${login}/addTab"/>",
            data: {contentName: $("#contentName").val(), content: $("#htmlContent").val()}
        }).done(function () {
            window.location.href = "<c:url value="${applicationPath}/people/${login}/edit"/>";
        })
    }

    function editTab() {
        $.ajax({
            type: "POST",
            url: "<c:url value="${applicationPath}/people/${login}/edit/tab/${content.id}"/>",
            data: {contentName: $("#contentName").val(), content: $("#htmlContent").val()}
        }).done(function () {
            window.location.href = "<c:url value="${applicationPath}/people/${login}/edit"/>";
        })
    }
</script>