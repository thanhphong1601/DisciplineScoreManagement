<%-- 
    Document   : header
    Created on : Jun 3, 2024, 1:17:46 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<nav class="navbar navbar-expand-sm bg-info navbar-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Quản Trị Hoạt Động</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/" />">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/users" />">Người Dùng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/points/list" />">Danh Sách Loại Điểm</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/reports" />">Danh Sách Báo Thiếu</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/activities/attending" />">Danh Sách Tham Gia</a>
                </li>
                
                <li class="nav-time dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Thống kê
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="<c:url value="/stats/faculty" />">Thống kê theo khoa</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/stats/class"/>">Thống kê theo lớp</a></li>
                        <li><a class="dropdown-item" href="#">Thống kê theo thành tích</a></li>
                    </ul>
                </li>
                

                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name == null}">
                        <li class="nav-item">
                            <a class="btn btn-danger" href="<c:url value="/login" />">Đăng Nhập</a>
                        </li>
                    </c:when>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <li class="nav-item">
                            <a class="btn btn-danger" href="<c:url value="/" />">Xin chào ${pageContext.request.userPrincipal.name}</a>
                        </li>
                        <li class="nav-item">
                            <a class="btn btn-info" href="<c:url value="/logout" />">Đăng Xuất</a>
                        </li>
                    </c:when>
                </c:choose>
            </ul>

        </div>
        <form action="<c:url value="/" />" class="d-flex">
            <input class="form-control me-2" name="kw" type="text" placeholder="Tìm kiếm tên hoạt động...">
            <button class="btn btn-primary" type="submit">Tìm</button>
        </form>
    </div>
</nav>