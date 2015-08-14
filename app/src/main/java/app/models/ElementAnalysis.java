package app.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.constants.FSConst;
import app.delegates.Util;

/**
 * Created by Steve on 8/13/2015.
 */
public class ElementAnalysis {

    private String elementName;
    private ArrayList<ElementResult> timeline;
    private static int LIMIT = 3;

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < timeline.size(); i++) {
            if (i >= LIMIT)
                break;
            ElementResult er = timeline.get(i);
            String dateStr = Util.dateToString(er.getDate());
            str.append(" " + dateStr + ":" + er.getValue());
        }
        return elementName + " " + str;
    }

    public String getElementName() {
        return elementName;
    }

    public ArrayList<ElementResult> getTimeline() {
        return timeline;
    }

    public ElementAnalysis(String elementName) {
        this.elementName = elementName;
        this.timeline = new ArrayList<ElementResult>();
    }

    public ElementAnalysis(String elementName, ElementResult elementResult) {
        this(elementName);
        this.timeline.add(elementResult);
    }

    public ElementAnalysis(String elementName, ArrayList<ElementResult> timeline) {
        this(elementName);
        this.timeline = timeline;
    }

    public void addOneTimeResult(ElementResult elementResult) {
        this.timeline.add(elementResult);
    }

    public void addAllOneTimeResult(ArrayList<ElementResult> arrayList) {
        this.timeline.addAll(arrayList);
    }

    public JSONObject toJson() {
        JSONArray timeline = new JSONArray();
        for (ElementResult er : this.timeline) {
            timeline.put(er.toJson());
        }
        JSONObject object = new JSONObject();
        try {
            object.put(FSConst.JSON_ELEMENT_ANALYSIS_NAME, this.elementName);
            object.put(FSConst.JSON_ELEMENT_ANALYSIS_TIMELINE, timeline);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;

    }
}

