package com.yourboot.rest.controller.common;

import com.yourboot.utils.EnumUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 通用控制器
 * @File: CommonController.java
 * @package com.youdy.mvc.controller
 * @author 宿继申 
 * @date: 2016年10月13日 下午5:58:02
 * @version 1.0
 * @Copyright (C) 2008-2016 www.oneapm.com. all rights reserved.
 *
 */
public class CommonController implements Serializable {

	private static final long serialVersionUID = -6746155676990970786L;
	
	public static final String DEFAULT_LOGGER_NAME = "COMMON_LOG";
	
	public static final int EXCEPTION_CODE = -1;	// controller请求异常返回代码
	
	public static final int SUCCESS_CODE = 1;		// controller请求成功返回代码

	public static final int ERROR_CODE = 1;			// controller请求失败代码

	public static final Log LOGGER = LogFactory.getLog(DEFAULT_LOGGER_NAME);
	
	public String getAccessPagePath(String pageId) {
		if (StringUtils.isEmpty(pageId)) {
			return StaticPage.NULL_PAGE.getPath();
		}

		String Path = "";

		try {
			Path = EnumUtil.getEnumInstanceByKey("pageId", pageId, StaticPage.class).getPath();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		if (StringUtils.isEmpty(Path)) {
			Path = pageId.replaceAll("\\." , "/");
		}

		return Path;
	}

	/**
	 * 静态页面枚举类
	 */
	public enum StaticPage {
		NULL_PAGE("404", "404", "公共HTML页面"),
		COMMON_HTML("common", "common/common", "公共HTML页面"),
		AREA_PAGE("area", "system/area/areaManage", "区域管理页面"),
        FILE_PAGE("file", "system/file/fileManage", "文件管理页面"),
        CANVAS_PAGE("canvas", "canvas/canvasMng", "画布管理页面"),
		CACHE_PAGE("cache", "cache/cacheMng", "缓存管理页面");

		private String pageId;
		private String path;
		private String comment;
		private StaticPage(final String pageId, final String path, final String comment) {
			this.path = path;
			this.comment = comment;
			this.pageId = pageId;
		}
		public String getPath() {
			return path;
		}
		public String getComment() {
			return comment;
		}
		public String getPageId() {
			return pageId;
		}

	}

	public void requestToModel(HttpServletRequest request, ModelMap model) {
		final Map<String, String[]> parameterMap = request.getParameterMap();
		Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
		Iterator<Entry<String, String[]>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, String[]> next = iterator.next();
			model.put(next.getKey(), request.getParameter(next.getKey()));
		}
	}

}
