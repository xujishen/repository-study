var _CONTEXTPATH = '/yourtest';

var _INCLUDES =	  '<script type="text/javascript" src="' + _CONTEXTPATH + '/js/common/jquery-3.1.1.min.js?num=' + Math.random() + '"></script>'
				+ '<script type="text/javascript" src="' + _CONTEXTPATH + '/js/common/json2.js?num=' + Math.random() + '"></script>'
				+ '<script type="text/javascript" src="' + _CONTEXTPATH + '/js/common/bootstrap-datetimepicker.js?num=' + Math.random() + '"></script>'
				+ '<script type="text/javascript" src="' + _CONTEXTPATH + '/js/layer/layer.js?num=' + Math.random() + '"></script>'
				+ '<script type="text/javascript" src="' + _CONTEXTPATH + '/js/common/dateFormat.js?num=' + Math.random() + '"></script>'
			   	+ '<script type="text/javascript" src="' + _CONTEXTPATH + '/js/common/common-function.js?num=' + Math.random() + '"></script>'
				+ '<script type="text/javascript" src="' + _CONTEXTPATH + '/js/common/Timer.js?num=' + Math.random() + '"></script>'
				+ '<script type="text/javascript" src="' + _CONTEXTPATH + '/js/common/CommonBean.js?num=' + Math.random() + '"></script>';
document.write(_INCLUDES);

var EMPTY = '';

/**
 * 扩展对象
 */
function _extend() {
	var len = arguments.length;
	if (len < 1) {
		return {};
	}
	
	var curr;
	for (var i = 0; i < len; i ++) {
		curr = arguments[i];
		
	}
	console.log(arguments);
};