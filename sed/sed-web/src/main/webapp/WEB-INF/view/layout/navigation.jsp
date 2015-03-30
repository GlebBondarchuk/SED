<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/tld/sed_library" prefix="sed" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.nav.information"/> <b
                            class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${applicationPath}/peoples"><spring:message code="label.nav.peoples"/></a>
                        </li>
                        <security:authorize access="isAuthenticated()">
                            <li>
                                <a href="${applicationPath}/students"><spring:message code="label.nav.students"/></a>
                            </li>
                        </security:authorize>
                        <li>
                            <a href="${applicationPath}/content/educational"><spring:message code="label.nav.educational"/></a>
                        </li>
                        <li>
                            <a href="${applicationPath}/content/scientific"><spring:message code="label.nav.scientific"/></a>
                        </li>
                        <li>
                            <a href="${applicationPath}/content/conference"><spring:message code="label.nav.conferences"/></a>
                        </li>
                        <li>
                            <a href="${applicationPath}/news"><spring:message code="label.news.title"/></a>
                        </li>
                        <li>
                            <a href="${applicationPath}/contact"><spring:message code="label.nav.contact"/></a>
                        </li>
                    </ul>
                </li>
                <security:authorize access="hasRole('ADMIN')">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.nav.admin"/> <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="${applicationPath}/admin/system"><spring:message code="label.nav.admin.system"/></a>
                            </li>
                            <li>
                                <a href="${applicationPath}/admin/users"><spring:message code="label.nav.admin.users"/></a>
                            </li>
                            <li>
                                <a href="${applicationPath}/admin/news/">News Configuration</a>
                            </li>
                            <li>
                                <a href="${applicationPath}/admin/processes/">Background Processes</a>
                            </li>
                        </ul>
                    </li>
                </security:authorize>
                <li>
                    <a href="${applicationPath}/search">Search</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <security:authorize access="isAnonymous()">
                    <li><a href="${applicationPath}/signUp"><span class="glyphicon glyphicon-user"></span> <spring:message
                            code="label.nav.signUp"/></a></li>
                    <li><a href="${applicationPath}/login"><span class="glyphicon glyphicon-log-in"></span> <spring:message code="label.nav.SignIn"/></a>
                    </li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <li>
                        <a href="${applicationPath}${userURL}"><spring:message code="label.nav.welcome"/>
                        <security:authentication property="principal.username"/></a>
                    </li>
                    <li><a href="${applicationPath}/j_spring_security_logout"><span class="glyphicon glyphicon-log-out"></span> <spring:message
                            code="label.nav.SignOut"/></a></li>
                </security:authorize>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="dLabel" role="button"
                       data-target="#">
                        <i class="icon-flag-${sed:getLangUpperCase()}">
                            &nbsp;&nbsp;&nbsp;&nbsp;</i><spring:message code="label.nav.language"/>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <li><a href="?lang=ru"><i class="icon-flag-RU">&nbsp;&nbsp;&nbsp;&nbsp;</i><spring:message code="label.nav.language.russian"/></a>
                        </li>
                        <li><a href="?lang=en"><i class="icon-flag-EN">&nbsp;&nbsp;&nbsp;&nbsp;</i><spring:message code="label.nav.language.english"/></a>
                        </li>
                        <li><a href="?lang=cn"><i class="icon-flag-CN">&nbsp;&nbsp;&nbsp;&nbsp;</i><spring:message code="label.nav.language.chinese"/></a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>