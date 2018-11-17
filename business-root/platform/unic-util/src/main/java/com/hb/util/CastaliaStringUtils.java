package com.hb.util;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastaliaStringUtils
{
    protected static Logger logger = LoggerFactory.getLogger(CastaliaStringUtils.class);

    public static String before(String src, String dest)
    {
        int pos = src.indexOf(dest);
        if (pos == -1) {
            return src;
        }
        String first = src.substring(0, pos);

        return first;
    }

    public static String after(String src, String dest)
    {
        int pos = src.indexOf(dest);
        if (pos == -1) {
            return src;
        }
        String last = src.substring(pos + 1);

        return last;
    }

    public static String lastBefore(String src, String dest)
    {
        int pos = src.lastIndexOf(dest);
        if (pos == -1) {
            return src;
        }
        String first = src.substring(0, pos);

        return first;
    }

    public static String lastAfter(String src, String dest)
    {
        int pos = src.lastIndexOf(dest);
        if (pos == -1) {
            return src;
        }
        String last = src.substring(pos + 1);

        return last;
    }

    public static String upperFirst(String value)
    {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
            String first = value.substring(0, 1);
            String last = value.substring(1);
            return first.toUpperCase() + last;
        }
        return value;
    }

    public static String lowerFirst(String value)
    {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
            String first = value.substring(0, 1);
            String last = value.substring(1);
            return first.toLowerCase() + last;
        }
        return value;
    }

    public static Class toClass(String type)
    {
        Class clz = String.class;
        if ((StringUtils.isEmpty(type)) || ("String".equals(type)) || ("java.lang.String".equals(type)))
            clz = String.class;
        else if (("Integer".equals(type)) || ("java.lang.Integer".equals(type)))
            clz = Integer.class;
        else if (("Long".equals(type)) || ("java.lang.Long".equals(type)))
            clz = Long.class;
        else if (("BigDecimal".equals(type)) || ("Decimal".equals(type)) || ("java.math.BigDecimal".equals(type)))
            clz = BigDecimal.class;
        else if (("Short".equals(type)) || ("java.lang.Short".equals(type)))
            clz = Short.class;
        else if (("Boolean".equals(type)) || ("java.lang.Boolean".equals(type)))
            clz = Boolean.class;
        else if (("Date".equals(type)) || ("java.util.Date".equals(type)))
            clz = Date.class;
        else if ("java.util.Date".equals(type))
            clz = Date.class;
        else if (("Time".equals(type)) || ("java.sql.Time".equals(type)))
            clz = Time.class;
        else if (("Char".equals(type)) || ("java.lang.Character".equals(type)))
            clz = Character.class;
        else if (("Double".equals(type)) || ("java.lang.Double".equals(type)))
            clz = Double.class;
        else if (("Timestamp".equals(type)) || ("java.sql.Timestamp".equals(type)))
            clz = Timestamp.class;
        else if (("Calendar".equals(type)) || ("java.util.Calendar".equals(type)))
            clz = Calendar.class;
        else if (("DateTime".equals(type)) || ("org.joda.time.DateTime".equals(type)))
            clz = DateTime.class;

        return clz;
    }

    public static int indexOfCount(String src, String search, int count)
    {
        int pos = 0;
        for (int i = 1; i < count; i++) {
            if (pos == 0) {
                pos = src.indexOf(search);
            }
            pos = src.indexOf(search, pos + 1);
        }
        return pos + 1;
    }

    public static String leftOfCount(String src, String search, int count)
    {
        int pos = indexOfCount(src, search, count);

        return StringUtils.left(src, pos);
    }

    public static String rightOfCount(String src, String search, int count)
    {
        int pos = indexOfCount(src, search, count);

        return StringUtils.right(src, pos - search.length());
    }

    public static String toString(byte[] filedByte, String charset)
    {
        String filed;
        try
        {
            filed = new String(filedByte, charset);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }

        return filed;
    }

    public static String subStringByByteLen(String message, String encoding, Integer start, Integer end)
    {
        if (StringUtils.isNotBlank(message)) {
            try {
                byte[] b = message.getBytes(encoding);
                byte[] target = new byte[b.length];
                int i = start.intValue(); for (int k = 0; (i < end.intValue()) &&
                        (i < b.length); k++)
                {
                    target[k] = b[i];

                    i++;
                }

                return new String(target, encoding);
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }

    public static String digitUppercase(double n)
    {
        String[] fraction = { "角", "分" };
        String[] digit = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String[][] unit = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };

        String head = n < 0.0D ? "负" : "";
        n = Math.abs(n);

        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s = s + new StringBuilder().append(digit[((int)(Math.floor(n * 10.0D * Math.pow(10.0D, i)) % 10.0D))]).append(fraction[i]).toString().replaceAll("(零.)+", "");
        }
        if (s.length() < 1) {
            s = "整";
        }
        int integerPart = (int)Math.floor(n);

        for (int i = 0; (i < unit[0].length) && (integerPart > 0); i++) {
            String p = "";
            for (int j = 0; (j < unit[1].length) && (n > 0.0D); j++) {
                p = digit[(integerPart % 10)] + unit[1][j] + p;
                integerPart /= 10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }

    public static String[] split(String data, int length)
    {
        int total = data.length();
        int seg = total / length;
        int mod = total % length;
        if (mod != 0) {
            seg++;
        }
        String[] splitData = new String[seg];
        int i = 0;
        for (int pos = 0; pos < total; pos += length) {
            int position = pos + length;
            if (position > total) {
                position = total;
            }
            splitData[i] = data.substring(pos, position);
            i++;
        }

        return splitData;
    }

    public static String merge(Object bean, String prefixName, Integer count) {
        String remark = "";
        for (int i = 0; i < count.intValue(); i++) {
            String r = "";
            try {
                r = (String)PropertyUtils.getSimpleProperty(bean, prefixName + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (r == null) break;
            remark = remark + r;
        }

        return remark;
    }

    public static void split(Object bean, String data, String prefixName, Integer count, Integer gap) {
        int length = 0;
        if (data != null) {
            length = data.length();
        }
        int c = length / gap.intValue();
        for (int i = 0; i < c; i++) {
            int beginIndex = i * gap.intValue();
            String r = data.substring(beginIndex, beginIndex + gap.intValue());
            try {
                PropertyUtils.setSimpleProperty(bean, prefixName + i, r);
                beginIndex += gap.intValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = c; i < count.intValue(); i++) {
            int beginIndex = i * gap.intValue();
            String r = null;
            try {
                if ((beginIndex < length) && (beginIndex + gap.intValue() > length)) {
                    r = data.substring(beginIndex, length);
                }
                PropertyUtils.setSimpleProperty(bean, prefixName + i, r);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String replaceSerial(String sql, String searchment, String replacement)
    {
        String[] ss = StringUtils.split(sql, searchment);
        if ((ss != null) && (ss.length > 0)) {
            String ql = "";
            for (int i = 0; i < ss.length; i++) {
                ql = ql + ss[i];
                if (i < ss.length - 1) {
                    ql = ql + replacement + i;
                }
                else if (sql.endsWith(searchment)) {
                    ql = ql + replacement + i;
                }

            }

            return ql;
        }

        return sql;
    }

    public static void main(String[] args)
    {
        String sql = StringUtils.replaceAll("${name}!,I'm ${name}!", "\\$\\{(.*?)\\}", "hujs");
        System.out.println(sql);
    }
}