package com.example.bmax;

import static kotlin.io.ConsoleKt.readLine;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class mainscreen extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem tabItem1,tabItem2,tabItem3;
    ViewPager viewPager;
    PageAapter pageAapter;



    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(mainscreen.this);
        AlertDialog.Builder builder1 = builder.setMessage("Do you want to log out ?").setPositiveButton("logout", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        })
                .setNegativeButton("No", null);

        AlertDialog alert= builder.create();
        alert.show();
        Button buttonPositive = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.black));
        Button buttonNegative = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonNegative.setTextColor(ContextCompat.getColor(this, R.color.green));
        
        //create a dialog to ask yes no question whether or not the user wants to exit
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);




        tabLayout=(TabLayout) findViewById(R.id.tablayout1);
        tabItem1=(TabItem) findViewById(R.id.tab1);
        tabItem2=(TabItem) findViewById(R.id.tab2);
        tabItem3=(TabItem) findViewById(R.id.tab3);
        viewPager=(ViewPager) findViewById(R.id.vpage);






        pageAapter =new PageAapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0||tab.getPosition()==1||tab.getPosition()==3){
                    pageAapter.notifyDataSetChanged();


                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }


}