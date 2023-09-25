
function dataTrial(){
    var data = [ 
        { Name: 'work', Amount: '30'},
        { Name: 'study', Amount: '40'},
        { Name: 'project', Amount: '10'},
        { Name: 'study english at home', Amount: '28'},
        { Name: 'database management system', Amount: '13'},
        { Name: 'english', Amount: '21'},
        { Name: 'website', Amount: '32'},
        { Name: 'play', Amount: '33'},
        { Name: 'other', Amount: '15'},
        { Name: 'self help', Amount: '24'},
    ];

    return data;
}

function filterNameLength(data) {
    var name = [];
    for(var i = 0; i < data.length; i++) {
        if (data[i].Name.length > 9) {
            name.push(data[i].Name.substring(0,6) + "...");
        } else {
            name.push(data[i].Name);
        }
    }
    return name;
}

function calcTotal(data) {

    var total = parseInt(data[0].Amount);

    for(var i = 1; i < data.length; i++) {
        total += parseInt(data[i].Amount);
    }

    return total;
}

function calcPercentages(data, total) {
    
    var percentages = [];
    var remain = 1;

    for (var i = 0; i < data.length - 1; i++) {
        var p = Number.parseFloat(((data[i].Amount)/total).toFixed(2));
        percentages.push(p);
        remain = Number.parseFloat((remain - p).toFixed(2));
    }

    percentages.push(remain);

    return percentages;
}

function createColorList(n) {
    var colorStopOnGradient = [    
        //'#00DEFF', 
        //'#DEFF00',
        '#FEE95C',
        // '#C88DE1',
        // '#01D5FB',
        // '#E17CFD',
        // '#EBBEEB',
        '#78A203',
    ];

    return color_ver_gradient_n(colorStopOnGradient, n);
}

function calcPie(percentages, radius) {
    // List cumulative arc, xEnd, yEnd of pies
    var arc = [];
    
    // Count cumulative percentage 
    var cumulative = 0;
    
    for(var i=0; i < percentages.length - 1; i++){
        cumulative += parseFloat(percentages[i]);

        // Calc arc from first to now
        var a = 2*Math.PI*(cumulative);

        // Calc xEnd, yEnd of pie
        var c = {   a: a,
                    x: Number.parseFloat(Math.cos(a)*radius).toFixed(2), 
                    y: Number.parseFloat(Math.sin(a)*radius).toFixed(2)
                };

        arc.push(c);
    }

    // Last pie = whole circle
    var c = {   a: 2*Math.PI,
                x: Number.parseFloat(radius).toFixed(2), 
                y: Number.parseFloat(0).toFixed(2)
            };

    arc.push(c);

    return arc;
}

function drawBackgroundCircle(radius){
    console.log(radius);

    var p = document.createElementNS('http://www.w3.org/2000/svg', 'path');
    var d = "M0," + radius + " A" + radius + "," + radius + " 0 1,0 " + (-radius) + ",0" + " A" + radius + "," + radius + " 0 1,0 " + (radius) + ",0";

    p.setAttribute('d', d);
    p.setAttribute('fill', "#E5E5E5");

    return p;
}

