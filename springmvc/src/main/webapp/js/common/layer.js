/**
* layer ʾ��
**/
layer.confirm("ȷ���޸�  " + vm.tableName + " ����", {
                title: '����ȷ��',
                btn: ['ȷ��', 'ȡ��']
            },
            function () {
                //��ఴť��ȷ��
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
                        layer.msg('�����ɹ�', {icon: 1});
                        var xxurl = '/map/resources/app/shujudituliebiao.html';
                        xxurl += "?databaseName=" + encodeURIComponent(encodeURIComponent(vm.databaseName));
                        window.setTimeout(function(){
                        	window.location.href = xxurl;
                        } , 2000);
                    } else {
                        showErrorMsg("����ʧ�ܣ� " + jsonData.message);
                        //$("#btdbTableClick").prop("disabled",false);
                        //diableAllinputs(false, "dbmodelForm");
                    }
                }
            },
            function () {
                //�Ҳఴť��ȡ��
            }
        );

		
		layer.open({
        content: msg,
        title: "������ʾ",
        icon: 2
    });

layer.msg('��ȡ�༭����ʧ��');