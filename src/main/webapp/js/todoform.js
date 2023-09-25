// Get today
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

// if (document.querySelector("input[type=date]").value == "") {
//     document.querySelector("input[type=date]").value = strToday;
// }

// var mintoday = new Date().toISOString().split('T')[0];
// document.querySelector("input[type=date]").setAttribute('min', mintoday);

var hh = String(today.getHours()).padStart(2, '0');
var min = String(today.getMinutes()).padStart(2, '0');


var strTime = hh + ":" + min;

var inputTime = document.querySelectorAll("input[type=time]");
for (var i = 0; i < inputTime.length; i++) {
    if (inputTime[i].value == "") {
        inputTime[i].value = strTime;
    }
}

// if (document.querySelector("input[type=time]").value == "") {
//     document.querySelector("input[type=time]").value = strTime;
// }