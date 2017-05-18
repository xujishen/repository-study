var c = document.getElementById('c'),
    ctx = c.getContext('2d');


function RotateCircle() {
    this.cw = c.width = 400;
    this.ch = c.height = 300;
    this.rand = function (a, b) {
        return ~~((Math.random() * (b - a + 1)) + a);
    };
    this.dToR = function (degrees) {
        return degrees * (Math.PI / 180);
    };
    this.circle = {
        x: (this.cw / 2) + 5,
        y: (this.ch / 2) + 22,
        radius: 90,
        speed: 2,
        rotation: 0,
        angleStart: 270,
        angleEnd: 90,
        hue: 220,
        thickness: 18,
        blur: 25
    };
    this.particles = [];
    this.particleMax = 100;
    ctx.shadowBlur = this.circle.blur;
    ctx.shadowColor = 'hsla(' + this.circle.hue + ', 80%, 60%, 1)';
    ctx.lineCap = 'round';

    var gradient1 = ctx.createLinearGradient(0, -this.circle.radius, 0, this.circle.radius);
    gradient1.addColorStop(0, 'hsla(' + this.circle.hue + ', 60%, 50%, .25)');
    gradient1.addColorStop(1, 'hsla(' + this.circle.hue + ', 60%, 50%, 0)');
    this.gradient1 = gradient1;

    var gradient2 = ctx.createLinearGradient(0, -this.circle.radius, 0, this.circle.radius);
    gradient2.addColorStop(0, 'hsla(' + this.circle.hue + ', 100%, 50%, 0)');
    gradient2.addColorStop(.1, 'hsla(' + this.circle.hue + ', 100%, 100%, .7)');
    gradient2.addColorStop(1, 'hsla(' + this.circle.hue + ', 100%, 50%, 0)');
    this.gradient2 = gradient2;

}
RotateCircle.prototype.draw = function () {
    setInterval(this.loop(this), 12);
};
RotateCircle.prototype.updateCircle = function () {
    if (this.circle.rotation < 360) {
        this.circle.rotation += this.circle.speed;
    } else {
        this.circle.rotation = 0;
    }
};
RotateCircle.prototype.renderCircle = function () {
    ctx.save();
    ctx.translate(this.circle.x, this.circle.y);
    ctx.rotate(this.dToR(this.circle.rotation));
    ctx.beginPath();
    ctx.arc(0, 0, this.circle.radius, this.dToR(this.circle.angleStart), this.dToR(this.circle.angleEnd), true);
    ctx.lineWidth = this.circle.thickness;
    ctx.strokeStyle = this.gradient1;
    ctx.stroke();
    ctx.restore();
};
RotateCircle.prototype.renderCircleBorder = function () {
    ctx.save();
    ctx.translate(this.circle.x, this.circle.y);
    ctx.rotate(this.dToR(this.circle.rotation));
    ctx.beginPath();
    ctx.arc(0, 0, this.circle.radius + (this.circle.thickness / 2), this.dToR(this.circle.angleStart), this.dToR(this.circle.angleEnd), true);
    ctx.lineWidth = 2;
    ctx.strokeStyle = this.gradient2;
    ctx.stroke();
    ctx.restore();
};
RotateCircle.prototype.renderCircleFlare = function () {
    ctx.save();
    ctx.translate(this.circle.x, this.circle.y);
    ctx.rotate(this.dToR(this.circle.rotation + 185));
    ctx.scale(1, 1);
    ctx.beginPath();
    ctx.arc(0, this.circle.radius, 30, 0, Math.PI * 2, false);
    ctx.closePath();
    var gradient3 = ctx.createRadialGradient(0, this.circle.radius, 0, 0, this.circle.radius, 30);
    gradient3.addColorStop(0, 'hsla(330, 50%, 50%, .35)');
    gradient3.addColorStop(1, 'hsla(330, 50%, 50%, 0)');
    ctx.fillStyle = gradient3;
    ctx.fill();
    ctx.restore();
};
RotateCircle.prototype.renderCircleFlare2 = function () {
    ctx.save();
    ctx.translate(this.circle.x, this.circle.y);
    ctx.rotate(this.dToR(this.circle.rotation + 165));
    ctx.scale(1.5, 1);
    ctx.beginPath();
    ctx.arc(0, this.circle.radius, 25, 0, Math.PI * 2, false);
    ctx.closePath();
    var gradient4 = ctx.createRadialGradient(0, this.circle.radius, 0, 0, this.circle.radius, 25);
    gradient4.addColorStop(0, 'hsla(30, 100%, 50%, .2)');
    gradient4.addColorStop(1, 'hsla(30, 100%, 50%, 0)');
    ctx.fillStyle = gradient4;
    ctx.fill();
    ctx.restore();
};
RotateCircle.prototype.createParticles = function () {
    if (this.particles.length < this.particleMax) {
        this.particles.push({
            x: (this.circle.x + this.circle.radius * Math.cos(this.dToR(this.circle.rotation - 85))) + (this.rand(0, this.circle.thickness * 2) - this.circle.thickness),
            y: (this.circle.y + this.circle.radius * Math.sin(this.dToR(this.circle.rotation - 85))) + (this.rand(0, this.circle.thickness * 2) - this.circle.thickness),
            vx: (this.rand(0, 100) - 50) / 1000,
            vy: (this.rand(0, 100) - 50) / 1000,
            radius: this.rand(1, 6) / 2,
            alpha: this.rand(10, 20) / 100
        });
    }
};
RotateCircle.prototype.updateParticles = function () {
    var i = this.particles.length;
    while (i--) {
        var p = this.particles[i];
        p.vx += (this.rand(0, 100) - 50) / 750;
        p.vy += (this.rand(0, 100) - 50) / 750;
        p.x += p.vx;
        p.y += p.vy;
        p.alpha -= .01;

        if (p.alpha < .02) {
            this.particles.splice(i, 1)
        }
    }
};
RotateCircle.prototype.renderParticles = function () {
    var i = this.particles.length;
    while (i--) {
        var p = this.particles[i];
        ctx.beginPath();
        ctx.fillRect(p.x, p.y, p.radius, p.radius);
        ctx.closePath();
        ctx.fillStyle = 'hsla(0, 0%, 100%, ' + p.alpha + ')';
    }
};
RotateCircle.prototype.clear = function () {
    ctx.globalCompositeOperation = 'destination-out';
    ctx.fillStyle = 'rgba(0, 0, 0, .1)';
    ctx.fillRect(0, 0, this.cw, this.ch);
    ctx.globalCompositeOperation = 'lighter';
};
RotateCircle.prototype.loop = function (self) {
    self.clear();
    self.updateCircle();
    self.renderCircle();
    self.renderCircleBorder();
    self.renderCircleFlare();
    self.renderCircleFlare2();
    self.createParticles();
    self.updateParticles();
    self.renderParticles();
};