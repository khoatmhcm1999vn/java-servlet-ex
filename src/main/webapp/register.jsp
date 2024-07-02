<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="jsp-header.jsp" %>

<main class="login-box">
    <div class="container">
        <div class="login-form">
            <form action="register" method="post">
                <h1>Đăng ký</h1>
                <input type="hidden" name="action" value="register">
                <div class="input-box">
                    <i></i>
                    <input type="text" placeholder="Nhập tên" name="name">
                </div>
                <div class="input-box">
                    <i></i>
                    <input type="email" placeholder="Nhập email" name="email">
                </div>
                <div class="input-box">
                    <i></i>
                    <input type="password" placeholder="Nhập mật khẩu" name="passwd">
                </div>
                <div class="input-box">
                    <i></i>
                    <input type="password" placeholder="Nhập lại mật khẩu" name="rePasswd">
                </div>
                <p class="text-danger">${status}</p>

                <div class="btn-box">
                    <button type="submit">
                        Đăng ký
                    </button>
                </div>
            </form>
        </div>
    </div>
</main>

<%@include file="jsp-footer.jsp" %>
