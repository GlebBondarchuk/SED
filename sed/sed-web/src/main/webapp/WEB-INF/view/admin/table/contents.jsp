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
        <table id="bootstrapTable" class="table table-striped" data-url="${applicationPath}/content/data"
               data-show-refresh="true" data-toggle="table" data-pagination="true" data-show-columns="true" data-show-toggle="true"
               data-show-filter="true" data-side-pagination="server" data-show-export="true" data-row-style="rowStyle"
               data-page-list="[10,20,50,100]" data-search="true"
               data-sort-name="updateDate" data-sort-order="desc">
            <thead>
            <tr>
                <th data-field="state" data-checkbox="true"></th>
                <th data-field="name" data-formatter="linkFormatter" data-align="left" data-sortable="true">Name</th>
                <th data-field="updateDate" data-align="center" data-sortable="true">Update Date</th>
                <th data-field="contentType" id="role" data-sortable="true">Content Type</th>
                <th data-field="operations" data-formatter="actionsFormatter" data-sortable="false" data-events="operationEvent">
                    Actions
                    <a class="remove" href="javascript:void(0)" onclick="confirmAction('/content/delete', deleteSelected)" title="Remove Selected">
                        <i class="glyphicon glyphicon-remove"></i>
                    </a>
                </th>
            </tr>
            </thead>
        </table>
        <hr>
    </div>
</div>

<script>
    function linkFormatter(value, row) {
        return '<a href="${applicationPath}/content/' + row.id + '">' + (row.name == "" ? "Empty-Name" : row.name) + '</a>'
    }
    function actionsFormatter() {
        return [
            '<a class="edit" title="Edit">',
            '<i class="glyphicon glyphicon-edit"></i>',
            '</a>',
            '<a class="remove" title="Remove">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join(' ');
    }
    window.operationEvent = {
        'click .edit': function (e, value, row) {
            window.location.href = "${applicationPath}/content/" + row.id + "/edit";
        },
        'click .remove': function (e, value, row) {
            confirmAction("${applicationPath}/content/" + row.id + "/delete", ajaxGet);
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