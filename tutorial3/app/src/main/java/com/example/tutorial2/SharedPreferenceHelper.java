package com.example.tutorial2;

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
    public int getAge(){
        return 0;
    }



    public void saveName(String name){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.profileNameKeySharedPreferences),name);
        editor.apply();   // or commit() here
    }







    public Profile getProfile(){

        // from shared pre to get all the paramters name/age id


        // create a new pro obejct


        return new Profile(0,10,"bob");
    }




    public void saveProfile(Profile profile){

        profile.getAge();
        profile.getID();
        profile.getName();


    }




}