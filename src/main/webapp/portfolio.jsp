<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="jsp-header.jsp"%>

<main id="main">
<!-- ======= Portfolio Section ======= -->
    <section id="portfolio" class="portfolio">
        <div class="container" data-aos="fade-up">
            <div class="section-title">
                <h2>Thực đơn</h2>
                <p style="display:inline-flex">
                    <input type="text" name="name" class="form-control" id="search" placeholder="Tìm kiếm" required style="width: 300px;margin-left: auto;
                    margin-right:auto;">
                    <a href="" class="linkedin">
                        <i class="bi bi-search" style="font-size: 20px; color: white; padding: 2px 5px;"></i>
                    </a>
                </p>
                <p></p>
            </div>

            <div class="row" data-aos="fade-up" data-aos-delay="100">
                <div class="col-lg-12 d-flex justify-content-center">
                    <ul id="portfolio-flters">
                        <li data-filter="*" class="filter-active">Tất cả</li>
                        <c:forEach var="category" items="${categoriesList}">
                            <li data-filter=".filter-${category.getCategoryCode()}">${category.getCategoryName()}</li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <div class="row portfolio-container" data-aos="fade-up" data-aos-delay="200">
                <c:forEach var="product" items="${productList}">
                    <div class="col-lg-4 col-md-6 portfolio-item filter-${product.getCategoryCode()}">
                        <div class="portfolio-wrap">
                            <img src="${pageContext.request.contextPath}/assets/img/product/${product.getProductCode()}.jpg" class="img-fluid" alt="">
                            <div class="portfolio-info">
                                <h4>${product.getProductName()}</h4>
                                <p>${product.getProductPrice()}đ/phần</p>
                                <div class="portfolio-links">
                                    <a href="cart?action=addToCart&productCode=${product.getProductCode()}">
                                       Đặt mua
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</main>
<!-- End Portfolio Section -->

<%@ include file="jsp-footer.jsp"%>
