// Get today
var today = new Date();
var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = today.getFullYear();

var strToday = yyyy + '-' + mm + '-' + dd ;

if (document.querySelector("input[type=date]").value == "") {
    document.querySelector("input[type=date]").value = strToday;
}

var mintoday = new Date().toISOString().split('T')[0];
document.querySelector("input[type=date]").setAttribute('min', mintoday);


function getWeekNumber(d) {
    // Copy date so don't modify original
    d = new Date(Date.UTC(d.getFullYear(), d.getMonth(), d.getDate()));
    // Set to nearest Thursday: current date + 4 - current day number
    // Make Sunday's day number 7
    d.setUTCDate(d.getUTCDate() + 4 - (d.getUTCDay()||7));
    // Get first day of year
    var yearStart = new Date(Date.UTC(d.getUTCFullYear(),0,1));
    // Calculate full weeks to nearest Thursday
    var weekNo = Math.ceil(( ( (d - yearStart) / 86400000) + 1)/7);
    // Return array of year and week number
    return weekNo;
}

var week = String(getWeekNumber(today)).padStart(2,0);
if (week == 53) {
    var strWeek = (yyyy-1) + '-W' + week ;
} else {
    var strWeek = yyyy + '-W' + week ;
}

if (document.querySelector("input[type=week]").value == "") {
    document.querySelector("input[type=week]").value = strWeek;
}

var strMonth = yyyy + "-" + mm
if (document.querySelector("input[type=month]").value == "") {
    document.querySelector("input[type=month]").value = strMonth;
}

var hh = String(today.getHours()).padStart(2, '0');
var min = String(today.getMinutes()).padStart(2, '0');


var strTime = hh + ":" + min;
console.log(strTime);
if (document.querySelector("input[type=time]").value == "") {
    document.querySelector("input[type=time]").value = strTime;
}
