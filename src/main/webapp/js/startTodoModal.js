
$(document).ready(function() {

	var hrefStop = "";
	
    $(window).click(function(e) {
        var startTodoModal = document.getElementById("startTodoModal");

        if (e.target == startTodoModal) {
            startTodoModal.style.display = "none";
            $("#stop").attr('href', hrefStop);
            
            reset();
            console.log($("#stop").attr('href'));
        }
    });

    $("a.btn-open-startTodoModal").click(function() {
        var id = $(this).find("p.id").text();
        var href = $("#stop").attr('href');
        
        hrefStop = href;
        
        $("#stop").attr('href', href + "id=" + id);

        var startTodoModal = document.getElementById("startTodoModal");
        startTodoModal.style.display = "flex";

        console.log($("#stop").attr('href'));
        
    });

    $("a#btn-close-startTodoModal").click(function() {
        var startTodoModal = document.getElementById("startTodoModal");
        startTodoModal.style.display = "none";
        $("#stop").attr('href', hrefStop);
        reset();

        console.log($("#stop").attr('href'));
    });

    $("a#start").click(function(e) {
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
		var yyyy = today.getFullYear();
        var hh = String(today.getHours()).padStart(2, '0');
        var min = String(today.getMinutes()).padStart(2, '0');
        var ss = String(today.getSeconds()).padStart(2, '0');
        var start =yyyy +"-" + mm +"-"+dd+" " +hh + ":" + min + ":" + ss;

        var href = $("#stop").attr('href');
        $("#stop").attr('href', href + "&start=" + start);

        console.log($("#stop").attr('href'));
    });
});