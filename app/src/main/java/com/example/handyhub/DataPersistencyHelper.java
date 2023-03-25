package com.example.handyhub;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataPersistencyHelper {

    public static Context context;

    public static void storeData(List<User> users) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        String json = new Gson().toJson(users);
        editor.putString("users", json);
        editor.commit();
    }

    public static List<User> loadData() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sp.getString("user", null);
        if (json != null) {
            Type type = new TypeToken<List<User>>() {
            }.getType();
            return new Gson().fromJson(json, type);
        } else {
            List<User> users = new ArrayList<>();
            users.add(new User(R.drawable.image, "Barbara Lamar", "barbara@gmail.com"));
            users.add(new User(R.drawable.john, "John Smith", "smith@gmail.com"));
            return users;
        }
        // if nothing stored return default
    }
}






