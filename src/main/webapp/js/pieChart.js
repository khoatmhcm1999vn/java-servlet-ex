
function dataTrial(){
    var data = [ 
        { Fruit: 'lime', Amount: '90', Baseline: 90 },
        { Fruit: 'lime', Amount: '100', Baseline: 90 },
    ];
    return data;
}

function pieChart(done, total) {
    // Calculate % done
    var percentDone = parseInt(done*100/total);

    // Tạo svg
    var svg = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
    svg.setAttribute('width','350');
    svg.setAttribute('height','350');

    var g = document.createElementNS('http://www.w3.org/2000/svg', 'g');
    // g.setAttribute('transform', "rotate(-90)");

    var w = svg.getAttribute('width');
    var h = svg.getAttribute('height');
    // TH: width < height
    var pad = (w/100)*10;
    var radius = w/2 - pad;
    // TH: height < width
    if( parseInt(h) < parseInt(w)) {
        pad = (h/100)*10;
        radius = h/2 - pad;
    }
    var xcenter = w/2;
    var ycenter = h/2;

    console.log(xcenter);
    console.log(ycenter);

    //coord = "-" + xcenter + " -" + ycenter + " " + w + " " + h;
    coord = "0 0 " + w + " " + h;
    
    svg.setAttribute('viewBox', coord);

    var circleTotal = document.createElementNS('http://www.w3.org/2000/svg', 'circle');
    circleTotal.setAttribute('r', radius);
    circleTotal.setAttribute('cx', xcenter);
    circleTotal.setAttribute('cy', ycenter);
    circleTotal.setAttribute('fill', '#cccccc');
    g.appendChild(circleTotal);

    var circleDone = document.createElementNS('http://www.w3.org/2000/svg', 'circle');
    circleDone.setAttribute('r', radius/2);
    circleDone.setAttribute('cx', xcenter);
    circleDone.setAttribute('cy', ycenter);
    console.log(circleDone.getAttribute("cx"))
    circleDone.setAttribute('fill', 'transparent');
    circleDone.setAttribute('stroke', 'tomato');
    circleDone.setAttribute('stroke-width', radius);
    circleDone.setAttribute('stroke-dasharray', percentDone*(Math.PI*radius)/100 + " " + Math.PI*radius);
    circleDone.setAttribute('transform', "rotate(-90) translate(-" + h + ")");
    circleDone.setAttribute('class', 'slice');
    g.appendChild(circleDone);
    

    svg.appendChild(g);
    var ct = document.getElementById('container');
    ct.appendChild(svg);
    
}


