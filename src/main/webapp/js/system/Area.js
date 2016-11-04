	/**
	 * 区域js
	 */
	$().ready(function() {
		AreaService.initAreaData();
		
	});
	
	/**
	 * 区域服务类
	 */
	var AreaService = {
			datas: {
				areaList: [],
				areaBean: new Object()
			},
			
			initAreaData: function () {
				var area = new Area();
				area.setAreaId(1);
			}
	};
	
	/**
	 * 区域对象
	 */
	function Area() {
		
	};
	Area.prototype.areaId = undefined;
	Area.prototype.setAreaId = function(val) {
		this.areaId = val;
	};
	
