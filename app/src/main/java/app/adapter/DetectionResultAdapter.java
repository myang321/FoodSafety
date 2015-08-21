package app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.models.ElementDetail;
import app.tabsample.R;

/**
 * Created by Steve on 8/20/2015.
 */
public class DetectionResultAdapter extends ArrayAdapter<ElementDetail> {
    private ArrayList<ElementDetail> data = null;
    private Context context;
    private int resource;

    public DetectionResultAdapter(Context context, int resource, List<ElementDetail> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.data = (ArrayList<ElementDetail>) objects;
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
            holder.textViewElementName = (TextView) row.findViewById(R.id.textView_detection_element_name);
            holder.textViewResult = (TextView) row.findViewById(R.id.textView_detection_result);
        } else {
            holder = (MyHolder) row.getTag();
        }
        ElementDetail ed = this.data.get(position);
        holder.textViewElementName.setText(ed.getName());
        holder.textViewResult.setText(ed.getValueWithGoal());
        return row;
    }

    static class MyHolder {
        TextView textViewElementName;
        TextView textViewTitle;
        TextView textViewResult;
        Button buttonSuggestion;
    }
}
