/**
 * Encode password to md5 string.
 * @param id of password input.
 */
function encodePassword(id) {
    var password = $(id).text();
    var encodedPassword = $.md5(password);
    $(id).text(encodedPassword);
}