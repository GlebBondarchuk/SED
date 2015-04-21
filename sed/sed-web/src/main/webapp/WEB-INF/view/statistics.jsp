<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="${applicationPath}/resources/css/bootstrap/statistics/sb-admin-2.css" rel="stylesheet">
<link href="${applicationPath}/resources/css/bootstrap/statistics/font-awesome.min.css" rel="stylesheet" type="text/css">

<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
               <spring:message code="label.statistics.lastMonth"/>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="flot-chart">
                    <div class="flot-chart-content" id="flot-line-chart"></div>
                </div>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>

    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <spring:message code="label.statistics.country"/>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="flot-chart">
                    <div class="flot-chart-content" id="flot-pie-chart"></div>
                </div>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
</div>
<!-- /.row -->

<script src="${applicationPath}/resources/js/bootstrap/statistics/jquery.flot.js"></script>
<script src="${applicationPath}/resources/js/bootstrap/statistics/jquery.flot.pie.js"></script>
<script src="${applicationPath}/resources/js/bootstrap/statistics/jquery.flot.time.js"></script>
<script src="${applicationPath}/resources/js/bootstrap/statistics/jquery.flot.tooltip.min.js"></script>
<script src="${applicationPath}/resources/js/bootstrap/statistics/flot-data.js"></script>

<script src="${applicationPath}/resources/js/bootstrap/statistics/sb-admin-2.js"></script>

