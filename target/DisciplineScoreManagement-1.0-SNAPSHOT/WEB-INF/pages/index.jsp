<%-- 
    Document   : index
    Created on : May 25, 2024, 10:24:37 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:url value="/" var="action" />
<c:url value="/activities" var="a"/>

<h1 class="text-center text-info mt-3 mb-3">Danh sách hoạt động</h1>
<a class="btn btn-info mt-1 mb-1" style="margin-left: 5px" href="${a}">Thêm hoạt động</a>


<table class="table table-striped">
    <tr>
        <th class="text-center">Hình ảnh</th>
        <th>Id</th>
        <th>Tên Hoạt Động</th>
        <th>Người Đăng</th>
        <th>Ngày đăng</th>
        <th>Mô tả</th>
    </tr>
    <c:forEach items="${activities}" var="a">
        <tr>
            <td><img class="rounded img-fluid" src="${a.image}" width="200" alt="${a.title}"></td>
            <td>${a.id}</td>
            <td>${a.title}</td>
            <td>${a.createdBy.getName()}</td>
            <td>
                <fmt:formatDate value="${a.date}" pattern="HH:mm, dd/MM/yyyy" type="both" dateStyle="short" timeStyle="short" />
            </td>
            <td>
                <a class="btn btn-info" href="${action}">Xem chi tiết</a>
            </td>
        </tr>
    </c:forEach>
</table>
