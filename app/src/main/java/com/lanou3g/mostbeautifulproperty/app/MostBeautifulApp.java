package com.lanou3g.mostbeautifulproperty.app;

import android.app.Application;
import android.content.Context;

/**
 *
 */

public class MostBeautifulApp extends Application{
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }
}
