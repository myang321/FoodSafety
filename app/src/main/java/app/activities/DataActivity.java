package app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import app.adapter.HistoryAdapter;
import app.adapter.TypeAnalysisAdapter;
import app.constants.FSConst;
import app.delegates.SharedPreferenceDelegate;
import app.models.SingleDetection;
import app.models.TypeAnalysis;
import app.tabsample.R;

/**
 * @author Adil Soomro
 */
public class DataActivity extends Activity {
    private SharedPreferenceDelegate sharedPreferenceDelegate = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        sharedPreferenceDelegate = new SharedPreferenceDelegate(this);


    }

    public void showMenuPage(View view) {
        // inflate listView
        View data_menu = inflateLayout(R.layout.activity_data_menu);
        // add to frameLayout
//        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.data_activity_layout);
//        frameLayout.addView(data_menu);
        addToFrame(data_menu);
    }

    public void showHistory(View view) {
        LinearLayout history = (LinearLayout) inflateLayout(R.layout.activity_data_history);
        addToFrame(history);
        populateListViewHistory();
        setTitle(FSConst.TITLE_CHINESE_HISTORY);
    }

    public void showBreastMilk(View view) {
        showTypeAnalysis(FSConst.TYPE_BREAST_MILK);
        setTitle(FSConst.TITLE_CHINESE_BREAST_MILK);
    }

    public void showMilkPowder(View view) {
        showTypeAnalysis(FSConst.TYPE_MILK_POWDER);
        setTitle(FSConst.TITLE_CHINESE_MILK_POWDER);
    }

    public void showSupplementaryFood(View view) {
        showTypeAnalysis(FSConst.TYPE_SUPPLEMENTARY_FOOD);
        setTitle(FSConst.TITLE_CHINESE_SUPPLEMENTARY_FOOD);
    }

    public void showTypeAnalysis(String typeName) {
        LinearLayout linearLayout = (LinearLayout) inflateLayout(R.layout.activity_data_type_analysis);
        addToFrame(linearLayout);
        populateListViewTypeAnalysis(typeName);
    }

    private View inflateLayout(int layoutID) {
//        final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        return inflater.inflate(layoutID, null);
        View view = getLayoutInflater().inflate(layoutID, null);
        return view;
    }

    @Override
    protected void onResume() {
        super.onResume();
        showHistory(null);
    }

    private ArrayList<SingleDetection> getDetectionList() {
        return sharedPreferenceDelegate.getDetectionList(this);
    }

    // list view for all detections
    private void populateListViewHistory() {
        ArrayList<SingleDetection> array = getDetectionList();
//        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.detection_list_item, R.id.textView1, array);
        HistoryAdapter historyAdapter = new HistoryAdapter(this, R.layout.listview_item_row_history, array);
        ListView lv1 = (ListView) this.findViewById(R.id.listview_history);
        lv1.setAdapter(historyAdapter);
    }


    private void populateListViewTypeAnalysis(String typeName) {
        TypeAnalysis typeAnalysis = sharedPreferenceDelegate.getTypeAnalysis(typeName);
//        ArrayList<String> array = new ArrayList<String>();
//        for (ElementAnalysis ea : typeAnalysis.getDetails()) {
//            array.add(ea.toString());
//        }
//        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.detection_list_item, R.id.textView1, typeAnalysis.getDetails());
        TypeAnalysisAdapter adapter = new TypeAnalysisAdapter(this, R.layout.listview_item_row_type_analysis, typeAnalysis.getDetails());
        ListView lv1 = (ListView) this.findViewById(R.id.listview_type_analysis);
        lv1.setAdapter(adapter);
    }

    private void addToFrame(View view) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.data_activity_layout);
        frameLayout.removeAllViewsInLayout();
        frameLayout.addView(view);
    }

    private void setTitle(String titleStr) {
        TextView title = (TextView) findViewById(R.id.textView_activity_data_title);
        title.setText(titleStr);
    }
}
