package app.models;

import java.util.ArrayList;
import java.util.Date;

import app.constants.FSConst;
import app.delegates.Util;

/**
 * Created by Steve on 8/11/2015.
 */
public class SingleDetection {

    private Date date;
    private String detectionType;
    private ArrayList<ElementDetail> details;
    private String jsonData;
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

    public String getJsonData() {
        return jsonData;
    }

    public SingleDetection(String detectionType, ArrayList<ElementDetail> details, String jsonData, int myHashCode) {
        this.date = new Date();
        this.detectionType = detectionType;
        this.details = details;
        this.jsonData = jsonData;
//        if (myHashCode == 0)
//            myHashCode = hashCode();
        this.myHashCode = myHashCode;
    }

    public SingleDetection(Date date, String detectionType, ArrayList<ElementDetail> details, String jsonData, int myHashCode) {
        this(detectionType, details, jsonData, myHashCode);
        this.date = date;
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


}
