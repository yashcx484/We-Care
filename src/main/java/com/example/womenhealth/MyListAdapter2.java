package com.example.womenhealth;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyListAdapter2 extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> msg ;
    private final List<String> username ;
    private final List<String> time ;


    public MyListAdapter2(Activity context, List<String> username,List<String> msg,List<String> time) {
        super(context, R.layout.my_message1,msg);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.msg=msg;
        this.username=username;
        this.time=time;



    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.my_message1, null,true);

        TextView c = (TextView) rowView.findViewById(R.id.msg_txt);
        TextView c1 = (TextView) rowView.findViewById(R.id.time_txt);
        TextView c2 = (TextView) rowView.findViewById(R.id.username);

        c.setText(time.get(position));
        c1.setText(username.get(position));
        c2.setText(msg.get(position));


        Log.d("listmsg",""+msg.get(position));

        return rowView;

    };
}
