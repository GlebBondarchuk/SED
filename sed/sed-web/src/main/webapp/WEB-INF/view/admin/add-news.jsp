<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script src="${applicationPath}/resources/js/sed-wysihtml.js"></script>

<div class="row">
    <div class="col-lg-12">
        <h4>
            <input id="contentName" type="text" class="form-control" placeholder="Enter Content Name..."
                   value="${content.name}">
            <br>
            <input id="photo" type="url" class="form-control" placeholder="Enter Photo Url..."
                   value="${photo.name}">
            <br>
            <textarea id="simpleText" style="resize: none" rows="5" class='form-control' title="" placeholder="Enter Simple Text Here..."><c:if test="${not empty news}">
                ${news.simpleText}
            </c:if></textarea>
        </h4>
                <textarea id="htmlContent" style="resize: vertical" rows="30" class='html-editable form-control'
                          title="" placeholder="Enter Text Here...">
                    <c:if test="${not empty content}">
                        ${content.html}
                    </c:if>
                </textarea>
    </div>
</div>

<security:authorize access="hasRole('ADMIN')">
    <hr>
    <div class="row">
        <div class="col-lg-12">
            <button onclick="editContent();" class="btn btn-danger btn-primary" type="button">
                <span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;<spring:message code="people.button.saveChanges"/>
            </button>
        </div>
    </div>
    <script>
        function editContent() {
            $.ajax({
                type: "POST",
                url: "<c:url value="${applicationPath}/news/add/"/>",
                data: {
                    contentName: $("#contentName").val(),
                    content: $("#htmlContent").val(),
                    photo: $("#photo").val(),
                    simpleText: $("#simpleText").val()
                }
            }).done(function () {
                window.location.href = "<c:url value="${applicationPath}/news"/>";
            })
        }
    </script>
</security:authorize>