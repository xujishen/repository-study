package com.youdy.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
	
	private static final Logger LOGGER = Logger.getLogger(DEFAULT_LOGGER_NAME);
	
	@RequestMapping(value = "/{pageId}/gotoPage.htmls")
	public ModelAndView gotoPage(HttpServletRequest request, @PathVariable(value = "pageId") String pageId, ModelMap model) {
		String page = "index";
		if (StringUtils.isNotEmpty(pageId)) {
			
		}
		LOGGER.info("进入" + pageId + "页面 !");
		return new ModelAndView(page, model);
	}
	
}
