<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title"><spring:message code="label.nav.admin.system"/></h3>
    </div>
    <table class="table table-striped" data-url="${applicationPath}/admin/system/data"
           data-show-refresh="true" data-toggle="table"
           data-pagination="true" data-show-columns="true" data-show-toggle="true" data-show-filter="true"
           data-select-item-name="toolbar1"  data-show-export="true"
           data-page-list="[10,20,50,100]" data-search="true">
        <thead>
        <tr>
            <th data-field="displayName" data-sortable="true">Name</th>
            <th data-field="value" data-sortable="true">Value</th>
            <th data-field="description"  data-sortable="true">Description</th>
        </tr>
        </thead>
    </table>
</div>

