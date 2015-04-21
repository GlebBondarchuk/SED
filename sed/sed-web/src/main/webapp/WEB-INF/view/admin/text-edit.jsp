<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="${applicationPath}/resources/js/bootstrap/validator/validator.js"></script>

<div class="row">
    <form class="form-horizontal col-lg-12" role="form" action="${applicationPath}/text/${text.id}" method="post">

        <div class="form-group">
            <div class="col-sm-10">
                <textarea maxlength="16777215" style="resize: vertical;" rows="10" class="form-control" name="text">${text.text}</textarea>
            </div>
        </div>

        <button class="btn btn-danger btn-primary" type="submit" name="Save">
            <span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;
            <spring:message code="button.save"/>
        </button>
    </form>
</div>