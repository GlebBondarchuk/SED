<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sed" uri="/tld/sed_library" %>

<%--Bootstrap Table CSS--%>
<link href="${applicationPath}/resources/css/bootstrap/table/bootstrap-table.css" rel="stylesheet">

<script src="${applicationPath}/resources/js/bootstrap/table/bootstrap-table.js"></script>

<script src="${applicationPath}/resources/js/bootstrap/table/bootstrap-table-export.js"></script>
<script src="${applicationPath}/resources/js/bootstrap/table/tableExport.js"></script>
<script src="${applicationPath}/resources/js/sed-table.js"></script>

<div class="row">
    <div class="col-lg-12">
        <table id="bootstrapTable" class="table table-striped" data-url="${applicationPath}/admin/news/data"
               data-show-refresh="true" data-toggle="table" data-pagination="true" data-show-columns="true" data-show-toggle="true"
               data-show-filter="true" data-side-pagination="server" data-show-export="true" data-row-style="rowStyle"
               data-page-list="[10,20,50,100]" data-search="true"
               data-sort-name="createdDate" data-sort-order="desc">
            <thead>
            <tr>
                <th data-field="state" data-checkbox="true"></th>
                <th data-field="url" data-align="center" data-formatter="urlFormatter" data-sortable="true">Title</th>
                <th data-field="creator"  data-sortable="true">Creator</th>
                <th data-field="createdDate" data-sortable="true">Created Date</th>
                <th data-field="operations" data-formatter="actionsFormatter" data-sortable="false" data-events="operationEvent">
                    Actions
                    <a href="javascript:void(0)" onclick="confirmAction('/admin/news/remove', deleteSelected)" title="Remove Selected">
                        <i class="glyphicon glyphicon-remove"></i>
                    </a>
                </th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script>
    function urlFormatter(value, row) {
        return '<a href="${applicationPath}/news/' + row.id + '">' + (row.name ? row.name:"Empty-Name") + '</a>'
    }
    function actionsFormatter() {
        return [
            '<a class="remove" title="Remove">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join(' ');
    }
    window.operationEvent = {
        'click .remove': function (e, value, row) {
            confirmAction("${applicationPath}/news/" + row.id + "/delete", ajaxGet);
        }
    };

    function confirmAction(url, callback) {
        bootbox.setDefaults({locale: '${sed:getLangLowerCase()}'});
        bootbox.confirm("<spring:message code="dialog.sure"/>", function (confirmed) {
            if (confirmed) {
                callback(url);
            }
        });
    }
</script>
