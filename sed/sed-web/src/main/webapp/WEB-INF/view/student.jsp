<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sed" uri="/tld/sed_library" %>

<script src="${applicationPath}/resources/js/bootstrap/validator/validator.js"></script>

<c:if test="${not edit}">
    <div class="row">
        <div class="col-md-2">
            <c:choose>
                <c:when test="${not empty student.user.photo}">
                    <img class="img-responsive img-hover" height="200" width="150" src="${student.user.photo}" alt="">
                </c:when>
                <c:otherwise>
                    <img class="img-responsive img-hover" height="200" width="150" src="http://placehold.it/300x400"
                         alt="">
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-10">
            <h3>${student.user.name}</h3>
            <h4><spring:message code="label.students.course"/>: ${student.course}</h4>
            <h4><spring:message code="label.students.group"/>: ${student.group}</h4>
            <c:if test="${not empty student.user.phone}">
                <p><i class="fa fa-phone"></i> ${student.user.phone}</p>
            </c:if>
            <c:if test="${not empty student.user.email}">
                <p><i class="fa fa-envelope-o"></i><a href="mailto:${student.user.email}"> ${student.user.email}</a></p>
            </c:if>
        </div>
    </div>
</c:if>
<security:authentication var="username" property="principal.username"/>
<security:authorize access="hasAnyRole('ADMIN','STUDENT')">
    <c:if test="${not edit and student.user.name == username}">
    <a href="<c:url value="/student/${student.user.login}/edit"/>">
        <span class="glyphicon glyphicon-edit"></span>
    </a>
    </c:if>
    <c:if test="${edit}">
        <div class="row">
            <div class="col-md-2">
                <c:choose>
                    <c:when test="${not empty student.user.photo}">
                        <img class="img-responsive img-hover" height="200" width="150" src="${student.user.photo}"
                             alt="">
                    </c:when>
                    <c:otherwise>
                        <img class="img-responsive img-hover" height="200" width="150" src="http://placehold.it/300x400"
                             alt="">
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-md-10">
                <form class="form-horizontal" role="form" data-toggle="validator"
                      action="<c:url value="/student/${student.user.login}/edit"/>"
                      method="post">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                <input type="text" class="form-control" name="name" placeholder="Name"
                                       value="${student.user.name}" required>
                            </div>
                            <span class="help-block with-errors hidden"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                <input type="text" class="form-control" name="course" placeholder="Course"
                                       value="${student.course}" required>
                            </div>
                            <span class="help-block with-errors hidden"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                <input type="text" class="form-control" name="group" placeholder="Group"
                                       value="${student.group}" required>
                            </div>
                            <span class="help-block with-errors hidden"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></span>
                                <input type="text" class="form-control" name="phone" placeholder="Phone"
                                       value="${student.user.phone}" required>
                            </div>
                            <span class="help-block with-errors hidden"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-envelope"></span></span>
                                <input type="email" class="form-control" name="login" placeholder="Email"
                                       value="${student.user.email}" required>
                            </div>
                            <span class="help-block with-errors hidden"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-picture"></span></span>
                                <input type="url" class="form-control" name="photo" placeholder="Photo Link"
                                       value="${student.user.photo}" required>
                            </div>
                            <span class="help-block with-errors hidden"></span>
                        </div>
                    </div>
                    <c:if test="${student.user.name == username}">
                        <input type="password" style="display:none">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input type="password" class="form-control" id="password" name="password" placeholder="Change Password"
                                       value="">
                            </div>
                        </div>
                    </div>
                    </c:if>
                    <button type="submit" name="submit" id="submit" class="btn btn-danger pull-left">
                        <span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;<spring:message
                            code="people.button.saveChanges"/>
                    </button>
                </form>
            </div>
        </div>
    </c:if>
</security:authorize>