package com.example.bkmiecik.android3h;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.*;

public class Dodaj extends AppCompatActivity {

    String tytul = "";
    String rezyser = "";
    double ocena = 0;
    String rok = "";
    String kategoria = "";

    FileOutputStream os;

    String[] kat = {"Inny", "Thriller", "Komedia", "Dramat", "Sci-Fi", "Animowany"};

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

                Toast.makeText(Dodaj.this, data, Toast.LENGTH_SHORT).show();
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
}