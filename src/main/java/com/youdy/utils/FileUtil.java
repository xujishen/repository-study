package com.youdy.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.UUID;

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
	
	public static String doUpload(final InputStream is, final String path) {
		System.out.println(path);
		InputStreamReader reader = new InputStreamReader(is, Charset.forName("UTF-8"));
		BufferedReader buffer = new BufferedReader(reader);
		
		//BufferedWriter bw = new BufferedWriter(out)
		
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		file.mkdirs();
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			try {
				fileOutputStream.write(buffer.read());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		buffer.
//		OutputStream os = new OutputStreamWriter(out)
		
		return "";
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
