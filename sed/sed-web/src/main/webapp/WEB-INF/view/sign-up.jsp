<%@ page import="com.bsu.sed.model.Role" %>
<%@ page import="com.bsu.sed.model.constraint.ConstraintConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags" %>
<script src="${applicationPath}/resources/js/bootstrap/validator/validator.js"></script>
<script src="${applicationPath}/resources/js/signUp.js"></script>

<div class="row">
    <form id="signUpForm" data-toggle="validator" class="form-horizontal col-lg-12" role="form" action="${applicationPath}${postUrl}" method="post">
        <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>&nbsp;&nbsp;Required Field</strong></div>

        <div class="form-group">
            <label class="col-sm-2 control-label">Role</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <select id="roleSelect" class="form-control" name="role" title="">
                        <security:authorize access="hasAnyRole('ADMIN','TEACHER')">
                            <option><%=Role.TEACHER%>
                            </option>
                        </security:authorize>
                        <option><%=Role.STUDENT%>
                        </option>
                    </select>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">Full Name</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <input maxlength="<%=ConstraintConstants.USER_NAME_MAX_LENGTH%>" type="text" class="form-control" name="name" placeholder="Name" value="${dto.name}" required>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
                <span class="help-block with-errors hidden"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Email</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <input type="text" class="form-control" name="login" placeholder="Email" value="${dto.login}" required pattern="[^@]+"
                           data-error="Domain name ${emailMask} has been already written.">
                    <span class="input-group-addon">${emailMask}</span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
                <span class="help-block with-errors hidden"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Phone</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <input type="text" class="form-control" name="phone" placeholder="Phone" value="${dto.phone}">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Link to Photo</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <input type="url" class="form-control" name="photo" placeholder="Photo Link" value="${dto.photo}" required
                           data-error="Please, enter correct link">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-link"></span></span>
                </div>
                <span class="help-block with-errors hidden"></span>
            </div>
        </div>
        <% try {
        } catch (Exception e) {
        } %><security:authorize access="hasAnyRole('ADMIN','TEACHER')">
            <div class="form-group teacher">
                <label class="col-sm-2 control-label">Position</label>

                <div class="col-sm-10">
                    <div class="input-group">
                        <input type="text" class="form-control" name="position" placeholder="People Position" value="${dto.position}" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-pencil"></span></span>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                    <span class="help-block with-errors hidden"></span>
                </div>
            </div>
            <div class="form-group teacher">
                <label class="col-sm-2 control-label">Address</label>

                <div class="col-sm-10">
                    <div class="input-group">
                        <input type="text" class="form-control" name="address" placeholder="Address" value="${dto.address}">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>
                    </div>
                </div>
            </div>
        </security:authorize>
        <div class="form-group student">
            <label class="col-sm-2 control-label">Course</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <input type="text" class="form-control" name="course" placeholder="Course" value="${dto.course}" required>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
                <span class="help-block with-errors hidden"></span>
            </div>
        </div>
        <div class="form-group student">
            <label class="col-sm-2 control-label">Group</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <input type="text" class="form-control" name="group" placeholder="Group" value="${dto.group}" required>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
                <span class="help-block with-errors hidden"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Password</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" value="${dto.password}" required>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
            </div>
        </div>
        <div class="form-group has-feedback">
            <label class="col-sm-2 control-label">Confirm Password</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <input type="password" class="form-control" name="confirmPassword" placeholder="Confirm Password"
                           value="${dto.confirmPassword}" required data-match="#password" data-match-error="Password doesn't match">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
                <span class="help-block with-errors hidden"></span>
            </div>
        </div>

        <input type="submit" name="submit" value="Sign Up" class="btn btn-primary pull-right">
    </form>
</div>