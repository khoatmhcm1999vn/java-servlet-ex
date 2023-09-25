function percentages_done_chart(percent) {

    var svg = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
    svg.setAttribute('viewBox', "0 0 36 36");
    // svg.setAttribute('class', 'svg-percent-chart');

    var d = "M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831";
    
    // whole circle 100%
    var wholePath = document.createElementNS('http://www.w3.org/2000/svg', 'path');       
    wholePath.setAttribute('class', 'whole-circle');
    wholePath.setAttribute('d', d);
        
    svg.appendChild(wholePath);

    // done %
    var donePath = document.createElementNS('http://www.w3.org/2000/svg', 'path');       
    donePath.setAttribute('class', 'done-circle');
    donePath.setAttribute('d', d);
    donePath.setAttribute('stroke-dasharray', percent + ', 100');
        
    svg.appendChild(donePath);

    // text %
    var percentText = document.createElementNS('http://www.w3.org/2000/svg', 'text');       
    percentText.setAttribute('class', 'percentages-text');
    percentText.setAttribute('x', 18);
    percentText.setAttribute('y', 18);
    percentText.innerHTML = percent + "%";
        
    svg.appendChild(percentText);

    // get percent-done-chart div
    var singleChart = document.getElementById('percent-done-chart');
    // singleChart.setAttribute('class', 'percentages-done-chart');
    singleChart.appendChild(svg);
}