package com.youdy.mvc.service;

import java.util.List;

import com.youdy.bean.SysAreaBean;
import com.youdy.mvc.service.impl.CommonService;

public interface SysAreaService {
	
	/**
	 * 搜索区域数据
	 * @param SysAreaBean bean
	 * @return
	 */
	public List<SysAreaBean> searchAreas(SysAreaBean bean);
	
}
