package com.youdy.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.youdy.bean.SysAreaBean;
import com.youdy.mvc.controller.common.CommonController;

@RequestMapping(value = "/area")
@Controller
public class SysAreaController extends CommonController {

	private static final long serialVersionUID = -5638731468974812824L;
	
	@RequestMapping(value = "/getAreaTypes")
	public String getAreaTypes() {
		return "";
	}
	
	/**
	 * 查询区域数据
	 * @param req
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "/searchAreaData.htmmls", method = RequestMethod.POST)
	public String searchAreaData(HttpServletRequest req, @RequestBody String body)
	{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try
		{
			if (StringUtils.isNotEmpty(body))
			{
				SysAreaBean areaBeam = (SysAreaBean) new Gson().fromJson(body, SysAreaBean.class);
			}
			else
			{
				LOGGER.info("searchAreaData.htmmls参数缺失, body为空! ");
			}
			resultMap.put("code", SUCCESS_CODE);
			resultMap.put("msg", "查询数据成功");
		}
		catch (Exception e)
		{
			LOGGER.error("查询区域数据异常, 错误信息: " + e.getMessage());
			e.printStackTrace();
			resultMap.put("code", EXCEPTION_CODE);
			resultMap.put("msg", "查询区域数据异常, 原因: " + e.getMessage());
		}
		
		return new Gson().toJson(resultMap);
	}

}
