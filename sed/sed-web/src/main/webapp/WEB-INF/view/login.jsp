
<div class="page-content" onload='document.loginForm.j_username.focus();'>
    <div class="logo"></div>
    <div class="panel">
        <div class="panel-content">
            <form name='loginForm' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'>
                <Label>Login</Label>
                <input type='text' name='j_username' value=''>
                <!-- Uncomment when Reset Password functionality is implemented -->
                <input type='password' name='j_password'/>
                <div class="buttons-holder">
                    <input class="btn-submit" name="submit" type="submit" value="Submit"/>
                </div>
            </form>
        </div>
    </div>
</div>