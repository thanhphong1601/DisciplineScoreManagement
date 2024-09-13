<%-- 
    Document   : testPage
    Created on : Jun 11, 2024, 2:51:30 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1 class="text-center">Test Page</h1>
<div>
    <table class="table table-striped">
        <tr>
            <th>Khoa</th>
            <th>Tổng điểm</th>

        </tr>
            <c:forEach items="${stats}" var="s">
            <tr>
                <td>${s[0]}</td>
                <td>${s[1]}</td>
            </tr>
                
            </c:forEach>
    </table>

</div>
