package io.github.dot166.jLib.app;

import static io.github.dot166.jLib.ThemeEngine.ThemeEngine.currentTheme;
import static io.github.dot166.jLib.ThemeEngine.ThemeEngine.getThemeFromDB;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

import io.github.dot166.jLib.ThemeEngine.ThemeEngine;
import io.github.dot166.jLib.utils.VersionUtils;

public class jActivity extends AppCompatActivity {
    protected jLIBCoreApp mLIBApp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(ThemeEngine.getSystemTheme(this));
        super.onCreate(savedInstanceState);
        mLIBApp = (jLIBCoreApp) this.getApplicationContext();
        mLIBApp.setCurrentActivity(this);

        if (VersionUtils.isAtLeastV()) {
            // Fix A15 EdgeToEdge
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, windowInsets) -> {
                Insets insets = windowInsets.getInsets(
                        WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime()
                                | WindowInsetsCompat.Type.displayCutout());
                int statusBarHeight = getWindow().getDecorView().getRootWindowInsets()
                        .getInsets(WindowInsetsCompat.Type.statusBars()).top;
                // Apply the insets paddings to the view.
                v.setPadding(insets.left, statusBarHeight, insets.right, insets.bottom);
                // Return CONSUMED if you don't want the window insets to keep being
                // passed down to descendant views.
                return WindowInsetsCompat.CONSUMED;
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLIBApp.setCurrentActivity(this);
        if (!Objects.equals(currentTheme, getThemeFromDB(this))) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        clearReferences();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    private void clearReferences(){
        Activity currActivity = mLIBApp.getCurrentActivity();
        if (this.equals(currActivity))
            mLIBApp.setCurrentActivity(null);
    }
}