function drawPieChart() {

    data = dataTrial()
    var total = parseInt(data[1].Amount);

    console.log("total: " + total);

    // Tính % (0.) cho mỗi (row - 1) data
    var pieData = [];
    var totalPercent = 0.00;
    console.log("data.length - 1: " + (data.length - 1));

    for(var i = 0; i < data.length -1; i++) {
        var p = parseFloat(parseInt(data[i].Amount)/total).toFixed(2);
        console.log("p: " + p);
        totalPercent += p;
        pieData.push(p);
    }

    // Tính % (0.) cho row cuối data
    var p = 1 - totalPercent;
    pieData.push(p);
    console.log("pieData: " + pieData);
    console.log("totalPercent: " + totalPercent);

    // Tạo svg
    var svg = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
    svg.setAttribute('width','350');
    svg.setAttribute('height','350');

    var g = document.createElementNS('http://www.w3.org/2000/svg', 'g');
    g.setAttribute('transform', "rotate(-90)");

    // Tính padding (=10%) và radius cho biểu đồ
    var w = svg.getAttribute('width');
    var h = svg.getAttribute('height');
    // TH: width < height
    var pad = (w/100)*10;
    var radius = w/2 - pad;
    // TH: height < width
    if ( parseInt(h) < parseInt(w)) {
        pad = (h/100)*10;
        radius = h/2 - pad;
    }

    // Tọa độ gốc
    var xcenter = w/2;
    var ycenter = h/2;

    // Đặt hệ tọa độ với gốc (0,0) ở trung tâm
    coord = "-" + xcenter + " -" + ycenter + " " + w + " " + h;
    svg.setAttribute('viewBox', coord);

    // Tạo dãy màu
    var colorGradient = [    
        '#E2A2DF',
        '#A9D3FB',
        '#F4D94F'
    ];

    var color = color_ver_gradient_n(colorGradient, pieData.length);

    // Tính điểm cuối
    var cumulative = 0;
    var arrC = [];

    for(var i=0; i < pieData.length - 1; i++){
        cumulative += parseFloat(pieData[i]);

        console.log("cumulative: " + cumulative);


        var a = 2*Math.PI*(cumulative);
        console.log("a: " + a);
        var c = {   a: a,
                    x: Number.parseFloat(Math.cos(a)*radius).toFixed(2), 
                    y: Number.parseFloat(Math.sin(a)*radius).toFixed(2)
                };
        console.log("c: " + c);
        arrC.push(c);
    }
    // Lát cuối quay về ban đầu
    var c = {   a: 2*Math.PI,
            x: Number.parseFloat(radius).toFixed(2), 
            y: Number.parseFloat(0).toFixed(2)
        };
    console.log("c: " + c);
    arrC.push(c);

    console.log(arrC)

    var xbegin = radius;
    var ybegin = 0;

    // Draw path
    for (var i=0; i<data.length; i++) {
        var path = document.createElementNS('http://www.w3.org/2000/svg', 'path');
        


        if (pieData[i]==1.00) {
            d = "M0," + radius + " A" + radius + "," + radius + " 0 1,0 " + (-radius) + ",0" + " A" + radius + "," + radius + " 0 1,0 " + (radius) + ",0";
            console.log(d);
            path.setAttribute('class', 'slice');
            path.setAttribute('d', d);
            path.setAttribute('fill', color[i]);
            path.setAttribute('stroke', '#ffffff');
            path.setAttribute('stroke-width', '1.5');
            g.appendChild(path);
            break;
        } else if(pieData[i]>0.5) {          
            d = "M0,0 L" + xbegin + "," + ybegin + " A" + radius + "," + radius + " 0 1,1 " + arrC[i].x + "," + arrC[i].y + " Z";
            console.log(d);
        }
        else {
            d = "M0,0 L" + xbegin + "," + ybegin + " A" + radius + "," + radius + " 0 0,1 " + arrC[i].x + "," + arrC[i].y + " Z";
            console.log(d);
        }
        path.setAttribute('class', 'slice');
        path.setAttribute('d', d);
        path.setAttribute('fill', color[i]);
        path.setAttribute('stroke', '#ffffff');
        path.setAttribute('stroke-width', '1.5');
        g.appendChild(path);
        
        xbegin = arrC[i].x;
        ybegin = arrC[i].y;
    }

    svg.appendChild(g);
    var ct = document.getElementById('container');
    ct.appendChild(svg);
}

