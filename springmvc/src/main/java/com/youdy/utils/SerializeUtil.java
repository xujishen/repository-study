package com.youdy.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 序列化工具类
 *
 * @author 宿继申
 * @version 1.0
 * @File: SerializeUtil.java
 * @package com.youdy.utils
 * @date: 2016年8月30日 下午5:30:33
 * @Copyright (C) 2008-2016 www.oneapm.com. all rights reserved.
 */
public final class SerializeUtil {

    static Logger logger = Logger.getLogger(SerializeUtil.class.toString());

    /**
     * 序列化对象 - 将JVM内存中的java对象序列为可被外部识别的byte数组
     *
     * @param t
     * @return
     */
    public static <T> byte[] doSerialize(T t) {
        if (t == null) {
            return new byte[0];
        }
        try (
    		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
    		ObjectOutputStream oos = new ObjectOutputStream(baos);
        ) {
            oos.writeObject((Object) t);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "序列化对象<" + t + ">失败, 原因: " + e.getMessage(), new Throwable());
            return null;
        }
    }

    /**
     * 反流化对象 - 将外部提过的byte数组序列化为JVM可识别的java对象
     *
     * @param t
     */
    @SuppressWarnings("unchecked")
    public static <T> T unSerialize(byte... bytes) {
        if (bytes == null || bytes.length < 1) {
            return null;
        }
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        		ObjectInputStream ois = new ObjectInputStream(bais);)
        {
            return (T) ois.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARNING, "反序列化对象<" + bytes + ">失败, 原因: " + e.getMessage(), new Throwable());
            return null;
        }
    }
}
