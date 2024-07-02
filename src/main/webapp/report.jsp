<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="jsp-header.jsp" %>

<main class="login-box">
  <div class="container">
      <div class="login-form">
          <form action="admin-sig" method="post">
              <h1>Thông báo</h1>
                  <h2>${status}</h2>
          </form>
      </div>
  </div>
</main>

<!-- Vendor JS Files -->
<script src="${pageContext.request.contextPath}/assets/vendor/aos/aos.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/glightbox/js/glightbox.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>

<script src="${pageContext.request.contextPath}/assets/vendor/purecounter/purecounter.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/waypoints/noframework.waypoints.js"></script>

<!-- Template Main JS File -->
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/login.js"></script>

</body>
