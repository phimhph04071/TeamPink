package com.example.phimau.teampink;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

import Entity.Employee;

/**
 * Created by phimau on 11/20/2016.
 */

public class PreferenceAccount {
    private SharedPreferences preferences;
    private static String TAG_ID = "id";
    private static String TAG_PASSWORD = "password";
    private static String TAG_NAME = "name";

    public PreferenceAccount(Context context) {
        preferences = context.getSharedPreferences("logindata",0);

    }
    public Employee getEmployee(){
        String id=preferences.getString(TAG_ID,"");
        String password =preferences.getString(TAG_PASSWORD,"");
        String name =preferences.getString(TAG_NAME,"");
        Employee employee = new Employee(id,name,password);
        return employee;
    }
    public void setLogin(Employee accountLogined){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putString(TAG_ID,accountLogined.getId());
        editor.putString(TAG_NAME,accountLogined.getName());
        editor.putString(TAG_PASSWORD,accountLogined.getPassword());
        editor.commit();
    }
}
