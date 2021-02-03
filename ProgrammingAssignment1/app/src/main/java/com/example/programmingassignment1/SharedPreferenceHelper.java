package com.example.programmingassignment1;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {


    private Context context;
    private SharedPreferences sharedPreferences;

    public SharedPreferenceHelper(Context context) {
        this.context=context; // closest one in the scope as for this-> pointer
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.profileSharedPreferencesFile), Context.MODE_PRIVATE);
    }


    public String getName(){
        return sharedPreferences.getString(context.getString(R.string.profileNameKeySharedPreferences), null);
    }
    public String getId(){
        return sharedPreferences.getString(context.getString(R.string.profileIDSharedPreferences), null);
    }
    public String getAge(){
        return sharedPreferences.getString(context.getString(R.string.profileAgeSharedPreferences), null);
    }

    public void saveName(String name,String id,String age){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.profileNameKeySharedPreferences),name);
        editor.putString(context.getString(R.string.profileIDSharedPreferences),age);
        editor.putString(context.getString(R.string.profileAgeSharedPreferences),id);
        editor.apply();   // or commit() here
    }


}