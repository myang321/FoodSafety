package app.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import app.constants.FSConst;
import app.delegates.Util;
import app.models.ElementDetail;
import app.models.SingleDetection;

/**
 * Created by Steve on 8/11/2015.
 */
public class JsonParser {
    public static void main(String[] args) {
        String s = "{ \"date\":\"2015-8-3\", \"type\":\"breast_milk\", \"detail\":[ { \"name\":\"ruqing_protein\", \"value\":1.5 }, { \"name\":\"lao_protein\", \"value\":1.5 }, { \"name\":\"rutie_protein\", \"value\":1.5 }, { \"name\":\"mianyiqiu_protein_slga\", \"value\":1.5 }, { \"name\":\"mianyiqiu_protein_igg1\", \"value\":1.5 }, { \"name\":\"folic_acid\", \"value\":1.5 } ] }";
    }

    public static ElementDetail parseElementDetail(JSONObject obj) {
        String name = null;
        double value = 0;
        String unit = "mg";
        try {
            name = obj.getString(FSConst.JSON_ELEMENT_DETAIL_NAME);
            value = obj.getDouble(FSConst.JSON_ELEMENT_DETAIL_VALUE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ElementDetail ed = new ElementDetail(name, value, unit);
        return ed;

    }

    public static SingleDetection parseSingDetectionFromFile(String s) {
        Date date = null;
        String detectionType = null;
        ArrayList<ElementDetail> details = new ArrayList<ElementDetail>();
        try {
            JSONObject obj = new JSONObject(s);
            date = Util.stringToDate(obj.getString(FSConst.JSON_SINGLE_DETECTION_DATE));
            detectionType = obj.getString(FSConst.JSON_SINGLE_DETECTION_TYPE);
            JSONArray detailsArray = obj.getJSONArray(FSConst.JSON_SINGLE_DETECTION_DETAIL);
            for (int i = 0; i < detailsArray.length(); i++) {
                ElementDetail tempElement = JsonParser.parseElementDetail(detailsArray.getJSONObject(i));
                details.add(tempElement);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SingleDetection sd = new SingleDetection(date, detectionType, details);
        return sd;
    }
}
