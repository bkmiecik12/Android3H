package com.example.bkmiecik.android3h;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lista extends AppCompatActivity implements AdapterView.OnItemClickListener{

    String plik = "dane.txt";
    ListView lista1;

    List<String> nazwy = new ArrayList<>();
    public static List<Film> filmy = new ArrayList<>();

    SharedPreferences sett;
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lista1 = (ListView)findViewById(R.id.lista1);

        Button drugaLista = (Button) findViewById(R.id.druga);
        drugaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lista.this,Lista3.class);
                startActivity(intent);
            }
        });

        //System.out.print("TU JESTEM0000");

        try {
            BtO(plik);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        lista1.setOnItemClickListener(this);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nazwy);
        lista1.setAdapter(adapter2);

        sett = getSharedPreferences("ustawienia", MODE_MULTI_PROCESS);

        tb = (Toolbar)findViewById(R.id.tbl);

        int r = sett.getInt("r", Color.red(R.color.colorPrimaryDark));
        int g = sett.getInt("g", Color.green(R.color.colorPrimaryDark));
        int b = sett.getInt("b", Color.blue(R.color.colorPrimaryDark));
        tb.setBackgroundColor(Color.rgb(r,g,b));
        int inv = 0xffffff - Color.rgb(r,g,b);
        tb.setTitleTextColor(inv);
        tb.setTitle("Lista prosta");
        setSupportActionBar(tb);
    }

    public void BtO(String plik) throws IOException, ClassNotFoundException {

        FileInputStream fin=openFileInput(plik);
        //InputStreamReader InputRead= new InputStreamReader(fileIn);
        filmy.clear();
        nazwy.clear();
        int c;
        String temp="";
        while( (c = fin.read()) != -1){
            temp = temp + Character.toString((char)c);
        }
        fin.close();
        Scanner scanner = new Scanner(temp);
        while (scanner.hasNextLine()) {
            String t = scanner.nextLine();
            //if(scanner.hasNextLine()) {
                String r = scanner.nextLine();
                double o = Double.parseDouble(scanner.nextLine());
                String k = scanner.nextLine();
                String rok = scanner.nextLine();
                Film f = new Film(t, r, o, k, rok);
                filmy.add(f);
                nazwy.add(t);
            //}
        }
        scanner.close();
        //Toast.makeText(Lista.this,temp,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String string = "Reżyseria: "+filmy.get(position).rezyser+
                "\nRok produkcji: "+filmy.get(position).rok+
                "\nKategoria: "+filmy.get(position).kategoria+
                "\nOcena: "+filmy.get(position).ocena;
        new AlertDialog.Builder(this)
                .setTitle(nazwy.get(position))
                .setMessage(string)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_menu_more)
                .show();
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
        tb.setTitle("Lista prosta");
        setSupportActionBar(tb);
    }
}
