package com.example.bkmiecik.android3h;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Lista3 extends AppCompatActivity {


    public class myAdapter extends BaseAdapter {
        private LayoutInflater inflater = null;

        public int getCount() {
            return Lista.filmy.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int poss, View cView, ViewGroup parent) {
            View mV;
            inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (cView == null) {
                cView = inflater.inflate(R.layout.list_row, null);
            }
            mV = cView;

            ImageView img = (ImageView) mV.findViewById(R.id.row_img);

            Film f = Lista.filmy.get(poss);
            if(f.kategoria.equals("Polski"))
                img.setImageResource(R.drawable.polska);
            else img.setImageResource(R.drawable.zagranica);

            TextView tv1 = (TextView) mV.findViewById(R.id.row_tyt);
            tv1.setText(f.tytul);
            TextView tv2 = (TextView) mV.findViewById(R.id.row_rez);
            tv2.setText(f.rezyser);
            TextView tv3 = (TextView) mV.findViewById(R.id.row_ocena);
            tv3.setText(String.valueOf(f.ocena));
            TextView tv4 = (TextView) mV.findViewById(R.id.row_rok);
            tv4.setText("("+f.rok+")");
            return mV;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista3);

        myAdapter adapter = new myAdapter();
        ListView lista3 = (ListView) findViewById(R.id.lista3);
        lista3.setAdapter(adapter);
    }
}
