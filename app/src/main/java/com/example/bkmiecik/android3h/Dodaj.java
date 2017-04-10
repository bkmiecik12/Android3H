package com.example.bkmiecik.android3h;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

import java.io.*;

public class Dodaj extends AppCompatActivity {

    String tytul = "";
    String rezyser = "";
    double ocena = 0;
    String rok = "";
    String kategoria = "";

    Toolbar tb;
    SharedPreferences sett;

    FileOutputStream os;

    String[] kat = {"Polski","Zagraniczny"};

    String plik = "dane.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj);
        Button zapisz = (Button) findViewById(R.id.zapisz);
        final EditText eTytul = (EditText) findViewById(R.id.tytul);
        final EditText eRezyser = (EditText) findViewById(R.id.rezyser);
        final EditText eRok = (EditText) findViewById(R.id.rok);
        final Spinner sKategoria = (Spinner) findViewById(R.id.kategorie);
        final RatingBar rOcena = (RatingBar) findViewById(R.id.ocena);

        tb = (Toolbar) findViewById(R.id.tbd);

        sett = getSharedPreferences("ustawienia",MODE_MULTI_PROCESS);
        int r = sett.getInt("r", Color.red(R.color.colorPrimaryDark));
        int g = sett.getInt("g", Color.green(R.color.colorPrimaryDark));
        int b = sett.getInt("b", Color.blue(R.color.colorPrimaryDark));
        tb.setBackgroundColor(Color.rgb(r,g,b));
        int inv = 0xffffff - Color.rgb(r,g,b);
        tb.setTitleTextColor(inv);
        tb.setTitle("Dodaj nowy film");
        setSupportActionBar(tb);

        zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eTytul.getText().length() > 0)
                    tytul = eTytul.getText().toString();
                if (eRezyser.getText().length() > 0)
                    rezyser = eRezyser.getText().toString();
                if (eRok.getText().length() > 0)
                    rok = eRok.getText().toString();
                ocena = rOcena.getRating();

                Film film = new Film(tytul, rezyser, ocena, kategoria, rok);
                String data = film.toString();


                try {
                    os = openFileOutput(plik, MODE_APPEND);
                    os.write(data.getBytes());
                    os.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(Dodaj.this, "Zapisano", Toast.LENGTH_SHORT).show();
                eTytul.setText("");
                eRezyser.setText("");
                eRok.setText("");
                rOcena.setRating(0);
            }
        });
        sKategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kategoria = kat[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, kat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sKategoria.setAdapter(adapter);
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
        tb.setTitle("Dodaj nowy film");
        setSupportActionBar(tb);
    }
}