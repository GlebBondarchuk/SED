<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <c:forEach items="${attributes}" var="attribute">
        <tr>
            <td>
                <c:out value="${attribute.displayName}"/>
            </td>
            <td>
                <c:out value="${attribute.value}"/>
            </td>
            <td>
                <c:out value="${attribute.description}"/>
            </td>
        </tr>
    </c:forEach>
</table>
