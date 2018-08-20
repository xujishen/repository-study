package com.yourboot.utils;

import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Su Jishen on 2018/1/18 14:32.
 */
public class MapUtil extends MapUtils {
	
	public interface Filter {
		boolean doFilter(Object key, Object value);
	}
	
	
	public static Map filterKeyAndValue(final Map map, final Filter keyFilter, final Filter valueFilter) {
		if (map == null || map.size() < 1 || (keyFilter == null && valueFilter == null)) {
			return map;
		}
		Map out = new HashMap(map);
		final Set<Map.Entry> set = map.entrySet();
		final Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			final Map.Entry entry = (Map.Entry) iterator.next();
			final Object key = entry.getKey();
			final Object value = entry.getValue();
			if (keyFilter != null && keyFilter.doFilter(key, null)) {
				out.remove(key);
			}
			if (valueFilter != null && valueFilter.doFilter(null, value)) {
				out.remove(key);
			}
		}
		return out;
	}
	
}
