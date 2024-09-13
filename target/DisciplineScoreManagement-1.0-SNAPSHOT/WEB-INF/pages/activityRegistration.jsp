<%-- 
    Document   : activityRegistration
    Created on : Jun 12, 2024, 11:52:00 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1 class="text-center mt-3 mb-3">Danh sách đăng ký hoạt động</h1>
<table class="table table-striped">
    <tr>
        <th>Mã hoạt động</th>
        <th>Tên hoạt động</th>
        <th>Sinh viên tham gia</th>
        <th>Lớp sinh viên</th>
        <th>Khoa</th>
    </tr>
        <c:forEach items="${joinedActivities}" var="a">
        <tr>
                    <td>${a[0].id}</td>
                    <td>${a[0].title}</td>
                    <td>${a[1].name}</td>
                    <td>${a[3].name}</td>
                    <td>${a[2].name}</td>
        </tr>
        </c:forEach>
</table>
