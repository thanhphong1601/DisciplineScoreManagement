<%-- 
    Document   : createActivity
    Created on : Jun 9, 2024, 2:53:02 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<c:url value="/activities" var="action" />
<c:url value="/points" var="a" />


<h1 class="text-center mt-3 mb-1">Tạo Hoạt Động</h1>


<form:form method="post" modelAttribute="activity" action="${action}" enctype="multipart/form-data" >
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="title" id="title" placeholder="Tiêu đề hoạt động" name="title"/>
        <label for="title">Tiêu đề hoạt động</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="description" id="description" placeholder="Nội dung hoạt động" name="description"/>
        <label for="title">Nội dung hoạt động</label>
    </div>


    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="createdBy" name="createdBy" path="createdBy">
            <c:forEach items="${users}" var="u">
                <c:if test="${u.roleId.id == 2 or u.roleId.id == 3}">
                    <option value="${u.id}">${u.name}</option>
                </c:if>
            </c:forEach>

        </form:select>
        <label for="createdBy" class="form-label">Người đăng</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="facultyId" name="facultyId" path="facultyId">
            <c:forEach items="${faculties}" var="f">
                <option value="${f.id}">${f.name}</option>
            </c:forEach>

        </form:select>
        <label for="createdBy" class="form-label">Người đăng</label>
    </div>

    <div class="form-floating mt-3 mb-3">
        <form:select class="form-select" id="disciplineScoreid" name="disciplineScoreid" path="disciplineScoreid">
            <c:forEach items="${scores}" var="s">
                <option value="${s.id}">
                <table>
                    <td>${s.score} điểm - </td>
                    <td>Điều ${s.criterion} - </td>
                    <td>Kỳ ${s.semester} - </td>
                    <td>${s.fromYear} đến ${s.toYear} </td>
                </table>
            </option>
        </c:forEach>
    </form:select>
    <label for="disciplineScoreid" class="form-label">Lựa chọn số điểm</label>

    <a href="${a}" class="btn btn-dark mt-2">Tạo điểm</a>


</div>

<div class="form-floating mb-3 mt-3">
    <form:input type="file" accept=".png, .jpg" class="form-control" id="file" path="file"/>
    <label for="file">Chọn ảnh cho hoạt động</label>
</div>



<div class="form-floating mb-3 mt-3">
    <button type="submit" class="btn btn-info">Thêm hoạt động</button   
    <form:hidden path="id" />
</div>

</form:form>

<script type="text/javascript">
    function autoResize(textarea) {
        textarea.style.height = 'auto'; // Đặt chiều cao tự động
        textarea.style.height = textarea.scrollHeight + 'px'; // Đặt chiều cao theo nội dung
    }
</script>