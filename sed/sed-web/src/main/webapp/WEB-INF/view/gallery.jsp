<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link href="${applicationPath}/resources/css/jquery.fileupload.css" rel="stylesheet">
<link href="${applicationPath}/resources/css/jquery.fileupload-ui.css" rel="stylesheet">

<c:forEach var="row" items="${galleries}">
    <!-- Projects Row -->
    <div class="row">
        <c:forEach var="gallery" items="${row}">
            <div class="col-md-4 img-portfolio">
                <security:authorize access="hasRole('ADMIN')">
                    <a href="${applicationPath}/gallery/delete/${gallery.id}" title="Remove Gallery Image">
                        <i class="glyphicon glyphicon-remove"></i>
                    </a>
                </security:authorize>
                <a onclick="view('${gallery.document.path}')">
                    <img style="max-width: 360px; max-height: 206px" class="img-responsive img-hover" src="${applicationPath}/get/${gallery.document.path}" alt="${gallery.document.name}">
                </a>

                <h6>
                    <i class="wysiwyg-color-gray pull-right">
                        <small>${gallery.document.createdDate}</small>
                    </i>
                </h6>
                <security:authorize access="hasRole('ADMIN')">
                    <form action="${applicationPath}/gallery/edit/${gallery.id}" method="post">
                        <div class="form-group">
                            <label class="control-label"><spring:message code="label.gallery.description"/></label>
                            <textarea name="description" class="form-control" placeholder="Description"
                                      maxlength="300">${gallery.description}</textarea>
                        </div>
                        <button class="btn btn-danger btn-primary" type="submit" name="Update">
                            <span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;<spring:message code="button.update"/>
                        </button>
                    </form>
                </security:authorize>
                <security:authorize access="isAnonymous() && permitAll()">
                    <p style="word-wrap:break-word;">${gallery.description}</p>
                </security:authorize>
            </div>
        </c:forEach>
    </div>
    <hr>
    <!-- /.row -->
</c:forEach>


<c:set var="next" value="${applicationPath}/gallery/${limit}/${offset - limit > 0 ? offset - limit : 0}"/>
<c:set var="previous" value="${applicationPath}/gallery/${limit}/${offset + limit >= count ? count - offset - limit : offset + limit}"/>

<c:set var="disabledOlder" value="${offset + limit >= count ? 'return false':''}"/>
<c:set var="disabledNewer" value="${offset - limit < 0 ? 'return false':''}"/>

<c:if test="${not empty galleries}">
    <!-- Pager -->
    <ul class="pager">
        <li class="previous ${offset + limit >= count ? 'disabled':''}">
            <a onclick="${disabledOlder}" href="${previous}">&larr; <spring:message code="label.news.older"/></a>
        </li>
        <li class="next ${offset - limit < 0 ? 'disabled':''}">
            <a onclick="${disabledNewer}" href="${next}"><spring:message code="label.news.newer"/> &rarr;</a>
        </li>
    </ul>
</c:if>
<!-- /.row -->

<script>
    function view(path) {
        bootbox.dialog({
            size: 'large',
            message: '<img class="img-responsive" src="${applicationPath}/get/' + path  + '" alt="">'
        });
    }
</script>

<security:authorize access="hasRole('ADMIN')">
    <div id="errors"></div>
    <form action="${applicationPath}/gallery/upload" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
            <span><spring:message code="label.galery.selectFile"/></span>
                <input type="file" name="file" id="fileupload" class="fileupload form-control">
            </span>
        </div>
    </form>

    <script src="${applicationPath}/resources/js/vendor/jquery.ui.widget.js"></script>

    <script src="${applicationPath}/resources/js/canvas-to-blob.min.js"></script>
    <script src="${applicationPath}/resources/js/load-image.all.min.js"></script>

    <script src="${applicationPath}/resources/js/jquery.iframe-transport.js"></script>
    <script src="${applicationPath}/resources/js/jquery.fileupload.js"></script>
    <script src="${applicationPath}/resources/js/jquery.fileupload-process.js"></script>
    <script src="${applicationPath}/resources/js/jquery.fileupload-image.js"></script>
    <script src="${applicationPath}/resources/js/jquery.fileupload-validate.js"></script>

    <script>
        $(function () {
            $('#fileupload').fileupload({
                url: "${applicationPath}/gallery/upload",
                autoUpload: true,
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                maxFileSize: 10485760, // 10 MB
                done: function (e, data) {
                    window.location.reload();
                },
                fail: function (e, data) {
                    $.each(data.files, function (index) {
                        var error = $('<span class="text-danger"/>').text('File upload failed.');
                        $(data.context.children()[index])
                                .append('<br>')
                                .append(error);
                    });
                }
            }).on('fileuploadprocessalways', function (e, data) {
                var file = data.files[data.index];
                var target = $("#errors");
                if (file.error) {
                    target.html("");
                    target.append($('<div class="alert alert-danger" role="alert"></div>').text(file.error));
                }
            })
        });
    </script>
</security:authorize>
