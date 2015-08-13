package app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
        // inflate listView
        LinearLayout detection_list = (LinearLayout) inflateLayout(R.layout.detections_list);
        // add to frameLayout
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.detection_activity_layout);
        frameLayout.addView(detection_list);
        // populate listView
        SingleDetection sd = getDetectionResult();
        populateListView(sd);

    }

    public SingleDetection getDetectionResult() {
        String json = "{ \"date\":\"2015-08-11 16:56:45\", \"type\":\"breast_milk\", \"detail\":[ { \"name\":\"ruqing_protein\", \"value\":1.5 }, { \"name\":\"lao_protein\", \"value\":1.5 }, { \"name\":\"rutie_protein\", \"value\":1.5 }, { \"name\":\"mianyiqiu_protein_slga\", \"value\":1.5 }, { \"name\":\"mianyiqiu_protein_igg1\", \"value\":1.5 }, { \"name\":\"folic_acid\", \"value\":1.5 } ] }";
        SingleDetection sd = JsonParser.parseSingDetectionFromFile(json);
        return sd;
    }

    // need to be called after inflate listview_history, and add it to activity
    public void populateListView(SingleDetection sd) {
        ArrayList<String> array = sd.detailsToString();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.detection_list_item, R.id.textView1, array);
        ListView lv1 = (ListView) this.findViewById(R.id.listview_history);
        lv1.setAdapter(adapter);
    }

    public View inflateLayout(int layoutID) {
//        final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        return inflater.inflate(layoutID, null);
        View view = getLayoutInflater().inflate(layoutID, null);
        return view;
    }

    public void closeDetectionResult(View view) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.detection_activity_layout);
        frameLayout.removeViewAt(1);
    }
}