<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${applicationPath}/"><spring:message code="label.title"/></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <%--<li>--%>
                    <%--<a href="about.html">About</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="services.html">Services</a>--%>
                <%--</li>--%>
                <li>
                    <a href="${applicationPath}/contact">Contact</a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Portfolio <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="portfolio-1-col.html">1 Column Portfolio</a>
                        </li>
                        <li>
                            <a href="portfolio-2-col.html">2 Column Portfolio</a>
                        </li>
                        <li>
                            <a href="portfolio-3-col.html">3 Column Portfolio</a>
                        </li>
                        <li>
                            <a href="portfolio-4-col.html">4 Column Portfolio</a>
                        </li>
                        <li>
                            <a href="portfolio-item.html">Single Portfolio Item</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Blog <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="blog-home-1.html">Blog Home 1</a>
                        </li>
                        <li>
                            <a href="blog-home-2.html">Blog Home 2</a>
                        </li>
                        <li>
                            <a href="blog-post.html">Blog Post</a>
                        </li>
                    </ul>
                </li>
                <security:authorize access="hasRole('ADMIN')">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="${applicationPath}/admin/system">System</a>
                            </li>
                            <li>
                                <a href="${applicationPath}/admin/users">Users</a>
                            </li>
                        </ul>
                    </li>
                </security:authorize>
            </ul>
            <%--<div class="col-sm-3 col-md-3 pull-right">--%>
                <%--<form class="navbar-form" role="search">--%>
                    <%--<div class="input-group">--%>
                        <%--<input type="text" class="form-control" placeholder="Search" name="q">--%>

                        <%--<div class="input-group-btn">--%>
                            <%--<button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i>--%>
                            <%--</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>
            <ul class="nav navbar-nav navbar-right">
                <security:authorize access="isAnonymous()">
                    <li><a href="${applicationPath}/signUp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="${applicationPath}/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <li><a href="${applicationPath}/user">Welcome, <security:authentication property="principal.username"/></a></li>
                    <li><a href="${applicationPath}/j_spring_security_logout"><span class="glyphicon glyphicon-log-out"></span> Sign Out</a></li>
                </security:authorize>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="dLabel" role="button"
                       data-target="#">
                        <i class="icon-flag-${not empty param.get("lang") ? param.get("lang").toUpperCase(): 'EN'}">
                            &nbsp;&nbsp;&nbsp;&nbsp;</i>Language
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <li><a href="?lang=ru"><i class="icon-flag-RU">&nbsp;&nbsp;&nbsp;&nbsp;</i>Russian</a></li>
                        <li><a href="?lang=en"><i class="icon-flag-EN">&nbsp;&nbsp;&nbsp;&nbsp;</i>English</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>