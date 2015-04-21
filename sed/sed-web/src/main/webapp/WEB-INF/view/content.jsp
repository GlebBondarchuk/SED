<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="${applicationPath}/resources/js/sed-wysihtml.js"></script>

<div class="row">
    <div class="col-lg-12">
        <c:choose>
            <c:when test="${edit}">
                <h4>
                    <input id="contentName" type="text" class="form-control" placeholder="Enter Content Name..." value="${content.name}" required>
                    <span class="help-block with-errors"></span>
                </h4>
                <select class="form-control" id="contentRole" placeholder="Choose Authority" title="Choose Authority">
                    <c:forEach var="role" items="${roles}">
                        <c:choose>
                            <c:when test="${role == null}">
                                <c:choose>
                                    <c:when test="${role == content.role}">
                                        <option selected value="${role}">ALL</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${role}">ALL</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:when test="${content.role == role}">
                                <c:choose>
                                    <c:when test="${empty role}">
                                        <option selected value="${role}">ALL</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option selected>${role}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <option>${role}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <hr>
                <textarea maxlength="16777215" id="htmlContent" style="resize: vertical" rows="30" class='html-editable form-control'
                          title="" placeholder="Enter Text Here...">
                    <c:if test="${not empty content}">
                        ${content.html}
                    </c:if>
                </textarea>
            </c:when>
            <c:otherwise>
                ${content.html}
                <i class="wysiwyg-color-gray pull-right"><spring:message code="label.people.contentUpdated"/>: ${content.updateDate}</i>
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
                       href="<c:url value="/content/${content.id}/edit"/>" role="button">
                        <span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;<spring:message code="people.button.editPage"/>
                    </a>
                </c:when>
                <c:otherwise>
                    <button onclick="editContent();" class="btn btn-danger btn-primary" type="button">
                        <span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;<spring:message code="people.button.saveChanges"/>
                    </button>
                    <a onclick="preview()" class="btn btn-danger btn-primary" role="button">
                        <span class="glyphicon glyphicon-floppy-disk"></span>&nbsp;&nbsp;<spring:message code="label.content.preview"/>
                    </a>
                </c:otherwise>
            </c:choose>
            <c:if test="${not content.isStatic()}">
                <a class="btn btn-danger btn-primary"
                   href="<c:url value="/content/${content.id}/delete"/>" role="button">
                    <span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;<spring:message code="people.button.deleteTab"/>
                </a>
            </c:if>
        </div>
    </div>
    <script>
        function editContent() {
            $.ajax({
                type: "POST",
                url: "<c:url value="/content/${content.id}/edit"/>",
                data: {
                    contentName: $("#contentName").val(),
                    content: $("#htmlContent").val(),
                    role : $("#contentRole").val()
                }
            }).done(function () {
                window.location.href = "<c:url value="/content/${content.id}"/>";
            })
        }

        function preview() {
            bootbox.dialog({
                size: 'large',
                title: "<div class=row><div class=col-lg-12>" + $("#contentName").val() + "</div></div>",
                message: $("#htmlContent").val() + "&nbsp;"
            });
        }
    </script>
</security:authorize>