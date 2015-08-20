package app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import app.delegates.Util;
import app.models.ElementAnalysis;
import app.models.ElementResult;
import app.tabsample.R;

/**
 * Created by Steve on 8/19/2015.
 */
public class TypeAnalysisAdapter extends ArrayAdapter<ElementAnalysis> {
    private ArrayList<ElementAnalysis> data = null;
    private Context context;
    private int resource;

    public TypeAnalysisAdapter(Context context, int resource, List<ElementAnalysis> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.data = (ArrayList<ElementAnalysis>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        View row = convertView;
        MyHolder holder = null;
        if (row == null || row.getTag() == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(this.resource, parent, false);

            holder = new MyHolder();
//            holder.textViewElementName = (TextView) row.findViewById(R.id.textView_type_analysis_element_name);
            holder.lineChart = (LineChart) row.findViewById(R.id.line_chart_type_analysis);
        } else {
            holder = (MyHolder) row.getTag();
        }
        ElementAnalysis elementAnalysis = this.data.get(position);
//        holder.textViewElementName.setText(elementAnalysis.getElementName());
        populateLineChart(holder.lineChart, elementAnalysis);
        return row;
    }

    private void populateLineChart(LineChart lineChart, ElementAnalysis elementAnalysis) {
        ArrayList<Entry> vals = new ArrayList<Entry>();
        ArrayList<ElementResult> arrayList = elementAnalysis.getTimelineWithLimit();
        ArrayList<String> xLabels = new ArrayList<String>();
        for (int i = 0; i < arrayList.size(); i++) {
            vals.add(new Entry((float) arrayList.get(i).getValue(), i));
            xLabels.add(Util.dateToStringShort(arrayList.get(i).getDate()));
        }
        LineDataSet lineDataSet = new LineDataSet(vals, elementAnalysis.getElementName());
//        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        // disable y axis right side
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);
        // disable y axis grid line
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        // disable x axis grid line
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);

        LineData data = new LineData(xLabels, lineDataSet);
        lineChart.setData(data);
        lineChart.invalidate();
    }

    static class MyHolder {
        TextView textViewElementName;
        LineChart lineChart;
    }
}
