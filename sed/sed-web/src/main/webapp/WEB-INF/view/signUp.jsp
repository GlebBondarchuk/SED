<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${applicationPath}/resources/js/signUp.js"></script>
<div class="container">
    <form class="form-horizontal" action="${applicationPath}/signUp" method="post">
        <input class="form-control" name="name" type="text" placeholder="Name"/>

        <div class="input-group">
            <input type="text" name="login" class="form-control" placeholder="Email" aria-describedby="basic-addon2">
            <span class="input-group-addon" id="basic-addon2">${emailMask}</span>
        </div>

        <select class="form-control" name="role">
            <c:forEach items="${roles}" var="role">
                <option>${role}</option>
            </c:forEach>
        </select>

        <input id="password" class="form-control" name="password" type="text" placeholder="Password"/>
        <input id="confirmPassword" class="form-control" name="confirmPassword" type="text"
               placeholder="Confirm Password"/>
        <input type="submit" class="btn btn-primary" value="Sign Up" onsubmit="encodePassword('password'); encodePassword('confirmPassword');">
    </form>
</div>