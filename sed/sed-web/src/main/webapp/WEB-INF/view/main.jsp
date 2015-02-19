<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="${applicationPath}/resources/js/bootstrap/boostrap-carousel.js"></script>

<!-- Heading Row -->
<div class="row">
    <div class="col-md-8">
        <div id="myCarousel" class="carousel slide">
            <div class="carousel-inner">
                <div class="item active">
                    <img class="img-responsive img-rounded" src="http://puu.sh/fDHIB/98750e2945.jpg" alt="">
                    <div class="carousel-caption">
                        <h4>Belarusian State University</h4>
                        <p>Minsk, Belarus</p>
                    </div>
                </div>
                <div class="item">
                    <img class="img-responsive img-rounded" src="http://puu.sh/fDHYt/7e29d8df36.jpg" alt="">
                    <div class="carousel-caption">
                        <h4>&#1043;&#1083;&#1072;&#1074;&#1085;&#1099;&#1081; &#1082;&#1086;&#1088;&#1087;&#1091;&#1089;</h4>
                        <p>&#1075;. &#1052;&#1080;&#1085;&#1089;&#1082;, &#1087;&#1083;. &#1053;&#1077;&#1079;&#1072;&#1074;&#1080;&#1089;&#1080;&#1084;&#1086;&#1089;&#1090;&#1080;</p>
                    </div>
                </div>
                <div class="item">
                    <img class="img-responsive img-rounded" src="http://puu.sh/fDKhp/c43a8db630.jpg" alt="">
                    <%--<div class="carousel-caption">--%>
                        <%--<h4>Третий слайд</h4>--%>
                        <%--<p>Многие вещи нам непонятны не потому, что наши понятия слабы; но потому, что сии вещи не входят в круг наших понятий.</p>--%>
                    <%--</div>--%>
                </div>
            </div>
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
        </div>
        <%--<img class="img-responsive img-rounded" src="https://pp.vk.me/c618616/v618616840/274a3/DrA6UdCERSE.jpg" alt="">--%>
    </div>
    <!-- /.col-md-8 -->
    <div class="col-md-4">
        <h1><spring:message code="label.nav.departmentInfo"/></h1>
        <div style="max-height: 120px;overflow-y: hidden;">
        ${departmentInfo.content.html}
        </div>
        <br>
        <a class="btn btn-primary btn-lg" href="${applicationPath}/content/${departmentInfo.content.id}">
            <spring:message code="button.moreInfo"/>
        </a>
    </div>
    <!-- /.col-md-4 -->
</div>
<!-- /.row -->

<hr>

<!-- Call to Action Well -->
<div class="row">
    <div class="col-lg-12">
        <div class="well text-center">
            <spring:message code="main.wellText"/>
        </div>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<!-- Content Row -->
<div class="row">
    <div class="col-md-4">
        <h2><spring:message code="label.nav.educational"/></h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Saepe rem nisi accusamus error velit animi non ipsa placeat. Recusandae, suscipit, soluta quibusdam accusamus a veniam quaerat eveniet eligendi dolor consectetur.</p>
        <a class="btn btn-default" href="${applicationPath}/content/educational"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;More Info</a>
    </div>
    <!-- /.col-md-4 -->
    <div class="col-md-4">
        <h2><spring:message code="label.nav.scientific"/></h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Saepe rem nisi accusamus error velit animi non ipsa placeat. Recusandae, suscipit, soluta quibusdam accusamus a veniam quaerat eveniet eligendi dolor consectetur.</p>
        <a class="btn btn-default" href="${applicationPath}/content/scientific"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;More Info</a>
    </div>
    <!-- /.col-md-4 -->
    <div class="col-md-4">
        <h2><spring:message code="label.nav.conferences"/></h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Saepe rem nisi accusamus error velit animi non ipsa placeat. Recusandae, suscipit, soluta quibusdam accusamus a veniam quaerat eveniet eligendi dolor consectetur.</p>
        <a class="btn btn-default" href="${applicationPath}/content/conference"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;More Info</a>
    </div>
    <!-- /.col-md-4 -->
</div>
<!-- /.row -->
