package com.example.projectthursday.Utils;

import android.util.Log;

import java.util.Locale;

public class Language {


    private static final String TAG = "Language";

    public static String get() {
        String lang = Locale.getDefault().getLanguage();
        Log.i(TAG, "getLanguage() = " + lang);

        switch (lang){
            case "en":
                lang = "eng";
        }

        return lang;
    }

}
