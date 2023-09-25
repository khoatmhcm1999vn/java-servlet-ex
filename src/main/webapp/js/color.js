function parseHSL(str) {
    // parse 'hsl(hue, saturation%, lightness%)' to [h,s,l]
    var hsl, h, s, l;
    hsl = str.replace(/[^\d,]/g, '').split(',');
    h = Number(hsl[0]);
    s = Number(hsl[1]);
    l = Number(hsl[2]);
    return [h, s, l];
}

function rgbToHEX(r, g, b) {
    var hex = function(x) {
        x = x.toString(16);
        if(x.length < 2) return '0' + x;
        return x.substring(x.length-2, x.length);
    };
    var color = hex(r) + hex(g) + hex(b);
    return '#' + color;
}

function hexToRGB(color) {
    var r = parseInt(color.substring(1,3), 16);
    var g = parseInt(color.substring(3,5), 16);
    var b = parseInt(color.substring(5,7), 16);
    return [r, g, b]
}

function rgbToHSL(r,g,b) {
   // Make r, g, and b fractions of 1
    r /= 255;
    g /= 255;
    b /= 255;

    // Find greatest and smallest channel values
    let cmin = Math.min(r,g,b),
        cmax = Math.max(r,g,b),
        delta = cmax - cmin,
        h = 0,
        s = 0,
        l = 0;
    // Calculate hue
    // No difference
    if (delta == 0) 
        h = 0;
    // Red is max
    else if (cmax == r)
            h = ((g - b) / delta) % 6;
    // Green is max
    else if (cmax == g)
            h = (b - r) / delta + 2;
    // Blue is max
    else
        h = (r - g) / delta + 4;

    h = Math.round(h * 60);
    
    // Make negative hues positive behind 360°
    if (h < 0)
        h += 360;

    // Calculate lightness
    l = (cmax + cmin) / 2;

    // Calculate saturation
    s = delta == 0 ? 0 : delta / (1 - Math.abs(2 * l - 1));
        
    // Multiply l and s by 100
    s = +(s * 100).toFixed(1);
    l = +(l * 100).toFixed(1);

    // return "hsl(" + h + "," + s + "%," + l + "%)";
    return [h,s,l];
}

function hexToHSL(hex) {
    // Convert hex to RGB first
    var [r,g,b] = hexToRGB(hex);
    // Then to HSL
    return rgbToHSL(r,g,b);
}

function color_ver_darklight(primary, num) {
    var color = [primary];
    var [h, s, l] = parseHSL(primary);
    var range = 90 - l;
    var interval = range/num;
    for(var i = 1; i < num; i++) {
        var light = (l + i*interval);
        var c = 'hsl(' + (h) + ', ' + (s) + '%, ' + (light) + '%)';
        color.push(c);
    }
    return color;
}

var colorIsLight = function (r, g, b) {
    var a = 1 - (0.299 * r + 0.587 * g + 0.114 * b) / 255;
    console.log(a);
    return (a < 0.5);
}

function ColorText (hex) {
    var [r, g, b] = hexToHSL(hex);
    return colorIsLight(r,g,b) ? "#000000" : "#ffffff" ;
}