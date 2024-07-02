<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="jsp-header.jsp" %>
<main class="login-box">
    <div class="contain">
        <div class="login-form">
            <form action="address" method="post">
                <h1>Chỉnh sửa thông tin giao hàng</h1>
                <input type="hidden" name="state" value="${state}">
                <input type="hidden" name="action" value="update">
                <div class="input-box ">
                    <i>Họ và tên người nhận: </i>
                    <input type="text" placeholder="Nhập tên" name="name" value="${address.getAddressName()}" >
                </div>
                <div class=" input-box ">
                    <i>Số điện thoại liên lạc :</i>
                    <input type="text" placeholder="Nhập số điện thoại" name="phone" value="${address.getPhone()}" >
                </div>
                <div class="input-box">
                    <i>Địa chỉ nhận hàng :</i>
                    <input type="text" placeholder="tỉnh" name="province" value="${address.getProvince()}">
                </div>
                <div class="input-box">
                    <input type="text" placeholder="huyện" name="district" value="${address.getDistrict()}" >
                </div>
                <div class="input-box">
                    <input type="text" placeholder="xã" name="commune" value="${address.getCommune()}" >
                </div>
                <div class="input-box">
                    <input type="text" placeholder="số nhà/ tên đường" name="details" value="${address.getDetails()}">
                </div>
                <br> <p class="text-danger" style="float:left;"> ${status}</p>
                <a style="color: #FFF9E5; ">
                    <div class="btn-box ">
                        <button type="submit ">
                            Cập nhật
                        </button>
                    </div>
                </a>
            </form>
        </div>
    </div>
</main>

<%@include file="jsp-footer.jsp" %>
