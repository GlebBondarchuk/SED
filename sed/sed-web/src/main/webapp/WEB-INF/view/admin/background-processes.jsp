<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-info">
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Process</th>
                    <th>Cron</th>
                    <th>Next Execution Time</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${processes}" var="process">
                    <tr>
                        <td>${process.displayName}</td>
                        <td>${process.cron}</td>
                        <td>
                                ${process.nextExecutionTime}
                            <c:if test="${not process.executing and not process.disabled}">
                                <a href="${applicationPath}/admin/processes/start/${process.processKey}">Start Now</a>
                            </c:if>
                        </td>
                        <td>${process.description}</td>
                        <td class="${process.executing  ? "alert-success" : process.disabled ? "alert-danger" : "alert-info"}">
                            <c:choose>
                                <c:when test="${process.executing}">
                                    Executing
                                </c:when>
                                <c:when test="${process.disabled}">
                                    Disabled
                                </c:when>
                                <c:otherwise>
                                    Waiting
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${process.disabled}">
                                    <a href="${applicationPath}/admin/processes/enable/${process.processKey}" title="Enable">
                                        <i class="glyphicon glyphicon-ok"></i>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${applicationPath}/admin/processes/disable/${process.processKey}" title="Disable">
                                        <i class="glyphicon glyphicon-remove"></i>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                            <a href="javascript:void(0)" onclick="rescheduleProcess('${process.processKey}','${process.cron}')" title="Reschedule">
                                <i class="glyphicon glyphicon-time"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    function rescheduleProcess(processName, processCron) {
        bootbox.prompt({
            title: "Reschedule Process",
            value: processCron,
            callback: function (result) {
                if (result === null) {

                } else {
                    window.location.href = mainContextPath + "/admin/processes/reschedule/" + processName + "/" + encodeURIComponent(result);
                }
            }
        });
    }
</script>
