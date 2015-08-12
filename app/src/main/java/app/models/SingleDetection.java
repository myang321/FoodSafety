package app.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Steve on 8/11/2015.
 */
public class SingleDetection {

    private Date date;
    private String detectionType;
    private ArrayList<ElementDetail> details;

    public Date getDate() {
        return date;
    }

    public String getDetectionType() {
        return detectionType;
    }

    public ArrayList<ElementDetail> getDetails() {
        return details;
    }

    public SingleDetection(String detectionType, ArrayList<ElementDetail> details) {
        this.date = new Date();
        this.detectionType = detectionType;
        this.details = details;
    }

    public SingleDetection(Date date, String detectionType, ArrayList<ElementDetail> details) {
        this(detectionType, details);
        this.date = date;
    }

    public ArrayList<String> detailsToString() {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (ElementDetail ed : details) {
            arrayList.add(ed.toString());
        }
        return arrayList;
    }

}
