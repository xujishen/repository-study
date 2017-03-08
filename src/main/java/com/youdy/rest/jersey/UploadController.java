package com.youdy.rest.jersey;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.youdy.constants.SystemPropertiesEnum;
import com.youdy.mvc.controller.common.CommonController;
import com.youdy.utils.FileUtil;
import com.youdy.utils.PropertiesUtil;

/**
 * 上传控制器
 * @File: UploadController.java
 * @package com.youdy.rest.jersey
 * @author 宿继申 
 * @date: 2017年3月2日 下午5:18:20
 * @version 1.0
 * @Copyright (C) 2008-2017 www.oneapm.com. all rights reserved.
 *
 */
@Path(value = "/upload")
public class UploadController extends CommonController {

	private static final long serialVersionUID = -5638731468974812822L;
	
	/**
	 * 批量上传文件
	 * @param req
	 * @param body
	 * @return
	 */
	@Path(value = "/multis")
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public String doUplpad(@Context HttpServletRequest request,
			@FormDataParam("files[]") InputStream is, @FormDataParam("files[]") FormDataContentDisposition disposition)
	{
		Map<String, Object> resultMap = new HashMap<String, Object>(10);
		try
		{
			String fileName;
			if (disposition != null && ( (fileName = disposition.getFileName()) != null) ) {
				String randomFileName = FileUtil.generateRandomFileName(fileName);
				
				// Image上传路径
				String imgRootPath = ( PropertiesUtil.getProperty(SystemPropertiesEnum.UPLOAD_ROOT_PATH.getValue()) + 
						PropertiesUtil.getProperty(SystemPropertiesEnum.IMG_ROOT_PATH.getValue()) ).replace("\\", "/");
				
				// 上传资源
				FileUtil.doUpload(is, imgRootPath, randomFileName, true);
				resultMap.put("code", SUCCESS_CODE);
				resultMap.put("msg", "成功");
				resultMap.put("data", randomFileName);
			}
		}
		catch (Exception e)
		{
			LOGGER.error("数据请求异常, cause: " + e.getMessage());
			e.printStackTrace();
			
			/**
			 * 回滚逻辑
			 */
			resultMap.put("code", EXCEPTION_CODE);
			resultMap.put("msg", "接口异常, cause: " + e.getMessage());
		}
		
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 获取请求中 IP:端口
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getUrlInfo (HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	}

}
