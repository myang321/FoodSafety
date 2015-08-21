package app.models;

import android.app.Activity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import app.constants.FSConst;
import app.delegates.Util;

/**
 * Created by Steve on 8/11/2015.
 */
public class ElementDetail {
    private String name;
    private double value;
    private String unit;
    // transient
    private double goalValue;
    private String goalType;

    private static double getRandomValue(double rangeMin, double rangeMax) {
        Random r = new Random();
        double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        return randomValue;
    }

    public static ArrayList<ElementDetail> getRandomDetails(String detectionType, Activity activity) {
        ArrayList<String> array = new ArrayList<String>();
        ArrayList<ElementDetail> details = new ArrayList<ElementDetail>();
        TypeStandard typeStandard = TypeStandard.getInstance(detectionType, activity);
        if (detectionType.equals(FSConst.TYPE_BREAST_MILK)) {
            String[] strs = {"乳清蛋白", "酪蛋白", "乳铁蛋白", "免疫球蛋白SIgA", "免疫球蛋白IgG1", "叶酸"};
            array.addAll(Arrays.asList(strs));
        } else if (detectionType.equals(FSConst.TYPE_MILK_POWDER)) {
            String[] strs = {"酪蛋白", "乳铁蛋白", "三聚氰胺", "黄曲霉毒素M1", "β-内酰胺类抗生素", "磺胺类抗生素"};
            array.addAll(Arrays.asList(strs));
        } else {
            String[] strs = {"抗生素", "农药残留", "黄曲霉毒素M1", "蛋白质", "叶酸"};
            array.addAll(Arrays.asList(strs));
        }
        for (String elementType : array) {
            double goalValue = typeStandard.getGoalValue(elementType);
            String goalType = typeStandard.getGoalType(elementType);
            ElementDetail ed = new ElementDetail(elementType, getRandomValue(1, 5), "mg", goalValue, goalType);
            details.add(ed);
        }
        return details;
    }

    @Override
    public String toString() {
        String valueStr = String.format("%.1f", value);
        return name + ": " + valueStr + unit;
    }

    public ElementDetail(String name, double value, String unit, double goalValue, String goalType) {
        this.name = name;
        this.value = value;
        this.unit = unit;

        this.goalValue = goalValue;
        this.goalType = goalType;
        if (this.goalType == null)
            Log.d("meng", "in ElementDetail constructor, goalType is null,name=" + name);
    }

    public double getGoalValue() {
        return goalValue;
    }

    public String getGoalType() {
        return goalType;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public String getValueStr() {
        String valueStr = String.format("%.1f", value);
        return valueStr;
    }

    public String getValueWithUnit() {
        String str = getValueStr() + unit;
        return str;
    }

    public String getValueWithGoal() {
        String sign = "";
        if (goalType.equals(FSConst.GOAL_TYPE_GREATER_THAN_WORD))
            sign = FSConst.GOAL_TYPE_GREATER_THAN_SIGN;
        else if (goalType.equals(FSConst.GOAL_TYPE_LESS_THAN_WORD))
            sign = FSConst.GOAL_TYPE_LESS_THAN_SIGN;
        String str = Util.round(value) + unit + sign + goalValue + unit;
        return str;
    }

    public String getUnit() {
        return unit;
    }

    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        try {
            object.put(FSConst.JSON_ELEMENT_DETAIL_NAME, this.name);
            object.put(FSConst.JSON_ELEMENT_DETAIL_VALUE, this.value);
            object.put(FSConst.JSON_ELEMENT_DETAIL_UNIT, this.unit);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
