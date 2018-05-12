package com.youdy.mvc.controller;

import com.google.gson.Gson;
import com.youdy.bean.SysAreaBean;
import com.youdy.enums.CacheDbEnum;
import com.youdy.handler.CacheHandler;
import com.youdy.handler.CacheManager;
import com.youdy.mvc.controller.common.CommonController;
import com.youdy.mvc.service.SysAreaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/cache")
@RestController
public class CacheController extends CommonController {

	private static final long serialVersionUID = -5187314689174812824L;
	
	/**
	 * 查询区域数据
	 * @param req
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "/execAreaCache.htmls", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String execAreaCache(HttpServletRequest req)
	{
		Map<String, Object> resultMap = new HashMap<String, Object>(10);
		try
		{
			CacheManager.initAreaCacheByRunnable();
			resultMap.put("code", SUCCESS_CODE);
			resultMap.put("msg", "查询数据成功");
		}
		catch (Exception e)
		{
			LOGGER.error("错误信息: " + e.getMessage());
			e.printStackTrace();
			resultMap.put("code", EXCEPTION_CODE);
			resultMap.put("msg", "数据异常, 原因: " + e.getMessage());
		}
		
		return new Gson().toJson(resultMap);
	}

}
