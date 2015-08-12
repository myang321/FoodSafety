package app.delegates;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import app.constants.FSConst;

/**
 * Created by Steve on 8/11/2015.
 */
public class SharedPreferenceDelegate {

    private SharedPreferences sharedPref = null;

    public SharedPreferenceDelegate(Activity activity) {
        this.sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public void updateSharedPrefs(String key, String value) {
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getSharedPrefsString(String key) {
        return this.getSharedPrefsString(key, null);
    }

    public String getSharedPrefsString(String key, String defaultValue) {
        String value = this.sharedPref.getString(key, defaultValue);
        return value;
    }

    public String getSharedPrefsStringHistory() {
        String defaultValue = "";
        String value = this.getSharedPrefsString(FSConst.SHARED_PREF_HISTORY, defaultValue);
        return value;
    }
//    public static SharedPreferenceDelegate getInstance() {
//        if (sharedPreferenceDelegate == null) {
//            sharedPreferenceDelegate = new SharedPreferenceDelegate();
//        }
//        return sharedPreferenceDelegate;
//    }
}
