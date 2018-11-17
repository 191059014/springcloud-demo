package com.hb.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class CastaliaBeanUtils {

    protected static Logger logger = LoggerFactory.getLogger(CastaliaBeanUtils.class);

    public static Object getSimpleProperty(Object bean, String name) {
        if ((bean instanceof Map))
            return ((Map) bean).get(name);
        try {
            return BeanUtils.getSimpleProperty(bean, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getProperty(Object bean, String name) {
        if ((bean instanceof Map))
            return ((Map) bean).get(name);
        try {
            return BeanUtils.getProperty(bean, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getPrivateProperty(Object bean, String name) {
        try {
            Field field = bean.getClass().getDeclaredField(name);
            if (field != null) {
                field.setAccessible(true);
                return field.get(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void setSimpleProperty(Object bean, String name, Object value) {
        if ((bean instanceof Map))
            ((Map) bean).put(name, value);
        try {
            PropertyUtils.setSimpleProperty(bean, name, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setProperty(Object bean, String name, Object value) {
        if ((bean instanceof Map))
            ((Map) bean).put(name, value);
        try {
            BeanUtils.setProperty(bean, name, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Field getDeclaredField(Object object, String propertyName)
            throws NoSuchFieldException {
        Assert.notNull(object);
        Assert.hasText(propertyName);
        return getDeclaredField(object.getClass(), propertyName);
    }

    public static Field getDeclaredField(Class clazz, String propertyName)
            throws NoSuchFieldException {
        Assert.notNull(clazz);
        Assert.hasText(propertyName);
        for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass())
            try {
                return superClass.getDeclaredField(propertyName);
            } catch (NoSuchFieldException localNoSuchFieldException) {
            }
        throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + propertyName);
    }

    public static Object forceGetProperty(Object object, String propertyName) {
        Assert.notNull(object);
        Assert.hasText(propertyName);
        try {
            Field field = getDeclaredField(object, propertyName);

            boolean accessible = field.isAccessible();
            field.setAccessible(true);

            Object result = null;

            result = field.get(object);
            field.setAccessible(accessible);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            if (logger.isErrorEnabled()) {
                logger.error("forceGetPropertyError");
            }
        }

        return null;
    }

    public static void forceSetProperty(Object object, String propertyName, Object newValue)
            throws NoSuchFieldException {
        Assert.notNull(object);
        Assert.hasText(propertyName);

        Field field = getDeclaredField(object, propertyName);
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(object, newValue);
        } catch (IllegalAccessException e) {
            logger.info("Error won't happen");
        }
        field.setAccessible(accessible);
    }

    public static Object invokePrivateMethod(Object object, String methodName, Object[] params)
            throws NoSuchMethodException {
        Assert.notNull(object);
        Assert.hasText(methodName);
        Class[] types = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            types[i] = params[i].getClass();
        }

        Class clazz = object.getClass();
        Method method = null;
        for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                method = superClass.getDeclaredMethod(methodName, types);
            } catch (NoSuchMethodException localNoSuchMethodException) {
            }
        }

        if (method == null) {
            throw new NoSuchMethodException("No Such Method:" + clazz.getSimpleName() + methodName);
        }
        boolean accessible = method.isAccessible();
        method.setAccessible(true);
        Object result = null;
        try {
            result = method.invoke(object, params);
        } catch (Exception e) {
            ReflectionUtils.handleReflectionException(e);
        }
        method.setAccessible(accessible);
        return result;
    }
}
