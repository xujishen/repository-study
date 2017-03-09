var modelName = "/rest/upload";

$().ready(function() {

	/**
	 * 初始化上传区域
	 */
	$('#ssi-upload').ssi_uploader({
		url: _CONTEXTPATH + modelName + '/multis',
		maxFileSize: 266,
		allowed: ['jpg','gif','txt','png','pdf', 'zip'],
		data: {},
        locale: 'chn',
        preview: true,
        dropZone: true,
        maxNumberOfFiles: '',
        responseValidation: true,
        ajaxOptions: {},
        onUpload: function () {
        },
        onEachUpload: function (response) {
        	var bean = {};
        	// 应用层返回值
        	var _responseData = response['_responseData'];
        	// 应用层生成的随机文件名
        	var finalFileName = _responseData.data;
        	// 原始文件名
        	var originalFileName = response['name'];
        	// 文件类型 eg. image/png
        	var fileType = response['type'];
        	// 文件大小  unit: M
        	var fileSize = response['size'];
        	
        	bean.finalFileName = finalFileName;
        	bean.originalFileName = originalFileName;
        	bean.fileType = fileType;
        	bean.fileSize = fileSize;
        },
        beforeUpload: function () {
        },
        beforeEachUpload: function () {
        },
        errorHandler: {
            method: function (msg) {
                alert(msg);
            },
            success: 'success',
            error: 'error'
        }
	});
	$('#ssi-upload2').ssi_uploader({url:'#',preview:false,allowed:['jpg','gif','txt','png','pdf']});
	$('#ssi-upload3').ssi_uploader({url:'#',dropZone:false,allowed:['jpg','gif','txt','png','pdf']});
	
});

