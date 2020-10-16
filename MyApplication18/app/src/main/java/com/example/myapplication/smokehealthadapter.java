package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class smokehealthadapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
public smokehealthadapter(Context context, String[] values){
    super(context, R.layout.smokehealthlist, values);
    this.context = context;
    this.values = values;
}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.smokehealthlist, parent, false);
        TextView txt1 = (TextView) rowView.findViewById(R.id.label);
        TextView txt2 = rowView.findViewById(R.id.logo);
        txt1.setText(values[position]);
      //  txt2.setText();

       return rowView;

    }
}
