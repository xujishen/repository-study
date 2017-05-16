package com.youdy.mvc.service;

import java.util.List;

import com.youdy.bean.SysAreaBean;

public interface SysAreaService extends CommonService {
	
	/**
	 * 搜索区域数据
	 * @param SysAreaBean bean
	 * @return
	 */
	public List<SysAreaBean> searchAreas(SysAreaBean bean);
	
}
