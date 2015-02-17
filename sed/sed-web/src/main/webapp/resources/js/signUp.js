$(function () {
    choseRole();
});


function choseRole() {
    var roleSelect = $("#roleSelect");

    var teacher = $(".teacher");
    var teacherFormControls = teacher.find(".form-control");
    var student = $(".student");
    var studentFormControls = student.find(".form-control");
    onRoleChange(roleSelect.val(), teacher, teacherFormControls, student, studentFormControls);
    roleSelect.on("change", function () {
        onRoleChange($(this).val(), teacher, teacherFormControls, student, studentFormControls);
    });
}


function onRoleChange(role, teacher, teacherFormControls, student, studentFormControls) {
    var form = $("#signUpForm");
    if (role == 'STUDENT') {
        teacher.hide();
        teacherFormControls.attr("disabled", "disabled");
        studentFormControls.removeAttr("disabled");
        student.show();
        form.attr('action', mainContextPath + '/signUp');
    } else {
        student.hide();
        studentFormControls.attr("disabled", "disabled");
        teacherFormControls.removeAttr("disabled");
        teacher.show();
        form.attr('action', mainContextPath + '/admin/users/add');
    }
}