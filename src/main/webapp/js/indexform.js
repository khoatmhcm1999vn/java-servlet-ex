var today = new Date();
var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = today.getFullYear();

var strToday = yyyy + '-' + mm + '-' + dd ;

var inputDate = document.querySelectorAll("input[type=date]");
for (var i = 0; i < inputDate.length; i++) {
    if (inputDate[i].value == "") {
        inputDate[i].value = strToday;
    }
}