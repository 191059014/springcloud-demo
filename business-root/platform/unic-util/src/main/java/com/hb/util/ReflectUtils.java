package com.hb.util;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils {

    private static Logger logger = LoggerFactory.getLogger(ReflectUtils.class);

    public static Class<?> getType(String entityName) {
        try {
            return Class.forName(entityName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("Method getType:", e);
        }
        return null;
    }

    public static Field[] getAllFields(Class<?> c) {
        Field[] fs = c.getDeclaredFields();
        if (c.getSuperclass() != Object.class) {
            fs = (Field[]) ArrayUtils.addAll(fs, getAllFields(c.getSuperclass()));
        }
        return fs;
    }

    public static String getGetterMethodName(String fieldName, Class<?> fieldType) {
        String methodName = null;
        if (fieldType == null) {
            return "get" + CastaliaStringUtils.upperFirst(fieldName);
        }
        if (fieldType.getName().equals("boolean"))
            methodName = "is" + CastaliaStringUtils.upperFirst(fieldName);
        else {
            methodName = "get" + CastaliaStringUtils.upperFirst(fieldName);
        }
        return methodName;
    }

    public static String getGetterMethodName(String fieldName) {
        return getGetterMethodName(fieldName, null);
    }

    public static String getGetterMethodName(Field field) {
        return getGetterMethodName(field.getName(), field.getType());
    }

    public static Method getGetterMethod(Class<?> c, String fieldName, Class<?> fieldType) {
        String methodName = getGetterMethodName(fieldName, fieldType);
        try {
            return c.getMethod(methodName, new Class[0]);
        } catch (NoSuchMethodException | SecurityException e) {
            logger.error("Method getGetterMethod error!Class is " + c.toString() + ",Field Name is " + fieldName);
            e.printStackTrace();
        }
        return null;
    }

    public static Method getGetterMethod(Class<?> c, Field field) {
        return getGetterMethod(c, field.getName(), field.getType());
    }

    public static Method getGetterMethod(Class<?> c, String fieldName) {
        return getGetterMethod(c, fieldName, null);
    }
}
