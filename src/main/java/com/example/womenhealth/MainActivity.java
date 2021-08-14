package com.example.womenhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(EditText)findViewById(R.id.email_edit_text_login);
        password=(EditText)findViewById(R.id.password_edit_text_login);
        Log.d("myTag", email.toString());
       // Intent it=new Intent(getApplicationContext(),Signup.class);

        Button btnTrav =(Button) findViewById(R.id.button2);
        btnTrav.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),signup.class);
                startActivity(i);
            }
            });

    }

    public void onLogIn(View view) {
       final String emailId = email.getText().toString();
       final String pwd = password.getText().toString();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Profile");
        try {
            Query q=ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                      int flag=0;
                    for (DataSnapshot sn : dataSnapshot.getChildren()) {
                      UserInfo p1=sn.getValue(UserInfo.class);
                        String auth_email=p1.getEmail() ;
                        String auth_password=p1.getPassword();
                        String uid_num =p1.getUid();

                        if(auth_email.toString().equals(emailId) &&auth_password.toString().equals(pwd))
                        {
                             flag=1;
                            //Toast.makeText(this, "Valid", Toast.LENGTH_SHORT).show();
                            Intent i= new Intent(getApplicationContext(),HomePage.class);
                            i.putExtra("Uid", uid_num);
                            startActivity(i);


                        }

                    }
                        if(flag ==0) {
                            Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                        }



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
//                public void printError()
//                {
//
//                }
            });


        }catch(Exception ex){}




    }
}
