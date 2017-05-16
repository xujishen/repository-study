package com.youdy.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youdy.bean.SysAreaBean;
import com.youdy.mvc.mapper.SysAreaMapper;
import com.youdy.mvc.service.SysAreaService;

@Service(value="areaService")
public class SysAreaServiceImpl implements SysAreaService {
	
	@Autowired
	SysAreaMapper areaMapper;
	
	/**
	 * 搜索区域数据
	 * @param SysAreaBean bean
	 * @return
	 */
	@Override
	public List<SysAreaBean> searchAreas(SysAreaBean bean) {
		try {
			List<SysAreaBean> list = areaMapper.searchAreas(bean);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("searchAreas错误, e=" + e.getMessage());
			return null;
		}
	}
	
}
