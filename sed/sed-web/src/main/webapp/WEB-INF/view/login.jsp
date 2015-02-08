<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="${applicationPath}/resources/css/singin.css" rel="stylesheet">

<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <div class="row">
        <div class="col-lg-12">
            <div class="alert alert-danger" role="alert">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <span class="sr-only">Error:</span>
                    ${SPRING_SECURITY_LAST_EXCEPTION.message}
            </div>
        </div>
    </div>
</c:if>

<div onload='document.loginForm.j_username.focus();'>
    <form class="form-signin" name='loginForm' action='${applicationPath}/j_spring_security_check' method='POST'>
        <h2 class="form-signin-heading">Login</h2>

        <div class="input-group">
            <span class="input-group-addon">@</span>
            <input class="form-control" type='email' name='j_username' placeholder="Email">
        </div>
        <input class="form-control" type='password' name='j_password' placeholder="Password"/>

        <div class="buttons-holder">
            <input class="btn btn-lg btn-primary btn-block" name="submit" type="submit" value="<spring:message code="button.signIn"/>"/>
        </div>
    </form>
</div>