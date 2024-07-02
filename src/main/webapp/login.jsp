<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="jsp-header.jsp" %>

<main class="login-box">
    <div class="container">
        <div class="login-form">
            <form action="sig" method="post">
                <h1>Đăng nhập</h1>
                <input type="hidden" name="action" value="login">
                <div class="input-box">
                    <i></i>
                    <input type="email" placeholder="Nhập email" name="email">
                </div>
                <div class="input-box">
                    <i></i>
                    <input type="password" placeholder="Nhập mật khẩu" name="passwd">
                </div>
                <a href="register" style="float:left ; font-size: 15px;">Bạn chưa có tài khoản ?</a> <br>

                <p class="text-danger"> ${status}</p>
                
                <div class="btn-box">
                    <button type="submit">
                        Đăng nhập
                    </button>
                </div>
            </form>
        </div>
    </div>
</main>

<%@include file="jsp-footer.jsp" %>
