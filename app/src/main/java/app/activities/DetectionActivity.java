package app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import app.json.JsonParser;
import app.models.SingleDetection;
import app.tabsample.R;

/**
 * @author Adil Soomro
 */
public class DetectionActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection);
    }

    public void startDetection(View view) {
        String json = "{ \"date\":\"2015-08-11 16:56:45\", \"type\":\"breast_milk\", \"detail\":[ { \"name\":\"ruqing_protein\", \"value\":1.5 }, { \"name\":\"lao_protein\", \"value\":1.5 }, { \"name\":\"rutie_protein\", \"value\":1.5 }, { \"name\":\"mianyiqiu_protein_slga\", \"value\":1.5 }, { \"name\":\"mianyiqiu_protein_igg1\", \"value\":1.5 }, { \"name\":\"folic_acid\", \"value\":1.5 } ] }";
        SingleDetection sd = JsonParser.parseSingDetectionFromFile(json);
        ArrayList<String> array = sd.detailsToString();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, R.id.textView1, array);
        ListView lv1 = (ListView) this.findViewById(R.id.listview_history);
        lv1.setAdapter(adapter);
    }
}