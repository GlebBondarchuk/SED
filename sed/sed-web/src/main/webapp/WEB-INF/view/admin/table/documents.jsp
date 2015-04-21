<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sed" uri="/tld/sed_library" %>

<%--Bootstrap Table CSS--%>
<link href="${applicationPath}/resources/css/bootstrap/table/bootstrap-table.css" rel="stylesheet">

<link href="${applicationPath}/resources/css/jquery.fileupload.css" rel="stylesheet">
<link href="${applicationPath}/resources/css/jquery.fileupload-ui.css" rel="stylesheet">


<script src="${applicationPath}/resources/js/bootstrap/table/bootstrap-table.js"></script>

<script src="${applicationPath}/resources/js/bootstrap/table/bootstrap-table-export.js"></script>
<script src="${applicationPath}/resources/js/bootstrap/table/tableExport.js"></script>
<script src="${applicationPath}/resources/js/sed-table.js"></script>

<script src="${applicationPath}/resources/js/vendor/jquery.ui.widget.js"></script>
<script src="${applicationPath}/resources/js/jquery.iframe-transport.js"></script>
<script src="${applicationPath}/resources/js/jquery.fileupload.js"></script>

<div class="row">
    <div class="col-lg-12">
        <table id="bootstrapTable" class="table table-striped" data-url="${applicationPath}/documents/data"
               data-show-refresh="true" data-toggle="table" data-pagination="true" data-show-columns="true" data-show-toggle="true"
               data-show-filter="true" data-side-pagination="server" data-show-export="true" data-row-style="rowStyle"
               data-page-list="[10,20,50,100]" data-search="true"
               data-sort-name="createdDate" data-sort-order="desc">
            <thead>
            <tr>
                <th data-field="state" data-checkbox="true"></th>
                <th data-field="name" data-formatter="linkFormatter" data-align="left" data-sortable="true">Name</th>
                <th data-field="url" data-formatter="fileURLFormatter" data-align="left" data-sortable="true">URL</th>
                <th data-field="role" data-align="center" data-formatter="accessFormatter" data-sortable="true">Access</th>
                <th data-field="createdDate" data-align="center" data-sortable="true">Created Date</th>
                <th data-field="contentType" id="role" data-sortable="true">Content Type</th>
                <th data-field="creator" id="login" data-sortable="true">Creator</th>
                <th data-field="operations" data-formatter="actionsFormatter" data-sortable="false" data-events="operationEvent">
                    Actions
                    <a class="remove" href="javascript:void(0)" onclick="confirmAction('/documents/delete', deleteSelected)" title="Remove Selected">
                        <i class="glyphicon glyphicon-remove"></i>
                    </a>
                </th>
            </tr>
            </thead>
        </table>
        <hr>
        <form action="${applicationPath}/documents/upload" method="post" enctype="multipart/form-data">
            <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
            <span>Select files...</span>
                <input type="file" name="files" id="fileupload" class="fileupload form-control" multiple>
            </span>
            <br/>
            <br/>

            <div id="progress" class="progress">
                <div class="progress-bar progress-bar-success"></div>
            </div>
            <div id="files" class="files"></div>
        </form>
    </div>
</div>
<script>
    function actionsFormatter() {
        return [
            '<a class="remove" title="Remove">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join(' ');
    }
    window.operationEvent = {
        'click .remove': function (e, value, row) {
            confirmAction("${applicationPath}/documents/delete/" + row.id, ajaxGet);
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
    function accessFormatter(value, row) {
        return value + "&nbsp;<a onclick=\"openDialog('" + row.id + "','" + row.role + "')\"><i class='glyphicon glyphicon-edit'></i></a>";
    }

    function linkFormatter(value, row) {
        return '<a href="${applicationPath}/get/' + row.path + '">' + row.name + '</a>'
    }

    function fileURLFormatter(value, row) {
        return '<a href="${applicationPath}/get/' + row.path + '">' + window.location.origin + mainContextPath + "/get/" + row.path + '</a>'
    }

    $(function () {
        $('#fileupload').fileupload({
            url: "${applicationPath}/documents/upload",
            done: function (e, data) {
                window.location.reload();
            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('.progress-bar').css('width', progress + '%');
            },
            add: function (e, data) {
                data.context = $('<p/>').text('Uploading ' + data.files[data.files.length - 1].name + '...').appendTo('#files');
                data.submit();
            }
        }).prop('disabled', !$.support.fileInput)
                .parent().addClass($.support.fileInput ? undefined : 'disabled');
    });

    function openDialog(id, role) {
        bootbox.dialog({
                    title: "Choose Access Role",
                    message: "<form class=form-horizontal action=${applicationPath}/documents/access/" + id + " method=post>" +
                    "<select class='form-control from-group' name=role>" +
                    "<option "+(role == '' ? 'selected' : '')+"></option>" +
                    "<option "+(role == 'ADMIN' ? 'selected' : '')+">ADMIN</option>" +
                    "<option "+(role == 'TEACHER' ? 'selected' : '')+">TEACHER</option>" +
                    "<option "+(role == 'STUDENT' ? 'selected' : '')+">STUDENT</option>" +
                    "</select>" +
                    "<hr>" +
                    "<button class='btn btn-success btn-primary' type='submit'>Save</button>" +
                    "</form>"
                }
        );
    }
</script>