function drawPieChart2(done, total) {

    console.log("total: " + total);

    // Tính % (0.) cho done va not done
    var pieData = [ parseFloat(done/total).toFixed(2),
                    parseFloat(1 - done/total).toFixed(2)];
    console.log("pieData: " + pieData);

    var ct = document.getElementById('container');
    
    
    // Tạo svg
    var svg = document.createElementNS('http://www.w3.org/2000/svg', 'svg');

    var g = document.createElementNS('http://www.w3.org/2000/svg', 'g');
    g.setAttribute('transform', "rotate(-90)");

    // radius = 100/(2pi)
    var radius = 15.9155;

    // Đặt hệ tọa độ với gốc (0,0) ở trung tâm
    coord = "-18 -18 36 36";
    svg.setAttribute('viewBox', coord);

    // Tạo dãy màu
    var color = [   '#FF6A66',
                    '#FFAB31'
                ];

    // Tính điểm cuối
    var arrC = [];

    
    var a = 2*Math.PI*(parseFloat(pieData[0]));
    console.log(a);

    var cdone = {   a: a,
                    x: Number.parseFloat(Math.cos(a)*radius).toFixed(2), 
                    y: Number.parseFloat(Math.sin(a)*radius).toFixed(2)
                };
    arrC.push(cdone);

    var ctotal = {  a: 2*Math.PI,
                    x: Number.parseFloat(radius).toFixed(2), 
                    y: Number.parseFloat(0).toFixed(2)
                };
    arrC.push(ctotal);

    console.log(arrC)

    var xbegin = radius;
    var ybegin = 0;
    var offset = 0;
    var sumAngle = 0;
    console.log("sumAngle: " + sumAngle);

    // Draw path
    for (var i=0; i<2; i++) {
    
        var a = 2*Math.PI*(parseFloat(pieData[i]))/100;
        const angleText = sumAngle + a/2;
        sumAngle += arrC[i].a;

        console.log("angle: " + arrC[i].a*180);

        console.log("angleText: " + i + " :" + angleText);
        console.log("angleText: " + angleText*180);
        console.log("sumAngle: " + sumAngle);
        console.log("arrC: " + arrC[i].a/2);
        console.log("offset: " + offset);
        var path = document.createElementNS('http://www.w3.org/2000/svg', 'path');
        var text = document.createElementNS('http://www.w3.org/2000/svg', 'text');
        text.innerHTML = pieData[i]*100 + "%";
        console.log(text.innerHTML);
        // text.setAttribute("x", "0%");
        var x = Number.parseFloat(Math.sin(-angleText)*radius/2).toFixed(2);
        var y = Number.parseFloat(Math.cos(-angleText)*radius/2).toFixed(2);
        text.setAttribute("x", x);
        text.setAttribute("y", y);
        console.log("x: " + x);
        console.log("y: " + y);

        

        // text.setAttribute("transform", "rotate(" + angleText + ")");
        text.setAttribute("transform-origin", x + " " + y);
        text.setAttribute("transform", "rotate(90) translate(0, -2)");
        // text.setAttribute("text-anchor", "middle");
        text.setAttribute("alignment-baseline", "baseline");
        text.setAttribute("fill", "#000000");
        text.setAttribute("class", "text-on-pie");

        var title = document.createElementNS('http://www.w3.org/2000/svg', 'title');
        title.innerHTML = pieData[i]*100 + "%";
        console.log(title.innerHTML);

        if (pieData[i]==1.00) {
            d = "M0," + radius + " A" + radius + "," + radius + " 0 1,0 " + (-radius) + ",0" + " A" + radius + "," + radius + " 0 1,0 " + (radius) + ",0";
            console.log(d);
            path.setAttribute('class', 'slice');
            path.setAttribute('d', d);
            path.setAttribute('fill', color[i]);
            path.setAttribute('stroke', '#ffffff');
            path.setAttribute('stroke-width', '1');
            g.appendChild(path);
            break;
        } else if(pieData[i]>0.5) {          
            d = "M0,0 L" + xbegin + "," + ybegin + " A" + radius + "," + radius + " 0 1,1 " + arrC[i].x + "," + arrC[i].y + " Z";
            console.log(d);
            
        }
        else {
            d = "M0,0 L" + xbegin + "," + ybegin + " A" + radius + "," + radius + " 0 0,1 " + arrC[i].x + "," + arrC[i].y + " Z";
            console.log(d);
            
        }
        path.setAttribute('class', 'slice');
        path.setAttribute('d', d);
        path.setAttribute('fill', color[i]);
        path.setAttribute('stroke', '#ffffff');
        path.setAttribute('stroke-width', '1');

        var gtooltip = document.createElementNS('http://www.w3.org/2000/svg', 'g');
        // gtooltip.setAttribute('transform', "rotate(-90)");
        
        
        var box = gtooltip.getBBox();
        console.log(box);
        
        var rectTooltip = document.createElementNS('http://www.w3.org/2000/svg', 'rect');
        rectTooltip.setAttribute('fill', "white");

        rectTooltip.setAttribute('x', x + 2);
        rectTooltip.setAttribute('y', y - 2);
        rectTooltip.setAttribute('rx', "0.1em");
        rectTooltip.setAttribute('stroke', "black");
        rectTooltip.setAttribute('stroke-width', "0.02em");

        rectTooltip.setAttribute('width', "0.4em");
        rectTooltip.setAttribute('height', "0.6em");

        
        
        gtooltip.appendChild(rectTooltip);
        gtooltip.appendChild(text);
        gtooltip.setAttribute('class', "pie-tooltip");
        

        console.log(gtooltip.getAttribute("width"));

        var gi = document.createElementNS('http://www.w3.org/2000/svg', 'g');
        gi.setAttribute('transform', "rotate(-90)");

        
        gi.appendChild(path);
        // gi.appendChild(text);
        gi.appendChild(gtooltip);
        // gi.setAttribute('onmousemove', "tooltip(this)");


        svg.appendChild(gi);

        // g.appendChild(path);
        // g.appendChild(text);
        // g.appendChild(title);

        xbegin = arrC[i].x;
        ybegin = arrC[i].y;
        offset += pieData[i]*100;
        
    }

    // svg.appendChild(g);
    var ct = document.getElementById('container');
    ct.appendChild(svg);
}

