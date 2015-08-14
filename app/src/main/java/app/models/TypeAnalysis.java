package app.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.constants.FSConst;

/**
 * Created by Steve on 8/13/2015.
 */
public class TypeAnalysis {
    private String typeName;
    private ArrayList<ElementAnalysis> details;

    public TypeAnalysis(String typeName) {
        this.typeName = typeName;
        this.details = new ArrayList<ElementAnalysis>();
    }

    public TypeAnalysis(String typeName, ArrayList<ElementAnalysis> arrayList) {
        this.typeName = typeName;
        this.details = arrayList;
    }

    public void addElement(ElementAnalysis elementAnalysis) {
        if (!isElementExist(elementAnalysis)) {
            this.details.add(elementAnalysis);
            return;
        }
        mergeTimeline(elementAnalysis);

    }

    private void mergeTimeline(ElementAnalysis elementAnalysis) {
        for (ElementAnalysis ed : this.details) {
            if (ed.getElementName().equals(elementAnalysis.getElementName())) {
                ed.addAllOneTimeResult(elementAnalysis.getTimeline());
            }
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public ArrayList<ElementAnalysis> getDetails() {
        return details;
    }

    public boolean isElementExist(ElementAnalysis elementAnalysis) {
        boolean flag = false;
        for (ElementAnalysis ed : this.details) {
            if (ed.getElementName().equals(elementAnalysis.getElementName())) {
                flag = true;
                break;
            }
        }
        return flag;

    }

    public void mergeTypeAnalysis(TypeAnalysis typeAnalysis) {
        if (!typeAnalysis.getTypeName().equals(this.getTypeName())) {
            Log.d("meng", "error in mergeTypeAnalysis type mismatch: " + typeAnalysis.getTypeName() + " " + this.getTypeName());
            return;
        }
        for (ElementAnalysis ea_other : typeAnalysis.getDetails()) {
            boolean found = false;
            for (ElementAnalysis ea : this.details) {
                if (ea_other.getElementName().equals(ea.getElementName())) {
                    found = true;
                    ea.addAllOneTimeResult(ea_other.getTimeline());
                }
            }
            if (found == false) {
                this.addElement(ea_other);
            }
        }

    }

    public JSONObject toJson() {
        JSONArray detailsJson = new JSONArray();
        for (ElementAnalysis ea : this.details) {
            detailsJson.put(ea.toJson());
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(FSConst.JSON_TYPE_ANALYSIS_TYPE, this.typeName);
            jsonObject.put(FSConst.JSON_TYPE_ANALYSIS_DETAILS, detailsJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }


}
