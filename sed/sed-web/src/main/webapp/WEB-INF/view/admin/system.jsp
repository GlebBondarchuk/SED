<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/tld/sed_library" prefix="sed" %>

<link rel="stylesheet" href="${applicationPath}/resources/css/bootstrap/datepicker/bootstrap-datetimepicker.min.css"/>
<script src="${applicationPath}/resources/js/bootstrap/datepicker/moment-with-locales.js"></script>
<script src="${applicationPath}/resources/js/bootstrap/datepicker/bootstrap-datetimepicker.min.js"></script>

<script src="${applicationPath}/resources/js/bootstrap/validator/validator.js"></script>

<div class="row">
    <div class="col-lg-12">
        <form:form action="${applicationPath}/admin/system" method="post" commandName="dto" data-toggle="validator">
            <div class="panel-group" id="accordion">
                <div class="panel-collapse ${varStatus.first ? 'in' : ''}">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Value</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="category" items="${categorizedAttributes.keySet()}" varStatus="varStatus">
                            <c:forEach var="attribute" items="${categorizedAttributes.get(category)}">
                                <tr>
                                    <td>${attribute.displayName}</td>
                                    <td hidden="hidden">
                                        <input class="form-control" type="text" name="id" value="${attribute.id}" required/>
                                        <span class="help-block with-errors hidden"></span>
                                    </td>
                                    <td class="form-group">
                                        <c:choose>
                                            <c:when test="${attribute.bit}">
                                                <select name="values['${attribute.key}']" class="form-control" title="" required>
                                                    <option class="alert-success" ${attribute.value ? 'selected':''} value="true">true</option>
                                                    <option class="alert-danger" ${attribute.value ? '':'selected'} value="false">false</option>
                                                </select>
                                                <span class="help-block with-errors hidden"></span>
                                            </c:when>
                                            <c:when test="${attribute.date}">
                                                <input maxlength="350" class="form-control date" type="text" name="values['${attribute.key}']"
                                                       value="${attribute.value}" required/>
                                                <span class="help-block with-errors hidden"></span>
                                            </c:when>
                                            <c:otherwise>
                                                <input maxlength="350" class="form-control" type="text" name="values['${attribute.key}']"
                                                       value="${attribute.value}" required/>
                                                <span class="help-block with-errors hidden"></span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="form-group">
                                        <input maxlength="200" class="form-control" type="text" name="descriptions['${attribute.key}']"
                                               value="${attribute.description}" required/>
                                        <span class="help-block with-errors hidden"></span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>


            </div>
            <button class="btn btn-danger btn-primary" type="submit" name="Update">
                <span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;<spring:message code="button.update"/>
            </button>
        </form:form>
    </div>
</div>

<script>
    $(".date").datetimepicker({
        format: 'DD/MM/YYYY HH:mm',
        locale: '${sed:getLangLowerCase()}'
    });
</script>
