<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="jsp-header.jsp" %>
<!-- End Header -->

<main class="confirmcart">
    <h1>Hoá đơn</h1>
    <h2 class="name">Họ và tên : ${address.getAddressName()}
    </h2>
    <h2 class="phone">Số điện thoại : ${address.phoneFormat()}
    </h2>
    <h2 class="address">Địa chỉ : ${address.fullAddress()}
    </h2>

    <ul class="responsive-table">
        <li class="table-header">
            <div class="col col-1">Tên sản phẩm</div>
            <div class="col col-2">Giá</div>
            <div class="col col-3">Số lượng</div>
            <div class="col col-4">Tổng</div>
        </li>
        <c:forEach var="lineItem" items="${cart.items}">
            <li class="table-row">
                <div class="col col-1" data-label="Product">${lineItem.getProduct().getProductName()}</div>
                <div class="col col-2" data-label="Amount">${lineItem.getPriceFormat()}</div>
                <div class="col col-3" data-label="Number">${lineItem.getQuantity()}</div>
                <div class="col col-4" data-label="Cost">${lineItem.getTotalCurrencyFormat()}</div>
            </li>
        </c:forEach>
    </ul>

    <ul class="responsive-table">
        <li class="table-header">
            <div class="col col-1">Mã khuyến mãi</div>
            <div class="col col-2">Tổng tiền</div>
            <div class="col col-3">Số tiền giảm</div>
            <div class="col col-4">Thành tiền</div>
        </li>
        <li class="table-row">
            <div class="col col-1" data-label="Product">
                <form class="form" action="check-out" method="post">
                    <input type="hidden" name="action" value="checkCode">
                    <input type="text" class="form__field" placeholder="Mã code" name="code" value="${cart.getDiscountCode()}">
                    <button type="submit" class="btn btn--primary btn--inside uppercase">Check</button>
                </form>
            </div>
            <div class="col col-2" data-label="Amount">${cart.getSumTotalFormat()}</div>
            <div class="col col-3" data-label="Number">${cart.getDiscountValueFormat()}</div>
            <div class="col col-4" data-label="Cost">${cart.getFinalTotalFormat()}</div>
        </li>
    </ul>

    <h3>
        <a href="check-out?action=final" class="btn btn-success btn-block">Thanh toán <i class="fa fa-angle-right"></i></a>
        <a href="address?state=bill" class="btn btn-success btn-block">Chỉnh sửa thông tin giao hàng <i
                class="fa fa-angle-right"></i></a>
        <a href="cart" class="btn btn-success btn-block">Trở về <i class="fa fa-angle-right"></i></a>
    </h3>
</main>

<%@ include file="jsp-footer.jsp" %>
