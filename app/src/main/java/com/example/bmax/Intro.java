package com.example.bmax;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.google.rpc.context.AttributeContext;

import pl.droidsonroids.gif.InputSource;

public class Intro extends AppIntro {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        String fistdec="Your personal health constructor Bmax! which tells you what to eat time to time with proper diet chart and even tells your health status.";
        String fistitle="WELCOME TO BMAX";
        addSlide(AppIntroFragment.newInstance(fistitle,
                "R.font.bungee",
                fistdec,
                "R.font.bungee",
                R.drawable.logoofbmax,
                ContextCompat.getColor(getApplicationContext(),R.color.first)
                ,ContextCompat.getColor(getApplicationContext(), R.color.black)
                ,ContextCompat.getColor(getApplicationContext(), R.color.black)));

        addSlide(AppIntroFragment.newInstance("INTENSITY AND MODES ","R.font.bungee","Choose your intensity and mode, rest the app will manage for you.","R.font.bungee"
                ,R.drawable.exer, ContextCompat.getColor(getApplicationContext(),R.color.second),ContextCompat.getColor(getApplicationContext(), R.color.black),ContextCompat.getColor(getApplicationContext(), R.color.black)));

        addSlide(AppIntroFragment.newInstance("TYPES, SYMPTOMS, CAUSES," +
                        "DIAGNOSIS & TREATMENT","R.font.bungee","Search your problem and get the\n" +
                        " best solution,provided with-\n" +
                        "\n" +
                        "Home remedies\n" +
                        "Medicine\n" +
                        "Description of problem\n" +
                        "Best and trusted result from web\n","R.font.bungee"
                ,R.drawable.problem, ContextCompat.getColor(getApplicationContext(),R.color.third),ContextCompat.getColor(getApplicationContext(), R.color.black),ContextCompat.getColor(getApplicationContext(), R.color.black)));

        addSlide(AppIntroFragment.newInstance("SIGNUP NOW","R.font.bungee","Much more to explore!\n" +
                        "Click on Done button to get started. ","R.font.bungee"
                ,R.drawable.doctor, ContextCompat.getColor(getApplicationContext(), R.color.third),ContextCompat.getColor(getApplicationContext(), R.color.black),ContextCompat.getColor(getApplicationContext(), R.color.black)));
        setFadeAnimation();

        sharedPreferences=getApplicationContext().getSharedPreferences("Myin", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        if (sharedPreferences!=null){
            boolean checkit=sharedPreferences.getBoolean("check",false);
            if(checkit==true){
                startActivity(new Intent(getApplicationContext(),MainActivity.class ));
            }
        }
    }

    @Override
    public void onSkipPressed(Fragment currentFragment){
        super.onSkipPressed(currentFragment);
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        editor.putBoolean("check",false).commit();
        finish();

    }
    @Override
    public void onDonePressed(Fragment currentFragment){
        super.onDonePressed(currentFragment);

        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        editor.putBoolean("check",true).commit();
        finish();


    }
}