var Painter = {
    elements: [],
    draw: function () {
        this.clear();
        for (var i in this.elements) {
            var el = this.elements[i];
            el.draw();
        }
    },
    init: function () {
        var node1 = new RectNode();
        var node2 = new RotateCircle();
        this.elements.push(node1);
        this.elements.push(node2);
    },
    clear: function () {
        //ctx.clearRect(0, 0, 400, 300);

        ctx.globalCompositeOperation = 'destination-out';
        ctx.fillStyle = 'rgba(0, 0, 0, .1)';
        ctx.fillRect(0, 0, 400, 300);
        ctx.globalCompositeOperation = 'lighter';
    }
};

Painter.init();
//window.setInterval(Painter.draw, 100);

window.setInterval(function (a, b) {
    return function() {
        b.call(a);
    }
} (Painter, Painter.draw), 40);
