	/**
	 * 区域js
	 */
	$().ready(function() {
		
		// 帮查询按钮事件
		$('#searchButton').on('click', AreaService.searchAreaData);
		
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
			this.buildAreaBean('s');
			
			$.ajax({
				url: _CONTEXTPATH + AreaService.datas.modelName + '/',
				type: 'POST',
				data: JSON.stringify(AreaService.datas.areaBean),
				success: function(resp) {
					
				}
			});
			
		},
		
		/**
		 * 渲染区域数据
		 */
		renderAreaList: function() {
			
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
		/*Area.prototype.setAreaId = function(val) {
			this.areaId = val;
		};*/
	
