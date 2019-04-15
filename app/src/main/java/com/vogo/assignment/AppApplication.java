package com.vogo.assignment;

import android.app.Application;
import android.content.Context;

/**
 * Created by Nitish Singh on 16/04/19.
 */
public class AppApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }


    public static Context getAppContext() {
        return mContext;
    }
}
