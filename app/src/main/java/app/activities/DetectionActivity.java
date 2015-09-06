package app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import app.TH_ProgressButton.MasterLayout;
import app.adapter.DetectionResultAdapter;
import app.delegates.SharedPreferenceDelegate;
import app.models.ElementDetail;
import app.models.SingleDetection;
import app.models.TypeAnalysis;
import app.tabsample.R;


public class DetectionActivity extends Activity {
    private SharedPreferenceDelegate sharedPreferenceDelegate = null;
    static MasterLayout masterLayout;   //Should be static

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService();
        setContentView(R.layout.activity_detection);
        sharedPreferenceDelegate = new SharedPreferenceDelegate(this);
        showMainView();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void startService() {
        Intent intent = new Intent(this, MyService.class);
        this.startService(intent);
    }

    public void setDetectionButton() {
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
        sharedPreferenceDelegate.addToDetectionList(sd.toJson().toString());
        TypeAnalysis typeAnalysis = sd.toTypeAnalysis();
        sharedPreferenceDelegate.addToTypeAnalysis(typeAnalysis);
    }

    public void closeNutritionSuggestion(View view) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.detection_activity_layout);
        frameLayout.removeViewAt(1);
    }

    public void showNutritionSuggestion(View view) {
        FrameLayout detection_list = (FrameLayout) inflateLayout(R.layout.activity_detection_nutrition_suggestion);
        // add to frameLayout
        addToFrame(detection_list);
    }

    // need to be called after inflate listview_history, and add it to activity
    // listview for a single detection, all elements
    public void populateListView(SingleDetection sd) {
//        ArrayList<String> array = sd.detailsToString();
        ArrayList<ElementDetail> array = sd.getDetails();
//        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.detection_list_item, R.id.textView1, array);
        DetectionResultAdapter adapter = new DetectionResultAdapter(this, R.layout.listview_item_row_detection_detail, array);
        ListView lv1 = (ListView) this.findViewById(R.id.listview_detection_result);
        lv1.setAdapter(adapter);
    }

    public View inflateLayout(int layoutID) {
//        final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        return inflater.inflate(layoutID, null);
        View view = getLayoutInflater().inflate(layoutID, null);
        return view;
    }

    public void closeDetectionResult(View view) {
        showMainView();
    }

    public void showMainView() {
        LinearLayout main = (LinearLayout) inflateLayout(R.layout.activity_detection_main);
        replaceFrame(main);
        setDetectionButton();
        clearTitle();
    }

    public void showDetectionResult(SingleDetection sd) {
        // inflate listView
        LinearLayout detection_list = (LinearLayout) inflateLayout(R.layout.activity_detections_detail);
        // add to frameLayout
        replaceFrame(detection_list);
        saveDetectionResult(sd);
        populateListView(sd);
        setTitle(sd.getDetectionTypeChinese());
    }

    private void addToFrame(View view) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.detection_activity_layout);
        frameLayout.addView(view);
    }

    private void replaceFrame(View view) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.detection_activity_layout);
        frameLayout.removeAllViewsInLayout();
        frameLayout.addView(view);
    }

    public void clearTitle() {
        TextView title = (TextView) findViewById(R.id.textView_detection_activity_title);
        title.setText("检测");
    }

    public void setTitle(String type) {
        TextView title = (TextView) findViewById(R.id.textView_detection_activity_title);
        title.setText("检测-" + type);
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
            showDetectionResult(sd);
        }

        @Override
        protected SingleDetection doInBackground(Void... params) {
//            String json = "{ \"date\":\"%s\", \"type\":\"breast_milk\", \"detail\":[ { \"name\":\"ruqing_protein\", \"value\":1.5 }, { \"name\":\"lao_protein\", \"value\":1.5 }, { \"name\":\"rutie_protein\", \"value\":1.5 }, { \"name\":\"mianyiqiu_protein_slga\", \"value\":1.5 }, { \"name\":\"mianyiqiu_protein_igg1\", \"value\":1.5 }, { \"name\":\"folic_acid\", \"value\":1.5 } ] }";
//            json = String.format(json, Util.dateToString(new Date()));
//            SingleDetection sd = JsonParser.parseSingleDetection(json);
            SingleDetection sd = SingleDetection.getRandom(DetectionActivity.this);
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