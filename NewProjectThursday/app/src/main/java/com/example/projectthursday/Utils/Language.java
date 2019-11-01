package com.example.projectthursday.Utils;

import java.util.Locale;

public class Language {

    public static String get(){
       return Locale.getDefault().getLanguage();
    }

}
