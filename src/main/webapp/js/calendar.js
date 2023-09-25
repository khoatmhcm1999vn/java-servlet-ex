function updateCalendar() {
    var now = new Date();
    var day = now.getDay(),
        month = now.getMonth(),
        date = now.getDate(),
        year = now.getFullYear();
    
    Number.prototype.pad = function(digits) {
        for (var n= this.toString(); n.length < digits; n = 0 + n);
        return n;
    }
    
    var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    
    var ids = ["month", "year"];
    var values = [months[month], year];
    for (var i = 0; i< ids.length; i++) {
        document.getElementById(ids[i]).firstChild.nodeValue = values[i];
    }

    var weekids = ["sun", "mon", "tue", "wed", "thu", "fri", "sat"];
    var begin = date - day;
    var dateInWeek = new Date();
    
    for (var i = 0; i < 7; i++) {
        dateInWeek.setDate(begin + i );
        document.getElementById(weekids[i]).firstChild.nodeValue = dateInWeek.getDate().pad(2);
    }

    document.getElementById(weekids[day]).parentElement.classList.add("today");

}

function initCalendar() {
    updateCalendar();
    window.setInterval("updateCalendar()", 1000);
}