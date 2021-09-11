package com.example.bmax;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class ftab1 extends Fragment {
    TextView cas,act,dea,rec;
    EditText searchbox;
    ImageButton searchit;
    TextView  outlines;
    ListView hist;
    ArrayList<String> items=new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View r= inflater.inflate(R.layout.fragment_ftab1, container, false);






        cas=(TextView) r.findViewById(R.id.cases);
        act=(TextView) r.findViewById(R.id.active);
        dea=(TextView) r.findViewById(R.id.deaths);
        rec=(TextView) r.findViewById(R.id.recovered);
        outlines=(TextView) r.findViewById(R.id.outputlines);
        searchbox=(EditText) r.findViewById(R.id.Search);
        searchit= (ImageButton) r.findViewById(R.id.searchbutton);
        hist=(ListView) r.findViewById(R.id.history);

        searchit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{ dename(searchbox.getText().toString(),items);}
                catch (Exception e){e.printStackTrace();}
            }
        });



        try {new Covid19().execute(null, null, null);}
        catch (Exception or){Toast.makeText(getContext(), "Covid Synce failed", Toast.LENGTH_SHORT).show();}

        return r;



    }


    public void dename(String problem ,ArrayList<String> arr) {
        InputStream inputStream = this.getResources().openRawResource(R.raw.dname);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String strData = "";
        if (inputStream != null) {
            try {
                while ((strData = bufferedReader.readLine()) != null) {
                    if (strData.equals(problem)) {
                        String sol = bufferedReader.readLine();
                        String med = bufferedReader.readLine();
                        if (med.equals("")){med="No medicine for problem "+problem+" found \nTake advise to doctors";}
                        String ans="\n\nResult found for: "+strData+". \n\n\nACCORDING TO BMAX\n\n\n\n"+"                  [Home Remedies]\n\n"+sol+"\n\n\n\n                         [Medicine]\n\n"+med;
                        outlines.setText(ans);
                        arr.add(problem);
                        System.out.println(arr);
                        String strhist="";
                        for(String each:items){
                            strhist=strhist+"\n"+each;
                        }
                        System.out.println(strhist);
                        if (strhist==""){strhist="No search Found";}
                        ArrayAdapter<String> arradp=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,arr);
                        hist.setAdapter(arradp);


                        break;
                    }
                }

            } catch (IOException e) {e.printStackTrace();}
        }
    }













    private class Covid19 extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            String line = "";
            try {
                URL url = new URL("https://corona.lmao.ninja/v3/covid-19/all");
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStreamReader inputstream = new InputStreamReader(conn.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputstream);
                line = bufferedReader.readLine();
            }

            catch (MalformedURLException e) {e.printStackTrace();}
            catch (IOException e) {e.printStackTrace();}
            String[] split1 = line.split(",");

            String[] ansarr = new String[4];     // All Values that needed (only 4)
            String[] divided = new String[2];
            for (String e : split1) {
                divided = e.split(":");
                if (divided[0].equals("\"cases\"")) {ansarr[0] = divided[1];}
                else if (divided[0].equals("\"deaths\"")) {ansarr[1] = divided[1];}
                else if (divided[0].equals("\"recovered\"")) {ansarr[2] = divided[1];}
                else if (divided[0].equals("\"active\"")) {ansarr[3] = divided[1];}

                try{
                    cas.setText("Total Cases:                    "+ansarr[0]);
                    act.setText("Active Cases:                 "+ansarr[3]);
                    dea.setText("Deaths reported:       "+ansarr[1]);
                    rec.setText("Recovered by now:     "+ansarr[2]);}
                catch (Exception es){es.printStackTrace();}

            }
            return null;
        }
    }
}