/**
* layer 示例
**/
layer.confirm("确定修改  " + vm.tableName + " 表吗？", {
                title: '建表确认',
                btn: ['确定', '取消']
            },
            function () {
                //左侧按钮，确定
                $.ajax({
                    url: '/map/database/editTableSubmit.action',
                    global: false,
                    async: false,
                    data: data,
                    type: "POST",
                    success: savedCallback,
                    dataType: "json"
                });
                function savedCallback(jsonData) {
                    if (jsonData.status) {
                        layer.msg('操作成功', {icon: 1});
                        var xxurl = '/map/resources/app/shujudituliebiao.html';
                        xxurl += "?databaseName=" + encodeURIComponent(encodeURIComponent(vm.databaseName));
                        window.setTimeout(function(){
                        	window.location.href = xxurl;
                        } , 2000);
                    } else {
                        showErrorMsg("操作失败： " + jsonData.message);
                        //$("#btdbTableClick").prop("disabled",false);
                        //diableAllinputs(false, "dbmodelForm");
                    }
                }
            },
            function () {
                //右侧按钮，取消
            }
        );

		
		layer.open({
        content: msg,
        title: "错误提示",
        icon: 2
    });

layer.msg('获取编辑内容失败');