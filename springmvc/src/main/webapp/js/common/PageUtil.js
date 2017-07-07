var PageUtil = {
	openPage: function(pageName) {
		var url = _CONTEXTPATH + '/static/' + pageName + '/gotoPage.htmls?a=1&b=2';
		window.open(url, '_blank');
	}
};