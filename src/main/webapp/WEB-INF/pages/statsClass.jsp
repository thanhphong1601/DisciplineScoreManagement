<%-- 
    Document   : statsClass
    Created on : Jun 14, 2024, 10:44:25 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1 class="text-center mt-3 mb-3">Thống kê theo lớp</h1>
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
    <button onclick="window.location.href='<c:url value="/stats/class/${2}/pdf"/>'" class="btn btn-info mt-1 mb-2" style="margin-right: 10px">Xuất PDF</button>
</div>
<table class="table table-striped">
    <tr>
        <th>Mã lớp</th>
        <th>Tên lớp</th>
        <th>Tổng số sinh viên</th>
        <th>Tổng số lượt đăng ký tham gia</th>
        <th>Sinh viên tích cực nhất</th>
        <th>Số hoạt động tham gia</th>
        <th>ĐRL</th>
        <th>Hoạt động được tham gia nhiều nhất</th>
        <th>Không tham gia</th>

    </tr>
    <c:forEach items="${stats}" var="s">
        <tr>
            <td>${s[0]}</td>
            <td>${s[1]}</td>
            <td>${s[2]}</td>
            <td>${s[3]}</td>
            <td>${s[4][1]}</td>
            <td>${s[4][2]}</td>
            <td>${s[8][2]}</td>
            <td>${s[6][1]}</td>
            <td>${s[7]}</td>

        </tr>
    </c:forEach>
</table>

<div class="row">
    <div class="col-md-12 col-12 mt-3">
        <canvas id="myChart1"></canvas>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    let labels = [];
    let data1 = [];
    let data2 = [];
    let data3 = [];
    <c:forEach items="${stats}" var="s">
    labels.push('${s[1]}');
    data1.push('${s[2]}');
    data2.push('${s[3]}');
    data3.push('${s[7]}');
    </c:forEach>

    function drawChart(ctx, labels, data1, data2) {
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [
                    {
                        label: 'Số lượng sinh viên của lớp',
                        data: data1,
                        backgroundColor: ['#f2acb9'],
                        borderWidth: 2,
                        borderRadius: 20,

                    },
                    {
                        label: 'Số lượng sinh viên đăng ký tham gia hoạt động',
                        data: data2,
                        backgroundColor: ['#efc06e'],
                        borderWidth: 2,
                        borderRadius: 20,
                    },
                    {
                        label: 'Số lượng sinh viên không tham gia',
                        data: data3,
                        backgroundColor: ['#6cace4'],
                        borderWidth: 2,
                        borderRadius: 20,
                    }
                ],
            },
            options: {
                plugins: {
                    title: {
                        display: true,
                        text: 'Biểu đồ thống kê sinh viên tham gia hoạt động theo lớp'
                    },
                    legend: {
                        position: 'bottom',
                    }
                },
                responsive: true,
                scales: {
                    x: {
                        stacked: true
                    },
                    y: {
                        stacked: true
                    }
                }
            }
        });
    }

    window.onload = function () {
        let ctx1 = document.getElementById("myChart1");

        drawChart(ctx1, labels, data1, data2);
    }
</script>