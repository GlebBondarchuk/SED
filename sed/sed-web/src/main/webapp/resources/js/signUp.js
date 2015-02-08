$(function(){
    $("#roleSelect").on("change", function(){
        if($(this).val() == 'STUDENT') {
            $("#details").hide();
        } else {
            $("#details").show();
        }
    });
});


/**
 * Encode password to md5 string.
 * @param id of password input.
 */
function encodePassword(id) {
    var container = $("#" + id);
    var password = container.val();
    var encodedPassword = $.md5(password);
    container.val(encodedPassword);
}

function setEmailValue(emailId, maskId) {
    var email = $("#" + emailId);
    var emailValue = email.val();
    var maskValue = $("#" + maskId).text();
    email.val(emailValue + maskValue);
}

function setNameHandler(inputNameId)  {
    $('#' + inputNameId).bind('keypress', function (event) {
        var regex = new RegExp("^[a-zA-Z]+$");
        var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
        if (!regex.test(key)) {
            event.preventDefault();
            return false;
        }
    });
}