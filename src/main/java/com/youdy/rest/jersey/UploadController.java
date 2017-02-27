package com.youdy.rest.jersey;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.http.protocol.HttpContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.youdy.mvc.controller.common.CommonController;

@Path(value = "/upload")
public class UploadController extends CommonController {

	private static final long serialVersionUID = -5638731468974812822L;
	
	private static final String upload_root_path = "/upload";
	
	private static final String img_root_path = "/images";
	
	private static final String video_root_path = "/videos";
	
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
			System.out.println(request.getParameter("files[]"));
			System.out.println(request.getParameterNames());
			String files = disposition.getFileName();
			System.out.println(files);
			resultMap.put("code", SUCCESS_CODE);
			resultMap.put("msg", "成功");
		}
		catch (Exception e)
		{
			LOGGER.error("数据请求异常, cause: " + e.getMessage());
			e.printStackTrace();
			resultMap.put("code", EXCEPTION_CODE);
			resultMap.put("msg", "接口异常, cause: " + e.getMessage());
		}
		
		return new Gson().toJson(resultMap);
	}

}
