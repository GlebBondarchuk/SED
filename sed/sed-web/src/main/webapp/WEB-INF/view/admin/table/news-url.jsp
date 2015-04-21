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
        <table id="bootstrapTable" class="table table-striped" data-url="${applicationPath}/admin/news-url/data"
               data-show-refresh="true" data-toggle="table" data-pagination="true" data-show-columns="true" data-show-toggle="true"
               data-show-filter="true" data-side-pagination="server" data-show-export="true" data-row-style="rowStyle"
               data-page-list="[10,20,50,100]" data-search="true"
               data-sort-name="lastSearch" data-sort-order="desc">
            <thead>
            <tr>
                <th data-field="state" data-checkbox="true"></th>
                <th data-field="image" data-formatter="imageFormatter" data-align="left">Logo</th>
                <th data-field="url" data-align="center" data-formatter="linkFormatter" data-sortable="true">URL</th>
                <th data-field="searchWords" data-formatter="breakWordFormatter" data-sortable="true">Search Words</th>
                <th data-field="lastSearch" data-sortable="true">Last Search</th>
                <th data-field="disabled" data-sortable="true">Disabled</th>
                <th data-field="operations" data-formatter="actionsFormatter" data-sortable="false" data-events="operationEvent">
                    Actions
                    <a href="javascript:void(0)" onclick="confirmAction('/admin/news-url/remove', deleteSelected)" title="Remove Selected">
                        <i class="glyphicon glyphicon-remove"></i>
                    </a>
                </th>
            </tr>
            </thead>
        </table>
        <hr>
        <a class="btn btn-danger btn-primary" href="<c:url value="/admin/news-url/add"/>" role="button">
            <span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<spring:message code="news.button.add"/>
        </a>
    </div>
</div>

<script>
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
            window.location.href = "${applicationPath}/admin/news-url/edit/" + row.id;
        },
        'click .remove': function (e, value, row) {
            confirmAction("${applicationPath}/admin/news-url/remove/" + row.id, ajaxGet);
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
