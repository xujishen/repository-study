package com.youdy.mvc.service.impl;

import com.google.gson.Gson;
import com.youdy.bean.SysAreaBean;
import com.youdy.cache.OneCache;
import com.youdy.handler.AreaCacheThread;
import com.youdy.mvc.annotation.ResultHandler;
import com.youdy.mvc.mapper.SysAreaMapper;
import com.youdy.mvc.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service(value="areaService")
@Qualifier(value="areaService")
public class SysAreaServiceImpl  extends CommonService implements SysAreaService {
	
	@Autowired
	SysAreaMapper areaMapper;

	/**
	 * 搜索区域数据
	 * @return
	 */
	@ResultHandler(clazz = AreaCacheThread.class, method = "handleAreaResult", args = {Collection.class})
	@Override
	public List<SysAreaBean> searchAreas(SysAreaBean bean) {
		try {
			
			final String sysAreas = OneCache.getCache().get("sysAreas");
			if (sysAreas != null && sysAreas.length() > 0) {
				List<SysAreaBean> cacheBean = new Gson().fromJson(sysAreas, List.class);
			}
			
			
			List<SysAreaBean> list = areaMapper.searchAreas(bean);
			/*Comparator<SysAreaBean> comparator = (o1, o2) -> {
				final Integer id1 = o1.getAreaID();
				final Integer id2 = o2.getAreaID();
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
			list.sort(Comparator.comparing(SysAreaBean :: getAreaID));*/

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("searchAreas错误, e=" + e.getMessage());
			return null;
		}
	}

}
