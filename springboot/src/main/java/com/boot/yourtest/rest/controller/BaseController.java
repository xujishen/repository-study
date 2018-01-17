package com.boot.yourtest.rest.controller;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created By shen on 2017-10-24 22:05
 */
public class BaseController implements Serializable {
	
	// Rest请求匹配字符串
	public static final String REST_PATTERN = ".rest";
	
	public static final Logger LOGGER = Logger.getLogger("");
	
	/**
	 * 将request中参数转换到map中
	 * @param request
	 */
	public Map<String, String> requestToMap(HttpServletRequest request) {
		final Map<String, String[]> parameterMap = request.getParameterMap();
		Set<Map.Entry<String, String[]>> entrySet = parameterMap.entrySet();
		Iterator<Map.Entry<String, String[]>> iterator = entrySet.iterator();
		Map<String, String> resultMap = new HashMap<String, String>(parameterMap.size(), 1f);
		while (iterator.hasNext()) {
			Map.Entry<String, String[]> next = iterator.next();
			resultMap.put(next.getKey(), request.getParameter(next.getKey()));
		}
		return resultMap;
	}
}
