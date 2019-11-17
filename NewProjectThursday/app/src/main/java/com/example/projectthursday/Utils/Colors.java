package com.example.projectthursday.Utils;


import android.graphics.Color;

import java.util.HashMap;

public class Colors {

    private static final HashMap<String,String> colors = new HashMap<>();

    public static int getColorByName(String key){
        return Color.parseColor(colors.get(key));
    }

    public static void setColor(String key,String value){
        colors.put(key, value);
    }

    public static String getColorRgbByName(String key){
        return colors.get(key);
    }

}
