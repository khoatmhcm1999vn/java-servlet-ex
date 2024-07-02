<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="jsp-header.jsp"%>
<!-- End Header -->

<main id="main">
    <!-- ======= About Section ======= -->
    <section id="about" class="about">
        <div class="container" data-aos="fade-up" style="background-color: transparent;">
            <div class="section-title">
                <h2>Giỏ hàng</h2>
            </div>
            <div class="container" style="background-color: orange;">
                <table id="cart" class="table table-hover table-condensed">
                    <thead>
                        <tr>
                            <th style="width:50%;">Tên sản phẩm</th>
                            <th style="width:10%">Giá</th>
                            <th style="width:8%">Số lượng</th>
                            <th style="width:22%" class="text-center">Thành tiền</th>
                            <th style="width:15%" colspan="2"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="lineItem" items="${cart.items}">
                            <tr>
                                <td data-th="Product">
                                    <div class="row">
                                        <div class="col-sm-2 hidden-xs">
                                            <img src="${pageContext.request.contextPath}/assets/img/product/${lineItem.getProduct().getProductCode()}.jpg"
                                                 alt=""
                                                 class="img-responsive" width="100">
                                        </div>
                                        <div class="col-sm-10">
                                            <h6 class="nomargin"
                                                style="font-size: 20px; text-align: center;">
                                                    ${lineItem.getProduct().getProductName()}
                                            </h6>
                                            <p style="text-align:center; padding: 0px 15px;">
                                                    ${lineItem.getProduct().getProductNote()}
                                            </p>
                                        </div>
                                    </div>
                                </td>
                                <td data-th="Price">${lineItem.getPriceFormat()}</td>
                                <form action="">
                                    <td data-th="Quantity">
                                        <input class="form-control text-center" name="quantity"
                                               value="${lineItem.getQuantity()}" type="number">
                                    </td>
                                    <td data-th="Subtotal" class="text-center">${lineItem.getTotalCurrencyFormat()}</td>
                                    <td style="padding: 40px 0px;">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="productCode" value="${lineItem.getProduct().getProductCode()}">
                                        <input class="btn btn-success btn-block" type="submit" value="cập nhật">
                                    </td>
                                </form>
                                <form action="">
                                    <td style="padding: 40px 0px;">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="productCode" value="${lineItem.getProduct().getProductCode()}">
                                        <input type="hidden" name="quantity" value="0">
                                        <input class="btn btn-success btn-block" type="submit" value="xóa">
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr class="visible-xs">
                            <td class="text-center"><strong>Tổng bill</strong>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="portfolio" class="btn btn-warning"><i class="fa fa-angle-left"></i> Tiếp tục mua hàng</a>
                            </td>
                            <td colspan="2" class="hidden-xs"> </td>
                            <td class="hidden-xs text-center">
                                <strong>Tổng tiền ${cart.getSumTotalFormat()}</strong>
                            </td>
                            <td>
                                <a href="check-out" class="btn btn-success btn-block">Thanh toán <i class="fa fa-angle-right"></i></a>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </section>
</main>

<%@ include file="jsp-footer.jsp"%>
