package com.youdy.mvc.service.impl;

import java.util.Collections;
import java.util.Comparator;
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
			Comparator<SysAreaBean> comparator = (o1, o2) -> {
				final Integer id1 = o1.getId();
				final Integer id2 = o2.getId();
				if (id1 > id2) {
					return 1;
				} else if (id1 < id2) {
					return -1;
				} else {
					return 0;
				}
			};

			// 基于 Lamda表达式排序
			Collections.sort(list, comparator);
			Collections.sort(list, (o1, o2) -> Integer.compare(o1.getAreaID(), o2.getAreaID()));

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("searchAreas错误, e=" + e.getMessage());
			return null;
		}
	}

}
