<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script src="${applicationPath}/resources/js/sed-wysihtml.js"></script>

<div class="row">
    <div class="col-lg-12">
        <h4>
            <input id="contentName" type="text" class="form-control" placeholder="Enter Content Name..." value="${news.content.name}">
            <br>
            <input id="photo" type="url" class="form-control" placeholder="Enter Photo Url..." value="${news.photo}">
            <br>
            <textarea id="simpleText" style="resize: none" rows="5" class='form-control' title="" placeholder="Enter Simple Text Here..."><c:if test="${not empty news}">${news.simpleText}</c:if></textarea>
        </h4>
        <textarea id="htmlContent" maxlength="16777215" style="resize: vertical" rows="30" class='html-editable form-control' title="" placeholder="Enter Text Here..."><c:if test="${not empty news.content.html}">${news.content.html}</c:if></textarea>
    </div>
</div>

<security:authorize access="hasAnyRole('ADMIN','TEACHER')">
    <hr>
    <div class="row">
        <div class="col-lg-12">
            <c:choose>
                <c:when test="${edit}">
                    <button onclick="updateNews();" class="btn btn-danger btn-primary" type="button">
                        <span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;<spring:message code="people.button.saveChanges"/>
                    </button>
                </c:when>
                <c:otherwise>
                    <button onclick="saveNews();" class="btn btn-danger btn-primary" type="button">
                        <span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;<spring:message code="people.button.save"/>
                    </button>
                    <a onclick="preview()" class="btn btn-danger btn-primary" role="button">
                        <span class="glyphicon glyphicon-floppy-disk"></span>&nbsp;&nbsp;Preview
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <script>
        function saveNews() {
            $.ajax({
                type: "POST",
                url: "<c:url value="/news/add"/>",
                data: {
                    contentName: $("#contentName").val(),
                    content: $("#htmlContent").val(),
                    photo: $("#photo").val(),
                    simpleText: $("#simpleText").val()
                }
            }).done(function () {
                window.location.href = "<c:url value="/news"/>";
            })
        }

        function updateNews() {
            $.ajax({
                type: "POST",
                url: "<c:url value="/news/${news.id}/edit"/>",
                data: {
                    contentName: $("#contentName").val(),
                    content: $("#htmlContent").val(),
                    photo: $("#photo").val(),
                    simpleText: $("#simpleText").val()
                }
            }).done(function () {
                window.location.href = "<c:url value="/news/${news.id}"/>";
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