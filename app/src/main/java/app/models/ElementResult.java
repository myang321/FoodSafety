package app.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import app.constants.FSConst;
import app.delegates.Util;

/**
 * Created by Steve on 8/13/2015.
 */
public class ElementResult {
    private Date date;
    private double value;

    public Date getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public ElementResult(Date date, double value) {
        this.date = date;
        this.value = value;
    }

    public JSONObject toJson() {
        String dateStr = Util.dateToString(this.date);
        JSONObject object = new JSONObject();
        try {
            object.put(FSConst.JSON_ELEMENT_RESULT_DATE, dateStr);
            object.put(FSConst.JSON_ELEMENT_RESULT_VALUE, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
