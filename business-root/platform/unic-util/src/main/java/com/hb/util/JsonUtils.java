package com.hb.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.util.List;

public class JsonUtils {
    private static ObjectMapper obj = new ObjectMapper();

    public static String toJsonString(Object o, Boolean prettyFormat) {
        return toJsonString(o, prettyFormat, Boolean.valueOf(false));
    }

    public static String toJsonString(Object o, Boolean prettyFormat, Boolean enableDefaultTyping) {
        return toJsonString(o, prettyFormat, enableDefaultTyping, Boolean.valueOf(false));
    }

    public static String toJsonString(Object o, Boolean prettyFormat, Boolean enableDefaultTyping, Boolean disableMapNull) {
        try {
            ObjectWriter ow = obj.writer();
            if (!prettyFormat.booleanValue()) {
                ow = ow.without(SerializationFeature.INDENT_OUTPUT);
            }
            if (!disableMapNull.booleanValue()) {
                ow = ow.with(SerializationFeature.WRITE_NULL_MAP_VALUES);
            }
            if (enableDefaultTyping.booleanValue())
                obj.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
            else {
                obj.disableDefaultTyping();
            }

            return ow.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T toJavaObject(String json, Class<T> valueType) {
        return toJavaObject(json, valueType, Boolean.valueOf(false));
    }

    public static <T> T toJavaObject(String json, Class<T> valueType, Boolean enableDefaultTyping) {
        try {
            if (enableDefaultTyping.booleanValue())
                obj.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
            else {
                obj.disableDefaultTyping();
            }

            return obj.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> List<T> toJavaList(String json, Class<T> valueType) {
        return toJavaList(json, valueType, Boolean.valueOf(false));
    }

    public static <T> List<T> toJavaList(String json, Class<T> valueType, Boolean enableDefaultTyping) {
        try {
            JavaType javaType = obj.getTypeFactory().constructParametrizedType(List.class, List.class, new Class[]{valueType});
            if (enableDefaultTyping.booleanValue())
                obj.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
            else {
                obj.disableDefaultTyping();
            }

            return (List) obj.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void copyProperties(Object src, Object target) {
        try {
            BeanUtils.copyProperties(target, src);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static <T> T toJavaObject(Object o, Class<T> valueType) {
        try {
            Object target = valueType.newInstance();
            copyProperties(o, target);

            return (T) target;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
    }

    static {
        obj.configure(SerializationFeature.INDENT_OUTPUT, true);
        obj.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
        obj.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        obj.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        obj.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        obj.enable(DeserializationFeature.USE_LONG_FOR_INTS);
        obj.disableDefaultTyping();
        obj.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        obj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DateFormat df = obj.getSerializationConfig().getDateFormat();
        if (df == null) {
            df = new StdDateFormat();
        }
        obj.setDateFormat(df);
    }
}
