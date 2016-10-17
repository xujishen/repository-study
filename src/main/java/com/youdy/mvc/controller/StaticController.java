package com.youdy.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.youdy.mvc.controller.common.CommonController;


@RequestMapping(value = "/static")
@Controller
@SuppressWarnings("serial")
public class StaticController extends CommonController {
	
	private static final Log LOGGER = LogFactory.getLog(DEFAULT_LOGGER_NAME);
	
	@RequestMapping(value = "/{pageId}/gotoPage.htmls")
	public ModelAndView gotoPage(HttpServletRequest request, @PathVariable(value = "pageId") String pageId, ModelMap model) {
		String page = StaticPage.COMMON_HTML.getPath();
		try {
			if (StringUtils.isNotEmpty(pageId)) {
				if (StaticPage.COMMON_HTML.getPageId().equals(pageId)) {
					
				}
			}
			LOGGER.debug("进入" + page + "页面 !");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(page, model);
	}
	
}
