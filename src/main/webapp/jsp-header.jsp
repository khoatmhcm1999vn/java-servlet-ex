<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>CHicKeN</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="${pageContext.request.contextPath}/assets/img/favicon.png" rel="icon">
    <link href="${pageContext.request.contextPath}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="${pageContext.request.contextPath}/assets/vendor/aos/aos.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/login.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/confirmcart.css" rel="stylesheet">

    <!-- =======================================================
  * Template Name: Kelly - v4.6.0
  * Template URL: https://bootstrapmade.com/kelly-free-bootstrap-cv-resume-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

<!-- ======= Header ======= -->
<header id="header" class="fixed-top">
    <div class="container-fluid d-flex justify-content-between align-items-center">
        <h1 class="logo me-auto me-lg-0"><a href="index.jsp">CHicKeN</a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="index.html" class="logo"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

        <nav id="navbar" class="navbar order-last order-lg-0">
            <ul>
                <li><a class="active" href="index.jsp">Trang Chủ</a></li>
                <li><a href="about.jsp">Nhân viên</a></li>
                <li><a href="portfolio">Thực đơn</a></li>
                <li><a href="resume.jsp">Thông tin</a></li>
                <li><a href="contact.jsp">Liên hệ</a></li>
            </ul>
            <i class="bi bi-list mobile-nav-toggle"></i>
        </nav>
        <!-- .navbar -->

        <div class="header-social-links"  style="display: flex; ">
            <c:choose>
                <c:when test="${sessionScope.get('customer') == null}">
                    <a href="sig" style="font: 1em sans-serif;">đăng nhập</a>
                </c:when>
                <c:when test="${sessionScope.get('customer') != null}">
                    <a href="cart" style="font: 1em sans-serif;">giỏ hàng</a>
                    <a href="info" style="font: 1em sans-serif;">Tài khoản</a>
                    <a href="sig?action=logout" style="font: 1em sans-serif;">Thoát</a>
                </c:when>
            </c:choose>
        </div>
    </div>
</header>
<!-- End Header -->
