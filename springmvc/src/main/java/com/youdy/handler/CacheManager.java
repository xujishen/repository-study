package com.youdy.handler;

import com.youdy.bean.SysAreaBean;
import com.youdy.cache.OneCache;
import com.youdy.enums.CacheDbEnum;
import com.youdy.enums.CachePrefixEnum;
import com.youdy.mvc.service.SysAreaService;
import com.youdy.utils.IocUtil;
import com.youdy.utils.MapUtil;
import org.apache.commons.beanutils.BeanUtils;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by Su Jishen on 2018/1/22 11:20.
 */
public final class CacheManager {
	
	/**
	 * 通过Runnable接口初始化
	 * 区域缓存数据
	 */
	public static void initAreaCacheByRunnable() {
		final ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(new AreaCacheJob());
	}
	
	/**
	 * 通过Callable接口初始化
	 * 区域缓存数据
	 */
	public static void initAreaCacheByCallable() {
		final ExecutorService executorService = Executors.newSingleThreadExecutor();
		try {
			FutureTask ft = new FutureTask(new AreaCacheTask());
			final Future<Integer> future = (Future<Integer>) executorService.submit(ft);
			Integer count = (Integer) ft.get();
			System.out.println("Callable缓存个数: " + count);
			executorService.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从Mysql同步区域数据至Redis
	 */
	public static int synAreaCache() {
		try {
		final Jedis cache = OneCache.getCache();
		
		// Inject the service bean
		SysAreaService sysAreaService = (SysAreaService) IocUtil.getService("areaService");
		
		SysAreaBean bean = new SysAreaBean();
		bean.setStartIndex(0);
		bean.setEndIndex(Integer.MAX_VALUE);
		List<SysAreaBean> searchAreas = sysAreaService.searchAreas(bean);
		AtomicInteger cacheCount = new AtomicInteger(0);
		int finalCount = 0;
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
				final Long oldCreateTime = new Long(cacheMap.get("createTimeLong") == null ? "0" : cacheMap.get
						("createTimeLong"));
				if (cacheMap != null && currCreateTime.compareTo(oldCreateTime) <= 0) {
					continue;
				}
				
				Map<String, String> areaMap = new HashMap<String, String>();
				
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
				cache.hmset(cacheKey, areaMap);
				finalCount = cacheCount.incrementAndGet();
			}
		}
		return finalCount;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}