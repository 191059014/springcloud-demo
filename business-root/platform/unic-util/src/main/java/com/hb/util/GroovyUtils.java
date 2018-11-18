package com.hb.util;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * this is description
 *
 * @author huangbiao
 * @date 2018/9/7
 */
public class GroovyUtils {

    public static Object evaluate(Map<String, Object> map, String groovyFilePath) {
        Binding binding = new Binding();
        if (map != null) {
            for (String k : map.keySet()) {
                Object v = map.get(k);
                binding.setProperty(k, v);
            }
        }
        GroovyShell groovyShell = new GroovyShell(binding);
        URI uri = null;
        Object value = null;
        try {
            uri = Thread.currentThread().getContextClassLoader().getResource(groovyFilePath).toURI();
            value = groovyShell.evaluate(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }


}
