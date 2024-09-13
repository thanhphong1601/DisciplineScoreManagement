<%-- 
    Document   : addUser
    Created on : Jun 3, 2024, 3:16:41 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<h1 class="text-center">Thêm Người Dùng</h1>

<c:url value="/users/add" var="action" />
<c:if test="${errMsg != null}">
    <div class="alert alert-danger">${errMsg}</div>
</c:if>
<form:form method="post" modelAttribute="user" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Họ tên người dùng" name="name" />
        <label for="name">Họ tên người dùng</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="email" class="form-control" path="email" id="email" placeholder="Email" name="email" />
        <label for="email">Email</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="username" id="username" placeholder="Username" name="username" />
        <label for="username">Tài khoản</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="password" class="form-control" path="password" id="password" placeholder="Mật khẩu" name="password"/>
        <label for="password">Mật khẩu</label>
    </div>

    <div class="form-floating">
        <form:select class="form-select" id="role" name="role" path="roleId">
            <c:forEach items="${roles}" var="r">
                <c:choose>
                    <c:when test="${r.id == user.roleId.getId()}">
                        <option selected value="${r.id}" >${r.role}</option>
                    </c:when>
                    <c:when test="${r.id != user.roleId.getId()}">
                        <option value="${r.id}" >${r.role}</option>
                    </c:when>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="role" class="form-label">Lựa chọn vai trò:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="class" name="classId" path="classId">
            <c:forEach items="${classes}" var="c">
                <c:choose>
                    <c:when test="${c.id == user.classId.getId()}">
                        <option selected value="${c.id}">${c.name}</option>
                    </c:when>
                    <c:when test="${c.id != user.classId.getId()}">
                        <option value="${c.id}">${c.name}</option>
                    </c:when>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="role" class="form-label">Lựa chọn lớp:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="class" name="facultyId" path="facultyId">
            <c:forEach items="${faculties}" var="f">
                <c:choose>
                    <c:when test="${f.id == user.facultyId.getId()}">
                        <option selected value="${f.id}">${f.name}</option>
                    </c:when>
                    <c:when test="${f.id != user.facultyId.getId()}">
                        <option value="${f.id}">${f.name}</option>
                    </c:when>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="role" class="form-label">Lựa chọn khoa:</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="file" accept=".png, .jpg" class="form-control" id="file" path="file"/>
        <label for="file">Ảnh đại diện</label>
        <c:if test="${user.getAvatar() != null}">
            <img src="${user.getAvatar()}" width="200" />
        </c:if>
    </div>
    <div class="form-floating mb-3 mt-3">
        <c:if test="${user.id != null}">
            <button type="submit" class="btn btn-info">Sửa thông tin</button>
        </c:if>
        <c:if test="${user.id == null}">
            <button type="submit" class="btn btn-info">Thêm người dùng</button
        </c:if>     
        <form:hidden path="id" />
    </div>
</form:form>