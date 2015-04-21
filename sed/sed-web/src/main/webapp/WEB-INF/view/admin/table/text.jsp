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
        <table id="bootstrapTable" class="table table-striped" data-url="${applicationPath}/text/data"
               data-show-refresh="true" data-toggle="table" data-pagination="true" data-show-columns="true" data-show-toggle="true"
               data-show-filter="true" data-side-pagination="server" data-show-export="true" data-row-style="rowStyle"
               data-page-list="[10,20,50,100]" data-search="true"
               data-sort-name="updateDate" data-sort-order="desc">
            <thead>
            <tr>
                <th data-field="state" data-checkbox="true"></th>
                <th data-field="text" data-align="left" data-sortable="true">Text</th>
                <th data-field="updateDate" data-sortable="true">Update Date</th>
                <th data-field="key" data-sortable="true">Key</th>
                <th data-field="operations" data-formatter="actionsFormatter" data-sortable="false">
                    Actions
                </th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script>
    function actionsFormatter(value, row) {
        return [
            '<a href="${applicationPath}/text/' + row.id + '" class="edit" title="Edit">',
            '<i class="glyphicon glyphicon-edit"></i>',
            '</a>'
        ].join(' ');
    }
</script>
