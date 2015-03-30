<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="logic" uri="http://www.springframework.org/tags/form" %>

<script src="${applicationPath}/resources/js/news-url.js"></script>
<script src="${applicationPath}/resources/js/bootstrap/validator/validator.js"></script>

<div class="row">
    <div class="col-lg-12">
        <form:form action="${applicationPath}/admin/news/update" method="post" role="form" commandName="dto" data-toggle="validator">
            <div class="panel panel-info">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Logo</th>
                        <th>URL</th>
                        <th>Comma-Separated Search Words</th>
                        <th>Disabled</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr></tr>
                    <c:forEach var="url" items="${urls}">
                        <tr class="${url.disabled ? 'danger' : ''}">
                            <td>
                                <c:if test="${not empty url.imageUrl}">
                                    <img src="${url.imageUrl}" width="80" height="15"/>
                                </c:if>
                            </td>
                            <td class="form-group">
                                <input class="form-control" type="url" name="url['${url.id}']" value="${url.url}" required/>
                                <span class="help-block with-errors hidden"></span>
                            </td>
                            <td class="form-group">
                                <textarea style="resize: vertical;" rows="5" class="form-control" name="searchWords['${url.id}']" required>${url.searchWords}</textarea>
                                <span class="help-block with-errors hidden"></span>
                            </td>
                            <td class="form-group">
                                <select name="disabled['${url.id}']" class="form-control" title="">
                                    <option class="alert-danger" ${url.disabled ? 'selected':''} value="true">true</option>
                                    <option class="alert-success" ${url.disabled ? '':'selected'} value="false">false</option>
                                </select>
                            </td>
                            <td class="form-group">
                                <a href="javascript:void(0)" title="Remove" onclick="deleteRow(this, '${url.id}')">
                                    <i class="glyphicon glyphicon-remove"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <a id="add-row" class="btn btn-danger btn-primary pull-right" href="javascript:void(0)" role="button">
                <span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;Add URL
            </a>
            <button class="btn btn-danger btn-primary" type="submit" name="Update">
                <span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;<spring:message code="button.update"/>
            </button>
        </form:form>
    </div>
</div>
