<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script src="${applicationPath}/resources/js/bootstrap/validator/validator.js"></script>

<c:choose>
    <c:when test="${edit}">
        <c:set var="action" value="/map/edit/${item.id}"/>
    </c:when>
    <c:otherwise>
        <c:set var="action" value="/map/add"/>
    </c:otherwise>
</c:choose>


<div class="row">
    <form class="form-horizontal col-lg-12" role="form" action="${applicationPath}${action}" method="post" data-toggle="validator">

        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.siteMap.text"/></label>

            <div class="col-sm-10">
                <input id="text" type="text" name="text" class="form-control" value="${item.text}" required/>
                <span class="help-block with-errors hidden"></span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.siteMap.url"/></label>

            <div class="col-sm-10">
                <c:choose>
                    <c:when test="${not empty item.url}">
                        <input id="url" type="url" name="url" class="form-control" value="${baseURL}${item.url}"/>
                    </c:when>
                    <c:otherwise>
                        <input id="url" type="url" name="url" class="form-control" value=""/>
                    </c:otherwise>
                </c:choose>
                <span class="help-block with-errors hidden"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.siteMap.authorizedOnly"/></label>

            <div class="col-sm-10">
                <select name="role" class="form-control" id="contentRole" placeholder="Choose Authority" title="Choose Authority">
                    <c:forEach var="role" items="${roles}">
                        <c:choose>
                            <c:when test="${role == null}">
                                <c:choose>
                                    <c:when test="${role == item.role}">
                                        <option selected value="${role}">ALL</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${role}">ALL</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:when test="${item.role == role}">
                                <c:choose>
                                    <c:when test="${empty role}">
                                        <option selected value="${role}">ALL</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option selected>${role}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <option>${role}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <span class="help-block with-errors hidden"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.siteMap.listNumber"/></label>

            <div class="col-sm-10">
                <input id="listNumber" type="text" name="listNumber" class="form-control" value="${empty item.listNumber ? 0 : item.listNumber}" required/>
                <span class="help-block with-errors hidden"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.siteMap.parent"/></label>

            <div class="col-sm-10">
                <select id="parent" name="parent" class="form-control" name="parent" title="">
                    <option value=""></option>
                    <c:forEach var="parent" items="${parents}">
                        <c:if test="${parent.id != item.id}">
                            <c:choose>
                                <c:when test="${not empty item.parent and item.parent.id == parent.id}">
                                    <option selected value="${parent.id}">${parent.text}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${parent.id}">${parent.text}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <button class="btn btn-danger btn-primary pull-right" type="submit" name="Save">
            <span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;
            <spring:message code="button.save"/>
        </button>
    </form>
</div>