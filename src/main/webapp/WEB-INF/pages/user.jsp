<%-- 
    Document   : user
    Created on : Jun 3, 2024, 2:35:20 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1 class="text-center text-info">Danh sách người dùng</h1>
<a href="<c:url value="/users/add" />" class="btn btn-info mb-1 mt-1" style="margin-left: 5px">Thêm Người Dùng</a>
<table class="table table-striped">
    <tr>
        <th>Ảnh đại diện</th>
        <th>Id</th>
        <th>Tên</th>
        <th>Vai trò</th>
        <th></th>
    </tr>
    <c:forEach items="${users}" var="u">
        <tr>
            <td><img class="rounded img-fluid" src="${u.avatar}" width="100" alt="${u.name}"></td>
            <td>${u.id}</td>
            <td>${u.name}</td>
            <td>${u.getRoleName()}</td>
            <td>
                <c:url value="/users/${u.id}" var="url" />
                <c:url value="/" var="urlDelete" />
                <a href="${url}" class="btn btn-info">Cập nhật thông tin người dùng</a>
            </td>
        </tr>
        
    </c:forEach>
</table>