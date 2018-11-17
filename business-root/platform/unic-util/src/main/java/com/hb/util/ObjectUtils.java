package com.hb.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ObjectUtils {
    private static Logger log = LoggerFactory.getLogger(ObjectUtils.class);

    public static Integer asObject(int i) {
        return new Integer(i);
    }

    public static Long asObject(long i) {
        return new Long(i);
    }

    public static Character asObject(char i) {
        return new Character(i);
    }

    public static Short asObject(short i) {
        return new Short(i);
    }

    public static Byte asObject(byte i) {
        return new Byte(i);
    }

    public static Object[] asArray(Object[] os) {
        return os;
    }

    public static void main(String[] args) {
        Object[] os = {Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)};
        asArray(os);
        asArray(new Object[]{os});
        asArray(new Object[]{os});
    }

    public static Object[] asArray(Map<String, Object> map) {
        Object[] o = new Object[map.size()];
        int i = 0;
        for (Map.Entry entry : map.entrySet()) {
            o[i] = entry.getValue();
            i++;
        }
        return o;
    }

    public static String asString(int i) {
        return "" + i;
    }

    public static String asString(long i) {
        return "" + i;
    }

    public static String asString(char i) {
        return "" + i;
    }

    public static String asString(short i) {
        return "" + i;
    }

    public static String asString(byte i) {
        return "" + i;
    }

    public static Integer asInteger(String value) {
        if ((value == null) || (value.equals("")) || (value.length() == 0))
            return null;
        return Integer.valueOf(value);
    }

    public static boolean equals(Object src, Object dest) {
        if (src == dest) {
            return true;
        }
        if (src != null) {
            return src.equals(dest);
        }
        if (dest != null) {
            return dest.equals(src);
        }

        return false;
    }

    public static String toJSONString(Object obj) {
        return JsonUtils.toJsonString(obj, Boolean.valueOf(true));
    }

    public static Object newInstance(String className) {
        if (!StringUtils.isBlank(className)) {
            Class clz = null;
            try {
                clz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (clz != null) {
                try {
                    return clz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public Object clone(Object o) {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);

            out.writeObject(o);
            out.close();

            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bin);

            Object ret = in.readObject();
            in.close();

            return ret;
        } catch (Exception e) {
        }
        return null;
    }
}