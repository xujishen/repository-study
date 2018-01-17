package com.boot.yourtest.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By shen on 2017-10-24 22:17
 */
@RestController
@RequestMapping(value = "/area")
public class AreaController extends BaseController {
	
	@RequestMapping(value = "list" + REST_PATTERN, method = {RequestMethod.GET, RequestMethod.POST})
	public String getList() {
		return "";
	}
	
}
