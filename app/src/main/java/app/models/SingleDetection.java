package app.models;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import app.constants.FSConst;
import app.delegates.Util;

/**
 * Created by Steve on 8/11/2015.
 */
public class SingleDetection {

    private Date date;
    private String detectionType;
    private ArrayList<ElementDetail> details;
    //    private String jsonData;
    private int myHashCode;

    @Override
    public String toString() {
        return Util.dateToStringShortWithYear(date) + ": " + this.detectionType;
    }

    public Date getDate() {
        return date;
    }

    public String getDateShort() {
        return Util.dateToStringShort(this.date);
    }

    public String getDateShortWithYear() {
        return Util.dateToStringShortWithYear(this.date);
    }

    public String getDetectionType() {
        return detectionType;
    }

    public String getDetectionTypeChinese() {
        String chineseName = null;
        if (detectionType.equals(FSConst.TYPE_BREAST_MILK))
            chineseName = FSConst.TITLE_CHINESE_BREAST_MILK;
        else if (detectionType.equals(FSConst.TYPE_MILK_POWDER))
            chineseName = FSConst.TITLE_CHINESE_MILK_POWDER;
        else if (detectionType.equals(FSConst.TYPE_SUPPLEMENTARY_FOOD))
            chineseName = FSConst.TITLE_CHINESE_SUPPLEMENTARY_FOOD;
        return chineseName;
    }

    public static String getSharedPrefName(String detectionType) {
        String sharedPrefName = null;
        if (detectionType.equals(FSConst.TYPE_BREAST_MILK))
            sharedPrefName = FSConst.SHARED_PREF_BREAST_MILK;
        else if (detectionType.equals(FSConst.TYPE_MILK_POWDER))
            sharedPrefName = FSConst.SHARED_PREF_MILK_POWDER;
        else if (detectionType.equals(FSConst.TYPE_SUPPLEMENTARY_FOOD))
            sharedPrefName = FSConst.SHARED_PREF_SUPPLEMENTARY_FOOD;
        return sharedPrefName;
    }

    public ArrayList<ElementDetail> getDetails() {
        return details;
    }

//    public String getJsonData() {
//        return jsonData;
//    }

    public SingleDetection(String detectionType, ArrayList<ElementDetail> details) {
        this.detectionType = detectionType;
        this.details = details;

        this.date = new Date();
        this.myHashCode = this.hashCode();
//        this.jsonData = jsonData;
//        if (myHashCode == 0)
//            myHashCode = hashCode();
    }

    // not hashcode
//    public SingleDetection(Date date, String detectionType, ArrayList<ElementDetail> details) {
//        this(detectionType, details);
//        this.date = date;
//
//    }
    // parse from file, has hashcode, date
    public SingleDetection(String detectionType, ArrayList<ElementDetail> details, int myHashCode, Date date) {
        this(detectionType, details);
        this.date = date;
        this.myHashCode = myHashCode;
    }

    public TypeAnalysis toTypeAnalysis() {
        TypeAnalysis typeAnalysis = new TypeAnalysis(this.detectionType);
        for (ElementDetail ed : this.getDetails()) {
            ElementAnalysis elementAnalysis = new ElementAnalysis(ed.getName(), new ElementResult(this.getDate(), ed.getValue()));
            typeAnalysis.addElement(elementAnalysis);
        }
        return typeAnalysis;
    }
//    public ArrayList<String> detailsToString() {
//        ArrayList<String> arrayList = new ArrayList<String>();
//        for (ElementDetail ed : details) {
//            arrayList.add(ed.toString());
//        }
//        return arrayList;
//    }

    private static String getRandomType() {
        ArrayList<String> type = new ArrayList<String>();
        type.add(FSConst.TYPE_BREAST_MILK);
        type.add(FSConst.TYPE_MILK_POWDER);
        type.add(FSConst.TYPE_SUPPLEMENTARY_FOOD);
        Random rand = new Random();
        int randomNum = rand.nextInt(3);
        return type.get(randomNum);
    }

    public static SingleDetection getRandom(Activity activity) {
        String type = getRandomType();
        SingleDetection sd = new SingleDetection(type, ElementDetail.getRandomDetails(type, activity));
        return sd;
    }

    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        try {
            object.put(FSConst.JSON_SINGLE_DETECTION_TYPE, this.getDetectionType());
            object.put(FSConst.JSON_SINGLE_DETECTION_DATE, Util.dateToString(this.getDate()));
            object.put(FSConst.JSON_SINGLE_DETECTION_HASHCODE, this.myHashCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray detailsJson = new JSONArray();
        for (ElementDetail ed : details) {
            detailsJson.put(ed.toJson());
        }
        try {
            object.put(FSConst.JSON_SINGLE_DETECTION_DETAIL, detailsJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }


}
