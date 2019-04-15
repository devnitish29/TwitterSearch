package com.vogo.assignment.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.vogo.assignment.AppApplication;

/**
 * Created by Nitish Singh on 15/04/19.
 */
public class Utils {
    public static final String MyPREFERENCES = AppApplication.class.getName() ;

    public static void saveAuthToken(String authToken){
        SharedPreferences sharedpreferences = AppApplication.getAppContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("token", authToken);
        editor.apply();
    }



    public static String getAuthToken(){
        SharedPreferences sharedpreferences =  AppApplication.getAppContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedpreferences.getString("token",null);

    }



}
