package com.example.bkmiecik.android3h;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar tb;
    SharedPreferences sett;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sett = getSharedPreferences("ustawienia",MODE_MULTI_PROCESS);
        String message = sett.getString("komunikat","Dzień dobry!");
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

        Button dodaj = (Button)findViewById(R.id.dodaj);
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Dodaj.class);
                startActivity(intent);
            }
        });

        Button lista = (Button)findViewById(R.id.wyswietl);
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Lista.class);
                startActivity(intent);
            }
        });

        Button ustawienia = (Button) findViewById(R.id.ustawienia);
        ustawienia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Ustawienia.class);
                startActivity(intent);
            }
        });

        tb = (Toolbar)findViewById(R.id.tbm);

        int r = sett.getInt("r", Color.red(R.color.colorPrimaryDark));
        int g = sett.getInt("g", Color.green(R.color.colorPrimaryDark));
        int b = sett.getInt("b", Color.blue(R.color.colorPrimaryDark));
        tb.setBackgroundColor(Color.rgb(r,g,b));
        int inv = 0xffffff - Color.rgb(r,g,b);
        tb.setTitleTextColor(inv);
        tb.setTitle("Menu główne");
        setSupportActionBar(tb);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int r = sett.getInt("r", Color.red(R.color.colorPrimaryDark));
        int g = sett.getInt("g", Color.green(R.color.colorPrimaryDark));
        int b = sett.getInt("b", Color.blue(R.color.colorPrimaryDark));
        tb.setBackgroundColor(Color.rgb(r,g,b));
        int inv = 0xffffff - Color.rgb(r,g,b);
        tb.setTitleTextColor(inv);
        tb.setTitle("Menu główne");
        setSupportActionBar(tb);
    }
}
