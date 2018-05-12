package com.youdy.handler;

import com.google.common.collect.Maps;
import com.youdy.bean.SysAreaBean;
import com.youdy.cache.OneCache;
import com.youdy.enums.CacheDbEnum;
import com.youdy.enums.CachePrefixEnum;
import com.youdy.mvc.service.SysAreaService;
import com.youdy.utils.IocUtil;
import com.youdy.utils.MapUtil;
import com.youdy.utils.StringUtil;
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
            // Inject the service bean
            SysAreaService sysAreaService = (SysAreaService) IocUtil.getService("areaService");

            SysAreaBean bean = new SysAreaBean();
            bean.setStartIndex(0);
            bean.setEndIndex(Integer.MAX_VALUE);
            List<SysAreaBean> searchAreas = sysAreaService.searchAreas(bean);
            AtomicInteger cacheCount = new AtomicInteger(0);
            int finalCount = 0;
            if (searchAreas != null && searchAreas.size() > 0) {
                final String prefix = CachePrefixEnum.AREA_PREFIX.getPrefix();
                final String seperate = CachePrefixEnum.AREA_PREFIX.getSeperate();
                Map<String, Double> scoreMaps = Maps.newHashMap();
                for (int i = 0; i < searchAreas.size(); i++) {
                    SysAreaBean area = searchAreas.get(i);

                    // 主键
                    Integer id = area.getAreaID();
                    // 当前记录的创建戳
                    Long currCreateTime = area.getCreateTime().getTime();
                    area.setCreateTimeLong(currCreateTime);
                    // 缓存key
                    String cacheKey = prefix + seperate + id;
                    scoreMaps.put(cacheKey, (double) i);

                    final Map<String, String> cacheMap = getArea(cacheKey);
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
                    saveArea(cacheKey, areaMap);
                    finalCount = cacheCount.incrementAndGet();
                }
                // 存储区域ID 至 Redis DB[1]的sorted set中
                saveAreaIdToCache(scoreMaps);

            }
            return finalCount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Map<String, String> getArea(String cacheKey) {
        final Jedis cache = OneCache.getCache();
        try {
            // 获取 0 号数据库
            cache.select(CacheDbEnum.AREA_DB.getDbIndex());
            return cache.hgetAll(cacheKey);
        } finally {
            cache.close();
        }
    }

    public static void saveArea(String cacheKey, Map<String, String> areaMap) {
        final Jedis cache = OneCache.getCache();
        // 获取 0 号数据库
        cache.select(CacheDbEnum.AREA_DB.getDbIndex());
        cache.hmset(cacheKey, areaMap);
        cache.close();
    }

    /**
     * 保存区域ID 至 sortedSet
     *
     * @param score
     * @param areaCacheKey
     */
    public static void saveAreaIdToCache(double score, String areaCacheKey) {
        if (!StringUtil.areNotBlank(score, areaCacheKey)) {
            return;
        }
        Jedis cache = OneCache.getCache();
        try {
            cache.select(CacheDbEnum.AREA_ID_DB.getDbIndex());
            cache.zadd("AreaIDs", score, areaCacheKey);
        } finally {
            cache.close();
        }
    }

    /**
     * 保存区域ID 至 sortedSet
     *
     * @param map
     */
    public static void saveAreaIdToCache(Map<String, Double> map) {
        Jedis cache = OneCache.getCache();
        cache.select(CacheDbEnum.AREA_ID_DB.getDbIndex());
        cache.zadd("AreaIDs", map);
    }

    public static List getAreaList(SysAreaBean areaBean) {
        Integer cacheDbNum = areaBean.getCacheDbNum();
        Integer startIndex = areaBean.getStartIndex();
        Integer endIndex = areaBean.getEndIndex();
        return null;
    }

}
