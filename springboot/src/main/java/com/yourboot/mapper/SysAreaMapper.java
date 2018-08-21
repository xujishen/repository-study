package com.yourboot.mapper;

import com.yourboot.bean.SysAreaBean;

import java.util.List;

public interface SysAreaMapper {
	
	List<SysAreaBean> searchAreas(SysAreaBean bean);
}
