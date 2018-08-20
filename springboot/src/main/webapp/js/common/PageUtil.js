var PageUtil = {
	openPage: function(pageName) {
		var url = _CONTEXTPATH + '/static/page/' + pageName + '.htmls?num=' + Math.random();
		window.open(url, '_blank');
	}
};