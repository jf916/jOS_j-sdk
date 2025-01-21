package io.github.dot166.jLib;

import android.os.Bundle;

import io.github.dot166.jLib.app.jWebActivity;

public class LIBChangelogActivity extends jWebActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setUri("https://github.com/dot166/jOS_j-lib/commits/v" + BuildConfig.LIBVersion);
        super.onCreate(savedInstanceState);
    }
}
