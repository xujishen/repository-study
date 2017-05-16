package com.youdy.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.youdy.mvc.controller.common.CommonController;

@RequestMapping(value = "/static")
@Controller
@SuppressWarnings("serial")
public class StaticController extends CommonController {
	
	@RequestMapping(value = "/{pageId}/gotoPage.htmls", method = RequestMethod.GET)
	public ModelAndView gotoPage(HttpServletRequest request, @PathVariable(value = "pageId") String pageId, ModelMap model) {
		String page = getAccessPagePath(pageId);
		LOGGER.debug("进入" + page + "页面 !");
		return new ModelAndView(page, model);
	}
	
}
