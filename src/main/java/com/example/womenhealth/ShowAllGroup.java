package com.example.womenhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowAllGroup extends AppCompatActivity {

    String uid="";
    String value="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_group);





        final List<String> groupNameList=new ArrayList<>();
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
             value = extras.getString("NextPage");
            uid = extras.getString("Uid");

            //The key argument here must match that used in the other activity
        }


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String nextPage;
        DatabaseReference ref1;
        if(value.equals("HP"))
        {
             ref1 = database.getReference().child("HealthProblem");
        }
        else
        {
            ref1 = database.getReference().child("HealthyHabits");
        }








//       Toast.makeText(this, "i am in", Toast.LENGTH_LONG).show();
        try{

            Query q=ref1.orderByKey();
            // For counting Users

            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot sn : dataSnapshot.getChildren()) {




                         String k1=sn.getKey();
                        //Log.d("myTag", k1);

                        groupNameList.add(k1);

                    }
                    //Toast.makeText(this, groupNameList.get(0).toString(), Toast.LENGTH_LONG).show();

                    // e1.setText(keylist.get(1));
                    dothis(groupNameList);
                }
               // Toast toast = Toast.makeText(this, k1, Toast.LENGTH_LONG);
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });



           }

        catch(Exception ex){}


    }
    public void dothis(final List<String> groupNameList)
    {
        MyListAdapter adapter=new MyListAdapter(this,groupNameList);



        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),String.valueOf(keylist1.get(position)),Toast.LENGTH_LONG).show();
                Intent it=new Intent(getApplicationContext(),Chat.class);
                it.putExtra("key",groupNameList.get(position));
                it.putExtra("Uid",uid);
                it.putExtra("Hgroup",value);
                startActivity(it);


            }





        });



        Toast toast = Toast.makeText(this,groupNameList.get(0), Toast.LENGTH_LONG);
        toast.show();
    }
}


