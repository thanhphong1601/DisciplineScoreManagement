<%-- 
    Document   : score
    Created on : Jun 10, 2024, 4:07:09 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:url value="/points" var="action"/>

<h1 class="text-center text-info mt-3 mb-3">Danh sách các điểm</h1>
<a href="${action}" class="btn btn-outline-dark mb-1 mt-1" style="margin-left: 5px" >Tạo Điểm</a>

<table class="table table-striped">
    <tr class="text-center">
        <th>Số Điểm</th>
        <th>Kỳ</th>
        <th>Điều</th>
        <th>Năm học</th>
        <th></th>
    </tr>
    <c:forEach items="${scores}" var="s">
        <tr class="text-center">
            <td>${s.score}</td>
            <td>${s.semester}</td>
            <td>${s.criterion}</td>
            <td>${s.fromYear} - ${s.toYear}</td>
            <td>
                <a class="btn btn-outline-info" href="#">
                    Chỉnh sửa
                </a>
            </td>

        </tr>
        
    </c:forEach>
</table>