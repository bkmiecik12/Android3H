package com.example.bkmiecik.android3h;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;

public class Ustawienia extends AppCompatActivity {

    SeekBar red;
    SeekBar green;
    SeekBar blue;

    Spinner kom;

    int r = Color.red(R.color.colorPrimaryDark);
    int g = Color.green(R.color.colorPrimaryDark);
    int b = Color.blue(R.color.colorPrimaryDark);
    String komunikat="";

    String[] stringi = {"Dzień dobry!","Co słychać w wielkim świecie?","Łubu-dubu!"};


    Toolbar pasek;

    int k=R.color.colorPrimaryDark;

    SharedPreferences dane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ustawienia);

        pasek = (Toolbar) findViewById(R.id.my_toolbar);
        red = (SeekBar) findViewById(R.id.red);
        green = (SeekBar) findViewById(R.id.green);
        blue = (SeekBar) findViewById(R.id.blue);

        kom = (Spinner) findViewById(R.id.komunikat);

        kom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                komunikat = stringi[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dane = getSharedPreferences("ustawienia",MODE_MULTI_PROCESS);
        r = dane.getInt("r",0);
        g = dane.getInt("g",0);
        b = dane.getInt("b",0);
        komunikat = dane.getString("komunikat",komunikat);

        pasek.setBackgroundColor(Color.rgb(r,g,b));


        red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updatePasek();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updatePasek();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updatePasek();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stringi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kom.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = dane.edit();
        editor.putInt("r",r);
        editor.putInt("g",g);
        editor.putInt("b",b);
        editor.putString("komunikat",komunikat);
        editor.commit();


    }

    public void updatePasek(){
        r = red.getProgress();
        g = green.getProgress();
        b = blue.getProgress();
        red.setProgress(r);
        green.setProgress(g);
        blue.setProgress(b);
        k = Color.rgb(r,g,b);
        pasek.setBackgroundColor(k);
    }

    public void przywroc(){
        k = R.color.colorPrimaryDark;
    }
}
