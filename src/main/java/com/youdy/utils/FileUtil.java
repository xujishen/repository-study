package com.youdy.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The file util class for operate the file.
 * @File: FileUtil.java
 * @package com.youdy.utils
 * @author 宿继申 
 * @date: 2017年3月1日 下午3:09:47
 * @version 1.0
 * @Copyright (C) 2008-2017 www.oneapm.com. all rights reserved.
 *
 */
public final class FileUtil {
	
	/**
	 * 分隔符
	 */
	private static final String SEPARATE_SYMBOL = "-".intern();
	

	private static final Log LOGGER = LogFactory.getLog(FileUtil.class);
	
	/**
	 * 生成随机文件名
	 * @param originalFileName
	 * @return
	 */
	public synchronized static final String generateRandomFileName(final String originalFileName) {
		FileSeparate fileSeparate = FileUtil.separateFileName(originalFileName);
		UUID uuid = UUID.randomUUID();
		return uuid.toString() + SEPARATE_SYMBOL + fileSeparate.getFileName() + SEPARATE_SYMBOL
				+ System.currentTimeMillis() + fileSeparate.getExtension();
	};
	
	/**
	 * To separate the file to name & extension
	 * @param originalFileName
	 * @return
	 */
	public static final FileSeparate separateFileName(final String originalFileName) {
		if (originalFileName != null && !"".equals(originalFileName.trim())) {
			int len = originalFileName.length();
			int pointIndex = originalFileName.lastIndexOf(".");
			String fileName = originalFileName.substring(0, pointIndex);
			String extension = originalFileName.substring(pointIndex, len);
			
			return new FileSeparate(fileName, extension);
		}
		return new FileSeparate();
	};
	
	/**
	 * 上传资源
	 * @param is
	 * @param path
	 * @param fileName
	 */
	public static void doUpload(final InputStream is, final String path, String fileName) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		file.mkdirs();
		
		FileOutputStream fileOutputStream = null;
		
		try {
			fileOutputStream = new FileOutputStream(new File(path, fileName));
			
			/**
			 * 读取频率4k
			 */
			byte[] bytes = new byte[1024 * 4];
			long count = 0;
		    int n = 0;
			while ( ( n = is.read(bytes) ) != -1 ) {
				fileOutputStream.write(bytes, 0, n);
	            count += n;
			}
			fileOutputStream.flush();
			LOGGER.info("读取文件大小: " + count);
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("写入文件错误: e=" + e.getMessage());
		}
		
		finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					LOGGER.info("关闭fileOutputStream错误: e=" + e.getMessage());
				}
			}
		}
		
		return;
	}
	
	/**
	 * The inner class for abstracting the file-name & file-extension
	 **/
	@SuppressWarnings("unused")
	private static final class FileSeparate {
		private String fileName;
		private String extension;
		
		public FileSeparate(){}
		
		public FileSeparate(String fileName, String extension) {
			this.fileName = fileName;
			this.extension = extension;
		}
		
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getExtension() {
			return extension;
		}
		public void setExtension(String extension) {
			this.extension = extension;
		}
		
		/**
		 * Rewrite the toString() method
		 */
		@Override
		public String toString() {
			return "FileSeparate [fileName=" + fileName + ", extension=" + extension + "]";
		}
		
	}
	
}
