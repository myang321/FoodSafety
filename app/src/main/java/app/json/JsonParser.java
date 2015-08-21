package app.json;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import app.constants.FSConst;
import app.delegates.Util;
import app.models.ElementAnalysis;
import app.models.ElementDetail;
import app.models.ElementResult;
import app.models.ElementStandard;
import app.models.SingleDetection;
import app.models.TypeAnalysis;
import app.models.TypeStandard;

/**
 * Created by Steve on 8/11/2015.
 */
public class JsonParser {
    public static void main(String[] args) {
        String s = "{ \"date\":\"2015-8-3\", \"type\":\"breast_milk\", \"detail\":[ { \"name\":\"ruqing_protein\", \"value\":1.5 }, { \"name\":\"lao_protein\", \"value\":1.5 }, { \"name\":\"rutie_protein\", \"value\":1.5 }, { \"name\":\"mianyiqiu_protein_slga\", \"value\":1.5 }, { \"name\":\"mianyiqiu_protein_igg1\", \"value\":1.5 }, { \"name\":\"folic_acid\", \"value\":1.5 } ] }";
    }

    public static ElementDetail parseElementDetail(JSONObject obj, TypeStandard typeStandard) {
        String name = null;
        double value = 0;
        String unit = "mg";
        try {
            name = obj.getString(FSConst.JSON_ELEMENT_DETAIL_NAME);
            value = obj.getDouble(FSConst.JSON_ELEMENT_DETAIL_VALUE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        double goalValue = typeStandard.getGoalValue(name);
        String goalType = typeStandard.getGoalType(name);
        ElementDetail ed = new ElementDetail(name, value, unit, goalValue, goalType);
        return ed;

    }

    public static SingleDetection parseSingleDetection(String s, Activity activity) {
        Date date = null;
        String detectionType = null;
        ArrayList<ElementDetail> details = new ArrayList<ElementDetail>();
        JSONObject obj = null;
        try {
            obj = new JSONObject(s);
            date = Util.stringToDate(obj.getString(FSConst.JSON_SINGLE_DETECTION_DATE));
            detectionType = obj.getString(FSConst.JSON_SINGLE_DETECTION_TYPE);
            JSONArray detailsArray = obj.getJSONArray(FSConst.JSON_SINGLE_DETECTION_DETAIL);
            TypeStandard typeStandard = TypeStandard.getInstance(detectionType, activity);
            for (int i = 0; i < detailsArray.length(); i++) {
                ElementDetail tempElement = JsonParser.parseElementDetail(detailsArray.getJSONObject(i), typeStandard);
                details.add(tempElement);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int hashcode = 0;
        try {
            hashcode = obj.getInt(FSConst.JSON_SINGLE_DETECTION_HASHCODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (hashcode == 0) {
            hashcode = obj.hashCode();
            try {
                obj.put(FSConst.JSON_SINGLE_DETECTION_HASHCODE, hashcode);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        SingleDetection sd = new SingleDetection(detectionType, details, hashcode, date);
//        Log.d("meng", "in parseSingleDetection: new SingleDetection");
//        Log.d("meng", "SingleDetection hashcode:" + sd.hashCode());
        return sd;
    }

    public static TypeAnalysis parseTypeAnalysis(String s) {
        JSONObject obj = null;
        String type = null;
        ArrayList<ElementAnalysis> details = null;
        try {
            obj = new JSONObject(s);
            type = obj.getString(FSConst.JSON_TYPE_ANALYSIS_TYPE);
            JSONArray detailsJson = obj.getJSONArray(FSConst.JSON_TYPE_ANALYSIS_DETAILS);
            details = parseElementAnalysisArray(detailsJson);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        TypeAnalysis typeAnalysis = new TypeAnalysis(type, details);
        return typeAnalysis;
    }

    public static ElementAnalysis parseElementAnalysis(JSONObject obj) {
        String elementName = null;
        ArrayList<ElementResult> arrayList = new ArrayList<ElementResult>();
        try {
            elementName = obj.getString(FSConst.JSON_ELEMENT_ANALYSIS_NAME);
            JSONArray jsonArray = obj.getJSONArray(FSConst.JSON_ELEMENT_ANALYSIS_TIMELINE);
            for (int i = 0; i < jsonArray.length(); i++) {
                ElementResult elementResult = parseElementResult(jsonArray.getJSONObject(i));
                arrayList.add(elementResult);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ElementAnalysis elementAnalysis = new ElementAnalysis(elementName, arrayList);

        return elementAnalysis;
    }

    public static ElementResult parseElementResult(JSONObject obj) {
        Date date = null;
        double value = 0;
        try {
            String dateStr = obj.getString(FSConst.JSON_ELEMENT_RESULT_DATE);
            date = Util.stringToDate(dateStr);
            value = obj.getDouble(FSConst.JSON_ELEMENT_RESULT_VALUE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ElementResult elementResult = new ElementResult(date, value);
        return elementResult;
    }

    public static ArrayList<ElementAnalysis> parseElementAnalysisArray(JSONArray jsonArray) {
        ArrayList<ElementAnalysis> result = new ArrayList<ElementAnalysis>();
        for (int i = 0; i < jsonArray.length(); i++) {
            ElementAnalysis ea = null;
            try {
                ea = parseElementAnalysis(jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            result.add(ea);
        }
        return result;
    }

    public static ElementStandard parseElementStandard(JSONObject object) {
        String name = null;
        double goalValue = 0;
        String goalType = null;
        try {
            name = object.getString(FSConst.JSON_ELEMENT_STANDARD_NAME);
            goalValue = object.getDouble(FSConst.JSON_ELEMENT_STANDARD_VALUE);
            goalType = object.getString(FSConst.JSON_ELEMENT_STANDARD_GOAL_TYPE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ElementStandard elementStandard = new ElementStandard(name, goalValue, goalType);
        return elementStandard;
    }

    public static TypeStandard parseTypeStandard(String s) {
        JSONObject object = null;
        try {
            object = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String type = null;
        try {
            type = object.getString(FSConst.JSON_TYPE_STANDARD_TYPE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<ElementStandard> arrayList = new ArrayList<ElementStandard>();
        try {
            object = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = null;
        try {
            jsonArray = object.getJSONArray(FSConst.JSON_TYPE_STANDARD_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            ElementStandard es = null;
            try {
                es = JsonParser.parseElementStandard(jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            arrayList.add(es);
        }
        TypeStandard ts = new TypeStandard(type, arrayList);
        return ts;
    }
}
