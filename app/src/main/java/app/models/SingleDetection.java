package app.models;

import java.util.ArrayList;
import java.util.Date;

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
        return Util.dateToStringShort(date) + ": " + this.detectionType;
    }

    public Date getDate() {
        return date;
    }

    public String getDetectionType() {
        return detectionType;
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
