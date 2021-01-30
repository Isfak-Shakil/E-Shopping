package com.example.onlineshop;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

    //session name
    public static final String SESSION_USERSESSION = "userLoginSession";
    public static final String SESSION_REMEMBERME = "rememberMeSession";

    // user session variable
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEW_PHONE = "phone";
    public static final String KEW_NAME = "name";
    public static final String KEW_ADDRESS = "address";
    public static final String KEW_PASSWORD = "password";

    // remember me session variable
    private static final String IS_REMEMBER = "IsRemember";

    public static final String KEW_SESSIONPHONENUMBER = "phone";
    public static final String KEW_SESSIONPASSWORD = "password";

    //constructor
    public SessionManager(Context context, String sessionName) {
        this.context = context;
        userSession = context.getSharedPreferences(sessionName, Context.MODE_PRIVATE);
        editor = userSession.edit();
    }
    // login session

    public void createUserLoginSession(String phone, String name, String address, String password) {
        editor.putBoolean(IS_LOGIN, true);


        editor.putString(KEW_PHONE, phone);
        editor.putString(KEW_NAME, name);
        editor.putString(KEW_ADDRESS, address);
        editor.putString(KEW_PASSWORD, password);

        editor.commit();
    }

    public HashMap<String, String> getUsersDetailsFromSession() {
        HashMap<String, String> userData = new HashMap<String, String>();
        userData.put(KEW_PHONE, userSession.getString(KEW_PHONE, null));
        userData.put(KEW_NAME, userSession.getString(KEW_NAME, null));
        userData.put(KEW_ADDRESS, userSession.getString(KEW_ADDRESS, null));
        userData.put(KEW_PASSWORD, userSession.getString(KEW_PASSWORD, null));
        return userData;
    }

    public boolean checkLogin() {
        if (userSession.getBoolean(IS_LOGIN, true)) {
            return true;
        } else
            return false;
    }

    public void logOutUserFromSession() {
        editor.clear();
        editor.clear();
    }

    // remember me session
    public void createRememberMeSession(String phone, String password) {

        editor.putBoolean(IS_REMEMBER, true);


        editor.putString(KEW_SESSIONPHONENUMBER, phone);
        editor.putString(KEW_SESSIONPASSWORD, password);

        editor.commit();
    }

    public HashMap<String, String> getRememberMeDetailsFromSession() {
        HashMap<String, String> userData = new HashMap<String, String>();
        userData.put(KEW_SESSIONPHONENUMBER, userSession.getString(KEW_SESSIONPHONENUMBER, null));
        userData.put(KEW_SESSIONPASSWORD, userSession.getString(KEW_SESSIONPASSWORD, null));
        return userData;
    }
    public boolean checkRememberMe() {
        if (userSession.getBoolean(IS_REMEMBER, true)) {
            return true;
        } else
            return false;
    }
}
