<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">Users</h3>
    </div>
    <table id="usersTable" class="table table-striped" data-url="${applicationPath}/admin/users/data"
           data-show-refresh="true" data-height="750" data-toggle="table"
           data-pagination="true" data-show-columns="true" data-show-toggle="true" data-show-filter="true"
           data-select-item-name="toolbar1" data-side-pagination="client" data-show-export="true"
           data-row-style="rowStyle"
           data-page-list="[1,10,15,20,50,100]" data-search="true">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="id" data-align="right" data-sortable="true">ID</th>
            <th data-field="name" data-align="center" data-sortable="true">Name</th>
            <th data-field="role" data-sortable="true">Role</th>
            <th data-field="login" id="login" data-sortable="true">Login</th>
            <th data-field="disabled" data-sortable="false">Disabled</th>
            <th data-field="operations" data-formatter="formatter" data-sortable="false" data-events="operationEvent">
                Operations
            </th>
        </tr>
        </thead>
    </table>
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

        },
        'click .remove': function (e, value, row, index) {
            $.ajax("${applicationPath}/admin/users/remove/" + row.id).done(function () {
                $("#usersTable").bootstrapTable('refresh');
            });
        }
    };
</script>
