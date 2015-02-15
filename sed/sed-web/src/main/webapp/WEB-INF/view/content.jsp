<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script src="${applicationPath}/resources/js/sed-wysihtml.js"></script>

<div class="row">
    <div class="col-lg-12">
        <c:choose>
            <c:when test="${edit}">
                <h4>
                    <input id="contentName" type="text" class="form-control" placeholder="Enter Content Name..."
                           value="${content.name}">
                </h4>
                <textarea id="htmlContent" style="resize: vertical" rows="30" class='html-editable form-control'
                          title="" placeholder="Enter Text Here...">
                    <c:if test="${not empty content}">
                        ${content.html}
                    </c:if>
                </textarea>
            </c:when>
            <c:otherwise>
                ${content.html}
                <i class="wysiwyg-color-gray pull-right">Updated: ${content.updateDate}</i>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<security:authorize access="hasRole('ADMIN')">
    <hr>
    <div class="row">
        <div class="col-lg-12">
            <c:choose>
                <c:when test="${not edit}">
                    <a class="btn btn-danger btn-primary"
                       href="<c:url value="${applicationPath}/content/${content.id}/edit"/>" role="button">
                        <span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;<spring:message code="people.button.editPage"/>
                    </a>
                </c:when>
                <c:otherwise>
                    <button onclick="editContent();" class="btn btn-danger btn-primary" type="button">
                        <span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;<spring:message code="people.button.saveChanges"/></button>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <script>
        function editContent() {
            $.ajax({
                type: "POST",
                url: "<c:url value="${applicationPath}/content/${content.id}/edit"/>",
                data: {contentName: $("#contentName").val(), content: $("#htmlContent").val()}
            }).done(function () {
                window.location.href = "<c:url value="${applicationPath}/content/${content.id}"/>";
            })
        }
    </script>
</security:authorize>