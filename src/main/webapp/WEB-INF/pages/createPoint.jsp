<%-- 
    Document   : createPoint
    Created on : Jun 10, 2024, 2:10:58 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<c:url value="/points" var="action"/>

<h1 class="text-center mt-3 mb-3">Trang tạo điểm</h1>
<c:if test="${errMsg != null}">
    <h1>${errMsg}</h1>
</c:if>

<form:form method="post" modelAttribute="s" action="${action}" >
    
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" id="score" placeholder="Số điểm" path="score"/>
        <label for="score">Số điểm</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" id="criterion" placeholder="Điều" path="criterion"/>
        <label for="criterion">Điều</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="semester" name="semester" path="semester">
            <option value="1">Kỳ 1</option>
            <option value="2">Kỳ 2</option>
            <option value="3">Kỳ 3</option>
        </form:select>
        <label for="semester" class="form-label">Lựa chọn học kỳ</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" id="fromYear" placeholder="Từ năm" path="fromYear"/>
        <label for="fromYear">Từ năm</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" id="toYear" placeholder="Đến năm" path="toYear"/>
        <label for="toYear">Đến năm</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">Thêm điểm</button   
        <form:hidden path="id" />
    </div>

</form:form>