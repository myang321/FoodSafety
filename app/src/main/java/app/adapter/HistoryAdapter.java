package app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.models.SingleDetection;
import app.tabsample.R;

/**
 * Created by Steve on 8/16/2015.
 */
public class HistoryAdapter extends ArrayAdapter<SingleDetection> {
    private ArrayList<SingleDetection> data = null;
    private Context context;
    private int resource;

    public HistoryAdapter(Context context, int resource, List<SingleDetection> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.data = (ArrayList<SingleDetection>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        View row = convertView;
        MyHolder holder = null;
        if (row == null || row.getTag() == null) {
//            Log.d("meng2", "HistoryAdapter getView if");
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(this.resource, parent, false);

            holder = new MyHolder();
            holder.textViewDate = (TextView) row.findViewById(R.id.textView_history_date);
            holder.textViewType = (TextView) row.findViewById(R.id.textView_history_type);
        } else {
//            Log.d("meng2", "HistoryAdapter getView else");
//            Log.d("meng2", "row.getTag() is null?" + (row.getTag() == null));
            holder = (MyHolder) row.getTag();
        }
        SingleDetection sd = this.data.get(position);
//        Log.d("meng2", "position:" + position);
//        Log.d("meng2", "holder is null?" + (holder == null));
//        Log.d("meng2", "holder.textViewDate is null?" + (holder.textViewDate == null));
        holder.textViewDate.setText(sd.getDateShortWithYear());
        holder.textViewType.setText(sd.getDetectionTypeChinese());
        return row;
    }

    static class MyHolder {
        TextView textViewDate;
        TextView textViewType;
    }
}
