<%-- 
    Document   : stats
    Created on : Jun 13, 2024, 9:08:32 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1 class="text-center mt-3 mb-3">Thống kê báo cáo</h1>
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
    <button onclick="window.location.href='<c:url value="/stats/faculty/${1}/pdf"/>'" class="btn btn-info mt-1 mb-2" style="margin-right: 10px">Xuất PDF</button>
</div>
<table class="table table-striped">
    <tr>
        <th>Mã khoa</th>
        <th>Tên khoa</th>
        <th>Tổng số hoạt động</th>
        <th>Tổng số sinh viên tham gia</th>
        <th>Hoạt động được tham gia nhiều nhất</th>
        <th>Số lượng sinh viên tham gia hoạt động</th>

    </tr>
    <c:forEach items="${stats}" var="s">
        <tr>
            <td>${s[0]}</td>
            <td>${s[1]}</td>
            <td>${s[2]}</td>
            <td>${s[3]}</td>
            <td>${s[4][1]}</td>
            <td>${s[4][2]}</td>
        </tr>
    </c:forEach>
</table>

<div class="row">
    <div class="col-md-6 col-12 mt-3">
        <canvas id="myChart1"></canvas>
    </div>
    <div class="col-md-6 col-12 mt-3">
        <canvas id="myChart2"></canvas>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    //chart1
    let labels = [];
    let data1 = [];
    let data2 = [];

    //chart2
    let labels2 = [];
    let data = [];


    <c:forEach items="${stats}" var="s">
    labels.push('${s[1]}');
    data1.push('${s[2]}');
    data2.push('${s[3]}');
    </c:forEach>

    <c:forEach items="${stats}" var="s">
    labels2.push('${s[4][1]}');
    data.push('${s[4][2]}');
    </c:forEach>

    function drawChart(ctx, labels, data1, data2) {
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                        label: 'Tổng số hoạt động',
                        data: data1,
                        borderColor: 'red',
                        backgroundColor: ['lightblue'],
                        order: 1
                    }, {
                        label: 'Số lượng sinh viên tham gia',
                        data: data2,
                        borderColor: ['peach'],
                        backgroundColor: ['lightblue'],
                        type: 'line',
                        order: 0
                    }]

            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'bottom',
                    },
                    title: {
                        display: true,
                        text: 'Biểu đồ thống kê số hoạt động và số lượng sinh viên'
                    }
                }
            }
        });

    }
    ;

    function drawChart2(ctx, labels, data) {
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                        label: 'Số lượng sinh viên tham gia',
                        data: data,
                        borderWidth: 1,
                        backgroundColor: ['#F2C9D5', '#B43E44', '#FADF92', 'gold', 'brown']
                    }],
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                },
                plugins: {
                    legend: {
                        position: 'bottom',
                    },
                    title: {
                        display: true,
                        text: 'Biểu đồ thống kê hoạt động được tham gia nhiều nhất'
                    }
                }
            }
        });
    }



    window.onload = function () {
        let ctx1 = document.getElementById("myChart1");
        let ctx2 = document.getElementById("myChart2");

        drawChart(ctx1, labels, data1, data2);
        drawChart2(ctx2, labels2, data);
    };
</script>