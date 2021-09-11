package com.example.bmax;

import static java.lang.Math.floor;
import static java.lang.Math.round;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ftab2 extends Fragment {

    TextView weight, height, bmi, state,update,exer,diet;
    Button daily,fatexercise,dump,reexer;

    public ftab2() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View b = inflater.inflate(R.layout.fragment_ftab2, container, false);

        weight = (TextView) b.findViewById(R.id.wei);
        height = (TextView) b.findViewById(R.id.hei);
        bmi = (TextView) b.findViewById(R.id.bmi);
        state = (TextView) b.findViewById(R.id.state);
        update = (TextView) b.findViewById(R.id.updates);
        exer = (TextView) b.findViewById(R.id.exercise);
        diet = (TextView) b.findViewById(R.id.dietplan);

        daily=(Button)b.findViewById(R.id.dailyExer);
        fatexercise=(Button)b.findViewById(R.id.fatExer);
        dump=(Button)b.findViewById(R.id.dumbleExer);
        reexer=(Button)b.findViewById(R.id.recomendedExer);





        SharedPreferences getShared = getContext().getSharedPreferences("DEMO", Context.MODE_PRIVATE);
        String h = getShared.getString("h", "er:200");
        String w = getShared.getString("w", "er:200");
        String ival = getShared.getString("ival", "er:200");
        String ageval = getShared.getString("ageval", "er:200");
        String g = getShared.getString("g", "er:200");


        float intheight = (float) Integer.parseInt(h) / 100;
        float intweight = (float) Integer.parseInt(w);
        float Bmi = intweight / (intheight * intheight);
        double roundOff = round(Bmi * 100.0) / 100.0;
        String currstate=StateBmi((float) Bmi);


        weight.setText(w);
        height.setText(h);
        bmi.setText(Double.toString(roundOff));
        state.setText(StateBmi((float) Bmi));
        String exerMeassage="According to your Height:"+h +" and Weight:"+w+", Bmax has calculated your bmi status (Bmi:"+roundOff+" Which means you are "+ currstate+") and according to state Bmax has recomended some exercises Below for you! ";
        exer.setText(exerMeassage);




        try {
            update.setText(currentState());
        }
        catch (Exception e){
            System.out.println("Problem with date"+e);
        }
        try {
            diet.setText(reqcal(Float.parseFloat(h),Float.parseFloat(w), Float.parseFloat(ageval) ,g, Float.parseFloat(ival) ));
        }catch (Exception e){
            System.out.println("Problem with dietplan"+e);

        }


        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(getActivity(),inten1.class);
                    startActivity(intent);
            }
        });

        fatexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),inten2.class);
                startActivity(intent);

            }
        });

        dump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),inten3.class);
                startActivity(intent);
            }
        });


        reexer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ival.equals("1")){
                    Intent intent=new Intent(getActivity(),inten1.class);
                    startActivity(intent);
                }
                else if (ival.equals("2")){
                    Intent intent=new Intent(getActivity(),inten2.class);
                    startActivity(intent);
                }
                else if (ival.equals("3")){
                    Intent intent=new Intent(getActivity(),inten3.class);
                    startActivity(intent);
                }
            }
        });

        return b;

    }




    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //ALL METHOD THAT ARE BEEN CALLED ARE BELOW




    public String StateBmi(float bmii) {
        if (bmii <= 18.5) {
            bmi.setTextColor(Color.parseColor("#0099cc"));
            state.setTextColor(Color.parseColor("#0099cc"));
            return "Underweight";
        } else if (bmii > 18.5 && bmii <= 24.9) {
            bmi.setTextColor(Color.parseColor("#00cc99"));
            state.setTextColor(Color.parseColor("#00cc99"));
            return "Normal";
        } else if (bmii >= 25 && bmii <= 29.9) {
            bmi.setTextColor(Color.parseColor("#ff5050"));
            state.setTextColor(Color.parseColor("#ff5050"));
            return "Overweight";
        } else if (bmii >= 30) {
            bmi.setTextColor(Color.parseColor("#ff0000"));
            state.setTextColor(Color.parseColor("#ff0000"));
            return "Obesity";
        } else {
            return "Invalid";
        }
    }
        @RequiresApi(api = Build.VERSION_CODES.O)
        public String currentState() {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
            LocalDateTime now = LocalDateTime.now();
            String ans=dtf.format(now);

            if (ans.equals("6")){return "Its morning time, you should start of by doing exercise.";}
            else if(ans.equals("7")){return "Nothing is more refreshing than a warm cup of tea. Hence make yourself a nice cup of tea.";}
            else if (ans.equals("8")){return "It is time for breakfast. Have something with high carbohydrate content like cereal with milk or sandwitches  to set you up for the day";}
            else if (ans.equals("9")){return "Quite a time went by since breakfast, have a fruit and make your gut happy";}
            else if (ans.equals("13")){return "Its lunch time now, have something with high protien content like daals";}
            else if (ans.equals("17")){return "Its been a while since lunch, have something with high energy content snack like dark cholate :D.";}
            else if(ans.equals("20")){return "Its dinner time, have something low fat like a soup with some vegetables.";}
            else if (ans.equals("21")){return "Make yorself a ginger tea by boiling some crushed ginger, cloves and tulsi(indian basil) in water. It will enhance your bodies metabolism.";}
            else{return "Nothing for now. Keep following social distancing and also keep washing your hands regularly.";}
        }

        public String reqcal(float h ,float w,float a ,String g,float act ){
            float rw=23*(h*h)/10000;
            int cal=0;
            float scal=0;
            String line1="No data found";
            float cal0=(float)(66.5+13.8*(w)+5*(h)-(6.8*a));
            float cal1=(float) 0.9+(act/10);
            if (g.equals("MALE")){cal= (int)(cal0*cal1);}
            else if(g.equals("FEMALE")){
                cal=(int)(((655.1+9.6*(w)+1.9*(h))-(4.7*a))*(0.9+(act/10)));}

            if (((int)w-(int)rw)<0){
                    scal=  (float)(round((((cal)+256)/100)*100))-100;
                    line1=("Follow diet of "+scal+" calories per day for "+(int)(w-rw) +" months."); }

            else if  (((int)w-(int)rw)>0){
                    scal =(float)(round(((cal - 256) / 100) * 100)) - 100;
                    line1 = ("Follow diet of "+scal+" calories per day for "+ (int)(w-rw) +" months.");}
            else if  ((int)w-(int)rw==0){
                    scal=(float)(round(cal/100))*100;
                    line1=("Your diet is perfectly fine");}


            InputStream inputStream=this.getResources().openRawResource(R.raw.diets);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String strData="";
            String line2="";
            String line="NO Chart Prepaired As given height and weight not satisfied please enter a correct values";
            if (inputStream!=null){
                try{
                    while ((strData=bufferedReader.readLine())!=null){
                        if(Integer.toString(((int)(scal))).equals(strData)){
                            String Breakfast=bufferedReader.readLine();
                            String Lunch=bufferedReader.readLine();
                            String Dinner=bufferedReader.readLine();
                            String Snack=bufferedReader.readLine();
                            line2="You should follow a diet plan of as follows:\n\nBreakfast: "+Breakfast+" \n\nLunch: "+Lunch+" \n\nDinner: "+Dinner+" \n\nSnack: "+Snack;

                            break;
                        }

                    }
                    line=line1+line2;
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return line;
        }
}



