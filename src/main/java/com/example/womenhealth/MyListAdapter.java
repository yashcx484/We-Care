package com.example.womenhealth;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> cnamelist ;



    public MyListAdapter(Activity context, List<String> cnamelist) {
        super(context, R.layout.mylist,cnamelist);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.cnamelist=cnamelist;



    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView c = (TextView) rowView.findViewById(R.id.Cname_Value);


        c.setText(cnamelist.get(position));


        Log.d("listmsg",""+cnamelist.get(position));

        return rowView;

    };
}