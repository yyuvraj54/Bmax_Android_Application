package com.example.bmax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {

    //>>>>>>>>>>>>>ALL WEDGET IN APP
    EditText user;
    EditText pass;
    EditText agee;

    EditText height_of_user;
    EditText weight_of_user;

    RadioButton male_button;
    RadioButton female_button;
    RadioGroup rgg;

    Button Sinup;

    TextView er;


    Spinner mySpinner;
    //>>>>>>>>>>>>>>>>>>>>> FIRE BASE CONNECTIONS
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //USERNAME AND PASSWORD REFRENCE
        user = findViewById(R.id.userName);
        pass = findViewById(R.id.PassWord);

        //HEIGHT AND WEIGHT REFRENCE
        height_of_user = findViewById(R.id.Height);
        weight_of_user = findViewById(R.id.Weight);

        //RADIO BUTTONS OF GENDER
        male_button = findViewById(R.id.Male_radio);
        female_button = findViewById(R.id.Female_radio);
        rgg = findViewById(R.id.rg);

        //Sign_up BUTTON ALSO CONNECTED WITH SIGNIT FUNCTION
        Sinup = findViewById(R.id.Sign_up);

        //AGE
        agee = findViewById(R.id.age);


        //ERROR MEASSAGE TEXT
        er = findViewById(R.id.error);




        //intensity
        mySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(signup.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Intensity));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);





    }


    //AFTER CLICKING ON SIGN UP
    public void SignIt(View view) {

        er.setTextColor(Color.parseColor("#FFE14073"));


        // TAKING VALUES OF EACH ENTRY ON BUTTON CLICK EACH TIME !
        String username1=user.getText().toString();
        String password1=pass.getText().toString();
        String hval=height_of_user.getText().toString();
        String wval=weight_of_user.getText().toString();
        String ival=mySpinner.getSelectedItem().toString();
        String ageval=agee.getText().toString();


        // for intensity number
        if (ival.equals("1 (easy Workout)")){ival="1";}
        else if (ival.equals("2 (Normal Workout)")){ival="2";}
        else if(ival.equals("3 (Intence Workout)")){ival="3";}




        //VALUE OF RADIO BUTTON EITHER MALE OR FEMALE!
        String gender="";
        if(male_button.isChecked()){gender="MALE";}
        else if (female_button.isChecked()){gender="FEMALE";}

        //CHECKING ALL VALUES IF WRONG THROWING ERROR TEXT
        CredentialsCheck errorm=new CredentialsCheck(username1,password1,hval,wval,gender,ival,ageval);
        er.setText(errorm.errorcheck());


        //IF ALL VALUES ARE SATISFIED CONNECTING TO FIREBASE AND UPLOADING THE VALUES
        if (er.getText().toString()=="Data check almost done"){
            er.setTextColor(Color.parseColor("#00A2FF"));
            rootNode = FirebaseDatabase.getInstance();
            reference=rootNode.getReference("users").child(username1);
            String finalGender = gender;
            String finalIval = ival;
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){er.setText("Name alredy taken try other");
                    }
                    else{
                        //MAIN DATA TO UPLOAD  IS PROCESSED BY THIS OBJECT
                        OrderHelper DATA=new OrderHelper(username1,password1,hval,wval, finalGender, finalIval,ageval);
                        //USEING CHILD  USERNAME SO THAT NAME SHOUD BE UNIQE SAME NAME CAN NOT EXISTS
                        reference.setValue(DATA);

                        String id=("Id created with name "+username1);
                        Toast.makeText(signup.this, id, Toast.LENGTH_LONG).show();
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}