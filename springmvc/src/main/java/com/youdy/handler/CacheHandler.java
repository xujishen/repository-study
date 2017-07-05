package com.youdy.handler;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.youdy.bean.SysAreaBean;
import com.youdy.mvc.service.SysAreaService;

public final class CacheHandler implements Serializable {

	private static final long serialVersionUID = -4934365135940315018L;
	
	@Autowired
	SysAreaService sysAreaService;
	
	public void init() {
		SysAreaBean bean = new SysAreaBean();
		bean.setStartIndex(0);
		bean.setEndIndex(Integer.MAX_VALUE);
		List<SysAreaBean> searchAreas = sysAreaService.searchAreas(bean);
		System.out.println("区域总数量: " + searchAreas.size());
	}

}
