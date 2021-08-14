package com.example.womenhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {
    EditText email, password,gender,height,age,weight;
    Button btnSignIn;
    String s1="";
    int numuser;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email=(EditText)findViewById(R.id.email_edit_text_signup);
        password=(EditText)findViewById(R.id.password_edit_text_signup);
        gender=(EditText)findViewById(R.id.gender_edit_text_signup);
        height=(EditText)findViewById(R.id.height_edit_text_signup);
        age =(EditText)findViewById(R.id.age_edit_text_signup);
        weight =(EditText)findViewById(R.id.age_edit_text_signup);

    }

    public void onSignup(View view) {
        String emailId=email.getText().toString();
        String pwd=password.getText().toString();
        String gen=gender.getText().toString();
        String hgt=height.getText().toString();
        String ag=age.getText().toString();
        String wt=weight.getText().toString();




        UserInfo user1 =new UserInfo();
        //setInfo
        user1.setEmail(emailId);
        user1.setPassword(pwd);
        user1.setGender(gen);
        user1.setHeight(hgt);
        user1.setAge(ag);
        user1.setWeight(wt);



        //*************
        try{

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference ref1 = database.getReference().child("dashboard").child("users");
            final String[] count = new String[1];

            Query q=ref1.orderByKey();

            // For counting Users

            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //Toast.makeText(this, groupNameList.get(0).toString(), Toast.LENGTH_LONG).show();

                    // e1.setText(keylist.get(1));

                    for(DataSnapshot sn:dataSnapshot.getChildren())
                    {
                        // Dashboardinfo p1=sn.getValue(Dashboardinfo.class);
                        String t1=sn.getValue().toString();
                        s1= String.valueOf(Integer.valueOf(t1)+1);

                        ref1.child("countuser").setValue(s1);

                    }

                    count[0] =dataSnapshot.getValue().toString();
                     //getnumUser(count[0]);
                }
                // Toast toast = Toast.makeText(this, k1, Toast.LENGTH_LONG);
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }

            });

            numuser=Integer.parseInt(count[0]);



        }

        catch(Exception ex){}
         String temp =("User:").concat(s1);
        user1.setUid(temp);



        //***********
        if(emailId.isEmpty())
        {
            email.setError("Please enter the email id");
            email.requestFocus();
        }
        else if(pwd.isEmpty())
        {
            password.setError("Please enter the password");
            password.requestFocus();
        }
        else if(emailId.isEmpty()&& pwd.isEmpty())
        {
            Toast.makeText(this,"Fields are empty!!", Toast.LENGTH_SHORT).show();
        }

        //****************************
        try{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            final DatabaseReference ref1 = database.getReference().child("dashboard").child("users");
//            ref1.child("countuser").setValue("21");

           // Query q=ref1.orderByKey();

            DatabaseReference ref1 = database.getReference().child("Profile").push();
            ref1.setValue(user1);
            if(!(emailId.isEmpty()|| pwd.isEmpty()))
            {
                Intent i = new Intent(getApplicationContext(), HomePage.class);
                startActivity(i);
            }

        }
        catch (Exception e)
        {
            Toast.makeText(signup.this," exception : "+e,Toast.LENGTH_LONG).show();

        }


        //*********************
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //final DatabaseReference ref = database.getReference().child("Dashboard");
        //final DatabaseReference ref1 = database.getReference().child("dashboard").child("users");

        //Toast.makeText(this,emailId, Toast.LENGTH_LONG).show();
        //Toast.makeText(this,pwd, Toast.LENGTH_SHORT).show();
    }

    void getnumUser(String count) {

        Toast.makeText(this,"in numuser", Toast.LENGTH_SHORT).show();
        numuser = Integer.parseInt(count);
        int temp = numuser + 1;
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference ref1 = database.getReference().child("dashboard").child("users").child("countuser");
//
//        try {
//            ref1.setValue(String.valueOf(temp));
//        } catch (Exception e) {
//        }
    }

}
