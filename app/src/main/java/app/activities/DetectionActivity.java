package app.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import app.TH_ProgressButton.MasterLayout;
import app.delegates.SharedPreferenceDelegate;
import app.delegates.Util;
import app.json.JsonParser;
import app.models.ElementDetail;
import app.models.SingleDetection;
import app.models.TypeAnalysis;
import app.tabsample.R;

/**
 * @author Adil Soomro
 */
public class DetectionActivity extends Activity {
    private SharedPreferenceDelegate sharedPreferenceDelegate = null;
    static MasterLayout masterLayout;   //Should be static

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection);
        sharedPreferenceDelegate = new SharedPreferenceDelegate(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        showDetectionButton();
    }

    public void showDetectionButton() {
        masterLayout = (MasterLayout) findViewById(R.id.MasterLayout01);
        masterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masterLayout.animation();
                switch (masterLayout.flg_frmwrk_mode) {

                    case 1:
                        //Start state. Call your method
                        startDetection(null);
                        break;
                    case 2:

                        //Running state. Call your method
                        break;

                    case 3:

                        //End state. Call your method
                        break;
                }
            }
        });
    }

    public void startDetection(View view) {

        // populate listView
        new ReceiveDetectionResultAsynTask().execute();
//        SingleDetection sd = getDetectionResult();
//        saveDetectionResult(sd);
//        populateListView(sd);
    }

    // mock detection process
//    public SingleDetection getDetectionResult() {
//        String json = "{ \"date\":\"%s\", \"type\":\"breast_milk\", \"detail\":[ { \"name\":\"ruqing_protein\", \"value\":1.5 }, { \"name\":\"lao_protein\", \"value\":1.5 }, { \"name\":\"rutie_protein\", \"value\":1.5 }, { \"name\":\"mianyiqiu_protein_slga\", \"value\":1.5 }, { \"name\":\"mianyiqiu_protein_igg1\", \"value\":1.5 }, { \"name\":\"folic_acid\", \"value\":1.5 } ] }";
//        json = String.format(json, Util.dateToString(new Date()));
//
//        SingleDetection sd = JsonParser.parseSingleDetection(json);
//
//        return sd;
//    }

    public void saveDetectionResult(SingleDetection sd) {
        sharedPreferenceDelegate.addToDetectionList(sd.getJsonData());
        TypeAnalysis typeAnalysis = sd.toTypeAnalysis();
        sharedPreferenceDelegate.addToTypeAnalysis(typeAnalysis);
    }

    // need to be called after inflate listview_history, and add it to activity
    // listview for a single detection, all elements
    public void populateListView(SingleDetection sd) {
//        ArrayList<String> array = sd.detailsToString();
        ArrayList<ElementDetail> array = sd.getDetails();
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

    protected class ReceiveDetectionResultAsynTask extends AsyncTask<Void, Integer, SingleDetection> {
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            masterLayout.cusview.setupprogress(values[0]);
        }

        @Override
        protected void onPostExecute(SingleDetection sd) {
            super.onPostExecute(sd);
            // inflate listView
            LinearLayout detection_list = (LinearLayout) inflateLayout(R.layout.detections_detail);
            // add to frameLayout
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.detection_activity_layout);
            frameLayout.addView(detection_list);
            saveDetectionResult(sd);
            populateListView(sd);
        }

        @Override
        protected SingleDetection doInBackground(Void... params) {
            String json = "{ \"date\":\"%s\", \"type\":\"breast_milk\", \"detail\":[ { \"name\":\"ruqing_protein\", \"value\":1.5 }, { \"name\":\"lao_protein\", \"value\":1.5 }, { \"name\":\"rutie_protein\", \"value\":1.5 }, { \"name\":\"mianyiqiu_protein_slga\", \"value\":1.5 }, { \"name\":\"mianyiqiu_protein_igg1\", \"value\":1.5 }, { \"name\":\"folic_acid\", \"value\":1.5 } ] }";
            json = String.format(json, Util.dateToString(new Date()));
            SingleDetection sd = JsonParser.parseSingleDetection(json);
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return sd;
        }
    }
}