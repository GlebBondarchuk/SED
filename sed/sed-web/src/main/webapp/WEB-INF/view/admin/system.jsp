<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title"><spring:message code="label.nav.admin.system"/></h3>
    </div>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>Name</th>
            <th>Value</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="attribute" items="${attributes}">
            <tr>
                <td>${attribute.displayName}</td>
                <form action="${applicationPath}/admin/system" method="post">
                    <td hidden="hidden"><input class="form-control" type="text" name="id" value="${attribute.id}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${attribute.bit}">
                                <select name="value" class="form-control" title="">
                                    <option class="alert-success" ${attribute.value ? 'selected':''} value="true">true</option>
                                    <option class="alert-danger" ${attribute.value ? '':'selected'} value="false">false</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                <input class="form-control" type="text" name="value" value="${attribute.value}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><input class="form-control" type="text" name="description" value="${attribute.description}"/></td>
                    <td><input class="form-control btn btn-danger" type="submit" name="Update" value="<spring:message code="button.update"/>"/></td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

