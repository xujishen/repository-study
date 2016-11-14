package com.youdy.mvc.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

interface CommonService {

	public static final String DEFAULT_LOGGER_NAME = "COMMON_LOG";
	
	public static final Log LOGGER = LogFactory.getLog(DEFAULT_LOGGER_NAME);
}
