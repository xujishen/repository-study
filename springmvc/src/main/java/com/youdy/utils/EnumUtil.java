package com.youdy.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 枚举工具类
 *
 * @author 宿继申
 * @version 1.0
 * @File: EnumUtil.java
 * @date: 2016年1月15日 上午10:15:43
 */
public final class EnumUtil {

    public static Log LOG = LogFactory.getLog(EnumUtil.class);

    /**
     * 遍历枚举
     * Note: 要求所有enum类必须声明私有属性和getters以及构造器
     * @param @param clazz
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @author Su Jishen
     * @date 2016年1月15日 上午10:15:59
     */
    public static <T> List<Map<String, Object>> iterateEnum(Class<T> clazz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if (clazz != null && clazz.isEnum()) {
            T[] enums = clazz.getEnumConstants();
            if (enums != null && enums.length > 0) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Iterate the enum class: " + clazz.getName());
                }
                List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(enums.length);
                Field[] fields = clazz.getDeclaredFields();
                for (T t : enums) {
                    for (Field field : fields) {
                        if (field.isEnumConstant() || field.isSynthetic()) {
                            continue;
                        }
                        String fieldName = field.getName().toLowerCase();
                        String methodName = "get" + StringUtils.left(fieldName, 1).toUpperCase()
                                + StringUtils.right(fieldName, fieldName.length() - 1);
                        Method method = clazz.getDeclaredMethod(methodName, new Class[] {});
                        Object value = method.invoke(t, new Object[]{});
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(fieldName, value);
                        result.add(map);
                    }

                }
                return result;
            }
        } else {
            new IllegalArgumentException("The clazz " + clazz + " is not an enum class ! ");
        }
        return null;
    }

    /**
     * 根据关键值获取指定枚举类中子枚举
     * 获取枚举类com.oneapm.enum.AAA中id=5的枚举类对象
     * this("id", 5, com.oneapm.enum.AAA.class)
     * Note: enumClazz中必须提供public getters.
     *
     * @param key:       子枚举唯一标识如, code/id
     * @param value:     枚举类中成员属性的值, 此值的类型不确定所有声明了<V>
     * @param enumClazz: 枚举的声明类型
     * @return
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    public static <T, V> T getEnumClazzByKey(String key, V value, Class<T> enumClazz) throws NoSuchMethodException,
            SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        if (!enumClazz.isEnum()) {
            new Exception("The enumClazz is not an enum ! ");
        }
        T[] enums = enumClazz.getEnumConstants();
        if (enums.length < 1) {
            new Exception("The enumClazz does not contains any concrete constants ! ");
        }
        for (T t : enums) {
            String getter = "get" + StringUtils.left(key, 1).toUpperCase() + StringUtils.right(key, key.length() - 1);
            Method getterMethod = enumClazz.getMethod(getter, new Class[]{});
            V v = (V) getterMethod.invoke(t, new Object[]{});
            if (v == value || StringUtils.equals(String.valueOf(value), String.valueOf(value))) {
                return t;
            }
        }
        return null;
    }
    
}
