<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/tld/sed_library" prefix="sed" %>


<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title"><spring:message code="label.nav.admin.users"/></h3>
            </div>
            <table id="usersTable" class="table table-striped" data-url="${applicationPath}/admin/users/data"
                   data-show-refresh="true" data-toggle="table"
                   data-pagination="true" data-show-columns="true" data-show-toggle="true" data-show-filter="true"
                   data-select-item-name="toolbar1" data-side-pagination="client" data-show-export="true"
                   data-row-style="rowStyle"
                   data-page-list="[10,20,50,100]" data-search="true">
                <thead>
                <tr>
                    <th data-field="state" data-checkbox="true"></th>
                    <th data-field="id" data-align="right" data-sortable="true">ID</th>
                    <th data-field="name" data-align="center" data-sortable="true">Name</th>
                    <th data-field="role" id="role" data-sortable="true">Role</th>
                    <th data-field="login" id="login" data-sortable="true">Login</th>
                    <th data-field="email" id="email">Email</th>
                    <th data-field="disabled" data-sortable="false">Disabled</th>
                    <th data-field="operations" data-formatter="formatter" data-sortable="false" data-events="operationEvent">
                        Operations
                    </th>
                </tr>
                </thead>
            </table>
        </div>
        <a class="btn btn-danger btn-primary" href="<c:url value="/admin/users/add"/>" role="button"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<spring:message code="admin.users.addNew"/></a>
    </div>
</div>
<script>
    function rowStyle(row, index) {
        if (row.disabled) {
            return {classes: "danger"};
        }
        return {classes: "success"};
    }

    function formatter(value, row, index) {
        return [
            '<a class="edit ml10" href="javascript:void(0)" title="Edit">',
            '<i class="glyphicon glyphicon-edit"></i>',
            '</a>',
            '<a class="remove ml10" href="javascript:void(0)" title="Remove">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join(' ');
    }

    window.operationEvent = {
        'click .edit': function (e, value, row, index) {
            if(row.role == 'STUDENT') {
                window.location.href = "${applicationPath}/student/" + row.login + "/edit";
            }  else {
                window.location.href = "${applicationPath}/people/" + row.login + "/edit";
            }
        },
        'click .remove': function (e, value, row, index) {
            bootbox.setDefaults({locale: '${sed:getLangLowerCase()}'});
            bootbox.confirm("<spring:message code="dialog.sure"/>", function (confirmed) {
                if (confirmed) {
                    $.ajax("${applicationPath}/admin/users/remove/" + row.id).done(function () {
                        $("#usersTable").bootstrapTable('refresh');
                    });
                }
            });
        }
    };
</script>
