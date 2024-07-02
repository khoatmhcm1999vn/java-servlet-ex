//Kích hoạt nút mở thẻ tên
var updatename = document.getElementById('update-name');
var buttonname = document.getElementById('button-name');

buttonname.onclick = function() {
    updatename.removeAttribute("disabled");
}

//Kích hoạt nút mở thẻ email
var updateemail = document.getElementById('update-email');
var buttonemail = document.getElementById('button-email');

buttonemail.onclick = function() {
    updateemail.removeAttribute("disabled");
}

//Kích hoạt nút mở thẻ điện thoại

var updatephone = document.getElementById('update-phone');
var buttonphone = document.getElementById('button-phone');

buttonphone.onclick = function() {
    updatephone.removeAttribute("disabled");
}

//Kích hoạt nút mở thẻ điện thoại

var updateaddr = document.getElementById('update-addr');
var buttonaddr = document.getElementById('button-addr');

buttonaddr.onclick = function() {
    updateaddr.removeAttribute("disabled");
}