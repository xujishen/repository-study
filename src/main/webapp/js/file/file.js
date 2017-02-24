var modelName = "/rest/upload";

$().ready(function() {

	$('#ssi-upload').ssi_uploader({
		url: _CONTEXTPATH + modelName + '/multis',
		maxFileSize: 6,
		allowed: ['jpg','gif','txt','png','pdf'],
		data: {},
        locale: 'chn',
        preview: true,
        dropZone: true,
        maxNumberOfFiles: '',
        responseValidation: false,
        ajaxOptions: {},
        onUpload: function () {
        },
        onEachUpload: function () {
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