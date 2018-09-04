package com.yourboot.service.impl;

import com.yourboot.bean.SysAreaBean;
import com.yourboot.mapper.SysAreaMapper;
import com.yourboot.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service/*(value="areaService")*/
/*@Qualifier(value="areaService")*/
public class SysAreaServiceImpl extends CommonService implements SysAreaService {
	
	@Autowired
	SysAreaMapper areaMapper;

	/**
	 * 搜索区域数据
	 * @return
	 */
	@Transactional
	@Override
	public List<SysAreaBean> searchAreas(SysAreaBean bean) {
		try {
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
