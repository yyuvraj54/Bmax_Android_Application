package com.example.bmax;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ftab3 extends Fragment {

    TextView names,height,weight,gender,ivalue,agevalue;
    ImageView ui;
    Button but;


    public ftab3() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_ftab3, container, false);

       SharedPreferences getShared= getContext().getSharedPreferences("DEMO", Context.MODE_PRIVATE);
       String name=getShared.getString("name",null);
       String pass=getShared.getString("pass",null);
       String g=getShared.getString("g",null);
       String h=getShared.getString("h",null);
       String w=getShared.getString("w",null);
        String ival = getShared.getString("ival", "er:200");
        String ageval = getShared.getString("ageval", "er:200");


        names=(TextView) v.findViewById(R.id.naam);
        height=(TextView) v.findViewById(R.id.he);
        weight=(TextView) v.findViewById(R.id.we);
        gender=(TextView) v.findViewById(R.id.gen);
        ui=(ImageView) v.findViewById(R.id.userimage);
        but=(Button) v.findViewById(R.id.logout);
        agevalue=(TextView) v.findViewById(R.id.agetext);
        ivalue=(TextView) v.findViewById(R.id.intentext);



        String firstLetStr = name.substring(0, 1);
        String remLetStr = name.substring(1);
        firstLetStr = firstLetStr.toUpperCase();
        String firstLetterCapitalizedName = firstLetStr + remLetStr;

        if (g.equals("FEMALE")){ui.setImageResource(R.drawable.female);firstLetterCapitalizedName="Ms."+firstLetterCapitalizedName;}
        else{ui.setImageResource(R.drawable.male);firstLetterCapitalizedName="Mr."+firstLetterCapitalizedName;}


        String imeassge="";
        if (ival.equals("1")){imeassge="Intensity: Easy (Workmode Level:1)";}
        else if (ival.equals("2")){imeassge="Intensity: Normal (Workmode Level:2)";}
        else if (ival.equals("3")){imeassge="Intensity: Intensive (Workmode Level:3)";}

        names.setText(firstLetterCapitalizedName);
        height.setText("Height:  "+h);
        weight.setText("Weight:  "+w);
        gender.setText("Gender:  "+g);
        agevalue.setText("Age :  "+ageval);
        ivalue.setText(imeassge);


        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });




        return v;





    }



}