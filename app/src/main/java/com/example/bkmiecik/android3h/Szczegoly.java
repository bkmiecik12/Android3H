package com.example.bkmiecik.android3h;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Szczegoly extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szczegoly);

        TextView info = (TextView) findViewById(R.id.szczegoly);

        Intent intent = getIntent();
        Bundle dane = intent.getExtras();
        String string = dane.getString("Dane");
        info.setText(string);
        info.setBackgroundColor(R.color.colorAccent);
    }
}
