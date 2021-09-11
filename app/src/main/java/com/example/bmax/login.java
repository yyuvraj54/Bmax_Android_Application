package com.example.bmax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    //CREATING ALL WEGET THAT ARE IN XML CODE
    TextView logerr;
    EditText name;
    EditText pass;
    Button log;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //FINDING IT BY VIEW BY ID METHOD
        logerr=findViewById(R.id.logerr);
        name=findViewById(R.id.logname);
        pass=findViewById(R.id.logpass);
        log=findViewById(R.id.logbut);
    }
    public void loginnow(View view){
        //GETTING VALUE OF USER AND PASSWORD THAT USER HAVE ENTERED FROM APP
        String n=name.getText().toString();
        String p=pass.getText().toString();

        //CHECKING IF THE ENTER VALUE IS NULL FROM METHOD CHEK >>CREATED AT BOTTOM OF CODE
        checklogC(n,p);

        //IF ERROR MEASSAAGE IS NULL THAN IF STATEMNET IS TRUE
        if ((logerr.getText().toString())==""){
            Intent intent=new Intent(this,mainscreen.class);  //>>>>>>>>>>



            //CREATING A REFRENCE TO GET THE USERS BRANCH FROM THE FIREBASE
            reff= FirebaseDatabase.getInstance().getReference("users");

            //QUERY >>TO CHECK IF EACH USERS HAVE USER EQUAL TO OUR ENTER USER
            Query checkuser=reff.orderByChild("user").equalTo(n);
            //GETTING ALL VALUES FROM CHEKUSER QUERY
            checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                //ALL VALUES ARE STORED IN snapshort
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    logerr.setTextColor(Color.parseColor("#D22B2B"));
                    //IF SOME DATA EXIST IN (username mached) SNAPSHORT THAN GETTING PASSWORD FROM FIREBASE
                    if (snapshot.exists()) {
                        String passFromDB = snapshot.child(n).child("pass").getValue(String.class);

                        //CHECKING IF PASSWORD OF DB IS EQUAL TO USER ENTERED PASSWORD
                        if (passFromDB.equals(p)) {
                            pass.setText("");
                            logerr.setTextColor(Color.parseColor("#5DEC2F"));logerr.setText("Sucessfully Logged In");
                            String genderDB = snapshot.child(n).child("g").getValue(String.class);
                            String heightDB = snapshot.child(n).child("h").getValue(String.class);
                            String weightDB = snapshot.child(n).child("w").getValue(String.class);
                            String agevalDB = snapshot.child(n).child("ageval").getValue(String.class);
                            String ivalDB = snapshot.child(n).child("ival").getValue(String.class);

                            SharedPreferences sharedpreferences;
                            sharedpreferences = getSharedPreferences("DEMO", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("name", n);
                            editor.putString("pass", p);
                            editor.putString("g", genderDB);
                            editor.putString("h", heightDB);
                            editor.putString("w", weightDB);
                            editor.putString("ival", ivalDB);
                            editor.putString("ageval", agevalDB);
                            editor.apply();

                            startActivity(intent);
                        }
                        //IF PASSWORD NOT MACHED FROM DB
                        else{logerr.setText("Incorrect password");}
                    }
                    //IF USERNAME NOT EXIST IN DB
                    else{logerr.setText("No user with name "+n);}

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {}});
        }
        //IF BOTH (NAME/PASSWORD)THE FIELD ARE EMPTY OR NULL
        else{Toast.makeText(login.this, "Invalid Entry", Toast.LENGTH_SHORT).show();}



    }

    //THE METHODE IS CALLED ABOVE >>USE TO CHECK USER AND PASS THAT IF NULL RETURN ERROR MEASSAGE ELSE LEAVE ERROR MEASSAGE BLANK
    private void checklogC(String n,String p) {
        logerr.setTextColor(Color.parseColor("#D22B2B"));
        if (p.length()==0 && n.length()==0){logerr.setText("Name/Password null not accepted");}
        else if(p.length()==0){logerr.setText("Enter your Password");}
        else if (n.length()==0){logerr.setText("Enter your Name");}
        else{logerr.setText("");}

    }
}
