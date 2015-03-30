function deleteRow(button, id) {
    if (id != null && id != undefined) {
        window.location.href = mainContextPath + '/admin/news/remove/' + id;
    }
    var row = $(button).parent().parent('tr');
    row.addClass("danger");
    row.fadeOut(300, function () {
        row.remove();
    });
}
$(document).ready(function () {
    var i = 10000000000000;
    $("#add-row").click(function () {
        var html = "<tr data-toggle='validator'>" +
            "<td></td>" +
            "<td><input class=form-control type=url name=url[" + i + "] required/><span class='help-block with-errors hidden'></span></td>" +
            "<td><textarea class=form-control name=searchWords[" + i + "] required/><span class='help-block with-errors hidden'></span></td>" +
            "<td><input class=text-center type=checkbox name=disabled[" + i + "]/></td>" +
            "<td><a href=javascript:void(0) title=Remove onclick='deleteRow(this)'><i class='glyphicon glyphicon-remove'></i></a></td>" +
            "</tr>";
        $('tbody tr:last').after(html);
        i++;
    });
});