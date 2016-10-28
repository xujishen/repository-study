$().ready(function() {
	window.requestNextAnimationFrame(animational);
});

function animational (time) {
	console.log('开始循环..' + time);
};