package in.faizal.travansoft.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Preference {
    private static Context context;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor pref_editor;

    private static void init(Context xContext) {
        context = xContext;
        preferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        pref_editor = preferences.edit();

    }

    public static void setUsername(String username, Context context) {
        init(context);
        pref_editor.putString(Constants.USERNAME, username);
        pref_editor.apply();
        Log.e("Preference", "setUsername: " + username);
    }

    public static String getUsername(Context context) {
        init(context);
        return preferences.getString(Constants.USERNAME, "");
    }

    public static void setEmpName(String chkID, Context context) {
        init(context);
        pref_editor.putString(Constants.PASSWORD, chkID);
        pref_editor.apply();
        Log.e("Preference", "setUsername: " + chkID);
    }

    public static String getEmpName(Context context) {
        init(context);
        return preferences.getString(Constants.PASSWORD, "");
    }

    public static void setUser(String name, String mobile,String password, Context context,Boolean registered) {
        init(context);
        pref_editor.putString(Constants.NAME, name);
        pref_editor.putString(Constants.MOBILE, mobile);
        pref_editor.putString(Constants.PASSWORD, password);
        pref_editor.putBoolean(Constants.REGISTERED, registered);
        pref_editor.apply();
        Log.e("Preference", name + ":" + mobile+":"+password);
    }

    public static String getUserName(Context context) {
        init(context);
        return preferences.getString(Constants.NAME, "");
    }
    public static Boolean isUserRegistered(Context context) {
        init(context);
        return preferences.getBoolean(Constants.REGISTERED, false);
    }

    public static String getUSerMobile(Context context) {
        init(context);
        return preferences.getString(Constants.MOBILE, "");
    }
    public static String getUserPassword(Context context) {
        init(context);
        return preferences.getString(Constants.PASSWORD, "");
    }


    public static void clearLogin(Context context) {
        init(context);
        pref_editor.remove(Constants.USERNAME);
        pref_editor.remove(Constants.PASSWORD);
        pref_editor.remove(Constants.USERID);
        pref_editor.apply();
        Log.e("Preference", "clearUsername");
    }

    public static void setUserId(String userId, Context context) {
        init(context);
        pref_editor.putString(Constants.USERID, userId);
        pref_editor.apply();
        Log.e("Preference", "setUserId: " + userId);
    }

    public static String getUserId(Context context) {
        init(context);
        return preferences.getString(Constants.USERID, "");
    }
}
