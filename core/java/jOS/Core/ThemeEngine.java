package jOS.Core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import java.util.Objects;

public class ThemeEngine {
    public static final String KEY_THEME = "pref_theme";
    public static String currentTheme;

    public static int getSystemTheme(Context context){
        SharedPreferences prefs;
        String Theme;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Theme = prefs.getString(KEY_THEME, "jOS_System");
        currentTheme = getSystemThemeValue(context);
        switch (Theme) {
            case "Holo":
                Log.i("jOS Theme Engine", "jOS.Core.R.style.jOS_Theme");
                return R.style.jOS_Theme;
            case "M3 Dark":
                Log.i("jOS Theme Engine", "com.google.android.material.R.style.Theme_Material3_Dark_NoActionBar");
                return com.google.android.material.R.style.Theme_Material3_Dark_NoActionBar;
            case "M3 Light":
                Log.i("jOS Theme Engine", "com.google.android.material.R.style.Theme_Material3_Light_NoActionBar");
                return com.google.android.material.R.style.Theme_Material3_Light_NoActionBar;
        }
        prefs.edit().putString(KEY_THEME, "Holo").apply();
        throw new IllegalArgumentException("jOS Theme Engine : " + "Unrecognised Theme '" + currentTheme + "'");
    }

    public static String getSystemThemeValue(Context context){
        SharedPreferences prefs;
        String Theme;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Theme = prefs.getString(KEY_THEME, "Holo");
        return Theme;
    }

    public static void relaunch(Activity context) {
        if (!Objects.equals(currentTheme, getSystemThemeValue(context))) {
            Intent intent = context.getIntent();
            context.finish();
            context.startActivity(intent);
        }
    }
}
