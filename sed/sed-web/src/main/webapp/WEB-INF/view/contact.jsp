<%@ taglib prefix="secutiry" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!-- Content Row -->
<div class="row">
    <!-- Map Column -->
    <div class="col-md-8">
        <!-- Embedded Google Map -->
        <iframe width="100%" height="400px" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="${mapURL}"></iframe>
    </div>
    <!-- Contact Details Column -->
    <div class="col-md-4">
        ${contactDetails.text}
        <security:authorize access="hasRole('ADMIN')">
            <a href="${applicationPath}/text/${contactDetails.id}">
                <i class="glyphicon glyphicon-edit"></i>
            </a>
        </security:authorize>
    </div>
</div>
<!-- /.row -->

<!-- Contact Form -->
<!-- In order to set the email address and subject line for the contact form go to the bin/contact_me.php file. -->
<secutiry:authorize access="isAuthenticated()">
    <div class="row">
        <div class="col-md-8">
            <h3><spring:message code="label.contact.sendMessage"/></h3>

            <form name="sentMessage" id="contactForm" novalidate action="${applicationPath}/contact" method="post">
                <div class="control-group form-group">
                    <div class="controls">
                        <label><spring:message code="label.contact.subject"/>:</label>
                        <input type="text" class="form-control" name="subject" required data-validation-required-message="Please enter your name.">

                        <p class="help-block"></p>
                    </div>
                </div>
                <div class="control-group form-group">
                    <div class="controls">
                        <label><spring:message code="label.contact.message"/>:</label>
                    <textarea rows="10" cols="100" class="form-control" name="message" required
                              data-validation-required-message="Please enter your message" maxlength="999" style="resize:none"></textarea>
                    </div>
                </div>
                <div id="success"></div>
                <!-- For success/fail messages -->
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;<spring:message
                        code="contact.button.sendMessage"/></button>
            </form>
        </div>
    </div>
    <!-- /.row -->
</secutiry:authorize>