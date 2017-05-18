var Painter = {
    elements: [],
    draw: function () {
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
    }
};

Painter.init();
Painter.draw();