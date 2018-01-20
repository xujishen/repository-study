package com.youdy.handler;

import com.youdy.bean.SysAreaBean;
import com.youdy.cache.OneCache;
import com.youdy.enums.CacheDbEnum;
import com.youdy.enums.CachePrefixEnum;
import com.youdy.mvc.service.SysAreaService;
import com.youdy.utils.MapUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CacheHandler implements Serializable {

	private static final long serialVersionUID = -4934365135940315018L;
	
	@Autowired
	SysAreaService sysAreaService;
	
	/*static {
		if (sysAreaService == null) {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:config/spring-servlet.xml");
			sysAreaService = (SysAreaService) context.getBean("areaService");
			System.out.println(sysAreaService);
		}
	}*/
	
	public void init() {
		final Jedis cache = OneCache.getCache();
		SysAreaBean bean = new SysAreaBean();
		bean.setStartIndex(0);
		bean.setEndIndex(Integer.MAX_VALUE);
		List<SysAreaBean> searchAreas = sysAreaService.searchAreas(bean);
		
		if (searchAreas != null && searchAreas.size() > 0) {
			// 获取 0 号数据库
			cache.select(CacheDbEnum.AREA_DB.getDbIndex());
			final String prefix = CachePrefixEnum.AREA_PREFIX.getPrefix();
			final String seperate = CachePrefixEnum.AREA_PREFIX.getSeperate();
			for (SysAreaBean area : searchAreas) {
				// 主键
				Integer id = area.getAreaID();
				// 当前记录的创建戳
				Long currCreateTime = area.getCreateTime().getTime();
				area.setCreateTimeLong(currCreateTime);
				// 缓存key
				String cacheKey = prefix + seperate + id;
				final Map<String, String> cacheMap = cache.hgetAll(cacheKey);
				// 缓存中旧的时间戳
				final Long oldCreateTime = new Long(cacheMap.get("createTimeLong") == null ? "0" : cacheMap.get("createTimeLong"));
				if (cacheMap != null && currCreateTime.compareTo(oldCreateTime) <= 0) {
					continue;
				}

				Map<String, String> areaMap = new HashMap<String, String>();
				try {
					try {
						areaMap = BeanUtils.describe(area);
						areaMap = MapUtil.filterKeyAndValue(areaMap, new MapUtil.Filter() {
							@Override
							public boolean doFilter(Object key, Object value) {
								if (key == null || "class".equals(key)) {
									return true;
								}
								return false;
							}
						}, new MapUtil.Filter() {
							@Override
							public boolean doFilter(Object key, Object value) {
								if (value == null || String.valueOf(value).trim().length() == 0) {
									return true;
								}
								return false;
							}
						});
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				cache.hmset(cacheKey, areaMap);
			}
		}
	}
	
}
