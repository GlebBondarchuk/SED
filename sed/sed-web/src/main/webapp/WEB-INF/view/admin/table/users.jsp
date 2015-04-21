<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/tld/sed_library" prefix="sed" %>

<%--Bootstrap Table CSS--%>
<link href="${applicationPath}/resources/css/bootstrap/table/bootstrap-table.css" rel="stylesheet">

<script src="${applicationPath}/resources/js/bootstrap/table/bootstrap-table.js"></script>

<script src="${applicationPath}/resources/js/bootstrap/table/bootstrap-table-export.js"></script>
<script src="${applicationPath}/resources/js/bootstrap/table/tableExport.js"></script>
<script src="${applicationPath}/resources/js/sed-table.js"></script>

<div class="row">
    <div class="col-lg-12">
        <div class="alert alert-danger" role="alert" hidden>
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
            <span id="result"></span>
        </div>
        <table id="bootstrapTable" class="table table-striped" data-url="${applicationPath}/admin/users/data"
               data-show-refresh="true" data-toggle="table" data-pagination="true" data-show-columns="true" data-show-toggle="true"
               data-show-filter="true" data-side-pagination="server" data-show-export="true" data-row-style="rowStyle"
               data-page-list="[10,20,50,100]" data-search="true"
               data-sort-name="name" data-sort-order="asc">
            <thead>
            <tr>
                <th data-field="state" data-checkbox="true"></th>
                <th data-field="id" data-align="right" data-sortable="true">ID</th>
                <th data-field="name" data-align="center" data-sortable="true">Name</th>
                <th data-field="role" id="role" data-sortable="true">Role</th>
                <th data-field="login" id="login" data-sortable="true">Login</th>
                <th data-field="email" id="email">Email</th>
                <th data-field="disabled" data-sortable="true">Disabled</th>
                <th data-field="operations" data-formatter="actionsFormatter" data-sortable="false" data-events="operationEvent">
                    Operations
                </th>
            </tr>
            </thead>
        </table>
        <a class="btn btn-danger btn-primary" href="<c:url value="/admin/users/add"/>" role="button">
            <span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<spring:message code="admin.users.addNew"/>
        </a>
    </div>
</div>
<script>

    function actionsFormatter(value, row) {
        var enableButton = row.disabled ? "<a class=enable title=Enable><i class='glyphicon glyphicon-ok'></i></a>" :
                "<a class=enable title=Disable><i class='glyphicon glyphicon-remove'></i></a>";
        return [
            '<a class="edit" title="Edit">',
            '<i class="glyphicon glyphicon-edit"></i>',
            '</a>',
            '<a class="remove" title="Remove">',
            '<i class="glyphicon glyphicon-trash"></i>',
            '</a>',
            enableButton
        ].join(' ');
    }

    function confirmAction(url, callback) {
        bootbox.setDefaults({locale: '${sed:getLangLowerCase()}'});
        bootbox.confirm("<spring:message code="dialog.sure"/>", function (confirmed) {
            if (confirmed) {
                callback(url);
            }
        });
    }

    window.operationEvent = {
        'click .edit': function (e, value, row, index) {
            if (row.role == 'STUDENT') {
                window.location.href = "${applicationPath}/student/" + row.login + "/edit";
            } else {
                window.location.href = "${applicationPath}/people/" + row.login + "/edit";
            }
        },
        'click .enable': function (e, value, row, index) {
            $.ajax({
                url: "${applicationPath}/admin/users/enable/" + row.id,
                data: {enable: row.disabled}
            }).done(function (data) {
                $("#bootstrapTable").bootstrapTable('refresh');
            });
        },
        'click .remove': function (e, value, row, index) {
            bootbox.setDefaults({locale: '${sed:getLangLowerCase()}'});
            bootbox.confirm("<spring:message code="dialog.sure"/>", function (confirmed) {
                if (confirmed) {
                    $.ajax("${applicationPath}/admin/users/remove/" + row.id).done(function (data) {
                        if (data != "") {
                            var alert = $("#result");
                            alert.html(data);
                            alert.parent().toggle();
                        }
                        $("#bootstrapTable").bootstrapTable('refresh');
                    });
                }
            });
        }
    };
</script>
