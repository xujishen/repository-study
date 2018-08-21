var c = document.getElementById('c');
    var ctx = c.getContext('2d');

function RectNode () {
    this.id = 0;
}
RectNode.prototype.draw = function () {
    ctx.beginPath();
    ctx.arc(50, 50, 20, 0, 2 * Math.PI, false);
    ctx.strokeStyle = '#506f74';
    ctx.lineWidth = 3;
    ctx.stroke();
    ctx.closePath();
    ctx.restore();
};