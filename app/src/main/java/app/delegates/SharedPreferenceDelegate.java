package app.delegates;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import app.constants.FSConst;
import app.json.JsonParser;
import app.models.SingleDetection;
import app.models.TypeAnalysis;

/**
 * Created by Steve on 8/11/2015.
 */
public class SharedPreferenceDelegate {

    private static SharedPreferences sharedPref = null;

    // constructor
    public SharedPreferenceDelegate(Activity activity) {
        this.sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
    }

    // set string
    public void setSharedPrefsString(String key, String value) {
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    // get string
    public String getSharedPrefsString(String key, String defaultValue) {
        String value = this.sharedPref.getString(key, defaultValue);
        return value;
    }

    // add a string to string set
    // DO NOT modify the return value of shared pref, create a new object instead
    public void addSharedPrefsStringSet(String key, String value) {
        // DO NOT change result
        Set<String> result = getSharedPrefsStringSet(key, new HashSet<String>());
        HashSet<String> hs = new HashSet<String>();
        for (String s : result) {
            hs.add(new String(s));
        }
        Log.d("meng", "in addSharedPrefsStringSet result size:" + hs.size());
        hs.add(value);
        Log.d("meng", "in addSharedPrefsStringSet result size after add:" + hs.size());
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.putStringSet(key, hs);
        editor.commit();
    }

    // get string set
    public Set<String> getSharedPrefsStringSet(String key, Set<String> defaultValue) {
        Set<String> value = this.sharedPref.getStringSet(key, defaultValue);
        return value;
    }

    public ArrayList<SingleDetection> getDetectionList() {
        Log.d("meng", "call getDetectionList");
        Set<String> value = this.getSharedPrefsStringSet(FSConst.SHARED_PREF_HISTORY, null);
        ArrayList<SingleDetection> result = new ArrayList<SingleDetection>();
        if (value == null)
            return result;
        for (String s : value) {
            SingleDetection sd = JsonParser.parseSingleDetection(s);
            result.add(sd);
        }
        Log.d("meng", "getDetectionList size:" + result.size());
        return result;
    }

    public void addToDetectionList(String json) {
        Log.d("meng", "call addToDetectionList ");
        this.addSharedPrefsStringSet(FSConst.SHARED_PREF_HISTORY, json);
    }

    public void clearAllData() {
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.clear();
        editor.commit();
    }

    public TypeAnalysis getTypeAnalysis(String typeName) {
        String defaultValue = "{ \"type\":\"%s\", \"details\":[ ] }";
        defaultValue = String.format(defaultValue, typeName);
        String sharedPrefName = getSharedPrefName(typeName);
        String json = getSharedPrefsString(sharedPrefName, defaultValue);
        TypeAnalysis typeAnalysis = JsonParser.parseTypeAnalysis(json);
        return typeAnalysis;
    }

    public String getSharedPrefName(String typeName) {
        String sharedPrefName = null;
        if (typeName.equals(FSConst.TYPE_BREAST_MILK))
            sharedPrefName = FSConst.SHARED_PREF_BREAST_MILK;
        else if (typeName.equals(FSConst.TYPE_MILK_POWDER))
            sharedPrefName = FSConst.SHARED_PREF_MILK_POWDER;
        else if (typeName.equals(FSConst.TYPE_SUPPLEMENTARY_FOOD))
            sharedPrefName = FSConst.SHARED_PREF_SUPPLEMENTARY_FOOD;
        return sharedPrefName;
    }

    public void addToTypeAnalysis(TypeAnalysis typeAnalysis) {
        TypeAnalysis old = getTypeAnalysis(typeAnalysis.getTypeName());
        old.mergeTypeAnalysis(typeAnalysis);
        setSharedPrefsString(getSharedPrefName(typeAnalysis.getTypeName()), old.toJson().toString());

    }
//    public static SharedPreferenceDelegate getInstance() {
//        if (sharedPreferenceDelegate == null) {
//            sharedPreferenceDelegate = new SharedPreferenceDelegate();
//        }
//        return sharedPreferenceDelegate;
//    }
}
