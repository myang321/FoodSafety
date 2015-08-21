package app.models;

import android.app.Activity;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import app.json.JsonParser;

/**
 * Created by Steve on 8/20/2015.
 */
public class TypeStandard {
    String typeName;
    private ArrayList<ElementStandard> elementList;

    public TypeStandard(String typeName, ArrayList<ElementStandard> elementList) {
        this.typeName = typeName;
        this.elementList = elementList;
    }

    public static TypeStandard getInstance(String type, Activity activity) {
        String filename = type + ".json";
        String content = null;

        try {
            AssetManager assetManager = activity.getAssets();
            InputStream input = assetManager.open(filename);
            content = new Scanner(input).useDelimiter("\\A").next();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TypeStandard ts = JsonParser.parseTypeStandard(content);
        return ts;
    }

    public ElementStandard getElementStandard(String elementType) {
        for (ElementStandard es : this.elementList) {
            if (elementType.equals(es.getElementName()))
                return es;
        }
        return null;
    }

    public double getGoalValue(String elementType) {
        return getElementStandard(elementType).getGoalValue();
    }

    public String getGoalType(String elementType) {
        return getElementStandard(elementType).getGoalType();
    }
}
