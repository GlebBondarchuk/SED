<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Sign In
            <small>Subheading</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${applicationPath}/">Home</a>
            </li>
            <li class="active">Sign In</li>
        </ol>
    </div>
</div>

<div onload='document.loginForm.j_username.focus();'>
    <form class="form-signin" name='loginForm' action='${applicationPath}/j_spring_security_check' method='POST'>
        <Label class="form-signin-heading">Login</Label>
        <div class="input-group">
            <span class="input-group-addon">@</span>
            <input class="form-control" type='email' name='j_username' placeholder="Email">
        </div>
        <input class="form-control" type='password' name='j_password' placeholder="Password"/>

        <div class="buttons-holder">
            <input class="btn btn-lg btn-primary btn-block" name="submit" type="submit" value="Sign In"/>
        </div>
    </form>
</div>