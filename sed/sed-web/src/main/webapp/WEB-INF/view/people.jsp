<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sed" uri="/tld/sed_library" %>

<script src="${applicationPath}/resources/js/bootstrap/validator/validator.js"></script>

<c:if test="${not edit}">
    <div class="row">
        <div class="col-md-2">
            <c:choose>
                <c:when test="${not empty people.user.photo}">
                    <img class="img-responsive img-hover" height="200" width="150" src="${people.user.photo}" alt="">
                </c:when>
                <c:otherwise>
                    <img class="img-responsive img-hover" height="200" width="150" src="http://placehold.it/300x400" alt="">
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-10">
            <h3>${people.user.name}</h3>
            <h4>${people.position}</h4>
            <c:if test="${not empty people.user.phone}">
                <p><i class="fa fa-phone"></i> ${people.user.phone}</p>
            </c:if>
            <c:if test="${not empty people.user.email}">
                <p><i class="fa fa-envelope-o"></i><a href="mailto:${people.user.email}"> ${people.user.email}</a></p>
            </c:if>
            <c:if test="${not empty people.address}">
                <p><i class="fa fa-home"></i> ${people.address}</p>
            </c:if>
            <c:if test="${people.head}">
                <p><i class="fa fa-header"></i> <span class="alert-success">Head of the department</span></p>
            </c:if>
        </div>
    </div>
</c:if>

<security:authorize access="hasRole('ADMIN')">
    <c:if test="${edit}">
        <div class="row">
            <div class="col-md-2">
                <c:choose>
                    <c:when test="${not empty people.user.photo}">
                        <img class="img-responsive img-hover" height="200" width="150" src="${people.user.photo}" alt="">
                    </c:when>
                    <c:otherwise>
                        <img class="img-responsive img-hover" height="200" width="150" src="http://placehold.it/300x400" alt="">
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-md-10">
                <form class="form-horizontal" role="form"  data-toggle="validator" action="<c:url value="/people/${people.user.login}/edit"/>"
                      method="post">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                <input type="text" class="form-control" name="name" placeholder="Name" value="${people.user.name}" required>
                            </div>
                            <span class="help-block with-errors hidden"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-pencil"></span></span>
                                <input type="text" class="form-control" name="position" placeholder="Position" value="${people.position}" required>
                            </div>
                        </div>
                        <span class="help-block with-errors hidden"></span>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></span>
                                <input type="text" class="form-control" name="phone" placeholder="Phone" value="${people.user.phone}" required>
                            </div>
                            <span class="help-block with-errors hidden"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
                                <input type="email" class="form-control" name="login" placeholder="Email" value="${people.user.email}" required>
                            </div>
                            <span class="help-block with-errors hidden"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>
                                <input type="text" class="form-control" name="address" placeholder="Address" value="${people.address}" required>
                            </div>
                            <span class="help-block with-errors hidden"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-picture"></span></span>
                                <input type="url" class="form-control" name="photo" placeholder="Photo Link" value="${people.user.photo}" required>
                            </div>
                            <span class="help-block with-errors hidden"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-header"></span></span>
                                <select  name="head" class="form-control" title="Head of the department">
                                    <option class="alert-success" ${people.head ? 'selected':''} value="true">true</option>
                                    <option class="alert-danger" ${people.head ? '':'selected'} value="false">false</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <button type="submit" name="submit" id="submit" class="btn btn-danger pull-left">
                        <span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;<spring:message code="people.button.saveChanges"/>
                    </button>
                </form>
            </div>
        </div>
    </c:if>
</security:authorize>

<hr>
<div class="row">
    <div class="col-lg-12">
        <ul id="myTab" class="nav nav-tabs nav-justified">
            <c:forEach var="content" items="${people.contents}" varStatus="status">
                <c:choose>
                    <c:when test="${status.first}">
                        <li class="active">
                            <a href="#${content.id}" data-toggle="tab">
                                <strong><i class="fa fa-support"></i> ${content.name}</strong>
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="#${content.id}" data-toggle="tab">
                                <strong><i class="fa fa-support"></i> ${content.name}</strong>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>

        <div id="myTabContent" class="tab-content">
            <c:forEach var="content" items="${people.contents}" varStatus="status">
                <c:choose>
                    <c:when test="${status.first}">
                        <div class="tab-pane fade active in" id="${content.id}">
                            <br>
                                ${content.html}
                            <i class="wysiwyg-color-gray pull-right">Updated: ${content.updateDate}</i>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="tab-pane fade in" id="${content.id}">
                            <br>
                                ${content.html}
                            <i class="wysiwyg-color-gray pull-right">Updated: ${content.updateDate}</i>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>
</div>

<security:authorize access="hasRole('ADMIN')">
    <hr>
    <div class="row">
        <div class="col-lg-12">
            <c:if test="${not edit}">
                <a class="btn btn-danger btn-primary"
                   href="<c:url value="/people/${people.user.login}/edit"/>" role="button">
                    <span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;<spring:message code="people.button.editPage"/>
                </a>
            </c:if>
            <c:if test="${edit}">
                <div>
                    <c:if test="${not empty people.contents}">
                        <a class="btn btn-danger btn-primary" onclick="editTab();" role="button"><span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;<spring:message code="people.button.editTab"/></a>
                        <a class="btn btn-danger btn-primary" onclick="deleteTab();" role="button"><span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;<spring:message code="people.button.deleteTab"/></a>
                    </c:if>
                    <a class="btn btn-danger btn-primary" onclick="addTab();" role="button"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;<spring:message code="people.button.addTab"/></a>
                </div>
            </c:if>
        </div>
    </div>

    <script>
        function editTab() {
            var activeContentId = $("#myTabContent").find(".active").attr('id');
            window.location.href = "<c:url value="/people/${people.user.login}/edit/tab/"/>" + activeContentId;
        }

        function deleteTab() {
            bootbox.setDefaults({locale: '${sed:getLangLowerCase()}'});
            bootbox.confirm("<spring:message code="dialog.sure"/>", function (confirmed) {
                if (confirmed) {
                    var activeContentId = $("#myTabContent").find(".active").attr('id');
                    window.location.href = "<c:url value="/people/${people.user.login}/edit/tab/delete/"/>" + activeContentId;
                }
            });
        }

        function addTab() {
            window.location.href = "<c:url value="/people/${people.user.login}/addTab"/>";
        }
    </script>
</security:authorize>


