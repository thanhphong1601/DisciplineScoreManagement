<%-- 
    Document   : missingReport
    Created on : Jun 11, 2024, 10:25:01 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<c:url value="/reports" var="action"/>
<h1 class="text-center mt-3 mb-3">Báo Thiếu</h1>
<form action="${action}" method="get" style="margin-left: 40%; margin-right: 40%">
    <div class="form-floating mt-3 mb-1">
        <select class="form-select" name="facultyId">
            <c:forEach items="${faculties}" var="f">
                <c:if test="${f.id == param.facultyId}">
                    <option selected value="${f.id}">${f.name}</option>
                </c:if>
                <c:if test="${f.id != param.facultyId}">
                    <option value="${f.id}">${f.name}</option>
                </c:if>
            </c:forEach>
        </select>
        <label for="facultyId" class="form-label">Khoa:</label>
    </div>
    <button type="submit" class="btn btn-outline-dark mb-2">Tìm</button>
</form>
<table class="table table-striped">
    <tr>
        <th>Mã Phiếu</th>
        <th>Sinh Viên</th>
        <th>Lớp</th>
        <th>Khoa</th>
        <th>Hoạt động</th>
        <th>Bằng chứng</th>
        <th></th>
    </tr>
    <c:forEach items="${reports}" var="r">
        <tr>
            <td>${r[0].id}</td>
            <td>${r[1].name}</td>
            <td>${r[4].name}</td>
            <td>${r[3].name}</td>
            <td>${r[2].title}</td>
            <td>
                <img class="card-img-top" src="${r[0].proof}" style="width:200px;">
            </td>
            <td>
                <c:if test="${r[0].status eq 'Pending'}">
                    <c:url value="/api/reports/${r[0].id}/" var="url"/>
                    <button onclick="confirmReport('${url}', '${r[0].id}')" class="btn btn-info">Xác nhận</button>
                    <button onclick="deleteReport('${url}', '${r[0].id}')" class="btn btn-danger">Hủy</button>
                </c:if>
                <c:if test="${r[0].status eq 'Confirmed'}"> 
                    <a href="#" class="btn btn-outline-dark">Đã Xác Nhận</a>
                </c:if>

            </td>
        </tr>
    </c:forEach>
</table>

<script src="<c:url value="/js/script.js"/>"></script>
