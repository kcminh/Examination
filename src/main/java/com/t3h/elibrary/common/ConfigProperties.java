package com.t3h.elibrary.common;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConfigProperties {
    private static final Map<String, String> data = new HashMap<>();

    static {
        Properties prop = new Properties();
        try {
            prop.load(new InputStreamReader(Objects.requireNonNull(ConfigProperties.class.getClassLoader()
                    .getResourceAsStream("config.properties")), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Enumeration<String> en = (Enumeration<String>) prop.propertyNames();
        while (en.hasMoreElements()) {
            String key = en.nextElement();
            data.put(key, prop.getProperty(key));
        }


    }

    /**
     * Lấy data từ key
     *
     * @param key key
     * @return data
     */
    public static String getData(String key) {
        String string = "";
        if (data.containsKey(key)) {
            string = data.get(key);
        }
        return string;
    }

}
