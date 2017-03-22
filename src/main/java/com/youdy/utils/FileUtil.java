package com.youdy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannel;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.netty.channel.Channel;

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
	 * BIO上传
	 * @param is - 输入流
	 * @param file - 文件对象
	 */
	private static void doUploadForBio(final InputStream is, final File file) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			/**
			 * 读取频率4k
			 */
			byte[] bytes = new byte[1024 * 4];
			long count = 0;
		    int n = 0;
		    long t = System.currentTimeMillis();
			while ( ( n = is.read(bytes) ) != -1 ) {
				fileOutputStream.write(bytes, 0, n);
			    count += n;
			}
			System.out.println("BIO上传耗时: " + (System.currentTimeMillis() - t));
			LOGGER.info("读取文件大小: " + count);
			fileOutputStream.flush();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
		finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
					LOGGER.info("关闭is错误: e=" + e.getMessage());
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					LOGGER.info("关闭fileOutputStream错误: e=" + e.getMessage());
				}
			}
		}
	}
	
	
	private static void doUploadForNio2(final InputStream is, Path path) {
		AsynchronousFileChannel fileChannel = null;
		try {
			fileChannel = AsynchronousFileChannel.open(path,
					new HashSet<StandardOpenOption>(
							EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)),
					Executors.newSingleThreadExecutor(Executors.defaultThreadFactory()), null);
			
			ByteBuffer buffer = ByteBuffer.allocateDirect(4 * 1024);
			
			byte[] bytes = new byte[4 * 1024];
			
			while (is.read(bytes) != -1) {
				buffer.put(bytes);
				buffer.flip();
				fileChannel.write(buffer, 0);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally {
			if (fileChannel != null) {
				try {
					fileChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
	}
	
	/**
	 * Nio上传 (base on Java 1.4)
	 * @param is - 输入流
	 * @param fileOutputStream - 输出流
	 */
	private static void doUploadForNio(final InputStream is, Path path) {
		// 输出管道
		FileChannel outChannel = null;
		
		try {
			outChannel = FileChannel.open(path, EnumSet.of(StandardOpenOption.WRITE));
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 1024);
			
			byte[] bytes = new byte[4 * 1024];
			
			try {
				long t = System.currentTimeMillis();
				while ( is.read(bytes) != -1 ) {
					byteBuffer.put(bytes);
					byteBuffer.flip();  
					outChannel.write(byteBuffer);
					byteBuffer.clear();  
				}
				System.out.println("NIO上传耗时: " + (System.currentTimeMillis() - t));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		finally {
			if (outChannel != null) {
				try {
					outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 上传资源
	 * @param is - 输入流
	 * @param path - 服务的文件路径
	 * @param fileName - 文件名称
	 */
	public static void doUpload(final InputStream is, final String path, final String fileName, boolean nio) {
		/**
		 * BIO
		 */
		if (!nio) {
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}
			file.mkdirs();
			String newFilePath = new String((path + "/" + fileName).getBytes(Charset.forName("UTF-8")));
			file = new File(newFilePath);
			
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					LOGGER.info("创建文件(" + file + ")出错, e=" + e.getMessage());
					return;
				}
			}
			doUploadForBio(is, file);
		}
		/**
		 * NIO
		 */
		else {
			doUploadForNio2(is, Paths.get(path, fileName));
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
