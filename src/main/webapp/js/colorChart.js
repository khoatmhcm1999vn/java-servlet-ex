function parseHSL(str) {
    var hsl, h, s, l
    hsl = str.replace(/[^\d,]/g, '').split(',')  
    h = Number(hsl[0])
    s = Number(hsl[1])
    l = Number(hsl[2])
    return [h, s, l]
}

function color_ver_darklight(primary, num) {
    var color = [primary];
    var [h, s, l] = parseHSL(primary)
    var range = 90 - l;
    var interval = range/num;
    for(var i = 1; i < num; i++) {
        var light = (l + i*interval)
        var c = 'hsl(' + (h) + ', ' + (s) + '%, ' + (light) + '%)';
        color.push(c);
    }
    return color;
}

function color_ver_rainbow(primary, num) {
    var color = [];
    var no_pri = primary.length;
    var no_perPri = num/no_pri;
    if(num%no_pri != 0) {
        no_perPri = no_perPri - (num%no_pri)/no_pri + 1;
    }
    var intervar = (360/no_pri)/num;
    for(var i = 0; i < no_pri; i++) {
        var [h, s, l] = parseHSL(primary[i])

        // Màu bên trái primary
        for(var j = parseInt((no_perPri - 1)/2); j > 0; j--) {
            var hueLeft = (h - j*intervar)
            var cleft = 'hsl(' + hueLeft + ', ' + (s) + '%, ' + (l) + '%)';
            color.push(cleft);
        }
        // Màu primary
        color.push(primary[i]);

        // Màu bên phải primary
        for(var j = 1; j <= parseInt(no_perPri/2); j++) {
            var hueRight = (h + j*intervar)
            var cright = 'hsl(' + hueRight + ', ' + (s) + '%, ' + (l) + '%)';
            color.push(cright);
        }
    }
    return color;
}

function HEX(r, g, b) {
    var hex = function(x) {
    x = x.toString(16);
    if(x.length < 2) return '0' + x;
        return x.substring(x.length-2, x.length);
    };
    var color = hex(r) + hex(g) + hex(b);
    return '#' + color;
}

function parseHEX(color) {
    var r = parseInt(color.substring(1,3), 16);
    var g = parseInt(color.substring(3,5), 16);
    var b = parseInt(color.substring(5,7), 16);
    return [r, g, b]
}

function color_ver_gradient(begin, end, num) {
    var color = [];

    // Tính radio
    var ratio = 1/num;
    var [rb, gb, bb] = parseHEX(begin);
    var [re, ge, be] = parseHEX(end);

    for(var i = 0; i < num; i++) {
        // Radio
        var rt = i*ratio

        // Red, green, blue
        var r = Math.ceil(rb * (1 - rt) + re * rt);
        var g = Math.ceil(gb * (1 - rt) + ge * rt);
        var b = Math.ceil(bb * (1 - rt) + be * rt);
        // Chuyển sang mã hex
        var c = HEX(r, g, b);
        color.push(c);
    }
    return color;
}

function color_ver_gradient_n(primary, num) {
    var color = [];
    var no_pri = primary.length;
    var no_perPri = num/no_pri;
    var gradient = [];
    for(var i = 0; i < no_pri - 1; i++) {
        var begin = primary[i];
        var end = primary[i + 1];
        gradient = color_ver_gradient(begin, end, no_perPri);
        color = color.concat(gradient);
    }
    gradient = color_ver_gradient(primary[no_pri - 1], primary[0], no_perPri);
    color = color.concat(gradient);
    return color;
}