function pieChart_for_statistic_todo_by_tag(data) {

    // Get sample data
    // var data = dataTrial();

    console.log(data.length);

    // Create svg
    var svg = document.createElementNS('http://www.w3.org/2000/svg', 'svg');

    // Set svg coordinate (0,0) center
    var coordinate = "-18 -18 36 36";
    svg.setAttribute('viewBox', coordinate);

    // Let radius = 100/(2pi)
    var radius = 15.9155;

    if (data.length == 0 || data == null){
        svg.appendChild(drawBackgroundCircle(radius));

        // Create circle center --> show percentage & name
        var circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle');
        circle.setAttribute('r', radius*0.5);
        circle.setAttribute('cx', 0);
        circle.setAttribute('cy', 0);
        circle.setAttribute('fill', 'rgba(255,255,255,1)');

        // Add circle --> svg
        svg.appendChild(circle);

        // Create percentage (text) at center
        var textPercent = document.createElementNS('http://www.w3.org/2000/svg', 'text');
        textPercent.setAttribute("x", 0);
        textPercent.setAttribute("y", 0);
        textPercent.setAttribute("class", "textPercentage-center");
        textPercent.innerHTML= "no";

        // Add percentage (text) at center --> svg
        svg.appendChild(textPercent);

        // Create name (text) at center
        var textName = document.createElementNS('http://www.w3.org/2000/svg', 'text');
        textName.setAttribute("x", 0);
        textName.setAttribute("y", 4);
        textName.setAttribute("class", "textName-center");
        textName.innerHTML= "todo";

        // Add name (text) at center --> svg
        svg.appendChild(textName);

    } else {
        // Filter name list --> name string <= 9 char
        var names = filterNameLength(data);

        // Calc total amount
        var total = calcTotal(data);

        // Calc percentages list (0.)
        var percentages = calcPercentages(data, total);

        // Create color list for pies
        var color = createColorList(percentages.length);

        // Calc xEnd, yEnd & cumulative arc of pies
        var arc = calcPie(percentages, radius);

        // Begin at top
        var xBegin = radius;
        var yBegin = 0;

        // Draw path
        for (var i=0; i < data.length; i++) {

            // Create group contain pie (path) & percentage (text) & name (text)
            var gi = document.createElementNS('http://www.w3.org/2000/svg', 'g');
            gi.setAttribute('transform', "rotate(-90)");

            // Create pie (path)
            var path = document.createElementNS('http://www.w3.org/2000/svg', 'path');

            // Calc d of path
            if (percentages[i]==1.00) {
                // This is for case: pie whole circle
                d = "M0," + radius + " A" + radius + "," + radius + " 0 1,0 " + (-radius) + ",0" + " A" + radius + "," + radius + " 0 1,0 " + (radius) + ",0";

                console.log("Ve nguyen vong tron");
                path.setAttribute('d', d);
                path.setAttribute('fill', color[i]);

                path.setAttribute('class', 'slice');

                // Add path --> group
                gi.appendChild(path);

                // Create percentage (text)
                var textPercentage = document.createElementNS('http://www.w3.org/2000/svg', 'text');
                textPercentage.setAttribute("class", "textPercentage");
                textPercentage.innerHTML= Math.round(percentages[i]*100) + "%";

                // Add percentage (text) --> group
                gi.appendChild(textPercentage);

                // Create name (text)
                var textName = document.createElementNS('http://www.w3.org/2000/svg', 'text');
                textName.setAttribute("class", "textName");
                textName.innerHTML= names[i];

                // Add name (text) --> group
                gi.appendChild(textName);

                // Add group --> svg
                svg.appendChild(gi);

                break;

            } else if (percentages[i]>0.5) {
                // This is for case: pie > 50%
                d = "M0,0 L" + xBegin + "," + yBegin + " A" + radius + "," + radius + " 0 1,1 " + arc[i].x + "," + arc[i].y + " Z";

                path.setAttribute('d', d);
                path.setAttribute('fill', color[i]);

                path.setAttribute('class', 'slice');
            }
            else {
                // This is for case: pie < 50%
                d = "M0,0 L" + xBegin + "," + yBegin + " A" + radius + "," + radius + " 0 0,1 " + arc[i].x + "," + arc[i].y + " Z";

                path.setAttribute('d', d);
                path.setAttribute('fill', color[i]);

                path.setAttribute('class', 'slice');
            }

            // Add path --> group
            gi.appendChild(path);

            // Create percentage (text)
            var textPercentage = document.createElementNS('http://www.w3.org/2000/svg', 'text');
            textPercentage.setAttribute("class", "textPercentage");
            textPercentage.innerHTML= Math.round(percentages[i]*100) + "%";

            // Add percentage (text) --> group
            gi.appendChild(textPercentage);

            // Create name (text)
            var textName = document.createElementNS('http://www.w3.org/2000/svg', 'text');
            textName.setAttribute("class", "textName");
            textName.innerHTML= names[i];

            // Add name (text) --> group
            gi.appendChild(textName);

            // Add group --> svg
            svg.appendChild(gi);

            // Set Begin of next = End of now
            xBegin = arc[i].x;
            yBegin = arc[i].y;
        }

        // Create circle center --> show percentage & name
        var circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle');
        circle.setAttribute('r', radius*0.5);
        circle.setAttribute('cx', 0);
        circle.setAttribute('cy', 0);
        circle.setAttribute('fill', 'rgba(255,255,255,1)');

        // Add circle --> svg
        svg.appendChild(circle);

        // Create percentage (text) at center
        var textPercent = document.createElementNS('http://www.w3.org/2000/svg', 'text');
        textPercent.setAttribute("x", 0);
        textPercent.setAttribute("y", 0);
        textPercent.setAttribute("class", "textPercentage-center");
        textPercent.innerHTML= "100%";

        // Add percentage (text) at center --> svg
        svg.appendChild(textPercent);

        // Create name (text) at center
        var textName = document.createElementNS('http://www.w3.org/2000/svg', 'text');
        textName.setAttribute("x", 0);
        textName.setAttribute("y", 4);
        textName.setAttribute("class", "textName-center");
        textName.innerHTML= "tag";

        // Add name (text) at center --> svg
        svg.appendChild(textName);
    }

    // Get div contain pie chart
    // Id: statistic-todo-by-tag
    var ct = document.getElementById('statistic-todo-by-tag');

    // Add svg to div
    ct.appendChild(svg);
}

// Event: Mouse over & mouse leave
$(document).ready(function() {

    // Event: Mouse over --> div#statistic-todo-by-tag --> svg --> g
    $("#statistic-todo-by-tag svg g").on('mouseover', function() {
        var value = $(this).find('.textPercentage').text();
        var name = $(this).find('.textName').text();
        var fill = $(this).find('path').css("fill");
        $(this).siblings(".textPercentage-center").text(value);
        $(this).siblings(".textPercentage-center").css({"fill":fill});
        $(this).siblings(".textName-center").text(name);
        $(this).siblings(".textName-center").css({"fill":fill});
    });

    // Event: Mouse leave --> div#statistic-todo-by-tag --> svg --> g
    $("#statistic-todo-by-tag svg g").on('mouseleave', function() {
        $(this).siblings(".textPercentage-center").text("100%");
        $(this).siblings(".textPercentage-center").css({"fill":"#00002A"});
        $(this).siblings(".textName-center").text("tag");
        $(this).siblings(".textName-center").css({"fill":"#00002A"});
    });
});