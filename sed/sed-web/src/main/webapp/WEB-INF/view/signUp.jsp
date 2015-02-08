<%@ page import="com.bsu.sed.model.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<script src="${applicationPath}/resources/js/signUp.js"></script>

<div class="row">
    <form class="form-horizontal col-lg-12" role="form" action="${applicationPath}${postURL}" method="post">
        <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Full Name</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Name" onchange="setNameHandler('name');" required>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Email</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <input type="text" class="form-control" id="email" name="login" placeholder="Email" required>
                    <span id="mask" class="input-group-addon">${emailMask}</span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
            </div>
        </div>
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
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Mobile Phone</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <input type="text" class="form-control" name="phone" placeholder="Mobile Phone">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></span>
                </div>
            </div>
        </div>
        <security:authorize access="hasRole('ADMIN')">
            <div id="details">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Link to Photo</label>

                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="url" class="form-control" id="photo" name="photo" placeholder="Photo Link" required>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-link"></span></span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Position</label>

                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="text" class="form-control" id="position" name="position" placeholder="People Position" required>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-pencil"></span></span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Address</label>

                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="text" class="form-control" id="address" name="address" placeholder="Address" required>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>
                        </div>
                    </div>
                </div>
            </div>
        </security:authorize>
        <div class="form-group">
            <label class="col-sm-2 control-label">Password</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Confirm Password</label>

            <div class="col-sm-10">
                <div class="input-group">
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" required>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                </div>
            </div>
        </div>

        <input onclick="setEmailValue('email','mask'); encodePassword('password');encodePassword('confirmPassword');"
               type="submit" name="submit" id="submit" value="Sign Up" class="btn btn-primary pull-right">
    </form>
</div>