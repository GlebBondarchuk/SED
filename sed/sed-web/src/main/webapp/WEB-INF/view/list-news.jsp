<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="row">

    <!-- Blog Entries Column -->
    <div class="col-md-8">
        <c:choose>
            <c:when test="${not empty newsList}">
                <c:forEach var="news" items="${newsList}">
                    <h2>
                        <a href="#">${news.content.name}</a>
                    </h2>

                    <p class="lead">
                        by <a href="${applicationPath}/people/${news.creator.login}">${news.creator.name}</a>
                    </p>

                    <p><i class="fa fa-clock-o"></i> Posted on ${news.createdDate}</p>
                    <hr>
                    <a href="blog-post.html">
                        <img class="img-responsive img-hover" src="http://img.tyt.by/620x620s/n/minsk/07/a/sneg_minsk_bas_tutby_phsl_20150123_010.jpg" alt="">
                    </a>
                    <hr>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolore, veritatis, tempora, necessitatibus inventore nisi quam quia
                        repellat ut
                        tempore laborum possimus eum dicta id animi corrupti debitis ipsum officiis rerum.</p>
                    <a class="btn btn-primary" href="#">Read More <i class="fa fa-angle-right"></i></a>
                    <hr>
                </c:forEach>

                <!-- Pager -->
                <ul class="pager">
                    <li class="previous">
                        <a href="#">&larr; Older</a>
                    </li>
                    <li class="next">
                        <a href="#">Newer &rarr;</a>
                    </li>
                </ul>
            </c:when>
            <c:otherwise>
                <div class="text-center alert-warning alert">
                    Nothing found
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Blog Sidebar Widgets Column -->
    <div class="col-md-4">

        <!-- Blog Search Well -->
        <div class="well">
            <h4>News Search</h4>

            <div class="input-group">
                <input type="text" class="form-control">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
                        </span>
            </div>
            <!-- /.input-group -->
        </div>

        <!-- Blog Categories Well -->
        <div class="well">
            <h4>News Categories</h4>

            <div class="row">
                <div class="col-lg-6">
                    <ul class="list-unstyled">
                        <li><a href="#">Category Name</a>
                        </li>
                        <li><a href="#">Category Name</a>
                        </li>
                        <li><a href="#">Category Name</a>
                        </li>
                        <li><a href="#">Category Name</a>
                        </li>
                    </ul>
                </div>
                <!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <ul class="list-unstyled">
                        <li><a href="#">Category Name</a>
                        </li>
                        <li><a href="#">Category Name</a>
                        </li>
                        <li><a href="#">Category Name</a>
                        </li>
                        <li><a href="#">Category Name</a>
                        </li>
                    </ul>
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
        </div>

        <!-- Side Widget Well -->
        <div class="well">
            <h4>Side Widget Well</h4>

            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus laudantium odit aliquam repellat
                tempore quos aspernatur vero.</p>
        </div>
        <security:authorize access="hasAnyRole('ADMIN','TEACHER')">
            <a href="${applicationPath}/news/add" class="btn btn-danger btn-primary" role="button">
                <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;
                <spring:message code="people.button.addTab"/>
            </a>
        </security:authorize>
    </div>

</div>
<!-- /.row -->