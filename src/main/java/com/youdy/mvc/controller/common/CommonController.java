package com.youdy.mvc.controller.common;

import java.io.Serializable;

import org.springframework.stereotype.Controller;

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
@Controller
public class CommonController implements Serializable {

	private static final long serialVersionUID = -6746155676990970786L;
	
	public static final String DEFAULT_LOGGER_NAME = "COMMON_LOG";
	
	// public static final String common_result_view = "result";
	
	public enum StaticPage {
		COMMON_HTML("common", "common/common", "公共HTML页面");
		
		private String pageId;
		private String path;
		private String comment;
		StaticPage(final String pageId, final String path, final String comment) {
			this.path = path;
			this.comment = comment;
			this.pageId = pageId;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public String getPageId() {
			return pageId;
		}
		public void setPageId(String pageId) {
			this.pageId = pageId;
		}
		
	}
}
