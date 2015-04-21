function breakWordFormatter(value) {
    return "<div style='word-break: break-all;'>" + value + "</div>"
}

function rowStyle(row) {
    if (row.disabled) {
        return {classes: "text-muted warning"};
    }
    return {classes: ""};
}

function imageFormatter(value) {
    return '<div class="row text-center"><img src="' + value + '" style="max-width: 50px"/></div>'
}

function linkFormatter(value) {
    return "<a target='_blank' href=" + value + ">" + value + "</a>";
}

function ajaxGet(url) {
    $.ajax(url).done(function () {
        $("#bootstrapTable").bootstrapTable('refresh');
    });
}

function deleteSelected(url) {
    var table = $("#bootstrapTable");
    var array = table.bootstrapTable("getSelections");
    if (array.length == 0) {
        return;
    }
    var ids = [];
    $.each(array, function (index, value) {
        ids.push(value.id);
    });
    $.ajax({
        url: mainContextPath + url,
        type: "post",
        data: {
            ids: ids + ""
        }
    }).done(function () {
        table.bootstrapTable("refresh");
    });
}