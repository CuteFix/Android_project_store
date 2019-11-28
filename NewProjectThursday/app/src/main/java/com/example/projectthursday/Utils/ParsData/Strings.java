package com.example.projectthursday.Utils.ParsData;

import java.util.HashMap;

public class Strings {
    private static final HashMap<String, String> strings = new HashMap<>();

    public static void setByKey(String key, String value) {
        strings.put(key, value);
    }

    public static String getByKey(String key) {
        return strings.get(key);
    }
}
