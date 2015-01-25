package com.bsu.sed.utils;


import org.junit.Assert;
import org.junit.Test;

/**
 * @author gbondarchuk
 */
public class ByteArrayUtilsTest {
    @Test
    public void test() {
        String html = "<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" trimDirectiveWhitespaces=\"true\" %>\n" +
                "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>\n" +
                "<%@ taglib uri=\"http://tiles.apache.org/tags-tiles\" prefix=\"tiles\" %>\n" +
                "<%@ taglib prefix=\"spring\" uri=\"http://www.springframework.org/tags\" %>\n" +
                "\n" +
                "<c:set var=\"applicationPath\" value=\"${pageContext.request.contextPath}\" scope=\"application\"/>\n" +
                "\n" +
                "<!DOCTYPE HTML>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "\n" +
                "    <%--css styles--%>\n" +
                "    <%--<link rel=\"stylesheet\" href=\"${applicationPath}/resources/css/main.css\"/>--%>\n" +
                "    <link rel=\"stylesheet\" href=\"${applicationPath}/resources/css/bootstrap.css\"/>\n" +
                "    <link rel=\"stylesheet\" href=\"${applicationPath}/resources/css/bootstrap-table.css\"/>\n" +
                "    <link rel=\"stylesheet\" href=\"${applicationPath}/resources/css/bootstrap-theme.css\"/>\n" +
                "    <link rel=\"stylesheet\" href=\"${applicationPath}/resources/css/bootstrap-responsive.css\"/>\n" +
                "\n" +
                "    <script src=\"${applicationPath}/resources/js/jquery/jquery-1.11.2.js\"></script>\n" +
                "    <script src=\"${applicationPath}/resources/js/bootstrap.js\"></script>\n" +
                "    <%--<script src=\"${applicationPath}/resources/js/bootstrap-paginator.js\"></script>--%>\n" +
                "    <script src=\"${applicationPath}/resources/js/bootstrap-table-export.js\"></script>\n" +
                "    <script src=\"${applicationPath}/resources/js/bootstrap-table-filter.js\"></script>\n" +
                "    <script src=\"${applicationPath}/resources/js/bootstrap-table.js\"></script>\n" +
                "    <%--<script src=\"${applicationPath}/resources/js/bootstrap-table-editable.js\"></script>--%>\n" +
                "    <script src=\"${applicationPath}/resources/js/bootstrap-table-flatJSON.js\"></script>\n" +
                "    <script src=\"${applicationPath}/resources/js/bootstrap-table-en-US.js\"></script>\n" +
                "\n" +
                "    <%--page icon--%>\n" +
                "    <link rel=\"shortcut icon\" href=\"${applicationPath}/resources/image/main/favicon.png\" type=\"image/x-icon\">\n" +
                "\n" +
                "    <%--page title--%>\n" +
                "    <c:set var=\"title\"><tiles:insertAttribute name=\"title\"/></c:set>\n" +
                "    <title><spring:message code=\"${title}\"/></title>\n" +
                "\n" +
                "    <script>\n" +
                "        function dropBanner() {\n" +
                "            for (var i = 1; i < 99999; i++)\n" +
                "                window.clearInterval(i);\n" +
                "        }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body onload=\"dropBanner()\">\n" +
                "\n" +
                "<header>\n" +
                "    <tiles:insertAttribute name=\"header-content\"/>\n" +
                "</header>\n" +
                "<div class=\"jumbotron\">\n" +
                "    <%--<div id=\"content-top\">--%>\n" +
                "    <%--<tiles:insertAttribute name=\"menu-content\"/>--%>\n" +
                "    <tiles:insertAttribute name=\"primary-content\"/>\n" +
                "    <%--</div>--%>\n" +
                "</div>\n" +
                "<footer>\n" +
                "    <tiles:insertAttribute name=\"footer-content\"/>\n" +
                "</footer>\n" +
                "</body>\n" +
                "</html>\n";
        byte[] bytes = html.getBytes();
        String decodedHtml = new String(bytes);
        Assert.assertEquals(html, decodedHtml);
    }
}
