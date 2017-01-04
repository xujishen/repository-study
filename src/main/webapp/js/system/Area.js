	/**
	 * 区域js
	 */
	$().ready(function() {
		
		// 帮查询按钮事件
		$('#searchButton').on('click', AreaService.searchAreaData);
		$('#createButton').on('click', AreaService.toCreate);

		// 初始化查询数据
		AreaService.searchAreaData();
		
	});
	
	/**
	 * 区域服务类
	 */
	var AreaService = {
		datas: {
			areaList: [],
			areaBean: new Object(),
			modelName: '/area'
		},
		
		/**
		 * 构建area对象
		 */
		buildAreaBean: function(op) {
			// 查询
			if (ObjectUtils.equals(op, 's')) {
				var areaSearchName = $.trim($('#' + op + '_areaName').val());
				var areaLevel = $('#' + op + 'areaLevel').val();
				var bean = new Area();
				bean.areaSearchName = areaSearchName;
				bean.areaLevel = areaLevel;
				AreaService.datas.areaBean = bean;
			}
		},
		
		/**
		 * 查询区域数据
		 */
		searchAreaData: function () {
			AreaService.buildAreaBean('s');
			
			$.ajax({
				url: _CONTEXTPATH + AreaService.datas.modelName + '/searchAreaData.htmls',
				type: 'POST',
				contentType: "application/json", 
				data: JSON.stringify(AreaService.datas.areaBean),
				success: function(resp) {
					if (ObjectUtils.isNotEmpty(resp)) {
						var jsonData = resp;
						var msg = jsonData.msg;
						AreaService.datas.areaList = resp.data;
						AreaService.renderAreaList();
					}
					else {
						console.log('Not get the response data !');
					}
				},
				error: function(data) {
					console.log(JSON.stringify(data));
				}
			});
			
		},
		
		/**
		 * 渲染区域数据
		 */
		renderAreaList: function() {
			var html = EMPTY;
			var list = AreaService.datas.areaList;
			if (CollectionUtils.isNotBlank(list)) {
				for (var i in list) {
					var bean = list[i];
					html +=
						'<tr>' +
						'	<td>' +
						'		<input type="radio" id="check' + bean.areaID + '"/>' +
						'	</td>' +
						'	<td>' + i + '</td>' +
						'	<td>' + bean.areaName + '</td>' +
						'	<td>' + bean.areaLevelName + '</td>' +
						'	<td>' + bean.status + '</td>' +
						'	<td>' + new Date(bean.createTime).Format("yyyy-MM-dd hh:mm:ss") + '</td>' +
						'</tr>';
				}
			}
			$('#areaList_tbody').empty().append(html);
		},

		/**
		 * 开始创建
		 */
		toCreate: function() {

		}
		
	};
	
	/**
	 * 区域对象
	 */
	function Area() {
		this.areaId;
		this.areaName;
		this.areaSearchName;
		this.areaCode;
		this.parentID;
		this.areaLevel;
		this.createTime;
		this.status;
	};

	Area.prototype = new CommonBean();
	/*Area.prototype.setAreaId = function(val) {
		this.areaId = val;
	};*/
	
