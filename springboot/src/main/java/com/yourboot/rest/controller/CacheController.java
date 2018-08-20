package com.yourboot.rest.controller;

import com.google.gson.Gson;
import com.yourboot.handler.CacheManager;
import com.yourboot.rest.controller.common.CommonController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/cache")
@RestController
public class CacheController extends CommonController {

	private static final long serialVersionUID = -5187314689174812824L;
	
	/**
	 * 查询区域数据
	 * @param req
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
