<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="jsp-header.jsp" %>

<main class="login-box">
    <div class="contain">
        <div class="login-form">
            <form action="info" method="post">
                <h1>Chỉnh sửa thông tin</h1>
                <div class="input-box ">
                    <i>Họ và tên : </i>
                    <input type="text" placeholder="Nhập tên" name="name" id="update-name" value="${user.getName()}">
                </div>
                <div class="input-box ">
                    <i>Email :</i>
                    <input type="email" placeholder="Nhập email" name="email" id="update-email" value="${user.getMail()}">
                </div>
                <div class="input-box">
                    <i>Thông tin giao hàng :</i>
                    <p style="text-align: left;">Tên người nhận: ${address.getAddressName()}</p>
                    <p style="text-align: left;">Số điện thoại người nhận: ${address.getPhone()}</p>
                    <p style="text-align: left;">Địa chỉ: ${address.fullAddress()}</p>
                </div>

                <a href="re-passwd" style="float:left;">Đổi mật khẩu ?</a><br>
                <a href="address?state=info" style="float:left;">Đổi thông tin giao hàng ?</a>
                <br> <p class="text-danger" style="float:left;"> ${status}</p>

                <a href="confirmemail.html " style="color: #FFF9E5; ">
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
