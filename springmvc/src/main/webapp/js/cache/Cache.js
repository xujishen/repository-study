	/**
	 * 区域js
	 */
	$().ready(function() {
		
		// 帮查询按钮事件
		$('#execAreaCache').on('click', CacheMng.execAreaCache);

	});

	var CacheMng = {
        execAreaCache: function () {
			$.ajax({
				type: 'POST',
                contentType: "application/json",
                url: _CONTEXTPATH + '/cache/execAreaCache.htmls',
				success: function (resp) {

                }
			});
        }
	}
	

