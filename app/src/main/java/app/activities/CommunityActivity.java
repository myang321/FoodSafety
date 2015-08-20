package app.activities;

import android.app.Activity;
import android.os.Bundle;

import app.tabsample.R;


public class CommunityActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
//        populateLineChart();
    }

//    private void populateLineChart() {
//        LineChart lineChart = (LineChart) findViewById(R.id.line_chart1);
//        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
//        valsComp1.add(new Entry(100.000f, 0));
//        valsComp1.add(new Entry(50.000f, 1));
//        valsComp1.add(new Entry(100.000f, 2));
//        valsComp1.add(new Entry(50.000f, 3));
//        LineDataSet setComp1 = new LineDataSet(valsComp1, "Company 1");
//        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
//
//        // disable y axis right side
//        YAxis rightAxis = lineChart.getAxisRight();
//        rightAxis.setEnabled(false);
//        // disable y axis grid line
//        YAxis leftAxis = lineChart.getAxisLeft();
//        leftAxis.setDrawGridLines(false);
//        // disable x axis grid line
//        XAxis xAxis = lineChart.getXAxis();
//        xAxis.setDrawGridLines(false);
//
//
//        ArrayList<String> xLabels = new ArrayList<String>();
//        xLabels.add("1.Q");
//        xLabels.add("2.Q");
//        xLabels.add("3.Q");
//        xLabels.add("4.Q");
//        LineData data = new LineData(xLabels, setComp1);
//        lineChart.setData(data);
//        lineChart.invalidate();
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.community, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
