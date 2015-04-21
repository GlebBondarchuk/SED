<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="row">
    <div class="col-lg-12">
        <c:forEach var="notification" items="${notifications}">
            <div class="alert alert-success alert-dismissible">
                <a href="${applicationPath}/notification/remove/${notification.id}" type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></a>
                <strong>${notification.message}</strong>

                <div class="pull-right">
                    <i><spring:message code="label.notifications.from"/>: ${notification.from.name}</i>&nbsp;<i>&nbsp;${notification.sendDate}</i>
                </div>
            </div>
            <hr>
        </c:forEach>

    </div>
    <security:authorize access="hasAnyRole('TEACHER','ADMIN')">
        <div class="col-lg-12">
            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#sendNotification"><spring:message code="label.notifications.send"/></a>
            <br>
            <br>
            <div id="sendNotification" class="panel-collapse collapse">
                <div class="form-group">
                        <%--<div class="col-lg-3">--%>
                    <select id="users" multiple size="10" class="form-control">
                        <c:forEach var="user" items="${users}">
                            <option value="${user.id}">${user.name}</option>
                        </c:forEach>
                    </select>
                        <%--</div>--%>
                </div>


                <div class="form-group">
                        <%--<div class="col-lg-9">--%>
                    <textarea style="resize: vertical;" rows="5" maxlength="500" class="form-control" name="text" id="message" required placeholder="Enter text..."></textarea>
                    <span class="help-block with-errors hidden"></span>
                        <%--</div>--%>
                </div>

                <a onclick="send()" class="btn btn-danger btn-primary" type="button" name="Send">
                    <span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;
                    <spring:message code="contact.button.sendMessage"/>
                </a>
            </div>

        </div>
    </security:authorize>
</div>
<security:authorize access="hasAnyRole('TEACHER','ADMIN')">
    <script>
        function send() {
            $.ajax({
                type: "post",
                url: "${applicationPath}/notification/send",
                data: {
                    to: $("#users").val() + "",
                    message: $("#message").val()
                }
            }).done(function (data) {
                window.location.reload();
            });
        }
    </script>
</security:authorize>