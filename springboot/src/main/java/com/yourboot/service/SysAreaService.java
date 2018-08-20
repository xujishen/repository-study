package com.yourboot.service;

import com.yourboot.bean.SysAreaBean;

import java.util.List;

public interface SysAreaService {
	
	/**
	 * 搜索区域数据
	 * @param bean
	 * @return
	 */
	public List<SysAreaBean> searchAreas(SysAreaBean bean);
	
}
