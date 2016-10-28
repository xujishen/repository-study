/**
 * 定时器, 兼容FF IE CHROME浏览器及其各版本实现
 * H5规范中的requestAnimationFrame
 * 若未实现requestAnimationFrame, 则默认执行javascript原生的setTimeout
 */
window.requestNextAnimationFrame = (function() {
	var originalWebkitMethod,
	wrapper = undefined,
	callback = undefined,
	geckoVersion = 0,
	userAgent = navigator.userAgent,
	index = 0,
	self = this;
	
	// Workaround for Chrome 10 bug where Chrome does not pass the time to the animation function
	if (window.webkitRequestAnimationFrame) {
		
		// Define the wrapper
		wrapper = function(time) {
			if (time === undefined) {
				time = + new Date();
			}
			self.callback(time);
		};
		
		// Make the switch
		originalWebkitMethod = window.webkitRequestAnimationFrame;
		
		window.webkitRequestAnimationFrame = function(callback, element) {
			self.callback = callback;
			// Browser calls wrapper; wrapper calls the callback;
			originalWebkitMethod(wrapper, element);
		};
	}
	
	// Workaround for Gecko 2.0, which has a bug in mozRequestAnimationFrame() that restricts to 30 - 40 fps
	if (window.mozRequestAnimationFrame) {
		// check gecko version. Gecko is used by browsers
		// other than FF. Gecko 2.0 corresponds to FF 4.0
		index = userAgent.indexOf('rv:');
		
		if (userAgent.indexOf('Gecko') != -1) {
			geckoVersion = userAgent.substr(index + 3, 3);
			if (geckoVersion === '2.0') {
				// force the return statement to fall through to the setTimeout() function
				window.mozRequestAnimationFrame = undefined;
			}
		}
	}
	
	return window.requestAnimationFrame 		||
		   window.webkitRequestAnimationFrame 	||
		   window.mozRequestAnimationFrame 		||
		   window.oRequestAnimationFrame 		||
		   window.msRequestAnimationFrame 		||
		function (callback, element) {
			var start, finished;
			window.setTimeout(function() {
				start = + new Date();
				callback(start);
				finished = + new Date();
				self.timeout = 1000 / 60 - (finished - start);
			}, self.timeout);
		};
})();