$(document).ready(function() {
    $("svg g").on('mouseover', function() {
        $(this).closest('g').find('.pie-tooltip').css({'display': 'block', 'order':'9999'});
    });
    $("svg g").on('mouseleave', function() {
        $(this).closest('g').find('.pie-tooltip').css({'display': 'none'});
    });
});

// function tooltip(element) {
//     $(this).children("g").children("pie-tooltip").css({"display":"block"});
//     console.log(this);
//     console.log($(this).children("g").children("pie-tooltip"));
// }

function drawPieChart3(data, colors) {

    //data = dataTrial()
    var total = parseInt(data[0].Amount);
    for(var i = 1; i < data.length; i++) {
        total += parseInt(data[i].Amount)
    }
    var pieData = [];
    var totalPercent = 0.00;
    for(var i = 0; i < data.length -1; i++) {
        var p = parseFloat(parseInt(data[i].Amount)/total).toFixed(2);
        totalPercent += p;
        pieData.push(p);
    }
    var p = 1 - totalPercent;
    pieData.push(p);
    
    // Tạo svg
    var svg = document.createElementNS('http://www.w3.org/2000/svg', 'svg');

    var g = document.createElementNS('http://www.w3.org/2000/svg', 'g');
    g.setAttribute('transform', "rotate(-90)");

    // radius = 100/(2pi)
    var radius = 15.9155;

    // Đặt hệ tọa độ với gốc (0,0) ở trung tâm
    coord = "-18 -18 36 36";
    svg.setAttribute('viewBox', coord);

    // Tạo dãy màu
    // var colorGradient = [    
    //     '#FF6347',
    //     '#FFFF00',
    //     '#40E0D0',
    //     '#008080',
    // ];

    // var color = color_ver_gradient_n(colorGradient, pieData.length);

    // Tính điểm cuối
    var cumulative = 0;
    var arrC = [];

    for(var i=0; i < pieData.length - 1; i++){
        cumulative += parseFloat(pieData[i]);
        var a = 2*Math.PI*(cumulative);
        var c = {   a: a,
                    x: Number.parseFloat(Math.cos(a)*radius).toFixed(2), 
                    y: Number.parseFloat(Math.sin(a)*radius).toFixed(2)
                };
        arrC.push(c);
    }
    // Lát cuối quay về ban đầu
    var c = {   a: 2*Math.PI,
                x: Number.parseFloat(radius).toFixed(2), 
                y: Number.parseFloat(0).toFixed(2)
            };
    arrC.push(c);

    var xbegin = radius;
    var ybegin = 0;
    // Draw path
    for (var i=0; i<data.length; i++){
        var path = document.createElementNS('http://www.w3.org/2000/svg', 'path');
        if(pieData[i]>0.5) {          
            d = "M0,0 L" + xbegin + "," + ybegin + " A" + radius + "," + radius + " 0 1,1 " + arrC[i].x + "," + arrC[i].y + " Z";
        }
        else {
            d = "M0,0 L" + xbegin + "," + ybegin + " A" + radius + "," + radius + " 0 0,1 " + arrC[i].x + "," + arrC[i].y + " Z";
        }
        path.setAttribute('class', 'slice');
        path.setAttribute('d', d);
        path.setAttribute('fill', colors[i]);
        path.setAttribute('stroke', '#ffffff');
        path.setAttribute('stroke-width', '1.5');
        g.appendChild(path);
        xbegin = arrC[i].x;
        ybegin = arrC[i].y;
    }


    


    svg.appendChild(g);
    var ct = document.getElementById('container');
    ct.appendChild(svg);
}