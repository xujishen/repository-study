package com.youdy.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.youdy.mvc.controller.common.CommonController;

@RequestMapping(value = "/area")
@Controller
public class SysAreaController extends CommonController {

	private static final long serialVersionUID = -5638731468974812824L;
	
	@RequestMapping(value = "/getAreaTypes")
	public String getAreaTypes() {
		return "";
	}

}
