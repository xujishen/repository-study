var PageUtil = {
	openPage: function(pageName) {
		var url = _CONTEXTPATH + '/static/' + pageName + '/gotoPage.htmls';
		window.open(url, '_blank');
	}
};