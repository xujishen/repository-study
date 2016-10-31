$().ready(function() {
	// 定时器
	//window.requestNextAnimationFrame(animational);
	
	initAreaData();
	
});

function animational (time) {
	console.log('开始循环..' + time);
	//window.requestNextAnimationFrame(animational(time));
};

/**
 * 区域对象
 */
function Area() {
	
};
Area.prototype.areaId = undefined;
Area.prototype.setAreaId = function(val) {
	this.areaId = val;
};

function initAreaData() {
	Area area = new Area();
	area.setAreaId(1);
};