package com.example.barkas_pavlos_18022_androidjan21;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends ArrayAdapter<DataEntry> {

    private final String TAG = "CORONA";

    private List<DataEntry> dataEntries;
    private final LayoutInflater inflater;
    private final int layoutResource;

    public DataAdapter(@NonNull Context context, int resource, @NonNull List<DataEntry> objects){
        super(context, resource, objects);
        dataEntries = objects;
        inflater = LayoutInflater.from(context);
        layoutResource = resource;
    }

    public DataEntry getDataEntry(int position){
        if (position < dataEntries.size()) {
            return dataEntries.get(position);
        }
        return new DataEntry();
    }

    public void setDataEntries(@NonNull List<DataEntry> dataEntries){
        this.dataEntries = dataEntries;
        notifyDataSetChanged();
    }

    static class DataViewHolder{
        public TextView areaTextView;
        public TextView daytotalTextView;
        public TextView daydiffTextView;
        public TextView totalvaccinationsTextView;

        public DataViewHolder(View itemview){
            areaTextView = itemview.findViewById(R.id.areaTxt);
            daytotalTextView = itemview.findViewById(R.id.dayTotalNum);
            daydiffTextView = itemview.findViewById(R.id.dayDiffNum);
            totalvaccinationsTextView = itemview.findViewById(R.id.totalVacNum);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DataViewHolder holder;
        if (convertView == null){
            convertView = inflater.inflate(layoutResource, parent, false);
            holder = new DataViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (DataViewHolder)convertView.getTag();
        }

        DataEntry dataEntry = dataEntries.get(position);
        holder.areaTextView.setText(dataEntry.getArea());
        holder.daytotalTextView.setText(dataEntry.getDaytotal());
        holder.daydiffTextView.setText(dataEntry.getDaydiff());
        if (Integer.parseInt(dataEntry.getDaydiff()) > 0){
            holder.daydiffTextView.setTextColor(Color.GREEN);
        }else{
            holder.daydiffTextView.setTextColor(Color.RED);
        }
        holder.totalvaccinationsTextView.setText(dataEntry.getTotalVaccinations());

        return convertView;
    }

    @Override
    public int getCount() {
        return dataEntries.size();
    }
}
